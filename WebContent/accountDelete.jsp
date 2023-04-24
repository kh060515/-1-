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
<h2>���� Ż�� ������</h2>
<form action="accountDelete.do" class="pure-form pure-form-stacked">
    <fieldset>
        <legend>Ż�� ����</legend>

        <label for="name">�̸�</label>
        <input id="name" name="name" type="text" placeholder="�̸�">

        <label for="id">���̵�</label>
        <input id="id" name="id" type="text" placeholder="ID">
        
        <label for="pw">��й�ȣ</label>
        <input id="pw" name="pw" type="password" placeholder="Password">

        <label for="juminNumber">�������</label>
        <input required type="number" id="juminNumber" name="juminNumber" placeholder="ex)20100506" maxlength="8" oninput="maxLengthCheck(this)">
        
		<input type="submit" value="����Ż��" class="pure-button pure-button-primary">
    </fieldset>
</form>
<a href="mainpage.jsp" class="pure-button">������������ ���ư���</a>

</body>
</html>