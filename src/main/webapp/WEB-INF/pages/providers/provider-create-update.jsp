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

<div class="container">
    <div class="form-container">
        <c:choose>
            <c:when test="${provider.id != null}">
                <h2 class="form-heading mb-4"><spring:message code="update.provider"/></h2>
                <c:url value='/providers/${provider.id}/update' var="action"/>
            </c:when>
            <c:otherwise>
                <h2 class="form-heading mb-4"><spring:message code="create.provider"/></h2>
                <c:url value='/providers/create' var="action"/>
            </c:otherwise>
        </c:choose>

        <form:form method="POST" modelAttribute="provider" action="${action}" role="form"
                   enctype="multipart/form-data">
            <label class="form-label" for="name"><spring:message code="name"/></label>
            <div class="input-group input-group-outline mb-3">
                <form:input path="name" id="name" class="form-control"/>
                <form:errors path="name" cssClass="text-danger"/>
            </div>
            <label class="form-label" for="phoneNumber"><spring:message code="phone.number"/></label>
            <div class="input-group input-group-outline mb-3">
                <form:input path="phoneNumber" id="phoneNumber" class="form-control"/>
                <form:errors path="phoneNumber" cssClass="text-danger"/>
            </div>
            <label class="form-label" for="email"><spring:message code="email"/></label>
            <div class="input-group input-group-outline mb-3">
                <form:input path="email" id="email" class="form-control"/>
                <form:errors path="email" cssClass="text-danger"/>
            </div>
            <label class="form-label" for="website"><spring:message code="website"/></label>
            <div class="input-group input-group-outline mb-3">
                <form:input path="website" id="website" class="form-control"/>
                <form:errors path="website" cssClass="text-danger"/>
            </div>
            <label class="form-label" for="address"><spring:message code="address"/></label>
            <div class="input-group input-group-outline mb-3">
                <form:input path="address" id="address" class="form-control"/>
                <form:errors path="address" cssClass="text-danger"/>
            </div>
            <div class="text-center">
                <c:choose>
                    <c:when test="${provider.id != null}">
                        <button type="submit" class="btn btn-lg bg-gradient-primary w-100 mt-4 mb-2">
                            <spring:message code="update"/>
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" class="btn btn-lg bg-gradient-primary w-100 mt-4 mb-2">
                            <spring:message code="create"/>
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="text-center">
                <a href="<c:url value='/providers' />" class="btn btn-secondary w-100 mt-2 mb-0">
                    <spring:message code="cancel"/>
                </a>
            </div>
        </form:form>
    </div>
</div>

