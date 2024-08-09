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
            <c:when test="${repair.id != null}">
                <h2 class="form-heading mb-4"><spring:message code="update.repair"/></h2>
                <c:url value='/repairs/${repair.id}/update' var="action"/>
            </c:when>
            <c:otherwise>
                <h2 class="form-heading mb-4"><spring:message code="create.repair"/></h2>
                <c:url value='/repairs/create' var="action"/>
            </c:otherwise>
        </c:choose>

        <form:form method="POST" modelAttribute="repair" action="${action}" role="form"
                   enctype="multipart/form-data">
            <label class="form-label" for="note"><spring:message code="repairedBy"/></label>
            <div class="input-group input-group-outline mb-3">
                <form:select path="repairedBy" id="repairedBy" class="form-control">
                    <form:option value="">
                        <spring:message code='all.repairedBys'/>
                    </form:option>
                    <form:options items="${repairedBys}"/>
                </form:select>
                <form:errors path="repairedBy" cssClass="text-danger"/>
            </div>
            <label class="form-label" for="note"><spring:message code="note"/></label>
            <div class="input-group input-group-outline mb-3">
                <form:input path="note" id="note" class="form-control"/>
                <form:errors path="note" cssClass="text-danger"/>
            </div>
            <c:if test="${repair.issue != null}">
                <label class="form-label" for="device"><spring:message code="issue"/></label>
                <div class="input-group input-group-outline mb-3">
                    <input value="${repair.issue.id} - ${repair.issue.title}" class="form-control" id="issue"
                           disabled type="text"/>
                    <form:hidden path="issue.id"/>
                </div>
            </c:if>
            <c:if test="${repair.device != null}">
                <label class="form-label" for="device"><spring:message code="device"/></label>
                <div class="input-group input-group-outline mb-3">
                    <input value="${repair.device.id} - ${repair.device.deviceCategory.name}" class="form-control"
                           id="device"
                           disabled type="text"/>
                    <form:hidden path="device.id"/>
                </div>
            </c:if>
            <form:hidden path="user.id" value="${currentUser.id}"/>
            <label class="form-label" for="cost"><spring:message code="cost"/></label>
            <div class="input-group input-group-outline mb-3">
                <form:input path="cost" id="cost" class="form-control" type="number"/>
                <form:errors path="cost" cssClass="text-danger"/>
            </div>
            <label class="form-label" for="receptionDate"><spring:message
                    code="receptionDate"/></label>
            <div class="input-group input-group-outline mb-3">
                <form:input path="receptionDate" id="receptionDate" class="form-control" type="date"/>
                <form:errors path="receptionDate" cssClass="text-danger"/>
            </div>
            <label class="form-label" for="completedDate"><spring:message
                    code="completedDate"/></label>
            <div class="input-group input-group-outline mb-3">
                <form:input path="completedDate" id="completedDate" class="form-control" type="date"/>
                <form:errors path="completedDate" cssClass="text-danger"/>
            </div>
            <label class="form-label" for="image"><spring:message code="issue.image"/></label>
            <div class="input-group input-group-outline mb-3">
                <input type="file" name="img" id="image" class="form-control" accept="image/*"/>
                <form:errors path="image" cssClass="text-danger"/>
            </div>
            <label class="form-label"><spring:message code="repairCategory.select"/></label>
            <div class="form-group col-md-12">
                <div class="col-md-7">
                    <form:select path="repairCategorySet" items="${repairCategories}" multiple="true" itemValue="id"
                                 itemLabel="name" class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="repairCategorySet" class="help-inline"/>
                    </div>
                </div>
            </div>
            <form:errors path="repairCategorySet" cssClass="text-danger"/>
            <div class="text-center">
                <c:choose>
                    <c:when test="${repair.id != null}">
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
                <a href="<c:url value='/repairs' />" class="btn btn-secondary w-100 mt-2 mb-0">
                    <spring:message code="cancel"/>
                </a>
            </div>
        </form:form>
    </div>
</div>