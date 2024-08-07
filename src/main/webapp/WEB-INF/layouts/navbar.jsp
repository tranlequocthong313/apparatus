<%-- 
    Document   : header
    Created on : Jul 31, 2024, 9:18:44 PM
    Author     : tranlequocthong313
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<nav class="navbar navbar-main navbar-expand-lg px-0 mx-4 shadow-none border-radius-xl" id="navbarBlur"
     data-scroll="true">
    <div class="container-fluid py-1 px-3">
        <c:forEach items="${categories}" var="c">
            <c:if test='${currentPath eq c.link}'>
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb bg-transparent mb-0 pb-0 pt-1 px-0 me-sm-6 me-5">
                        <li class="breadcrumb-item text-sm"><a class="opacity-5 text-dark" href="javascript:;">Pages</a>
                        </li>
                        <li class="breadcrumb-item text-sm text-dark active" aria-current="page">
                            <spring:message code="breadcrumb.${c.title}" />
                        </li>
                    </ol>
                    <h6 class="font-weight-bolder mb-0">
                        <spring:message code="header.${c.title}" />
                    </h6>
                </nav>
            </c:if>
        </c:forEach>
        <div class="collapse navbar-collapse mt-sm-0 mt-2 me-md-0 me-sm-4" id="navbar">
            <div class="ms-md-auto pe-md-3 d-flex align-items-center">
            </div>
            <ul class="navbar-nav  justify-content-end">
                <li class="nav-item d-flex align-items-center">
                    <a class="btn btn-outline-primary btn-sm mb-0" target="_blank"
                       href="https://www.creative-tim.com/builder?ref=navbar-material-dashboard">
                        <spring:message code="online.threads" />
                    </a>
                </li>
                <security:authorize access="isAuthenticated()">
                    <li class="nav-item d-flex align-items-center mx-3">
                        <span class="d-sm-inline d-none">
                            <spring:message code="welcome" /> <security:authentication property="principal.username"/>
                        </span>
                    </li>
                </security:authorize>
                <security:authorize access="!isAuthenticated()">
                    <li class="nav-item d-flex align-items-center mx-3">
                        <a href="<c:url value='/users/login' />" class="nav-link text-body font-weight-bold px-0 mb-0">
                            <i class="fa fa-user me-sm-1"></i>
                            <span class="d-sm-inline d-none"><spring:message code="login" /></span>
                        </a>
                    </li>
                </security:authorize>
            </ul>
        </div>
    </div>
</nav>

