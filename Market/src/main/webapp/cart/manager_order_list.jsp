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
<section>
<br>
  <div align="center">
  <h2> 주문리스트</h2>

<table  border=1 >
<tr>
<th>주문번호</th><th>주문건수</th><th>주문일</th><th>출고확인</th>
</tr>
<%

for(HashMap<String, Object> m:li){
%>
<input type="hidden" name=orderG value=<%=m.get("orderG")%>>
<input type="hidden" name=mid value=<%=login%>>
<input type="hidden" name=idxOrder value=<%=m.get("idxOrder")%>>
<tr>
<td><a href="${path}/CartController?sw=E&orderG=<%=m.get("orderG")%>"><%=m.get("orderG")%></a></td>
<td><%=m.get("tc")%></td>
<td><%=m.get("today")%></td>
<td><input  type="button"  value="출고완료" onclick="OK2(<%=m.get("orderG")%>)"></td>
</tr>
<%} %>
</table>
  </div> 
<br>  
</section>
<%@ include file="/include/bottom.jsp" %>