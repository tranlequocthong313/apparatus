<%--
    Document   : sidebar
    Created on : Aug 5, 2024, 10:14:24 PM
    Author     : tranlequocthong313
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<aside class="sidenav navbar navbar-vertical navbar-expand-xs border-0 border-radius-xl my-3 fixed-start ms-3 bg-gradient-dark"
       id="sidenav-main">
    <div class="sidenav-header">
        <i class="fas fa-times p-3 cursor-pointer text-white opacity-5 position-absolute end-0 top-0 d-none d-xl-none"
           aria-hidden="true" id="iconSidenav"></i>
        <a class="navbar-brand m-0" href="<c:url value="/" />">
            <img src="${pageContext.request.contextPath}/assets/img/logo-ct.png" class="navbar-brand-img h-100" alt="main_logo">
            <span class="ms-1 font-weight-bold text-white">Apparatus</span>
        </a>
    </div>
    <hr class="horizontal light mt-0 mb-2">
    <div class="collapse navbar-collapse w-auto " id="sidenav-collapse-main">
        <ul class="navbar-nav">
            <c:forEach items="${categories}" var="c">
                <c:if test="${c.divider != null}">
                    <li class="nav-item mt-3">
                        <h6 class="ps-4 ms-2 text-uppercase text-xs text-white font-weight-bolder opacity-8">
                            <spring:message code="${c.divider}" />
                        </h6>
                    </li>
                </c:if>
                <c:url value="${c.link}" var="cPath"/>
                <li class="nav-item">
                    <a class="nav-link text-white <c:if test='${currentPath eq c.link}'>active bg-gradient-primary</c:if>"
                       href="${cPath}">
                        <div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
                            <i class="material-icons opacity-10">${c.icon}</i>
                        </div>
                        <span class="nav-link-text ms-1">
                            <spring:message code="${c.title}" />
                        </span>
                    </a>
                </li>
            </c:forEach>
        </ul>
    </div>
</aside>

