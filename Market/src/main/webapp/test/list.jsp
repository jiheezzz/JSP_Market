<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="Test.*" %>
<%
List<HashMap<String,String>> li =(List<HashMap<String,String>>)request.getAttribute("li");
%>
<%@ include file="/include/top.jsp" %>

<%@ include file="/css/test_list.jsp" %>
<section>
<br>
  <div align="center">
  <h2> 회원목록조회</h2>


<table  border=1 >
<%
int amount=0;
int sum=0;
for(HashMap<String,String> m:li){
	 amount = Integer.parseInt(m.get("amount"));
	 sum = sum+ amount;
%>
<tr>
<td><input type=text name=name value=<%=m.get("name")%>></td>
<td><input type=text name=amount value=<%=m.get("amount")%>></td>
</tr>
<%} %>
<tr>
<td colspan="2" align="left">총 수량: <%=sum%></td>
</tr>
</table>

  </div> 
<br>  
</section>
<%@ include file="/include/bottom.jsp" %>