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
                    <h2><spring:message code="location.building" arguments="${location.building}"/></h2>
                </div>
                <div class="card-body text-center">
                    <h4 class="card-title"><spring:message code="location.building"
                                                           arguments="${location.building}"/></h4>
                    <p class="card-text"><strong><spring:message code="address"/>:</strong> ${location.address}</p>
                    <p class="card-text"><strong><spring:message code="note"/>:</strong> ${location.note}
                    </p>
                    <p class="card-text"><strong><spring:message
                            code="created.at"/>:</strong> ${location.createdAt}</p>
                    <div class="mt-4">
                        <a href="<c:url value='/locations/${location.id}/update' />"
                           class="btn btn-warning"><spring:message code="edit"/></a>
                        <a href="<c:url value='/locations/${location.id}/delete' />"
                           class="btn btn-danger"
                           onclick="return confirm('<spring:message code="confirm.delete"/>');"><spring:message
                                code="delete"/></a>
                        <a href="<c:url value='/locations' />" class="btn btn-secondary"><spring:message
                                code="back.to.list"/></a>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

