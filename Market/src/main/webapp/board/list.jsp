<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="Board.*" %>
<%
List<BoardVO> li =(List<BoardVO>)request.getAttribute("li");
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
<%@ include file="/css/test_list.jsp" %>
<style>
table{
width: 40%;
text-align: left;
}
.td1{
text-align: left;
}
</style>
<section>
<br>
  <div align="center">
  <h2> 후기게시판 </h2>

<div align="center">
<form action=BoardController>
<a href="${path}/BoardController?sw=F"><input type="button" value="새글 작성"></a>
&emsp;&emsp;&emsp;&emsp;
<input  type=hidden  name=sw  value="S">
<select name=str1>
<option value=name> 이름 </option>
<option value=title> 제목 </option>
</select>
<input  type=text  name=str2>
<input  type=submit  value="검색하기"> 
</form>
</div>

<table  border=1 id=d>
<tr> <th>번호</th><th width="300">제목</th><th>아이디</th><th>작성일</th><th>조회수</th></tr>
<%
for(BoardVO m:li){
	int num=xidx+1;
	xidx++;
%>
<tr>
<td><%=num%> </td>
<td class=td1><a href="${path}/BoardController?sw=E&idx=<%=m.getIdx()%>"><%=m.getTitle()%></a></td>
<td><%=m.getName()%> </td>
<td><%=m.getDay()%> </td>
<td><%=m.getCnt()%> </td>
</tr>
<%} %>
</table>
<%
if(str2 != null){ 
str2=java.net.URLEncoder.encode(str2,"UTF-8");
}
%>


<a href="${path}/BoardController?sw=S&xidx=0&str1=<%=str1 %>&str2=<%=str2 %>"> 처음 </a> &emsp;&emsp;
<!-- *** 이전페이지 시작 *** -->
<% int beforePage = (listStartPage - pageListSize - 1) * pageSize + 1;
if(listStartPage > pageListSize) {
%> <a href="${path}/BoardController?sw=S&xidx=<%=beforePage %>&str1=<%=str1 %>&str2=<%=str2 %>"> 이전<%=pageListSize %> </a> 
<%} else { %>
 이전<%=pageListSize %>
<%} %>
<!-- *** 이전페이지 끝 *** -->
&emsp;&emsp;
<!-- for문 시작   -->
<% for (int  i=listStartPage  ; i <=listEndPage ; i++) { 
	xidx = (i-1) * pageSize;
	if (i <= pagetotalCount) {%>
 <a href="${path}/BoardController?sw=S&xidx=<%=xidx %>&str1=<%=str1 %>&str2=<%=str2 %>">[<%=i%>]</a> &nbsp;
<%}} %> &emsp;
<!-- for 끝   -->
<!-- *** 다음페이지 시작 ****  -->
<% 
int nextPage = listEndPage * pageSize +2;
if ( pagetotalCount != nowPage) {
%><a href="${path}/BoardController?sw=S&xidx=<%=nextPage %>&str1=<%=str1 %>&str2=<%=str2 %>"> 다음<%=pageListSize %> </a> &emsp;&emsp;
<% } else { %>
 다음<%=pageListSize %>  &emsp;&emsp;
<% } %>
<!-- *** 다음페이지 끝 ****  -->
<!-- *** 마지막 페이지  *** -->
<a href="${path}/BoardController?sw=S&xidx=<%=endIdx%>&str1=<%=str1 %>&str2=<%=str2 %>"> 마지막 </a> &emsp;&emsp;
  </div> 
<br>  
</section>
<%@ include file="/include/bottom.jsp" %>