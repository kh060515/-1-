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
<h2>��۴ޱ�</h2>
	<table border="1" width="800">
		<tr>
			<td>${orgdto.content}</td>
		</tr>
	</table>
<form action="reply.bo" method="post">
	<input type="hidden" name="orgnum" value="${orgdto.num}">
	<input type="hidden" name="orgroot" value="${orgdto.repRoot}">
	<input type="hidden" name="orgstep" value="${orgdto.repStep}">
	<input type="hidden" name="orgindent" value="${orgdto.repIndent}">
	<table border="1" width="800">
		<tr>
			<td>�ۼ���</td>
			<td><input type="text" name="writer" placeholder="�ۼ���" required></td>
		</tr>
		<tr>
			<td>����</td>
			<td><input type="text" name="title" placeholder="����" required></td>
		</tr>
		<tr>
			<td colspan="2">
				<textarea name="content" rows="10" cols="50" maxlength="2000" required></textarea>
			</td>
		</tr>
	</table>
	<input type="submit" value="�� ������ �����">
	<input type="reset" value="�� �̾� �װ� ���;">
</form>

</body>
</html>