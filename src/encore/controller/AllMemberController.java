package encore.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servlet.model.MemberDAOImpl;
import servlet.model.MemberVO;

public class AllMemberController implements Controller {
	
	public ModelAndView execute (HttpServletRequest request, HttpServletResponse rewponse) throws SQLException{
 
		String path = "index.jsp";
		try {
			ArrayList<MemberVO> list=MemberDAOImpl.getInstance().showAllMember();
			request.setAttribute("list", list);
			path = "allView.jsp";
		} catch (SQLException e) {
			
		}

		return new ModelAndView(path);

}
}
