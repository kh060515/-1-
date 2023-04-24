<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://unpkg.com/purecss@1.0.0/build/pure-min.css"
	integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w"
	crossorigin="anonymous">
</head>
<body style="margin: 30px;">
	<h2>즐겨찾기 목록</h2>
	<table class="pure-table pure-table-horizontal" style="width: 800px">
		<thead>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>조회수</th>
				<th>작성일</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach var="like" items="${like}">
				<tr>
					<td>${like.num}</td>
					<td><c:forEach begin="1" end="${like.repIndent}">
					▷
				</c:forEach> <a href="read.bo?num=${like.num}&mb_id=${like.mb_id}">${like.title}</a></td>
					<td>${like.mb_id}</td>					
					<td>${like.readcnt}</td>
					<td>${like.writerday}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty like}">
				<tr>
					<td colspan="5" style="text-align:center;">리스트가 없습니다.</td>
				</tr>
			</c:if>
		</tbody>
	</table>
	<div style="margin-top:10px;"><a href="mainpage.jsp" class="pure-button">메인페이지로 돌아가기</a>
	</div>
</body>
</html>