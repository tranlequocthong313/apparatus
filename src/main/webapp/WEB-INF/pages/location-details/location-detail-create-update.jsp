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
            <c:when test="${locationDetail.id != null}">
                <h2 class="form-heading mb-4"><spring:message code="update.location.detail"/></h2>
                <c:url value='/location-details/${locationDetail.id}/update' var="action"/>
            </c:when>
            <c:otherwise>
                <h2 class="form-heading mb-4"><spring:message code="create.location.detail"/></h2>
                <c:url value='/location-details/create' var="action"/>
            </c:otherwise>
        </c:choose>

        <form:form method="POST" modelAttribute="locationDetail" action="${action}" role="form"
                   enctype="multipart/form-data">
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="floor"><spring:message code="floor"/></label>
                <form:input path="floor" id="floor" class="form-control"/>
                <form:errors path="floor" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="room"><spring:message code="room"/></label>
                <form:input path="room" id="room" class="form-control"/>
                <form:errors path="room" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="note"><spring:message code="note"/></label>
                <form:input path="note" id="note" class="form-control"/>
                <form:errors path="note" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="location"><spring:message code="select.location"/></label>
                <form:select path="location.id" id="location" class="form-control">
                    <form:option value="">
                        <spring:message code='select.location' />
                    </form:option>
                    <form:options items="${locations}" itemValue="id" itemLabel="address"/>
                </form:select>
                <form:errors path="location.id" cssClass="text-danger"/>
            </div>
            <div class="text-center">
                <c:choose>
                    <c:when test="${locationDetail.id != null}">
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
                <a href="<c:url value='/location-details' />" class="btn btn-secondary w-100 mt-2 mb-0">
                    <spring:message code="cancel"/>
                </a>
            </div>
        </form:form>
    </div>
</div>

