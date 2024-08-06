<%-- 
    Document   : header
    Created on : Jul 31, 2024, 9:18:44 PM
    Author     : tranlequocthong313
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">My Sale App</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link" href="<c:url value="/" />">Apparatus</a>
                </li>

                
                <li class="nav-item">
                    <a class="nav-link text-success" href="<c:url value="/stats" />">Thống kê</a>
                </li>
                
            </ul>
        </div>
    </div>
</nav>