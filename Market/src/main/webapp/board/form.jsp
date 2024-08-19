<%@page import="java.net.URLEncoder"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="Board.*" %>
<%@ include file="/include/top.jsp" %>
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

<section>
<br><br>
<%@ include file="/css/test_list.jsp" %>
<div align="center">
<h2>후기 게시판 작성</h2>
<form name="f1"  action="${path}/BoardController"  method="post" enctype="multipart/form-data" >
<input  type="hidden"  name=sw value="I"  />
<input type=hidden name=name value=<%=login%>  >
<table border="1" >
<tr> <td>id</td><td><%=login%></td></tr>
<tr> <td>제목</td><td><input type=text name=title  ></td></tr>
<tr> <td>글</td><td><textarea id="summernote" name="etc"></textarea></td></tr>
<tr> <td>이미지</td><td><input type=file name=img  ></td></tr>
<tr> 
<td colspan=4 align="center" >
	<input  type=submit  value="후기쓰기" > &emsp;
    <input  type="reset"  value="다시쓰기" > &emsp;
    
</td>
</tr>
</table>
</form>
</div>
</section>
<%@ include file="/include/bottom.jsp" %>