<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String contextPath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<h2>Hello World!</h2>
  <p>
      server端执行/server.do
      client端执行/thrift0.do
  </p>
<%--<p>
    <a href="<%=contextPath%>/server.do">Server</a>
</p>--%>
<p>
    <a href="<%=contextPath%>/thrift0.do">Client</a>
</p>
</body>
</html>
