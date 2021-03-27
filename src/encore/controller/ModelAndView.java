package encore.controller;
/*
 * 1. 결과 페이지 이름 (전에 path의 부분)
 * 2. 네비게이션 하는 방법   (redirect 와 forward)
 * 
 * 이렇게 위의 2가지 정보를 담는 클래스에서 객체가 필요한 것 이다 
 */

public class ModelAndView {
	private String path;
	private boolean isRedirect; // default 로 false 로 잡힌다 . 90% 가 forwarding 하기 때문에 애초에 isRedirect 로 boolean 변수를 만들면 redirect 가 false 즉 forwarding 의미
	
	
	public ModelAndView(String path, boolean isRedirect) {
		this.path = path;
		this.isRedirect = isRedirect;
	}
	
	public ModelAndView(String path) {
		this.path = path;
	}
	
	public ModelAndView() {
		
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public boolean isRedirect() {
		return isRedirect;
	}

	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	
	
	
	
	
	
	

}
