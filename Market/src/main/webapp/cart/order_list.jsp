<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="Cart.*" %>
<%
List<HashMap<String, Object>> li =(List<HashMap<String, Object>>)request.getAttribute("li");

%>
<%@ include file="/include/top.jsp" %>
<%@ include file="/css/test_list.jsp" %>

<style>
a{
text-decoration: none;
}
</style>
<section>
<br>
  <div align="center">
  <h2> 주문리스트</h2>

<table  border=1 >
<tr>
<th>주문번호</th><th>총수량</th><th>주문날짜</th>
</tr>
<%

for(HashMap<String, Object> m:li){
%>
<input type="hidden" name=orderG value=<%=m.get("orderG")%>>
<input type="hidden" name=mid value=<%=login%>>
<tr>
<td><a href="${path}/CartController?sw=E&orderG=<%=m.get("orderG")%>"><%=m.get("orderG")%></a></td>
<td><%=m.get("tc")%></td>
<td><%=m.get("today")%></td>
</tr>
<%} %>
</table>
  </div> 
<br>  
</section>
<%@ include file="/include/bottom.jsp" %>