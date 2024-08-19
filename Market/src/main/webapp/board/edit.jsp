<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Board.*" %>
<%@ include file="/include/top.jsp" %>
<%
BoardVO m = (BoardVO)request.getAttribute("m");
%>

<!-- include libraries(jQuery, bootstrap) -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css" rel="stylesheet">
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

<!-- include summernote css/js -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote.min.js"></script>

  <script>
    $(document).ready(function() {
        $('#summernote').summernote({
        	placeholder:'내용을 작성해 주세요!!',
            tabsize:2,
        	height:300        	
        });
    });
  </script>
  
<script>
<%@include file="/js/edit.js" %>
</script>
<style>
table{
width: 40%;
}
</style>
<section>
<br><br>
   <div align="center" > 
<h2> 상세보기 </h2>
<form name="f1"  action="${path}/BoardController"  method="post" enctype="multipart/form-data">
<input type="hidden"  name=mid value=<%=login%>>
<input  type="hidden"  name=sw value=U  />
<table border="1">

<tr> <td>아이디</td><td><%=m.getName() %></td>
<%if(!m.getImg().equals("space.PNG")){ %>
<td rowspan="2"><img src="${path}/board/img/<%=m.getImg()%>" width=150 height="130"></td>
<%}else{ }%>
</tr>
<tr> <td>제목</td><td><input type=text name=title  value="<%=m.getTitle()%>" ></td></tr>
<tr> <td>내용</td><td colspan=2><textarea id="summernote" name=etc row=20 col=10><%=m.getEtc()%></textarea></td></tr>
<tr> <td>이미지</td><td colspan=2><input type=file name=img  value=<%=m.getImg() %>></td></tr>
<tr> 
<td colspan=4 align="center" >
	<input  type="button"  value="목록보기" onclick="elist()">&emsp;
	<input  type="submit"  value="수정하기" > &emsp;  
</td>
</tr>
</table>
</form>
  </div> 
<br>  
</section>


<%@include file="/js/kakao.jsp" %>

<%@ include file="/include/bottom.jsp" %>