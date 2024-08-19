<%@ page language="java"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="path" scope="session" 
       value="${pageContext.request.contextPath}" />
<%
String nickname = (String)request.getSession().getAttribute("nickname");
String  login =  (String) session.getAttribute("mid"); %> 
=>session: <%=login %> / 관리자id,pwd : dyhj625, 24 / 고객id,pwd : ppk, 12  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<jsp:include page="/css/style.jsp"  />

</head>
<body>
<header>짱구는 못말려 굿즈샵</header>
<nav>
<%if(login!=null&&login.equals("dyhj625")){ %>
&emsp;&emsp;<a href="${path}/ProductController?sw=F">상품등록</a>
&emsp;&emsp;<a href="${path}/ProductController?sw=S">상품목록</a>
&emsp;&emsp;<a href="${path}/MemberController?sw=S">고객목록</a>
&emsp;&emsp;<a href="${path}/CartController?sw=morderL">주문내역</a>
&emsp;&emsp;<a href="${path}/BoardController?sw=MS">후기게시판</a>
&emsp;&emsp;<a href="${path}/MemberController?sw=OUT">로그아웃</a>
&emsp;&emsp;<a href="${path}/index.jsp">홈으로</a>
<%}else if(login!=null&&!login.equals("dyhj625")){%>
&emsp;&emsp;<a href="${path}/ProductController?sw=S">상품목록</a>
&emsp;&emsp;<a href="${path}/CartController?sw=S">장바구니</a>
&emsp;&emsp;<a href="${path}/CartController?sw=orderL">주문내역</a>
&emsp;&emsp;<a href="${path}/BoardController?sw=S">후기게시판</a>
&emsp;&emsp;<a href="${path}/MemberController?sw=OUT">로그아웃</a>
&emsp;&emsp;<a href="${path}/index.jsp">홈으로</a>	
<%}if(login==null&&nickname==null){ %>
&emsp;&emsp;<a href="${path}/MemberController?sw=loginF">로그인</a>
&emsp;&emsp;<a href="${path}/MemberController?sw=F">회원가입</a>
&emsp;&emsp;<a href="${path}/index.jsp">홈으로</a>
<%}%>
</nav>