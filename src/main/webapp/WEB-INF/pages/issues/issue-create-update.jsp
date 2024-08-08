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
        <c:choose>
            <c:when test="${issue.id != null}">
                <h2 class="form-heading mb-4"><spring:message code="update.issue.category"/></h2>
                <c:url value='/issues/${issue.id}/update' var="action"/>
            </c:when>
            <c:otherwise>
                <h2 class="form-heading mb-4"><spring:message code="create.issue.category"/></h2>
                <c:url value='/issues/create' var="action"/>
            </c:otherwise>
        </c:choose>

        <form:form method="POST" modelAttribute="issue" action="${action}" role="form"
                   enctype="multipart/form-data">
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="title"><spring:message code="issue.title"/></label>
                <form:input path="title" id="title" class="form-control"/>
                <form:errors path="title" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="description"><spring:message
                        code="issue.description"/></label>
                <form:input path="description" id="description" class="form-control"/>
                <form:errors path="description" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="occurredAt"><spring:message
                        code="issue.occurredAt"/></label>
                <form:input path="occurredAt" id="occurredAt" class="form-control" type="date"/>
                <form:errors path="occurredAt" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="note"><spring:message code="note"/></label>
                <form:input path="note" id="note" class="form-control"/>
                <form:errors path="note" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <input value="${issue.device.id} - ${issue.device.deviceCategory.name}" class="form-control"
                       disabled type="text"/>
                <form:hidden path="device.id"/>
            </div>
            <form:hidden path="user.id" value="${currentUser.id}"/>
            <div class="input-group input-group-outline mb-3">
                <form:select path="severity" id="severity" class="form-control">
                    <form:option value="">
                        <spring:message code='issue.severity'/>
                    </form:option>
                    <form:options items="${severities}"/>
                </form:select>
                <form:errors path="severity" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="image"><spring:message code="issue.image"/></label>
                <input type="file" name="img" id="image" class="form-control" accept="image/*"/>
                <form:errors path="image" cssClass="text-danger"/>
            </div>
            <div class="text-center">
                <c:choose>
                    <c:when test="${issue.id != null}">
                        <button type="submit" class="btn btn-lg bg-gradient-primary w-100 mt-4 mb-2">
                            <spring:message code="update.issue"/>
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" class="btn btn-lg bg-gradient-primary w-100 mt-4 mb-2">
                            <spring:message code="create.issue"/>
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="text-center">
                <a href="<c:url value='/issues' />" class="btn btn-secondary w-100 mt-2 mb-0">
                    <spring:message code="cancel"/>
                </a>
            </div>
        </form:form>

    </div>
</div>

