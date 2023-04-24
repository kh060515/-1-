<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
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
<body style="margin: 30px; width: 800px">
	<h2>�α� �Խñ�</h2>
	<!-- <form class="pure-form pure-form-stacked">
	    <fieldset>
			<div class="pure-u-1 pure-u-md-1-3">
	                <select id="state" class="pure-input-1-2">
	                    <option></option>	                   
	                </select>
	            </div>
	        <button type="submit" class="pure-button pure-button-primary">Submit</button>
	    </fieldset>
	</form> -->
	<table class="pure-table pure-table-horizontal" style="width: 800px">
		<thead>
			<tr>
				<th>��ȣ</th>
				<th>����</th>
				<th>�ۼ���</th>
				<th>��ȸ��</th>
				<th>�ۼ���</th>
			</tr>
		</thead>

		<tbody>
			<c:forEach items="${pop}" var="pop">
				<tr>
					<td>${pop.num}</td>
					<td><c:forEach begin="1" end="${pop.repIndent}">
                     ��
                  </c:forEach> <a href="read.bo?num=${pop.num}">${pop.title}</a></td>
					<td>${pop.mb_id}</td>
					<td>${pop.readcnt}</td>
					<td>${pop.writerday}</td>
				</tr>
			</c:forEach>
			<c:if test="${empty pop}">
				<tr>
					<td colspan="5" style="text-align: center;">����Ʈ�� �����ϴ�.</td>
				</tr>
			</c:if>
		</tbody>
	</table>
	<div style="margin-top: 10px">
		<a href="mainpage.jsp" class="pure-button">������������ ���ư���</a>
	</div>
</body>
</html>