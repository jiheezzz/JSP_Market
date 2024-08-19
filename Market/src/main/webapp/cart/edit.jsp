<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Cart.*" %>
<%@ page import="java.util.*" %>
<%@ include file="/include/top.jsp" %>
<%
List<HashMap<String, Object>> li =(List<HashMap<String, Object>>)request.getAttribute("li");

%>
<script>
<%@include file="/js/edit.js" %>
</script>
<section>
<br>
   <div align="center" > 
<h2> 상세보기 </h2>
<table border="1">

<tr><th>아이디</th><th>이름</th><th>전화번호</th><th>주소</th></tr>
<%
for(HashMap<String, Object> m:li){
	String maddr = (String)m.get("maddr1")+" "+m.get("maddr2")+" "+m.get("maddr3");
%>
<tr>
<td><%=m.get("mid")%></td>
<td><%=m.get("mname")%></td>
<td><%=m.get("mphone")%></td>
<td><%=maddr %></td>
<%} %>
</table>
<br>
<table>
<tr><th>순번</th><th>상품id</th><th>상품이름</th><th>상품가격</th><th>구매수량</th></tr>
<%
int amount=0;
int sum1=0;
int price=0;
int sum2=0;
for(HashMap<String, Object> m:li){
	 amount = Integer.parseInt((String)m.get("camount"));
	 price = Integer.parseInt((String)m.get("product_price"));
	 sum1 = amount + sum1;
	 sum2 = amount * price + sum2 ;
%>
<tr>
<td><%=m.get("orderG")%> </td>
<td><%=m.get("pid")%> </td>
<td><%=m.get("product_name")%> </td>
<td><%=m.get("product_price")%></td>
<td><%=m.get("camount")%> </td>	
</tr>
<%} %>
<tr>
<td colspan=7 align="center"> 총 수량 : <%=sum1 %> 개 / 배송비 : 2500 원 / 상품 금액 : <%=sum2%> 원</td>
</tr>
=> 총 결제 금액 : <%=sum2+2500%> 원

</table>

  </div> 
<br>  
</section>


<%@include file="/js/kakao.jsp" %>

<%@ include file="/include/bottom.jsp" %>