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
                    <h2><spring:message code="device.category.name" arguments="${deviceCategory.name}"/></h2>
                </div>
                <div class="card-body text-center">
                    <img src="<c:url value='${deviceCategory.image}' />" alt="${deviceCategory.name}"
                         class="img-fluid mb-3">
                    <h4 class="card-title"><spring:message code="device.category.name"
                                                           arguments="${deviceCategory.name}"/></h4>
                    <p class="card-text"><strong><spring:message code="model"/>:</strong> ${deviceCategory.model}</p>
                    <p class="card-text"><strong><spring:message code="producer"/>:</strong> ${deviceCategory.producer}
                    </p>
                    <p class="card-text"><strong><spring:message code="origin"/>:</strong> ${deviceCategory.origin}</p>
                    <p class="card-text"><strong><spring:message
                            code="device.type"/>:</strong> ${deviceCategory.deviceType.getName()}</p>
                    <p class="card-text"><strong><spring:message
                            code="created.at"/>:</strong> ${deviceCategory.createdAt}</p>
                    <div class="mt-4">
                        <a href="<c:url value='/device-categories/${deviceCategory.id}/update' />"
                           class="btn btn-warning"><spring:message code="edit"/></a>
                        <a href="<c:url value='/device-categories/${deviceCategory.id}/delete' />"
                           class="btn btn-danger"
                           onclick="return confirm('<spring:message code="confirm.delete"/>');"><spring:message
                                code="delete"/></a>
                        <a href="<c:url value='/device-categories' />" class="btn btn-secondary"><spring:message
                                code="back.to.list"/></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

