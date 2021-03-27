package encore.controller;

public class HandlerMapping {
	
	private static HandlerMapping Factory = new HandlerMapping();
	private HandlerMapping () {}
	public static HandlerMapping getInstance() {
		return Factory;
	}
	
	public Controller createController(String command) {
		
		Controller controller= null;
	
		//String path = "index.jsp";
		
		if(command.equals("find.do")) {
			//command 값을 주고 controller type 으로 받는다  (재사용성!!! 위해서_controller를 구현한 실제적인 component가 자동. component.execute()로 일일이X)
			controller = new FindController();  // 받는 command 값에 따라서 만드는 component 를 만든다   그 후 만든 component 자체가 아니라 controller 로 타입으로 던짐(인터페이스타입)
		}else if(command.equals("login.do")){  // DispatcherServlet 에서 넘어온 path 를 받아서 적절한 component 를 생성하도록 하고 controller 의 타입으로 받는다
			controller = new LoginController();
		}else if(command.equals("allmember.do")) {  //왜냐면 allmember.do 와 같이 이런거 전에 allmember 는 hidden 으로 넘긴거 에서 받아온 command 의 value 였기 때문에  
			controller = new AllMemberController();
		}else if(command.equals("logout.do")) {
			controller = new LogoutController();
		}else if(command.equals("update.do")) {
			controller = new UpdateController();
		}else if(command.equals("register.do")) {
			controller = new RegisterController();
		}else if(command.equals("idcheck.do")) {
			controller = new IdCheckController();
		}
		
		return controller;
	}	

}
