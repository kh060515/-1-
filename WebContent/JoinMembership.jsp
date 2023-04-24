<%@page import="java.util.function.Function"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	function idcheck(){
		var id = $("#id").val();

		$.ajax({
			url:"idcheck.ck",
			type:"post",
			data : {
				"id" : id					
			},
			datatype:"json",
	        contentType: "application/x-www-form-urlencoded; charset=utf-8",			
			success:function(data){
				if(data.cnt == 0){
					alert("��밡���� ���̵� �Դϴ�.");
					$("#id").attr("readonly", "readonly");
					$("#idcheckok").val("Y");
				} else {
					alert("�̹� ������� ���̵��Դϴ�.");
				}
			},
			error:function(data){
				alert("�ߺ�Ȯ�� ����");
			}
		});
	}

	function formsubmit(){
		var id = $("#id").val();
		if(!id){
			alert("id�� �Է����ּ���");
			$("#id").focus();
			return false;
		}
		var idck = $("#idcheckok").val();
		if(idck != 'Y'){
			alert("ID �ߺ�Ȯ���� �ʿ��մϴ�,");
			$("#id").focus();
			return false;
		}
		
		var pw = $("#pw").val();
		if(!pw){
			alert("PW�� �Է����ּ���");
			$("#pw").focus();
			return false;
		}
		
		var pwok = $("#pwok").val();
		if(!pwok){
			alert("PWOK�� �Է����ּ���");
			$("#pwok").focus();
			return false;
		}
		
		if(pw != pwok){
			alert("PW�� PWȮ�ΰ� �ٸ��ϴ�. �ٽ� �Է����ּ���.");
			$("#pw").val("");
			$("#pwok").val("");
			$("#pw").focus();
			return false;
		}
		
		var name = $("#name").val();
		if(!name){
			alert("�̸��� �Է����ּ���");
			$("#name").focus();
			return false;
		}
		
		var jumin = $("#juminNumber").val();
		if(!jumin){
			alert("�ֹι�ȣ���ڸ��� �Է����ּ���");
			$("#juminNumber").focus();
			return false;
		}
		
		var email = $("#email").val();
		if(!email){
			alert("�̸����� �Է����ּ���");
			$("#email").focus();
			return false;
		}

		var chk_trans = document.getElementsByName("trans");
		var chk_cnt = 0;
		for(var i=0;i<chk_trans.length;i++){
			if(chk_trans[i].checked == true){ 
				sel_type = chk_trans[i].value;
				chk_cnt = 1;
			}
		}
		
		if(chk_cnt == 0){
			alert("������ �����ϼ���");
			return false;
		}
		
		$("#joinForm").submit();
	}
	</script>
	<form  onsubmit="return formsubmit();" action="JoinMembership.do" method="post" id="joinForm" class="pure-form pure-form-aligned">
	<input type="hidden" id="idcheckok" value="N">
	
    <fieldset>
        <div class="pure-control-group">
            <label for="foo">ID</label>
			<input required type="text" name="id" id="id" placeholder="id">
			<a href="javascript:idcheck();" style="background: rgb(202, 60, 60); color:#fff;" class="pure-button">�ߺ�Ȯ��</a> 
        </div>

        <div class="pure-control-group">
            <label for="password">Password</label>
            <input required name="pw" id="pw" type="password" placeholder="Password">
        </div>
        
        <div class="pure-control-group">
            <label for="password">Password Ȯ��</label>
            <input required name="pwok" id="pwok" type="password" placeholder="Password Ȯ��">
        </div>
        
        <div class="pure-control-group">
            <label for="name">�̸�</label>
            <input id="name" required name="name" type="text" placeholder="�̸�">
            <!-- <span class="pure-form-message-inline">This is a required field.</span> -->
        </div>

        <div class="pure-control-group">
            <label for="email">Email Address</label>
            <input id="email" type="email"  required name="email" placeholder="Email Address">
        </div>

        <div class="pure-control-group">
            <label for="juminNumber">�ֹε�Ϲ�ȣ ���ڸ�</label>
            <input id="juminNumber" type="number" required name="juminNumber" placeholder="ex)19901219" maxlength="8" oninput="maxLengthCheck(this)">
        </div>

        <div class="pure-controls">
	        <label for="trans1" class="pure-radio">
		        <input id="trans1" type="radio" name="trans" value="����">
		      	 ����
		    </label>
	        <label for="trans2" class="pure-radio">
		        <input id="trans2" type="radio" name="trans" value="����">
		       	����
		    </label>

            <input type="submit" class="pure-button pure-button-primary" value="ȸ������">
            <a href="mainpage.jsp" class="pure-button">������������ ���ư���</a>
        </div>
    </fieldset>
   
</form>

	

</body>
</html>