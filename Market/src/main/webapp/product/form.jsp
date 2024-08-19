<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>
<%@ include file="/css/test_list.jsp" %>

<section>
<br><br>
   <div align="center"> 
<h2> 상품입력  </h2>
<form name="f1"  action="${path}/ProductController"  method="post" enctype="multipart/form-data">
<input  type="hidden"  name=sw value="I"  />
<table border="1" >
<tr><td>상품번호</td><td><input  type=text  name=product_id  /></td></tr>	
<tr><td>상품이름</td><td><input  type=text  name=product_name  /></td></tr>	
<tr> <td>가격</td><td><input type=text name=product_price ></td></tr>
<tr> <td>이미지</td><td><input type=file name=product_img ></td></tr>
<tr> <td>내용</td><td><textarea cols=40 rows=10 name=product_etc ></textarea></td></tr>
<tr> 
<th colspan=4 align="center" >
	<input  type=submit  value="저장하기" > &emsp;
    <input  type="reset"  value="다시쓰기" > &emsp;
    
</th>
</tr>
</table>
</form>
  </div> 
<br>  
</section>
<%@ include file="/include/bottom.jsp" %>