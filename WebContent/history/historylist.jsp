<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<link rel="stylesheet" href="https://unpkg.com/purecss@1.0.0/build/pure-min.css" integrity="sha384-nn4HPE8lTHyVtfCBi5yW9d20FjT8BJwUXyWZT9InLYax14RDjBj46LmSztkmNP9w" crossorigin="anonymous">
</head>
<body style="margin:30px; width:800px">
	<h2>최신 게시글</h2> 
	<c:if test="${not empty login.id}">
		<div style="margin-bottom:20px;text-align: right;">
			<a href="writeui.bo" class="pure-button">글쓰기</a>
		</div>
	</c:if>
	<table class="pure-table pure-table-horizontal" style=" width:800px">
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
			<c:forEach items="${list}" var="dto">
				<tr>
					<td>${dto.num}</td>
					<td>
						<c:forEach begin="1" end="${dto.repIndent}">
							▷
						</c:forEach>
						<a href="read.bo?num=${dto.num}">${dto.title}</a>
					</td>
					<td>${dto.mb_id}</td>
					<td>${dto.readcnt}</td>
					<td>${dto.writerday}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty list}">
				<tr>
					<td colspan="5" style="text-align:center;">리스트가 없습니다.</td>
				</tr>
			</c:if>
	    </tbody>
	</table>
	<div style="margin-top:10px">
		<a href="mainpage.jsp" class="pure-button">메인페이지로 돌아가기</a>
	</div>
</body>
</html>