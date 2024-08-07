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
            <c:when test="${location.id != null}">
                <h2 class="form-heading mb-4"><spring:message code="update.location"/></h2>
                <c:url value='/locations/${location.id}/update' var="action"/>
            </c:when>
            <c:otherwise>
                <h2 class="form-heading mb-4"><spring:message code="create.location"/></h2>
                <c:url value='/locations/create' var="action"/>
            </c:otherwise>
        </c:choose>

        <form:form method="POST" modelAttribute="location" action="${action}" role="form"
                   enctype="multipart/form-data">
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="address"><spring:message code="address"/></label>
                <form:input path="address" id="address" class="form-control"/>
                <form:errors path="address" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="building"><spring:message code="building"/></label>
                <form:input path="building" id="building" class="form-control"/>
                <form:errors path="building" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="note"><spring:message code="note"/></label>
                <form:input path="note" id="note" class="form-control"/>
                <form:errors path="note" cssClass="text-danger"/>
            </div>
            <div class="text-center">
                <c:choose>
                    <c:when test="${location.id != null}">
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
                <a href="<c:url value='/locations' />" class="btn btn-secondary w-100 mt-2 mb-0">
                    <spring:message code="cancel"/>
                </a>
            </div>
        </form:form>
    </div>
</div>

