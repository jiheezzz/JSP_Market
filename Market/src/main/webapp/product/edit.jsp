<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="product.*" %>
<%@ include file="/include/top.jsp" %>
<%
ProductVO m = (ProductVO)request.getAttribute("m");
%>
<style>
table {
    width: 20%;
    }
</style>
<script>
<%@include file="/js/edit.js" %>
</script>
<section>
<br>
   <div align="center" > 
<h2> 상세보기 </h2>
<form name="f1"  action="${path}/MemberController"  method="post" >
<input type="hidden"  name=mid value=<%=login%>>
<input  type="hidden"  name=sw value=U  />
<input  type=hidden  name=product_id value=<%=m.getProduct_id()%> >
<input  type=hidden  name=product_name value=<%=m.getProduct_name()%> >
<input type=hidden name=product_price value=<%=m.getProduct_price()%> >
<input type=hidden name=product_etc value=<%=m.getProduct_etc()%> >
<table border="1">

<tr><td>상품번호</td><td><%=m.getProduct_id()%><img src="${path}/product/img/<%= m.getProduct_img() %>"
name=product_img width=150 height="130"></td></tr>	
<tr><td>상품이름</td><td><%=m.getProduct_name()%></td></tr>	
<tr> <td>가격</td><td><%=m.getProduct_price()%></td></tr>
<tr> <td>내용</td><td><%=m.getProduct_etc() %></td></tr>
<tr> <td>수량</td><td><input type=text name=amount>
<tr> 
<th colspan=3 align="center" >
	<input  type="button"  value="목록보기" onclick="KKlist()">&emsp;
    <input  type="button"  value="장바구니" onclick="Kcart()"> &emsp;   
</th>
</tr>
</table>
</form>
  </div> 
<br>  
</section>


<%@include file="/js/kakao.jsp" %>

<%@ include file="/include/bottom.jsp" %>