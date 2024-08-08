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
            <c:when test="${device.id != null}">
                <h2 class="form-heading mb-4"><spring:message code="update.device.category"/></h2>
                <c:url value='/devices/${device.id}/update' var="action"/>
            </c:when>
            <c:otherwise>
                <h2 class="form-heading mb-4"><spring:message code="create.device.category"/></h2>
                <c:url value='/devices/create' var="action"/>
            </c:otherwise>
        </c:choose>

        <form:form method="POST" modelAttribute="device" action="${action}" role="form"
                   enctype="multipart/form-data">
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="id"><spring:message code="device.id"/></label>
                <form:input path="id" id="id" class="form-control"/>
                <form:errors path="id" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="serial"><spring:message code="device.serial"/></label>
                <form:input path="serial" id="serial" class="form-control"/>
                <form:errors path="serial" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="dateStartedOperation"><spring:message
                        code="device.dateStartedOperation"/></label>
                <form:input path="dateStartedOperation" id="dateStartedOperation" class="form-control" type="date"/>
                <form:errors path="dateStartedOperation" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="dateOfManufacture"><spring:message
                        code="device.dateOfManufacture"/></label>
                <form:input path="dateOfManufacture" id="dateOfManufacture" class="form-control" type="date"/>
                <form:errors path="dateOfManufacture" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="price"><spring:message code="device.price"/></label>
                <form:input path="price" id="price" class="form-control" type="number"/>
                <form:errors path="price" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="dateOfPurchase"><spring:message code="device.dateOfPurchase"/></label>
                <form:input path="dateOfPurchase" id="dateOfPurchase" class="form-control" type="date"/>
                <form:errors path="dateOfPurchase" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="warrantyPeriod"><spring:message code="device.warrantyPeriod"/></label>
                <form:input path="warrantyPeriod" id="warrantyPeriod" class="form-control" type="number"/>
                <form:errors path="warrantyPeriod" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="link"><spring:message code="device.link"/></label>
                <form:input path="link" id="link" class="form-control"/>
                <form:errors path="link" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="yearOfDepreciation"><spring:message
                        code="device.yearOfDepreciation"/></label>
                <form:input path="yearOfDepreciation" id="yearOfDepreciation" class="form-control" type="number"/>
                <form:errors path="yearOfDepreciation" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <form:select path="deviceCategory.id" id="deviceCategory" class="form-control">
                    <form:option value="">
                        <spring:message code='device.deviceCategory'/>
                    </form:option>
                    <form:options items="${deviceCategories}" itemValue="id" itemLabel="name"/>
                </form:select>
                <form:errors path="deviceCategory.id" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <form:select path="location.id" id="location" class="form-control">
                    <form:option value="">
                        <spring:message code='device.location'/>
                    </form:option>
                    <form:options items="${locations}" itemValue="id" itemLabel="building"/>
                </form:select>
                <form:errors path="location.id" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <form:select path="locationDetail.id" id="locationDetail" class="form-control">
                    <form:option value="">
                        <spring:message code='device.location.detail'/>
                    </form:option>
                    <form:options items="${locationDetails}" itemValue="id" itemLabel="room"/>
                </form:select>
                <form:errors path="locationDetail.id" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <form:select path="provider.id" id="provider" class="form-control">
                    <form:option value="">
                        <spring:message code='device.provider'/>
                    </form:option>
                    <form:options items="${providers}" itemValue="id" itemLabel="name"/>
                </form:select>
                <form:errors path="provider.id" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <form:select path="user.id" id="user" class="form-control">
                    <form:option value="">
                        <spring:message code='device.manager'/>
                    </form:option>
                    <form:options items="${users}" itemValue="id" itemLabel="fullName"/>
                </form:select>
                <form:errors path="user.id" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <form:select path="status" id="status" class="form-control">
                    <form:option value="">
                        <spring:message code='device.status'/>
                    </form:option>
                    <form:options items="${statuses}" />
                </form:select>
                <form:errors path="status" cssClass="text-danger"/>
            </div>
            <div class="input-group input-group-outline mb-3">
                <label class="form-label" for="image"><spring:message code="device.image"/></label>
                <input type="file" name="img" id="image" class="form-control" accept="image/*"/>
                <form:errors path="image" cssClass="text-danger"/>
            </div>
            <div class="text-center">
                <c:choose>
                    <c:when test="${device.id != null}">
                        <button type="submit" class="btn btn-lg bg-gradient-primary w-100 mt-4 mb-2">
                            <spring:message code="update.device"/>
                        </button>
                    </c:when>
                    <c:otherwise>
                        <button type="submit" class="btn btn-lg bg-gradient-primary w-100 mt-4 mb-2">
                            <spring:message code="create.device"/>
                        </button>
                    </c:otherwise>
                </c:choose>
            </div>
            <div class="text-center">
                <a href="<c:url value='/devices' />" class="btn btn-secondary w-100 mt-2 mb-0">
                    <spring:message code="cancel"/>
                </a>
            </div>
        </form:form>

    </div>
</div>
