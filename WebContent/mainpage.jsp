<%@page import="com.domain.JoinMembershipDTO"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
<!-- <style>
ul {list-style-type: upper-roman;}
ul > li {
	padding:5px;
	font-size:20px;
}
a:link { color: red; text-decoration: none;}
a:visited { color: black; text-decoration: none;}
a:hover { color: blue; text-decoration: underline;}
</style> -->
</head>
<body style="margin:30px;">
<%
	JoinMembershipDTO login = (JoinMembershipDTO)request.getSession().getAttribute("login");
%>

<div class="pure-menu custom-restricted-width" align="center">
    <ul class="pure-menu-list">
        <li class="pure-menu-item pure-menu-selected"><a href="listpage.bo" class="pure-menu-link">��ü�Խ���</a></li>
        <li class="pure-menu-item"><a href="Pop.po" class="pure-menu-link">�α�Խ���</a></li>
        <c:if test="${empty login.id}">
			<li class="pure-menu-item"><a href="JoinMembership.jsp" class="pure-menu-link">ȸ������</a></li>
			<li class="pure-menu-item"><a href="loginform.jsp" class="pure-menu-link">�α���</a></li>
			<li class="pure-menu-item"><a href="PwFind.jsp" class="pure-menu-link">��й�ȣ ã��</a></li>
		</c:if>
	        
		<c:if test="${not empty login.id}">
			<li class="pure-menu-item"><a href="historylist.hi" class="pure-menu-link">�ֱٰԽñ�</a></li>
			<li class="pure-menu-item"><a href="like.li" class="pure-menu-link">���ã��Խñ�</a></li>
			<li class="pure-menu-item"><a href="PIMUI.jsp" class="pure-menu-link">������������</a></li>
			<li class="pure-menu-item"><a href="logout.do" class="pure-menu-link">�α׾ƿ�</a></li>
			<li class="pure-menu-item"><a href="accountDelete.jsp" class="pure-menu-link">���� Ż��</a></li>
		</c:if>
        
    </ul>
</div>
</body>
</html>