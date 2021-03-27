package encore.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import servlet.model.MemberDAOImpl;
import servlet.model.MemberVO;

public class LoginController implements Controller {
	
	public ModelAndView execute (HttpServletRequest request, HttpServletResponse rewponse) throws SQLException{
 
		String id = request.getParameter("id");
		String password = request.getParameter("password");
		
		String path= "index.jsp";
		
		//2. dao 메서드 호출. 이때 인자값으로 폼값 넣고 , 리턴값 받고
		MemberDAOImpl dao = MemberDAOImpl.getInstance();
		try {
			MemberVO vo = dao.login(id, password);
			if(vo!=null) {
				HttpSession session = request.getSession();  
				session.setAttribute("vo", vo);
				
				path ="login_result.jsp";
			}else {
				
		}} catch(SQLException e) {
			
		}
		
	return new ModelAndView(path);
}

}
