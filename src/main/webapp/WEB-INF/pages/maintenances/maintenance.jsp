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
            <div class="card">
                <div class="card-header bg-gradient-primary text-white text-center">
                    <h2>${device.deviceCategory.name}</h2>
                </div>
                <div class="card-body text-center">
                    <img src="<c:url value='${device.image}' />" alt="${device.deviceCategory.name}"
                         class="img-fluid mb-3">
                    <h4 class="card-title">${device.id}</h4>
                    <p class="card-text"><strong><spring:message code="device.serial"/>:</strong> ${device.serial}</p>
                    <p class="card-text"><strong><spring:message code="type"/>:</strong> ${device.deviceCategory.deviceType.name}</p>
                    <p class="card-text"><strong><spring:message code="device.deviceCategory"/>:</strong> ${device.deviceCategory.name}</p>
                    <p class="card-text"><strong><spring:message code="device.location"/>:</strong> ${device.location.building}</p>
                    <p class="card-text"><strong><spring:message code="device.location.detail"/>:</strong> ${device.locationDetail.room}</p>
                    <p class="card-text"><strong><spring:message code="device.provider"/>:</strong> ${device.provider.name}</p>
                    <p class="card-text"><strong><spring:message code="device.status"/>:</strong> ${device.status}</p>
                    <p class="card-text"><strong><spring:message code="created.at"/>:</strong> ${device.createdAt}</p>
                    <div class="mt-4">
                        <a href="<c:url value='/devices/${device.id}/update' />"
                           class="btn btn-warning"><spring:message code="edit"/></a>
                        <a href="<c:url value='/devices/${device.id}/delete' />"
                           class="btn btn-danger"
                           onclick="return confirm('<spring:message code="confirm.delete"/>');"><spring:message code="delete"/></a>
                        <a href="<c:url value='/devices' />" class="btn btn-secondary"><spring:message code="back.to.list"/></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


