package encore.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servlet.model.MemberDAOImpl;
import servlet.model.MemberVO;

public class RegisterController implements Controller {
	
	public ModelAndView execute (HttpServletRequest request, HttpServletResponse rewponse) throws SQLException{
 
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		
		String path = "index.jsp";
		
		MemberVO pvo  = new MemberVO(id, password, name, address);			
	
		try {
			MemberDAOImpl dao = MemberDAOImpl.getInstance();
			dao.registerMember(pvo);
			path = "allmember.do"; 
		} catch(SQLException e) {
			
		}
		return new ModelAndView(path, true);

}
}
