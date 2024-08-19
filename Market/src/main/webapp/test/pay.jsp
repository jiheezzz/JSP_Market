<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ include file="/include/top.jsp" %>
<script type="text/javascript">
function kakao(){
	quantity=f1.quantity.value
	amount=f1.amount.value
	f1.total_amount.value=quantity*amount
	//alert("total_amount:"+quantity*amount);
	//return false
}
</script>
 <%@ include file="/css/test_list.jsp" %>   
<section>
<div align="center" >
<br>
<h2>계산</h2>
    <form name=f1 action="${path}/PaymentServlet" method="post" onsubmit="return kakao()">
    <table>
     <tr><td>주문번호</td><td><input type=text name=partner_order_id ></td></tr>
     <tr><td>user_id</td><td><input type=text name=partner_user_id></td></tr>
     <tr><td>품명</td><td><input type=text name=item_name></td></tr>
     <tr><td>수량</td><td><input type=text name=quantity></td></tr>
     <tr><td>가격</td><td><input type=text name=amount></td></tr>
     <tr><td>총가격</td><td><input type="text" name=total_amount></td></tr>
        <tr><td colspan="2" align="center"><input type="submit" value="카카오페이로 결제하기"></td></tr>
    </table>
    </form>
    </div>
</section>
<%@ include file="/include/bottom.jsp" %>