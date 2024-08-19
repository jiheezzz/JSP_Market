<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="product.*" %>
<%
List<ProductVO> li = (List<ProductVO>)request.getAttribute("li");
%>

<%@ include file="/include/top.jsp" %>
<%@ include file="/css/test_list.jsp" %>
<section>
<br>
  <div align="center" >
  <h2> 상품리스트 </h2>


<table  border=1 >
<%
int count=0;
for(ProductVO m:li){
	if(count%4!=0){
		count++;
%>
<td><a href="${path}/ProductController?sw=E&product_id=<%= m.getProduct_id() %>">
<img src="${path}/product/img/<%= m.getProduct_img() %>" alt="Product Image" width=200 height="180"></a><%= m.getProduct_name() %>
</td>
<%}else{
	count++;%>
<tr><td><a href="${path}/ProductController?sw=E&product_id=<%= m.getProduct_id() %>">
<img src="${path}/product/img/<%= m.getProduct_img() %>" alt="Product Image" width=200 height="180"></a><%= m.getProduct_name() %>
</td>
<%}} %>
</table>

  </div> 
<br>  
</section>
<%@ include file="/include/bottom.jsp" %>