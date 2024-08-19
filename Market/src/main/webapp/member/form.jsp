<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import="member.*" %>
<%@ include file="/include/top.jsp" %>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<Script>
var  path='${pageContext.request.contextPath}';
jQuery.ajaxSetup({cache:false});

$(document).ready( function(){
	  $('#idCkeck').click( function(){
		 		 
		  alert("확인:" + $('#mid').val());
		  var  dataStr={
				id  : $('#mid').val()
				
		  };
		  
		  $.ajax({
			  type: "GET",
			  url : path + "/MemberController?sw=ck",
			  data : dataStr,
			  success: function (data){
			    alert("확인:" + data);   
			    if (data == 0){
			    	alert("사용가능한 ID 입니다.");
			    }else{
			    	alert("중복 ID 입니다.");
			    	$('#mid').val("");
			    	$('#mid').focus();			    	
			    } 			  
			  }	  		
		  })   
	  })    
}) 
 
</Script>
<style>
table {
    width: 30%;
    }
</style>
<section>
<br>
   <div align="center"> 
<h2>회원가입</h2>
<form name="f1"  action="${path}/MemberController"  method="post">
<input  type="hidden"  name=sw value="I"  />
<table border="1" >
<tr> <td>아이디</td><td><input type=text name=mid id=mid ><input type="button" id="idCkeck" value="중복확인" ></td></tr>
<tr> <td>비밀번호(평문)</td><td><input type=text name=mpassword1  ></td></tr>
<tr> <td>전화번호</td><td><input type=text name=mphone  ></td></tr>
<tr> <td>우편번호</td><td><input type=text id="sample6_postcode" name=maddr1  >
<input type="button" onclick="sample6_execDaumPostcode()" value="주소찾기"></td></tr>
<tr> <td>주소</td><td><input type=text id="sample6_address" name=maddr2  ></td></tr>
<tr> <td>상세주소</td><td><input type=text id="sample6_detailAddress" name=maddr3  ></td></tr>
<tr> <td>이름</td><td><input type=text name=mname  ></td></tr>
<tr> <td>나이</td><td><input type=text name=mage  ></td></tr>
<tr> <td>성별</td><td><input type=text name=mgender  ></td></tr>
<tr> 
<td colspan=4 align="center" >
	<input  type=submit  value="저장하기" > &emsp;
    <input  type="reset"  value="다시쓰기" > &emsp;
    
</td>
</tr>
</table>
</form>
  </div> 
  <br>  
</section>

<input type="hidden" id="sample6_postcode" placeholder="우편번호">
<input type="hidden" id="sample6_detailAddress" placeholder="상세주소">
<input type="hidden" id="sample6_extraAddress" placeholder="참고항목">

<%@ include file="/js/kakao.jsp"%>
<%@ include file="/include/bottom.jsp" %>