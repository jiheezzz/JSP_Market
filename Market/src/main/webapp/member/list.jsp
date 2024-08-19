<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="member.*" %>
<%
List<MemberVO> li =(List<MemberVO>)request.getAttribute("li");
int tc=(int)request.getAttribute("tc");

String str1=(String)request.getAttribute("str1");
String str2=(String)request.getAttribute("str2");


int xidx=(int)request.getAttribute("xidx");
int pageSize=(int)request.getAttribute("pageSize");


int pagetotalCount =(int)(Math.ceil(tc / (double)pageSize ));
int nowPage =  ( xidx ) / pageSize   + 1  ;
int endIdx = ( pagetotalCount - 1 ) * pageSize;

int pageListSize=(int)request.getAttribute("pageListSize");
int listStartPage=(nowPage-1)/pageListSize*pageListSize+1;
int listEndPage=listStartPage + pageListSize -1;
%>

<%@ include file="/include/top.jsp" %>
<style>
table{
width: 80%;
}
.td1{
text-align: left;
}
</style>
<section>
<br>
  <div align="center">
  <h2> 회원목록조회</h2>
<div align="center">
<form action=MemberController>
<input  type=hidden  name=sw  value="S">
<select name=str1>
<option value=mname> 이름 </option>
<option value=mgrade> 등급 </option>
</select>
<input  type=text  name=str2>
<input  type=submit  value="검색하기"> 
</form>
</div>

<table  border=1 >
<tr> <th>번호</th><th>회원번호</th>  <th>비밀번호</th><th>이름</th><th>전화번호</th> <th>우편번호</th> <th>주소</th> <th>상세주소</th>
     <th>나이</th><th>성별</th><th>등급</th><th>특이사항</th><th>삭제</th></tr>
<%
for(MemberVO m:li){
	int num=xidx+1;
	xidx++;
%>
<tr>
<td><%=num %></td>
<td><a href="${path}/MemberController?sw=E&mid=<%=m.getMid()%>"><%=m.getMid()%></a></td>
<td><%=m.getMpassword2()%> </td>
<td><%=m.getMname()%></td>
<td><%=m.getMphone()%></td>
<td> <%=m.getMaddr1()%>  </td>
<td class=td1> <%=m.getMaddr2()%>  </td> 
<td class=td1><%=m.getMaddr3()%> </td>
<td><%=m.getMage()%> </td>
<td><%=m.getMgender()%> </td>
<td class=td1><%=m.getMgrade()%> </td>
<td class=td1><%=m.getMetc()%> </td>
<td><a href="${path}/MemberController?sw=D&mid=<%=m.getMid()%>">삭제</a></td>
</tr>
<%} %>
</table>
<%
if(str2 != null){ 
str2=java.net.URLEncoder.encode(str2,"UTF-8");
}
%>

<a href="${path}/MemberController?sw=S&xidx=0&str1=<%=str1 %>&str2=<%=str2 %>"> 처음 </a> &emsp;&emsp;
<!-- *** 이전페이지 시작 *** -->
<%                
int beforePage = (listStartPage - pageListSize - 1) * pageSize + 1;
if(listStartPage > pageListSize) {
%>
<a href="${path}/MemberController?sw=S&xidx=<%=beforePage %>&str1=<%=str1 %>&str2=<%=str2 %>"> 이전<%=pageListSize %> </a> 
<%} else { %>
 이전<%=pageListSize %>
<%} %>
<!-- *** 이전페이지 끝 *** -->
&emsp;&emsp;
<!-- for문 시작   -->
<%
for (int  i=listStartPage  ; i <=listEndPage ; i++) { 
	xidx = (i-1) * pageSize;
	if (i <= pagetotalCount) {
%>
 <a href="${path}/MemberController?sw=S&xidx=<%=xidx %>&str1=<%=str1 %>&str2=<%=str2 %>">[<%=i%>]</a> &nbsp;
<%
}} %> &emsp;
<!-- for 끝   -->
<!-- *** 다음페이지 시작 ****  -->
<% 
//  10*10+1 = 101 ,  20*10+1 = 201
int nextPage = listEndPage * pageSize +2;
if ( pagetotalCount != nowPage) {
%>
<a href="${path}/MemberController?sw=S&xidx=<%=nextPage %>&str1=<%=str1 %>&str2=<%=str2 %>"> 다음<%=pageListSize %> </a> &emsp;&emsp;
<% } else { %>
 다음<%=pageListSize %>  &emsp;&emsp;
<% } %>
<!-- *** 다음페이지 끝 ****  -->
<!-- *** 마지막 페이지  *** -->
<a href="${path}/MemberController?sw=S&xidx=<%=endIdx%>&str1=<%=str1 %>&str2=<%=str2 %>"> 마지막 </a> &emsp;&emsp;
  </div> 
<br>  
</section>
<%@ include file="/include/bottom.jsp" %>