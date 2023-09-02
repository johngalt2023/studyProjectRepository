<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="<${pageContext.request.contextPath}/readuser">
    userId: <input type="text" name="userId"><br>
    <button type="submit">readuser</button>
</form>
<c:if test="${error != null}">
    <p>${error}</p>
</c:if>
</body>
</html>
