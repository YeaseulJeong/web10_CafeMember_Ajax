package encore.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;

import servlet.model.MemberDAOImpl;
import servlet.model.MemberVO;

public class IdCheckController implements Controller {
	
	public ModelAndView execute (HttpServletRequest request, HttpServletResponse response) throws SQLException{
 
		
		
		//예전의 FindServlet
		//1. 폼값받아서
		String id = request.getParameter("id");	
		System.out.println(id);
		String path = "idcheck.jsp";
		try {
			PrintWriter out = response.getWriter();
			boolean flag=MemberDAOImpl.getInstance().idExist(id);			
			request.setAttribute("flag", flag);
//			System.out.println(flag);
//			out.print(flag);
			
		}catch(Exception e) {
			System.out.println(e);
		}			
		return new ModelAndView(path);

}
}
