
<%@ page contentType="text/html; charset=UTF-8" language="java" isELIgnored="false" %>

<nav class="top ">
    <c:if test="${contextPath}">
        <a href="${contextPath}">
            <span style="color:#C40000;margin:0px" class=" glyphicon glyphicon-home redColor"></span>
            天猫首页
        </a>
    </c:if>
    <c:if test="${!contextPath}">
        <a href="forehome">
            <span style="color:#C40000;margin:0px" class=" glyphicon glyphicon-home redColor"></span>
            天猫首页
        </a>
    </c:if>



    <span>喵，欢迎来天猫</span>

    <c:if test="${!empty user}">
        <a href="loginPage">${user.name}</a>
        <a href="forelogout">退出</a>
    </c:if>

    <c:if test="${empty user}">
        <a href="loginPage">请登录</a>
        <a href="registerPage">免费注册</a>
    </c:if>

    <span class="pull-right">
            <a href="forebought">我的订单</a>
            <a href="forecart">
            <span style="color:#C40000;margin:0px" class=" glyphicon glyphicon-shopping-cart redColor"></span>
            购物车<strong>${cartTotalItemNumber}</strong>件</a>
        </span>

</nav>