package servlet.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.xdevapi.Session;

import encore.controller.Controller;
import encore.controller.HandlerMapping;
import encore.controller.ModelAndView;
import servlet.model.MemberDAOImpl;
import servlet.model.MemberVO;

/**
 *  <servlet>
    <servlet-name>RegisterServlet</servlet-name>
    <servlet-class>servlet.controller.DispatcherServlet (FQCN)  </servlet-class>
    <load-on-startup>1</load-on-startup>
  </servlet>
  <servlet-mapping>
    <servlet-name>DispatcherServlet</servlet-name>
    <url-pattern>/front.do</url-pattern>
  </servlet-mapping>
  
  
 */
@WebServlet("*.do")  // 확장자가 .do 인거는 다 받을 수 있도록 find.do, login.do, logout.do 등등등

// 비즈니스 로직 하나 처리하자고 용량이 큰 서블릿 하나를 각각 계속 만드는 건 비효율적이다 
//그래서 그전의 각각의 서블릿들에 해당하는 메서드들을 하나의 서블릿안에 구현하면 됨
// DispatcherServlet 은 command 를 먼저 확인해서 어떤 요청을 요구하는건지 if else 로 판단을 제일먼저 해줘야


public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public DispatcherServlet() {
        super();
        // Servlet은 서버가 만든다  그래서 생성자가 있다 
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로직은 여기서 작성..
		
		//1. 아래와 같이 hidden 값으로 들어온 요청을 command로 받지 않고 들어온 요청의 주소를 직접 인식시킨다
		// 매번 모든 화면에서 요청마다 hidden 으로 태그 써주기 힘들다 너무 많아지면 프로그래밍이 아니다 
		// String command = request.getParameter("command");
		
		// 이렇게 들어온 요청의 주소를 직접 인식시킨다
		String requestURI = request.getRequestURI();  // 이 function 이 여기에 web10_CafeMember_Ajax/find.do 까지 받아오는 것 _ index.jsp 에서 선택하는거에 따라 달라짐
		System.out.println("requestURI: "+requestURI);  // 여기에 web10_CafeMember_Ajax/find.do 까지 받아오는 것 
		
		String contextPath = request.getContextPath();
		System.out.println("contextPath: "+contextPath);   // 여기에 web10_CafeMember_Ajax 까지 받아오는 것 
		
		String command = requestURI.substring(contextPath.length()+1);    // find.do값만 추출하자 
		System.out.println("command: "+command);
		
		String path = "error.jsp";
		
		ModelAndView mv = null; 
		
		//2. Factory 의 메서드 호출해서 Controller 리턴 받는다
		Controller controller = HandlerMapping.getInstance().createController(command);
		
		//3. Controller 의 메서드 호출해서 패스를 리턴 받는다 
		try {
			mv = controller.execute(request, response);    // 원래는 mv 대신 controller 타입의 component 들로부터 리턴 된 path 를 받았었는데 
			path = mv.getPath();    
			// path 를 까서 보는 것    이때 mv를 또 만든 이유는 path 말고 forward, redirect 내용도 넣고 싶다 그래서 mv로 크게 받아서 .getPath(), .isRedirect()로 나눠서 받는것
		}catch(SQLException e) {
			
		}
		
		//4. 네비게이션 (결과 jsp 들로_ path 에 담음)
		//각각의 Controller 에서 setAttribute 를 한 것에 대한 것 
		// idCheckController 에서는 flag 값을 idcheck.jsp 로 forwarding 함
		
		if(mv!=null) {
			if(mv.isRedirect()) response.sendRedirect(path);     // 방법(redirect || forward)인지를 까서 보는 것 
			else request.getRequestDispatcher(path).forward(request, response);
		}
		
		
		
	}
	
}
