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
	<h1>�������� ����</h1>
	<form action="PIM.do">
	<input name="id" value="${dto.id}" type="hidden">
	�̸�: <input name="name" type="text" value="${dto.name}"><br>
	�ֹι�ȣ: <input name="juminNumber" type="number" value="${dto.juminNumber}" maxlength="6" oninput="maxLengthCheck(this)"><br>
	
	����: <input name="trans" type="radio" value="����">����
		<input name="trans" type="radio" value="����">����<br>
		
	mail:<input name="email" type="text" value="${dto.email}" >
	<input type="submit" value="Ȯ��">
	</form>
	
</body>
</html>