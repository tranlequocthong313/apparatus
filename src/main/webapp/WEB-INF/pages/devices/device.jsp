<%--
  Created by IntelliJ IDEA.
  User: tranlequocthong313
  Date: 06/08/2024
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container mt-5">
    <div class="row">
        <div class="col-md-8 offset-md-2">
            <div class="card shadow-sm">
                <div class="card-header bg-gradient-primary text-white text-center py-4">
                    <h3 class="mb-0">${device.id} - ${device.deviceCategory.name}</h3>
                </div>
                <div class="card-body text-center">
                    <img src="<c:url value='${device.image}' />" alt="${device.deviceCategory.name}"
                         class="img-fluid mb-4 rounded">
                    <div class="mb-3">
                        <h5 class="card-title"><strong><spring:message code="device.id"/>:</strong> ${device.id}</h5>
                        <p class="card-text text-muted mb-1"><strong><spring:message
                                code="device.type"/>:</strong> ${device.deviceCategory.deviceType.name}</p>
                        <p class="card-text text-muted mb-1"><strong><spring:message
                                code="device.location"/>:</strong> ${device.location.building}</p>
                        <p class="card-text text-muted mb-1"><strong><spring:message
                                code="device.location.detail"/>:</strong> ${device.locationDetail.room}</p>
                        <p class="card-text text-muted mb-1"><strong><spring:message
                                code="device.dateStartedOperation"/>:</strong> ${device.dateStartedOperation}</p>
                        <p class="card-text text-muted mb-1"><strong><spring:message
                                code="device.dateOfManufacture"/>:</strong> ${device.dateOfManufacture}</p>
                        <p class="card-text text-muted mb-1"><strong><spring:message
                                code="device.model"/>:</strong> ${device.deviceCategory.model}</p>
                        <p class="card-text text-muted mb-1"><strong><spring:message
                                code="device.producer"/>:</strong> ${device.deviceCategory.producer}</p>
                        <p class="card-text text-muted mb-1"><strong><spring:message
                                code="device.provider"/>:</strong> ${device.provider.name}</p>
                        <p class="card-text text-muted mb-1"><strong><spring:message
                                code="device.serial"/>:</strong> ${device.serial}</p>
                        <p class="card-text text-muted mb-1"><strong><spring:message
                                code="device.deviceCategory"/>:</strong> ${device.deviceCategory.name}</p>
                        <p class="card-text text-muted mb-1"><strong><spring:message
                                code="device.status"/>:</strong> ${device.status}</p>
                        <p class="card-text text-muted mb-1"><strong><spring:message
                                code="device.manager"/>:</strong> ${device.user.fullName}</p>
                        <p class="card-text text-muted mb-1"><strong><spring:message
                                code="device.dateOfPurchase"/>:</strong> ${device.dateOfPurchase}</p>
                        <p class="card-text text-muted mb-1"><strong><spring:message
                                code="device.warrantyPeriod"/>:</strong> ${device.warrantyPeriod} <spring:message
                                code="month"/></p>
                        <p class="card-text text-muted mb-1"><strong><spring:message
                                code="device.yearOfDepreciation"/>:</strong> ${device.yearOfDepreciation}
                            <spring:message code="year"/></p>
                        <p class="card-text text-muted mb-1"><strong><spring:message code="device.link"/>:</strong> <a
                                href="${device.link}" target="_blank" class="text-primary"
                                class="text-decoration-underline text-primary"><spring:message code="website.link"/></a>
                        </p>
                        <p class="card-text text-muted mb-1"><strong><spring:message
                                code="issue.total"/>:</strong> ${totalIssue}</p>
                        <p class="card-text text-muted mb-1"><strong><spring:message
                                code="issue.total.cost"/>:</strong> ${totalCost} VND</p>
                        <p class="card-text text-muted mb-1"><strong><spring:message
                                code="issue.unresolved.day"/>:</strong> ${unresolvedDays} <spring:message code="day"/>
                        </p>
                        <p class="card-text text-muted mb-1"><strong><spring:message
                                code="created.at"/>:</strong> ${device.createdAt}</p>
                    </div>
                    <div class="d-flex justify-content-around mt-4">
                        <a href="<c:url value='/issues/device/${device.id}/create' />"
                           class="btn btn-outline-danger"><spring:message code="issue"/></a>
                        <a href="<c:url value='/repairs/${device.id}/create' />"
                           class="btn btn-outline-succes"><spring:message code="repair"/></a>
                        <a href="<c:url value='/devices/${device.id}/update' />"
                           class="btn btn-outline-issue"><spring:message code="edit"/></a>
                        <a href="<c:url value='/devices/${device.id}/delete' />"
                           class="btn btn-outline-danger"
                           onclick="return confirm('<spring:message code="confirm.delete"/>');"><spring:message
                                code="delete"/></a>
                        <a href="<c:url value='/devices' />" class="btn btn-outline-secondary"><spring:message
                                code="back.to.list"/></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

