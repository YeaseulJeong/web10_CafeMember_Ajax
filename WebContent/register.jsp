<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
<script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
	$(function() {
		$('#id').keyup(function(){
			var id = $(this).val();    // this 현재 이벤트가 적용된 객체
			 //alert(id);  // 확인차  
				if(id.length<=2 || id.length>8){
					$('#idCheckView').html("아이디는 3자 이상, 8 미만으로 입력하세요!!").css('color','red');
					return;
				}else{
				$.ajax({
				type:'post',
				url:'idcheck.do',
				data: "id="+id+"&command=idcheck",   // form값이 id밖에 받는게 없느니까 form값 전체를 받아와도 된다  이렇게 serialize() 로 받으면 form값의 이름으로 보낸다
				success: function(data){
					//$('#idCheckView').html(data);
					
					// 여기에 data 로 idcheck.jsp 의 flag 값이 전해진다 
					 if($.trim(data)=='true') $('#idCheckView').html(id+":: 사용불가").css('color', 'red');
					// 이때 true 를 'true'로 해서 받아줘야 함 true 를 넘길 때 String 이 되어버린다 
					else  $('#idCheckView').html(id+":: 사용가능").css('color', 'green');
					} 
				}); // ajax
			} // else
		}); // keyup	
	});
</script>
</head>
<body>
<h3>회원가입</h3>
<!-- register_action에서 하고있는 기능이  RegisterServletd으로 이동
	 register_result.jsp로 결과를 보낸다.
-->
<form action="register.do" name="registerForm" >
<!-- <input type="hidden" name="command" value="register"/> -->
ID : <input type="text" name="id" id="id" required="required">
<span id="idCheckView"></span><br><br>
PASSWORD : <input type="password" name="password" required="required"><br><br>
NAME : <input type="text" name="name" required="required"><br><br>
ADDRESS : <input type="text" name="address" required="required"><br><br>

<input type="submit" value="member register">
</form>
</body>
</html>
