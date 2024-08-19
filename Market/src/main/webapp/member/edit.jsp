<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="member.*" %>
<%@ include file="/include/top.jsp" %>
<%
MemberVO m = (MemberVO)request.getAttribute("m");
%>
<script>
<%@include file="/js/edit.js" %>
</script>
<section>
<br><br>
   <div align="center"> 
<h2> 홈쇼핑 회원 정보 수정 </h2>
<form name="f1"  action="${path}/MemberController"  method="post">
<input  type="hidden"  name=sw value=U  />
<table border="1" >

<tr><th>회원번호</th><th><input  type=text  name=mid value=<%=m.getMid()%> /></th></tr>
<tr> <th>비밀번호(평문)</th><th ><input type=text name=mpassword1  value=<%=m.getMpassword1()%> ></th></tr>
<tr> <th>비밀번호(암호문)</th><th ><input type=text name=mpasswored2  value=<%=m.getMpassword2()%> ></th></tr>
<tr> <th>이름</th><th ><input type=text name=mname  value=<%=m.getMname()%> ></th></tr>
<tr> <th>우편번호</th><th ><input type=text name=maddr1 id="sample6_postcode"  value=<%=m.getMaddr1()%> ><input type="button" onclick="sample6_execDaumPostcode()" value="주소찾기"></th></tr>
<tr> <th>주소</th><th ><input type=text name=maddr2  id="sample6_address" value=<%=m.getMaddr2()%> ></th></tr>
<tr> <th>상세주소</th><th ><input type=text name=maddr3  id="sample6_detailAddress" value=<%=m.getMaddr3()%> ></th></tr>
<tr> <th>전화번호</th><th ><input type=text name=mphone  value=<%=m.getMphone()%> readonly ></th></tr>
<tr> <th>나이</th><th ><input type=text name=mage  value=<%=m.getMage()%> ></th></tr>
<tr> <th>성별</th><th ><input type=text name=mgender  value=<%=m.getMgender()%> ></th></tr>
<tr> <th>고객등급(A:VIP,B:일반,C:직원,M:관리자)</th><th><input type=text name=mgrade  value=<%=m.getMgrade()%> ></th></tr>
<tr> <th>특이사항</th><th ><input type=text name=metc  value=<%=m.getMetc()%> ></th></tr>
<tr> 
<th colspan=4 align="center" >
	<input  type="submit"  value="수정하기" > &emsp;
    <input  type="button"  value="목록보기" onclick="Klist()"> &emsp;   
</th>
</tr>
</table>
</form>
  </div> 
<br>  
</section>


<%@include file="/js/kakao.jsp" %>

<%@ include file="/include/bottom.jsp" %>