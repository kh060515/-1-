<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript">
	function maxLengthCheck(object) {
		if (object.value.length>object.maxLength) {
			object.value=object.value.slice(0,object.maxLength);
		}
	}
</script>
	<h1>개인정보 수정</h1>
	<form action="PIM.do">
	<input name="id" value="${dto.id}" type="hidden">
	이름: <input name="name" type="text" value="${dto.name}"><br>
	주민번호: <input name="juminNumber" type="number" value="${dto.juminNumber}" maxlength="6" oninput="maxLengthCheck(this)"><br>
	
	성별: <input name="trans" type="radio" value="남성">남성
		<input name="trans" type="radio" value="여성">여성<br>
		
	mail:<input name="email" type="text" value="${dto.email}" >
	<input type="submit" value="확인">
	</form>
	
</body>
</html>