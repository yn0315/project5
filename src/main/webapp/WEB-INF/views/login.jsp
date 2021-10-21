

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
   <!-- reset.css -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reset-css@4.0.1/reset.min.css" />
<!-- normalize.css -->
<link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css" />
<style>
   .login{
    width: 100%;
    /* background: gray; */
    background: url("data:image/jpeg;base64,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") no-repeat center;
    background-size: cover;
    height: 100vh;

}

.login form{
    width: 80%;
  text-align: center;
    /* background: red; */
    padding-top: 8%;
    margin: 0 auto;
}

.login form .body{
    /* border: 1px solid gray; */
    width: 40%;
    margin:  0 auto;
    height: 50vh;
    /* background: gray; */
    /* padding-top: 8%; */
   
}

.login form .body h1{
    align-items: center;
   color: #fff;
    margin-top: 15%;
}


.login form .body .id{
    color: #fff;
    font-size: 25px;
}

.login form .body .pw{
    color: #fff;
    font-size: 25px;
}

.login form .body .id input{

    outline: none;
    width: 80%;
    height: 5vh;
    font-size: large;
    background: transparent;
    border: none;
    border-bottom: 1px solid #fff;

}

.login form .body .pw input{
    outline: none;
    width: 80%;
    height: 5vh;
    font-size: 20px;
    background: transparent;
    border: none;
    border-bottom: 1px solid #fff;
    color: #fff;

}

.login form .body .sumbit{
    width: 80%;
    margin: 5% auto;

}

.login form .body .sumbit input{
    border: none;
    outline: none;
    border-radius: 10px;
    width: 35%;
    height: 5vh;
}
</style>
    

</head>

<body>
    <div class="login">
        <form action="/login" method="POST">

            <div class="body">
                <h1>일정관리</h1>
                <div class="id">
                    #ID :  <input type="text" name="empNo">
                </div>

                <div class="pw">
                    #PW  : <input type="password" name="password">
                </div>
                <div class="sumbit">
                    <input type="submit" value="로그인">
                </div>
            </div>
        </form>
    </div>
</body>

</html>