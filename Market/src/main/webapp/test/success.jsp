<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Test.*" %>

<%@ include file="/include/top.jsp" %>

<section>
<br>
  <div align="center">
  <h2> 결제 완료!! </h2>
	상품번호: ${partner_order_id}
	사용자 ID: ${partner_user_id}
	결제금액: ${total_amount}
  </div> 
<br>  
</section>
<%@ include file="/include/bottom.jsp" %>