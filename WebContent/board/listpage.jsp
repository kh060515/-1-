<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="/board/list.jsp"></jsp:include>
<jsp:include page="/board/page.jsp"></jsp:include>
<p><a href="mainpage.jsp" class="pure-button">메인페이지로 돌아가기</a></p>
</body>
</html>