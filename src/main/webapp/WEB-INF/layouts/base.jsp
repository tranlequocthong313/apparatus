<%--
    Document   : base
    Created on : Jul 31, 2024, 9:17:16 PM
    Author     : tranlequocthong313
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="apple-touch-icon" sizes="76x76" href="${pageContext.request.contextPath}/assets/img/apple-icon.png">
    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/img/favicon.png">
    <title>
        <tiles:insertAttribute name="title"/>
    </title>

    <!-- Fonts and icons -->
    <link rel="stylesheet" type="text/css"
          href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700,900|Roboto+Slab:400,700"/>

    <!-- Nucleo Icons -->
    <link href="${pageContext.request.contextPath}/assets/css/nucleo-icons.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/assets/css/nucleo-svg.css" rel="stylesheet"/>

    <!-- Font Awesome Icons -->
    <script src="https://kit.fontawesome.com/42d5adcbca.js" crossorigin="anonymous"></script>

    <!-- Material Icons -->
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons+Round" rel="stylesheet">

    <!-- CSS Files -->
    <link id="pagestyle" href="${pageContext.request.contextPath}/assets/css/material-dashboard.css?v=3.1.0"
          rel="stylesheet"/>
</head>
<body class="g-sidenav-show  bg-gray-100">

<!-- Sidebar -->
<tiles:insertAttribute name="sidebar"/>
<!-- End Sidebar -->
<main class="main-content border-radius-lg ">
    <!-- Navbar -->
    <tiles:insertAttribute name="navbar"/>
    <!-- End Navbar -->
    <div class="container-fluid py-4">
        <!-- Main Content -->
        <tiles:insertAttribute name="main"/>
        <!-- End Main Content -->
        <!-- Footer -->
        <tiles:insertAttribute name="footer"/>
        <!-- End Footer -->
    </div>
</main>

<div class="fixed-plugin">
    <a class="fixed-plugin-button text-dark position-fixed px-3 py-2">
        <i class="material-icons py-2">settings</i>
    </a>
    <div class="card shadow-lg">
        <div class="card-header pb-0 pt-3">
            <div class="float-start">
                <h5 class="mt-3 mb-0"><spring:message code="ui.configurator"/></h5>
                <p><spring:message code="see.dashboard.options"/></p>
            </div>
            <div class="float-end mt-4">
                <button class="btn btn-link text-dark p-0 fixed-plugin-close-button">
                    <i class="material-icons">clear</i>
                </button>
            </div>
            <!-- End Toggle Button -->
        </div>
        <hr class="horizontal dark my-1">
        <div class="card-body pt-sm-3 pt-0">
            <!-- Sidebar Backgrounds -->
            <div>
                <h6 class="mb-0"><spring:message code="sidebar.colors"/></h6>
            </div>
            <a href="javascript:void(0)" class="switch-trigger background-color">
                <div class="badge-colors my-2 text-start">
                    <span class="badge filter bg-gradient-primary active" data-color="primary"
                          onclick="sidebarColor(this)"></span>
                    <span class="badge filter bg-gradient-dark" data-color="dark" onclick="sidebarColor(this)"></span>
                    <span class="badge filter bg-gradient-info" data-color="info" onclick="sidebarColor(this)"></span>
                    <span class="badge filter bg-gradient-success" data-color="success"
                          onclick="sidebarColor(this)"></span>
                    <span class="badge filter bg-gradient-warning" data-color="warning"
                          onclick="sidebarColor(this)"></span>
                    <span class="badge filter bg-gradient-danger" data-color="danger"
                          onclick="sidebarColor(this)"></span>
                </div>
            </a>
            <!-- Sidenav Type -->
            <div class="mt-3">
                <h6 class="mb-0"><spring:message code="sidenav.type"/></h6>
                <p class="text-sm"><spring:message code="choose.sidenav.type"/></p>
            </div>
            <div class="d-flex">
                <button class="btn bg-gradient-dark px-3 mb-2 active" data-class="bg-gradient-dark"
                        onclick="sidebarType(this)"><spring:message code="dark"/></button>
                <button class="btn bg-gradient-dark px-3 mb-2 ms-2" data-class="bg-transparent"
                        onclick="sidebarType(this)"><spring:message code="transparent"/></button>
                <button class="btn bg-gradient-dark px-3 mb-2 ms-2" data-class="bg-white"
                        onclick="sidebarType(this)"><spring:message code="white"/></button>
            </div>
            <p class="text-sm d-xl-none d-block mt-2"><spring:message code="sidenav.desktop.only"/></p>
            <!-- Navbar Fixed -->
            <div class="mt-3 d-flex">
                <h6 class="mb-0"><spring:message code="navbar.fixed"/></h6>
                <div class="form-check form-switch ps-0 ms-auto my-auto">
                    <input class="form-check-input mt-1 ms-auto" type="checkbox" id="navbarFixed"
                           onclick="navbarFixed(this)">
                </div>
            </div>
            <hr class="horizontal dark my-3">
            <div class="mt-2 d-flex">
                <h6 class="mb-0"><spring:message code="light.dark"/></h6>
                <div class="form-check form-switch ps-0 ms-auto my-auto">
                    <input class="form-check-input mt-1 ms-auto" type="checkbox" id="dark-version"
                           onclick="darkMode(this)">
                </div>
            </div>
            <hr class="horizontal dark my-sm-4">

            <!-- Language Selection -->
            <div class="mt-3">
                <h6 class="mb-0"><spring:message code="language"/></h6>
                <div class="d-flex">
                    <a href="javascript:void(0)" id="lang-vi" onclick="setLanguage('vi')"
                       class="<c:if test='${pageContext.request.locale == "vi"}'>lang-active</c:if>"><spring:message
                            code="vietnamese"/></a>
                    <a href="javascript:void(0)" id="lang-en"
                       class="ms-3 <c:if test='${pageContext.request.locale == "en"}'>lang-active</c:if>"
                       onclick="setLanguage('en')"><spring:message code="english"/></a>
                </div>
            </div>

        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/core/popper.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/core/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/plugins/perfect-scrollbar.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/plugins/smooth-scrollbar.min.js"></script>
<script>
    var win = navigator.platform.indexOf('Win') > -1;
    if (win && document.querySelector('#sidenav-scrollbar')) {
        var options = {
            damping: '0.5'
        }
        Scrollbar.init(document.querySelector('#sidenav-scrollbar'), options);
    }
</script>
<!-- Control Center for Material Dashboard: parallax effects, scripts for the example pages etc -->
<!--<script src="${pageContext.request.contextPath}/assets/js/material-dashboard.min.js?v=3.1.0"></script>-->
<script src="${pageContext.request.contextPath}/assets/js/material-dashboard.js"></script>

</body>
</html>

<style>
    a.lang-active {
        font-weight: bold;
        color: #EC407A; /* Hoặc bất kỳ màu nào bạn muốn */
    }
</style>

