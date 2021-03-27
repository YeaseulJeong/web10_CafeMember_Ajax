package encore.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servlet.model.MemberDAOImpl;
import servlet.model.MemberVO;

public class UpdateController implements Controller {
	
	public ModelAndView execute (HttpServletRequest request, HttpServletResponse rewponse) throws SQLException{
 
		//예전의 FindServlet
		//1. 폼값받아서
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		
		String path = "index.jsp";
		MemberVO pvo = new MemberVO(id, password, name, address);
		
		try {
			MemberDAOImpl.getInstance().updateMember(pvo);
			
			HttpSession session  = request.getSession();
			if(session.getAttribute("vo") !=null) {
				session.setAttribute("vo", pvo); //중요
			}
			path = "update_result.jsp";
		}catch(Exception e) {
			
		}	

		return new ModelAndView(path);

}
}
