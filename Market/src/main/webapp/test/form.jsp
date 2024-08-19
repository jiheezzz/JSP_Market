<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>
<%@ include file="/css/test_list.jsp" %>

<section>
<br><br>
<div align="center">
<h2>배열TEST</h2>
<form action="${path}/TestController">
<input type="hidden" name=sw value="I">
<table>
<tr>
<td><input type=text name=name value="사과"></td>
<td><input type=text name=amount value="3"></td>
</tr>
<tr>
<td><input type=text name=name value="수박"></td>
<td><input type=text name=amount value="2"></td>
</tr>
<tr>
<td><input type=text name=name value="딸기"></td>
<td><input type=text name=amount value="5"></td>
</tr>
<tr>
<td colspan="2" align="center"><input type="submit" value="저장하기"></td>
</tr>
</table>
</form>
</div>
<br>
</section>
<%@ include file="/include/bottom.jsp" %>