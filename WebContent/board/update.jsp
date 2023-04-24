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
<h2>글수정</h2>
<form action="update.bo" method="post">
	<input type="hidden" name="num" value="${dto.num}">
	작성자&nbsp;<input type="text" name="writer" style="background-color:#aeaeae;" value="${dto.mb_id}" readonly>
	<br>
	제&nbsp;&nbsp;&nbsp;목&nbsp;<input type="text" name="title" value="${dto.title}" placeholder="제목" required>
	<br>
	내용
	<br>
	<textarea name="content" rows="10" cols="50" maxlength="2000" required>${dto.content}</textarea>
	<br>
	<c:if test="${dto.login_read != 'Y'}">
		<input type="checkbox" name="login_read"> 로그인한 회원만 조회 가능하게 하려면 체크하세요.
	</c:if>
	<c:if test="${dto.login_read == 'Y'}">
		<input type="checkbox" name="login_read" checked="checked"> 로그인한 회원만 조회 가능하게 하려면 체크하세요.
	</c:if>
	<br><br>
	<input type="submit" value="너의 마음에 저장☆">
	<input type="reset" value="아 미안 그거 취소;">
</form>
<a href="read.bo?num=${dto.num}">게시글로 돌아가기</a><br>
<a href="listpage.bo">목록</a>
</body>
</html>