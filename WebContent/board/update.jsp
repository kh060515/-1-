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
<h2>�ۼ���</h2>
<form action="update.bo" method="post">
	<input type="hidden" name="num" value="${dto.num}">
	�ۼ���&nbsp;<input type="text" name="writer" style="background-color:#aeaeae;" value="${dto.mb_id}" readonly>
	<br>
	��&nbsp;&nbsp;&nbsp;��&nbsp;<input type="text" name="title" value="${dto.title}" placeholder="����" required>
	<br>
	����
	<br>
	<textarea name="content" rows="10" cols="50" maxlength="2000" required>${dto.content}</textarea>
	<br>
	<c:if test="${dto.login_read != 'Y'}">
		<input type="checkbox" name="login_read"> �α����� ȸ���� ��ȸ �����ϰ� �Ϸ��� üũ�ϼ���.
	</c:if>
	<c:if test="${dto.login_read == 'Y'}">
		<input type="checkbox" name="login_read" checked="checked"> �α����� ȸ���� ��ȸ �����ϰ� �Ϸ��� üũ�ϼ���.
	</c:if>
	<br><br>
	<input type="submit" value="���� ������ �����">
	<input type="reset" value="�� �̾� �װ� ���;">
</form>
<a href="read.bo?num=${dto.num}">�Խñ۷� ���ư���</a><br>
<a href="listpage.bo">���</a>
</body>
</html>