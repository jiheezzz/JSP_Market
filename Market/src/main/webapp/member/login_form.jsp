<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/include/top.jsp" %>
<style>
table {
    width: 30%;
    }
</style>
<section>
<br><br>
   <div align="center"> 
<h2>LOGIN</h2>
<form action="${path}/MemberController" name="f1"   method="post">
<input  type="hidden"  name=sw id=sw value=IN />
<table border="1" >
<tr><th>아이디</th><th><input  type=text  name=mid id=mid /></th></tr>
<tr><th>패스워드</th><th><input type="password" name=mpassword1 id=mpassword1 ></th></tr>
<tr> 
<th colspan=4 align="center" >
	<input  type=submit  value="로그인하기" > &emsp;
    <input  type="reset"  value="다시쓰기" > &emsp;
    
</th>
</tr>
</table>
</form>
  </div>
  <br>
<section>
<a id="kakao-login-btn" href="javascript:loginWithKakao()">
  <img src="https://k.kakaocdn.net/14/dn/btroDszwNrM/I6efHub1SN5KCJqLm1Ovx1/o.jpg" width="200"
    alt="카카오 로그인 버튼" />
</a>

<p id="token-result"></p>

</section>

<script src="https://t1.kakaocdn.net/kakao_js_sdk/2.3.0/kakao.min.js"
  integrity="sha384-70k0rrouSYPWJt7q9rSTKpiTfX6USlMYjZUtr1Du+9o4cGvhPAWxngdtVZDdErlh" crossorigin="anonymous"></script>
<script>
	
  // (1) 사용하려는 앱의 JavaScript 키 입력
  Kakao.init('050b1d9368a5c0f44780fea4890a3248');
   
</script>


<script>
  function loginWithKakao() {
    Kakao.Auth.authorize({
	  // (2) redirectUri 값 변경하기 	
      redirectUri: 'http://localhost:8099/Market/KakaoLoginController',
    });
  }

  // 아래는 데모를 위한 UI 코드입니다.
  displayToken()
  function displayToken() {
    var token = getCookie('authorize-access-token');

    if(token) {
      Kakao.Auth.setAccessToken(token);
      Kakao.Auth.getStatusInfo()
        .then(function(res) {
          if (res.status === 'connected') {
            document.getElementById('token-result').innerText
              = 'login success, token: ' + Kakao.Auth.getAccessToken();
          }
        })
        .catch(function(err) {
          Kakao.Auth.setAccessToken(null);
        });
    }
  }

  function getCookie(name) {
    var parts = document.cookie.split(name + '=');
    if (parts.length === 2) { return parts[1].split(';')[0]; }
  }
</script>

<%@ include file="/include/bottom.jsp" %>