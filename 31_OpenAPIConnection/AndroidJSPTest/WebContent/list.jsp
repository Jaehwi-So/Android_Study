<%@page import="action.UserLogin"%>
<%@page import="action.UserJoin"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	request.setCharacterEncoding("UTF-8");

	//안드로이드에서 보내준 파라미터를 받는다
	String type = request.getParameter("type");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");	
	
	if( type == null ){
		return;
	}
	
	if( type.equals("type_regi") ){
		
		UserJoin join = UserJoin.getWriter();
		String res = join.write( id, pwd );
		out.println(res);
		
	}else if( type.equals("type_login") ){
		
		UserLogin login = UserLogin.getWriter();
		String res = login.write( id, pwd );
		out.println(res);		
	}

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	안녕
</body>
</html>