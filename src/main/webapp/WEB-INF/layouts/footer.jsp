<%-- 
    Document   : footer
    Created on : Jul 31, 2024, 9:18:20 PM
    Author     : tranlequocthong313
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<footer class="footer py-4">
    <div class="container-fluid">
        <div class="row align-items-center justify-content-lg-between">
            <div class="col-lg-6 mb-lg-0 mb-4">
                <div class="copyright text-center text-sm text-muted text-lg-start">
                    <spring:message code="footer.copyright"/>
                </div>
            </div>
            <div class="col-lg-6">
                <ul class="nav nav-footer justify-content-center justify-content-lg-end">
                    <li class="nav-item">
                        <a href="https://www.creative-tim.com" class="nav-link text-muted"
                           target="_blank"><spring:message code="footer.link1"/></a>
                    </li>
                    <li class="nav-item">
                        <a href="https://www.creative-tim.com/presentation" class="nav-link text-muted" target="_blank"><spring:message
                                code="footer.link2"/></a>
                    </li>
                    <li class="nav-item">
                        <a href="https://www.creative-tim.com/blog" class="nav-link text-muted"
                           target="_blank"><spring:message code="footer.link3"/></a>
                    </li>
                    <li class="nav-item">
                        <a href="https://www.creative-tim.com/license" class="nav-link pe-0 text-muted" target="_blank"><spring:message
                                code="footer.link4"/></a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</footer>

