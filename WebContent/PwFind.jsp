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
</head>
<body style="margin:30px">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script type="text/javascript">
function maxLengthCheck(object) {
	if (object.value.length>object.maxLength) {
		object.value=object.value.slice(0,object.maxLength);
	}
}
</script>

<form action="PwFind.do" method="post" class="pure-form pure-form-stacked">
    <fieldset>
        <legend>비밀번호 찾기</legend>
			
        <label for="id">ID</label>
        <input id="id" type="text" placeholder="ID" name="id" required>
        <span class="pure-form-message">This is a required field.</span>

        <label for="name">이름</label>
        <input id="name" type="text" placeholder="이름">
        
        <label for="juminNumber">주민번호 앞자리</label>
        <input id="juminNumber" name="juminNumber" type="number" maxlength="6" oninput="maxLengthCheck(this)" placeholder="주민번호 앞자리">
        
        <label for="email">E-mail</label>
        <input id="email" type="email" name="email" placeholder="Email">

        <input type="submit" class="pure-button pure-button-primary" style="margin-top:10px;" value="찾기">
    </fieldset>
</form>
<a href="mainpage.jsp" class="pure-button">메인페이지로 돌아가기</a>
</body>
</html>