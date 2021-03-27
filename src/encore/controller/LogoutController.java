package encore.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servlet.model.MemberDAOImpl;
import servlet.model.MemberVO;

public class LogoutController implements Controller {
	
	public ModelAndView execute (HttpServletRequest request, HttpServletResponse rewponse) throws SQLException{
 

		HttpSession session = request.getSession();
		String path = "index.jsp";
		if(session.getAttribute("vo") !=null) {
			session.invalidate();
			path = "logout.jsp";
		}

		return new ModelAndView(path);

}
}
