<%--
    Document   : login
    Created on : Aug 5, 2024, 11:50:02 PM
    Author     : tranlequocthong313
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<div class="container position-sticky z-index-sticky top-0">
    <div class="row">
        <div class="col-12">
            <!-- Navbar -->
            <nav class="navbar navbar-expand-lg blur border-radius-xl top-0 z-index-3 shadow position-absolute my-3 py-2 start-0 end-0 mx-4">
                <div class="container-fluid ps-2 pe-0">
                    <a class="navbar-brand font-weight-bolder ms-lg-0 ms-3" href="../pages/dashboard.html">
                        Apparatus
                    </a>
                    <button class="navbar-toggler shadow-none ms-2" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navigation" aria-controls="navigation" aria-expanded="false"
                            aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon mt-2">
                            <span class="navbar-toggler-bar bar1"></span>
                            <span class="navbar-toggler-bar bar2"></span>
                            <span class="navbar-toggler-bar bar3"></span>
                        </span>
                    </button>
                    <div class="collapse navbar-collapse" id="navigation">
                        <ul class="navbar-nav mx-auto">
                            <li class="nav-item">
                                <a class="nav-link d-flex align-items-center me-2 active" aria-current="page"
                                   href="<c:url value='/' />">
                                    <i class="fa fa-chart-pie opacity-6 text-dark me-1"></i>
                                    <spring:message code="navbar.dashboard" />
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link me-2" href="<c:url value='/users/login' />">
                                    <i class="fas fa-key opacity-6 text-dark me-1"></i>
                                    <spring:message code="navbar.login" />
                                </a>
                            </li>
                        </ul>
                        <ul class="navbar-nav d-lg-flex d-none">
                            <li class="nav-item d-flex align-items-center">
                                <a class="btn btn-outline-primary btn-sm mb-0 me-2" target="_blank"
                                   href="http://localhost:3000"><spring:message code="navbar.onlineThreads" /></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </nav>
            <!-- End Navbar -->
        </div>
    </div>
</div>

<main class="main-content mt-0">
    <div class="page-header align-items-start min-vh-100"
         style="background-image: url('https://images.unsplash.com/photo-1497294815431-9365093b7331?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=1950&q=80');">
        <span class="mask bg-gradient-dark opacity-6"></span>
        <div class="container my-auto">
            <div class="row">
                <div class="col-lg-4 col-md-8 col-12 mx-auto">
                    <div class="card z-index-0 fadeIn3 fadeInBottom">
                        <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                            <div class="bg-gradient-primary shadow-primary border-radius-lg py-3 pe-1">
                                <h4 class="text-white font-weight-bolder text-center mt-2 mb-0">
                                    <spring:message code="login.title" />
                                </h4>
                                <div class="row mt-3">
                                    <div class="col-2 text-center ms-auto">
                                        <a class="btn btn-link px-3" href="javascript:;">
                                            <i class="fa fa-facebook text-white text-lg"></i>
                                        </a>
                                    </div>
                                    <div class="col-2 text-center px-1">
                                        <a class="btn btn-link px-3" href="javascript:;">
                                            <i class="fa fa-github text-white text-lg"></i>
                                        </a>
                                    </div>
                                    <div class="col-2 text-center me-auto">
                                        <a class="btn btn-link px-3" href="javascript:;">
                                            <i class="fa fa-google text-white text-lg"></i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:url var="action" value='/users/login'/>
                        <div class="card-body">
                            <form role="form" class="text-start" action="${action}" method="post">
                                <div class="input-group input-group-outline my-3">
                                    <label class="form-label"><spring:message code="login.username" /></label>
                                    <input type="username" name="username" class="form-control">
                                </div>
                                <div class="input-group input-group-outline mb-3">
                                    <label class="form-label"><spring:message code="login.password" /></label>
                                    <input type="password" name="password" class="form-control">
                                </div>
                                <div class="form-check form-switch d-flex align-items-center mb-3">
                                    <input class="form-check-input" type="checkbox" id="rememberMe" checked
                                           name="remember-me">
                                    <label class="form-check-label mb-0 ms-3" for="rememberMe"><spring:message code="login.rememberMe" /></label>
                                </div>
                                <div class="text-center">
                                    <button type="submit" class="btn bg-gradient-primary w-100 my-4 mb-2">
                                        <spring:message code="login.submit" />
                                    </button>
                                </div>

                                <!-- Error Message Display -->
                                <c:if test="${param.error != null}">
                                    <div class="alert alert-danger" role="alert">
                                        <spring:message code="login.error" />
                                    </div>
                                </c:if>
                                <c:if test="${param.logout != null}">
                                    <div class="alert alert-success" role="alert">
                                        <spring:message code="login.logout" />
                                    </div>
                                </c:if>
                                <c:if test="${param.accessDenied != null}">
                                    <div class="alert alert-danger" role="alert">
                                        <spring:message code="login.accessDenied" />
                                    </div>
                                </c:if>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <footer class="footer position-absolute bottom-2 py-2 w-100">
            <div class="container">
                <div class="row align-items-center justify-content-lg-between">
                    <div class="col-12 col-md-6 my-auto">
                        <div class="copyright text-center text-sm text-white text-lg-start">
                            <spring:message code="footer.copyright" />
                        </div>
                    </div>
                    <div class="col-12 col-md-6">
                        <ul class="nav nav-footer justify-content-center justify-content-lg-end">
                            <li class="nav-item">
                                <a href="https://www.creative-tim.com" class="nav-link text-white" target="_blank"><spring:message code="footer.link1" /></a>
                            </li>
                            <li class="nav-item">
                                <a href="https://www.creative-tim.com/presentation" class="nav-link text-white" target="_blank"><spring:message code="footer.link2" /></a>
                            </li>
                            <li class="nav-item">
                                <a href="https://www.creative-tim.com/blog" class="nav-link text-white" target="_blank"><spring:message code="footer.link3" /></a>
                            </li>
                            <li class="nav-item">
                                <a href="https://www.creative-tim.com/license" class="nav-link pe-0 text-white" target="_blank"><spring:message code="footer.link4" /></a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </footer>
    </div>
</main>

