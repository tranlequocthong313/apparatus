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
                        <spring:message code="device.list.title"/>
                    </h6>
                    <a class="btn bg-gradient-dark mb-0 mx-2" href="<c:url value='/devices/create' />">
                        <i class="material-icons text-sm">add</i>&nbsp;&nbsp;
                        <spring:message code="add.new.device"/>
                    </a>
                </div>
            </div>
            <div class="card-body px-0 pb-2">
                <!-- Search Form -->
                <form action="<c:url value='/devices' />" method="get" class="mx-4">
                    <div class="row">
                        <div class="col-md-4 mb-1">
                            <div class="input-group input-group-outline">
                                <input type="text" name="q" id="search" value="${searchQuery}" class="form-control"
                                       placeholder="<spring:message code='search' />" aria-label="Search"/>
                            </div>
                        </div>
                        <div class="col-md-2 mb-3">
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
                            <div class="input-group input-group-outline">
                                <select name="location" id="location" class="form-control">
                                    <option value=""><spring:message code="all.locations"/></option>
                                    <c:forEach items="${locations}" var="lo">
                                        <option value="${lo.id}" ${location == lo.id ? 'selected' : ''}>
                                                ${lo.building}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-2 mb-3">
                            <div class="input-group input-group-outline">
                                <select name="provider" id="provider" class="form-control">
                                    <option value=""><spring:message code="all.providers"/></option>
                                    <c:forEach items="${providers}" var="pr">
                                        <option value="${pr.id}" ${provider == pr.id ? 'selected' : ''}>
                                                ${pr.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-2 mb-3">
                            <div class="input-group input-group-outline">
                                <select name="status" id="status" class="form-control">
                                    <option value=""><spring:message code="all.statuses"/></option>
                                    <c:forEach items="${statuses}" var="st">
                                        <option value="${st}" ${status == st ? 'selected' : ''}>
                                            <spring:message code="status.${st.toLowerCase()}"/>
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


                <form id="bulkActionForm" action="<c:url value='/devices/bulk-action' />" method="post">

                    <div class="mx-4 d-flex  mb-1">
                        <div class="col-md-2">
                            <div class=" input-group input-group-outline">
                                <select name="action" id="bulkActionSelect" class="form-control">
                                    <option value=""><spring:message code="select.action"/></option>
                                    <option value="delete"><spring:message code="delete"/></option>
                                    <!-- Add more actions here if needed -->
                                </select>
                            </div>
                        </div>
                        <div class="col-md-2 mx-2">
                            <button type="button" id="executeBulkAction" class="btn btn-primary" disabled>
                                <spring:message code="execute.action"/>
                            </button>
                        </div>
                    </div>
                    <div class="table-responsive p-0">
                        <table class="table align-items-center mb-0">
                            <thead>
                            <tr>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-3">
                                    <input type="checkbox" id="selectAll">
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-3">
                                    <a href="<c:url value='/devices?sort=id&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="device.id"/>
                                        <i class="material-icons">${param.sort eq 'id' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <spring:message code="device.name"/>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/devices?sort=serial&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="device.serial"/>
                                        <i class="material-icons">${param.sort eq 'serial' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <spring:message code="device.type"/>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/devices?sort=location&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="device.location"/>
                                        <i class="material-icons">${param.sort eq 'location' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/devices?sort=locationDetail&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="device.location.detail"/>
                                        <i class="material-icons">${param.sort eq 'locationdetail' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/devices?sort=provider&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="device.provider"/>
                                        <i class="material-icons">${param.sort eq 'provider' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/devices?sort=status&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="device.status"/>
                                        <i class="material-icons">${param.sort eq 'status' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-secondary opacity-7">
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${devices}" var="device">
                                <tr>
                                    <td class="ps-3">
                                        <input type="checkbox" name="selectedIds" value="${device.id}"
                                               class="selectItem">
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${device.id}</p>
                                    </td>
                                    <td>
                                        <div class="d-flex px-2 py-1">
                                            <div class="d-flex flex-column justify-content-center">
                                                <h6 class="mb-0 text-sm">
                                                    <a href="<c:url value='/devices/${device.id}' />"
                                                       class="text-dark">${device.deviceCategory.name}</a>
                                                </h6>
                                            </div>
                                        </div>
                                    </td>
                                    <td>
                                        <p class="text-xs font-weight-bold mb-0">${device.serial}</p>
                                    </td>
                                    <td>
                                        <p class="text-xs font-weight-bold mb-0">${device.deviceCategory.deviceType.name}</p>
                                    </td>
                                    <td>
                                        <p class="text-xs font-weight-bold mb-0">${device.location.building}</p>
                                    </td>
                                    <td>
                                        <p class="text-xs font-weight-bold mb-0">${device.locationDetail.room}</p>
                                    </td>
                                    <td>
                                        <p class="text-xs font-weight-bold mb-0">${device.provider.name}</p>
                                    </td>
                                    <td>
                                        <p class="text-xs font-weight-bold mb-0">${device.status}</p>
                                    </td>
                                    <td class="align-middle">
                                        <a class="text-dark font-weight-bold text-xs"
                                           href="<c:url value='/repairs/device/${device.id}/create' />"
                                           data-toggle="tooltip"
                                           data-original-title="<spring:message code="add.new.repair"/>"
                                        >
                                            <spring:message code="add.new.repair"/>
                                        </a>
                                        |
                                        <a class="text-dark font-weight-bold text-xs"
                                           href="<c:url value='/issues/device/${device.id}/create' />"
                                           data-toggle="tooltip"
                                           data-original-title="<spring:message code="add.new.issue"/>"
                                        >
                                            <spring:message code="add.new.issue"/>
                                        </a>
                                        |
                                        <a href="<c:url value='/devices/${device.id}/update' />"
                                           class="text-dark font-weight-bold text-xs"
                                           data-toggle="tooltip"
                                           data-original-title="<spring:message code='edit' />">
                                            <spring:message code="edit"/>
                                        </a>
                                        |
                                        <a href="<c:url value='/devices/${device.id}/delete' />"
                                           class="text-dark text-xs" data-toggle="tooltip"
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
                </form>
            </div>
        </div>
        <!-- Pagination -->
        <div class="pagination-container justify-content-center">
            <ul class="pagination pagination-primary">
                <c:if test="${currentPage > 1}">
                    <li class="page-item">
                        <a class="page-link"
                           href="?q=${searchQuery}&type=${type}&location=${location}&provider=${provider}&status=${status}&page=${currentPage - 1}"
                           aria-label="Previous">
                                    <span aria-hidden="true"><span
                                            class="material-icons">keyboard_arrow_left</span></span>
                        </a>
                    </li>
                </c:if>
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <li class="page-item ${i == currentPage ? 'active' : ''}">
                        <a class="page-link"
                           href="?q=${searchQuery}&type=${type}&location=${location}&provider=${provider}&status=${status}&page=${i}">${i}</a>
                    </li>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link"
                           href="?q=${searchQuery}&type=${type}&location=${location}&provider=${provider}&status=${status}&page=${currentPage + 1}"
                           aria-label="Next">
                                    <span aria-hidden="true"><span
                                            class="material-icons">keyboard_arrow_right</span></span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>


<script>
    // JavaScript to handle select all/none functionality and enable/disable execute button
    document.addEventListener('DOMContentLoaded', function () {
        const selectAllCheckbox = document.getElementById('selectAll');
        const checkboxes = document.querySelectorAll('.selectItem');
        const executeButton = document.getElementById('executeBulkAction');
        const bulkActionSelect = document.getElementById('bulkActionSelect');

        selectAllCheckbox.addEventListener('change', function () {
            checkboxes.forEach(checkbox => {
                checkbox.checked = selectAllCheckbox.checked;
            });
            toggleExecuteButton();
        });

        checkboxes.forEach(checkbox => {
            checkbox.addEventListener('change', toggleExecuteButton);
        });

        bulkActionSelect.addEventListener('change', toggleExecuteButton);

        function toggleExecuteButton() {
            const anyChecked = Array.from(checkboxes).some(checkbox => checkbox.checked);
            const actionSelected = bulkActionSelect.value !== '';
            executeButton.disabled = !(anyChecked && actionSelected);
        }

        executeButton.addEventListener('click', function () {
            if (confirm('<spring:message code="confirm.bulk.action" />')) {
                document.getElementById('bulkActionForm').submit();
            }
        });
    });
</script>


