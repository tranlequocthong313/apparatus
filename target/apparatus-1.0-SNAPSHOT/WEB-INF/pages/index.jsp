<%-- 
    Document   : index
    Created on : Jul 31, 2024, 5:44:58?PM
    Author     : tranlequocthong313
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<h3><spring:message code="greeting"/></h3>
<ul>
    <c:forEach var="cate" items="${categories}">
        <li>${cate.name}</li>
        </c:forEach>
</ul>
<
