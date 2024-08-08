<%--
  Created by IntelliJ IDEA.
  User: tranlequocthong313
  Date: 06/08/2024
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="container">
    <div class="form-container">
        <h2 class="form-heading mb-4"><spring:message code="resolve.issue"/></h2>
        <c:url var="action" value="/issues/${issue.id}/resolve"/>

        <form:form method="POST" modelAttribute="issue" action="${action}" role="form"
                   enctype="multipart/form-data">
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="note"><spring:message code="note"/></label>
                <form:input path="note" id="note" class="form-control"/>
                <form:errors path="note" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="resolvedAt"><spring:message
                        code="issue.resolvedAt"/></label>
                <form:input path="resolvedAt" id="resolvedAt" class="form-control" type="date"/>
                <form:errors path="resolvedAt" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="cost"><spring:message code="issue.cost"/></label>
                <form:input path="cost" id="cost" class="form-control"/>
                <form:errors path="cost" cssClass="text-danger"/>
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-lg bg-gradient-primary w-100 mt-4 mb-2">
                    <spring:message code="resolve.issue"/>
                </button>
            </div>
            <div class="text-center">
                <a href="<c:url value='/issues' />" class="btn btn-secondary w-100 mt-2 mb-0">
                    <spring:message code="cancel"/>
                </a>
            </div>
        </form:form>

    </div>
</div>
