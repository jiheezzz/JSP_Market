<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/include/top.jsp"/>
<script>
	function dup(){
		location.href="${path}/cartController?sw=Dup&cart_id="+f1.cart_id.value+"&product_id="+f1.product_id.value
		
	}
</script>

<section>
    <div align="center"> 
	<h2> cart 저장 <a href = ${path}/index.jsp >(index)</a> </h2>
	<form name="f1"   action="${path}/cartController">
	<input  type="hidden"  name="sw"  value="I"  />
	<table border="1" >
	<tr><td class="td1"> cart_id </td><td><input type=text  name=cart_id value=${rt} /> </td> <td><input type=button class=button value="중복확인" onClick="dup()" ></td></tr>
	
	<tr><td class="td1"> mid </td><td colspan=2><input type=text  name=mid value="${vo2.getId()}" /> </td></tr>
	<tr><td class="td1"> product_id </td><td colspan=2><input type=text  name=pid value="${product_id}" /> </td></tr>
	<tr><td class="td1"> amount </td><td colspan=2><input type=text  name=amount /> </td></tr>
		<td colspan=4 align="center" >
	    	<input  type=submit class=button value="저장하기" /> &emsp;
	    </td>
	</tr>
</table>
</form>
</div> 
  
<div align="center"><h2> product 리스트 <a href = ${path}/index.jsp >(index)</a></h2></div> 
  
  <div align="center"> 
	
	<c:if test="${Pli == null}">
		<tr><td colspan=3 class=td2> 레코드가 존재 하지 않습니다. </td> </tr>
	</c:if>
		
	<c:if test="${Pli != null}">
		<c:set var="i">${i = 0} </c:set>
		<table  border=1 class=tab>
			<c:forEach var="Pli" items="${Pli}">
					<td>
						<img src='${path}/product/img/${li.get("product_img")}' width = 190 height = 190>
						<p>
						<a href="${path}/ProductController?sw=E&product_id=${li.get("product_id")}">${li.get("product_name")}</a>
					</td>
			
				<c:set var="i">${i = i + 1} </c:set>
					<c:if test="${i % 4 == 0}">
						</tr>				
					</c:if>
			</c:forEach>
			</table>
	</c:if>
  </div>
  <jsp:include page="/include/bottom.jsp"/>