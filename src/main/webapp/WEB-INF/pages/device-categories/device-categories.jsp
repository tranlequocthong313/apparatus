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

<div class="row">
    <div class="col-12">
        <div class="card my-4">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2">
                <div class="bg-gradient-primary shadow-primary border-radius-lg pt-4 pb-3">
                    <h6 class="text-white text-capitalize ps-3">
                        <spring:message code="device.categories.title"/>
                    </h6>
                    <a class="btn bg-gradient-dark mb-0 mx-2" href="<c:url value='/device-categories/create' />">
                        <i class="material-icons text-sm">add</i>&nbsp;&nbsp;
                        <spring:message code="add.new.category"/>
                    </a>
                </div>
            </div>
            <div class="card-body px-0 pb-2">
                <!-- Search Form -->
                <form action="<c:url value='/device-categories' />" method="get" class="mb-3 mx-4">
                    <div class="row">
                        <div class="col-md-6 mb-3">
                            <div class="input-group input-group-outline">
                                <input type="text" name="q" id="search" value="${searchQuery}" class="form-control"
                                       placeholder="<spring:message code='search' />" aria-label="Search"/>
                            </div>
                        </div>
                        <div class="col-md-4 mb-3">
                            <div class="input-group input-group-outline">
                                <select name="type" id="type" class="form-control">
                                    <option value=""><spring:message code="all.device.types"/></option>
                                    <c:forEach items="${deviceTypes}" var="deviceType">
                                        <option value="${deviceType.id}" ${type == deviceType.id ? 'selected' : ''}>
                                                ${deviceType.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-2 mb-3">
                            <button class="btn btn-primary w-100" type="submit">
                                <spring:message code="search"/>
                            </button>
                        </div>
                    </div>
                </form>
                <div class="table-responsive p-0">
                    <table class="table align-items-center mb-0">
                        <thead>
                        <tr>
                            <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-3">
                                <spring:message code="id"/>
                            </th>
                            <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                <spring:message code="name"/>
                            </th>
                            <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                <spring:message code="model"/>
                            </th>
                            <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                <spring:message code="producer"/>
                            </th>
                            <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                <spring:message code="origin"/>
                            </th>
                            <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                <spring:message code="type"/>
                            </th>
                            <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                <spring:message code="created.at"/>
                            </th>
                            <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                <spring:message code="updated.at"/>
                            </th>
                            <th class="text-secondary opacity-7"></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${deviceCategories}" var="deviceCategory">
                            <tr>
                                <td class="ps-3">
                                    <p class="text-xs font-weight-bold mb-0">${deviceCategory.id}</p>
                                </td>
                                <td>
                                    <div class="d-flex px-2 py-1">
                                        <div class="d-flex flex-column justify-content-center">
                                            <h6 class="mb-0 text-sm">
                                                <a href="<c:url value='/device-categories/${deviceCategory.id}' />"
                                                   class="text-dark">${deviceCategory.name}</a>
                                            </h6>
                                        </div>
                                    </div>
                                </td>
                                <td>
                                    <p class="text-xs font-weight-bold mb-0">${deviceCategory.model}</p>
                                </td>
                                <td>
                                    <p class="text-xs font-weight-bold mb-0">${deviceCategory.producer}</p>
                                </td>
                                <td>
                                    <p class="text-xs font-weight-bold mb-0">${deviceCategory.origin}</p>
                                </td>
                                <td>
                                    <p class="text-xs font-weight-bold mb-0">${deviceCategory.deviceType.name}</p>
                                </td>
                                <td>
                                    <p class="text-xs font-weight-bold mb-0">${deviceCategory.createdAt}</p>
                                </td>
                                <td>
                                    <p class="text-xs font-weight-bold mb-0">${deviceCategory.updatedAt}</p>
                                </td>
                                <td class="align-middle">
                                    <a href="<c:url value='/device-categories/${deviceCategory.id}/update' />"
                                       class="text-dark font-weight-bold text-xs" data-toggle="tooltip"
                                       data-original-title="<spring:message code='edit' />">
                                        <spring:message code="edit"/>
                                    </a>
                                    |
                                    <a href="<c:url value='/device-categories/${deviceCategory.id}/delete' />"
                                       class="text-secondary font-weight-bold text-xs" data-toggle="tooltip"
                                       data-original-title="<spring:message code='delete' />"
                                       onclick="return confirm('<spring:message code='confirm.delete'/>');">
                                        <spring:message code="delete"/>
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
        <!-- Pagination -->
        <div class="pagination-container justify-content-center">
            <ul class="pagination pagination-primary">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link"
                           href="?q=${searchQuery}&type=${type}&page=${currentPage - 1}"
                           aria-label="Previous">
                            <span aria-hidden="true"><span class="material-icons">keyboard_arrow_left</span></span>
                        </a>
                    </li>
                </c:if>
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link" href="?q=${searchQuery}&type=${type}&page=${i}">${i}</a>
                    </li>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link"
                           href="?q=${searchQuery}&type=${type}&page=${currentPage + 1}"
                           aria-label="Next">
                            <span aria-hidden="true"><span class="material-icons">keyboard_arrow_right</span></span>
                        </a>
                    </li>
                </c:if>
            </
            </ul>
        </div>
    </div>
</div>

