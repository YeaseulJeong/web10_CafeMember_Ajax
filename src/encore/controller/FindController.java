package encore.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servlet.model.MemberDAOImpl;
import servlet.model.MemberVO;

public class FindController implements Controller{

	@Override
	public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		//예전의 FindServlet
		//1. 폼값받아서
		String id = request.getParameter("id");
		
		String path ="find_fail.jsp";
		//2. dao의 메소드 호출, 이때 인자값으로 폼값 넣음 ... 리턴값 받기
		MemberDAOImpl dao = MemberDAOImpl.getInstance();
		try {
			MemberVO vo =dao.findByIdMember(id);
			if(vo!=null) {
				request.setAttribute("vo",vo);
				path = "find_ok.jsp";
			}
		} catch (SQLException e) {
			
		}
		//3. 바인딩
			
		//4. 네비게이션
		return new ModelAndView(path);   // 그냥 path 만 해도 되지만 여기에 DispatcherServlet 에서 forward, redirect도 같이 전달해주고 싶어서 ModelAndView 객체를 만들어서 넣는 것 
	}
}

