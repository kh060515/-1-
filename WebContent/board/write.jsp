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
<body style="margin:30px;">
<h2>글 작성</h2>
<form action="write.bo" method="post" enctype="multipart/form-data" class="pure-form pure-form-aligned">
    <fieldset>
        <div class="pure-control-group">
            <label for="name">제목</label>
            <input id="title" name="title" type="text" placeholder="제목" required>
            <!-- <span class="pure-form-message-inline">This is a required field.</span> -->
        </div>

        <div class="pure-control-group">
            <label for="content">내용</label>
			<textarea name="content" id="content" rows="10" cols="50" maxlength="2000" required></textarea>
        </div>
        
        <div class="pure-control-group">
            <label for="file">첨부파일</label>
			<input name="file" id="file" type="file">
        </div>

        <div class="pure-controls">
            <label for="login_read" class="pure-checkbox">
                <input id="login_read" type="checkbox" name="login_read"> 로그인한 회원만 조회 가능하게 하려면 체크하세요.
            </label>

			<input type="submit" class="pure-button pure-button-primary" value="너의 마음에 저장☆">
			<input type="reset" class="pure-button pure-button-primary" value="아 미안 그거 취소;">
        </div>
    </fieldset>
</form>
<a href="listpage.bo" class="pure-button">목록</a>
</body>
</html>