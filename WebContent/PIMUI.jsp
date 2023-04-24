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
<body style="margin:30px;">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script type="text/javascript">
	function maxLengthCheck(object) {
		if (object.value.length>object.maxLength) {
			object.value=object.value.slice(0,object.maxLength);
		}
	}
</script>
<h2>개인정보 수정 페이지</h2>

<form action="PIMUI.do" class="pure-form pure-form-stacked">
    <fieldset>
        <legend>개인정보 수정</legend>

        <label for="name">이름</label>
        <input required name="name" type="text" id="name">

        <label for="juminNumber">생년월일</label>
        <input required type="number" id="juminNumber" name="juminNumber" maxlength="8" oninput="maxLengthCheck(this)">
        
		<input value="확인" type="submit" class="pure-button pure-button-primary">
    </fieldset>
</form>
<a href="mainpage.jsp" class="pure-button">메인페이지로 돌아가기</a>
</body>
</html>