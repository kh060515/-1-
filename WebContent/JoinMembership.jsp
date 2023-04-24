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
					alert("사용가능한 아이디 입니다.");
					$("#id").attr("readonly", "readonly");
					$("#idcheckok").val("Y");
				} else {
					alert("이미 사용중인 아이디입니다.");
				}
			},
			error:function(data){
				alert("중복확인 오류");
			}
		});
	}

	function formsubmit(){
		var id = $("#id").val();
		if(!id){
			alert("id를 입력해주세요");
			$("#id").focus();
			return false;
		}
		var idck = $("#idcheckok").val();
		if(idck != 'Y'){
			alert("ID 중복확인이 필요합니다,");
			$("#id").focus();
			return false;
		}
		
		var pw = $("#pw").val();
		if(!pw){
			alert("PW를 입력해주세요");
			$("#pw").focus();
			return false;
		}
		
		var pwok = $("#pwok").val();
		if(!pwok){
			alert("PWOK를 입력해주세요");
			$("#pwok").focus();
			return false;
		}
		
		if(pw != pwok){
			alert("PW와 PW확인가 다릅니다. 다시 입력해주세요.");
			$("#pw").val("");
			$("#pwok").val("");
			$("#pw").focus();
			return false;
		}
		
		var name = $("#name").val();
		if(!name){
			alert("이름을 입력해주세요");
			$("#name").focus();
			return false;
		}
		
		var jumin = $("#juminNumber").val();
		if(!jumin){
			alert("주민번호앞자리를 입력해주세요");
			$("#juminNumber").focus();
			return false;
		}
		
		var email = $("#email").val();
		if(!email){
			alert("이메일을 입력해주세요");
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
			alert("성별을 선택하세요");
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
			<a href="javascript:idcheck();" style="background: rgb(202, 60, 60); color:#fff;" class="pure-button">중복확인</a> 
        </div>

        <div class="pure-control-group">
            <label for="password">Password</label>
            <input required name="pw" id="pw" type="password" placeholder="Password">
        </div>
        
        <div class="pure-control-group">
            <label for="password">Password 확인</label>
            <input required name="pwok" id="pwok" type="password" placeholder="Password 확인">
        </div>
        
        <div class="pure-control-group">
            <label for="name">이름</label>
            <input id="name" required name="name" type="text" placeholder="이름">
            <!-- <span class="pure-form-message-inline">This is a required field.</span> -->
        </div>

        <div class="pure-control-group">
            <label for="email">Email Address</label>
            <input id="email" type="email"  required name="email" placeholder="Email Address">
        </div>

        <div class="pure-control-group">
            <label for="juminNumber">주민등록번호 앞자리</label>
            <input id="juminNumber" type="number" required name="juminNumber" placeholder="ex)19901219" maxlength="8" oninput="maxLengthCheck(this)">
        </div>

        <div class="pure-controls">
	        <label for="trans1" class="pure-radio">
		        <input id="trans1" type="radio" name="trans" value="남성">
		      	 남성
		    </label>
	        <label for="trans2" class="pure-radio">
		        <input id="trans2" type="radio" name="trans" value="여성">
		       	여성
		    </label>

            <input type="submit" class="pure-button pure-button-primary" value="회원가입">
            <a href="mainpage.jsp" class="pure-button">메인페이지로 돌아가기</a>
        </div>
    </fieldset>
   
</form>

	

</body>
</html>