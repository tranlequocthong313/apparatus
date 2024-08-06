<%-- 
    Document   : base
    Created on : Jul 31, 2024, 9:17:16 PM
    Author     : tranlequocthong313
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
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


    <!--     Fonts and icons     -->
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

<tiles:insertAttribute name="main"/>

<!--   Core JS Files   -->
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
<%--<script src="${pageContext.request.contextPath}/assets/js/material-dashboard.min.js?v=3.1.0"></script>--%>
<script src="${pageContext.request.contextPath}/assets/js/material-dashboard.js"></script>

</body>

</html>
