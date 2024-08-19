<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="Cart.*" %>
<%
List<HashMap<String, Object>> li =(List<HashMap<String, Object>>)request.getAttribute("li");
CartVO a = new CartVO();
%>
<%@ include file="/include/top.jsp" %>
<%@ include file="/css/test_list.jsp" %>

<script>
function kakaoK(){
	var pprices = document.getElementsByName('product_price');
	var amounts = document.getElementsByName('amount');
	var shipping_fee = 2500;  
		
	shipping = parseFloat(shipping_fee);
	
	var total_amount = 0;  
	for (var i = 0; i < pprices.length; i++) {
		var pay = amounts[i].value * pprices[i].value;
		total_amount += pay + shipping/pprices.length;
	}
	
	alert("total_amount:" + total_amount);
	location.href="${path}/PaymentServlet?partner_user_id=${mid}&total_amount="+total_amount;
	
}
</script>

<section>
<br>
  <div align="center">
  <h2> 장바구니</h2>

<table  border=1 >
<tr>
<th>상품</th><th>수량</th><th>개당가격</th>
</tr>
<%
int sum1=0;
int sum2=0;
for(HashMap<String, Object> m:li){
	 int amount = Integer.parseInt(String.valueOf(m.get("amount")));
	 int price = Integer.parseInt(String.valueOf(m.get("product_price")));
	 sum1 = amount + sum1;
	 sum2 = amount * price + sum2;
%>
<form name=f1 action="${path}/CartController" >
<input type="hidden" name=sw value="U">
<input type="hidden" name=cart_id value="<%=m.get("cart_id")%>">
<input type="hidden" name=product_price value=<%=m.get("product_price")%>>
<input type="hidden" name=mid value=<%=m.get("mid")%>>
<input type="hidden" name=product_id value=<%=m.get("pid")%>>
<input type="hidden" name=shipping_fee value=<%=a.getShipping_fee() %>>
<tr>
<td><img src="${path}/product/img/<%=m.get("product_img")%>" name=product_img width="150" height="150">
<%=m.get("product_name")%></td>
<td><input type=text name=amount value=<%=m.get("amount")%>></td>
<td><%=m.get("product_price")%></td>
</tr>
<%} %>
<tr><td colspan="4">총 수량 : <%=sum1%> / 상품 가격 : <%=sum2%> / 배송비 : <%=a.getShipping_fee() %></td></tr>
<tr> 
<th colspan=4 align="center" >
	<input  type="submit"  value="수정하기" > &emsp;
    <input  type="button"  value="결제하기" onclick="kakaoK()"> &emsp;   
</th>
</tr>
</form>
</table>
  </div> 
<br>  
</section>
<%@ include file="/include/bottom.jsp" %>