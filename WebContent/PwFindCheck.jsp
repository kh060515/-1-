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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
function formsubmit(){
	var checknumber = $("#checknumber").val();
	if(!checknumber){
		alert("인증번호를 입력하세요");
		$("#checknumber").focus();
		return false;
	}
	
	$("#joinForm").submit();
}
</script>
<form onsubmit="return formsubmit();" action="PwFindCheck.do">
	인증번호 <input name="checknumber" id="checknumber">
	<input type="submit" value="확인">
</form>
</body>
</html>