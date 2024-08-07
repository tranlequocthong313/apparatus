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
                <form action="<c:url value='/device-categories' />" method="get" class="mx-4">
                    <div class="row">
                        <div class="col-md-6 mb-1">
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

                <!-- Bulk Action Form -->
                <form id="bulkActionForm" action="<c:url value='/device-categories/bulk-action' />" method="post">
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
                                    <a href="<c:url value='/device-categories?sort=id&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="id"/>
                                        <i class="material-icons">${param.sort eq 'id' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                    <a href="<c:url value='/device-categories?sort=name&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="name"/>
                                        <i class="material-icons">${param.sort eq 'name' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/device-categories?sort=model&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="model"/>
                                        <i class="material-icons">${param.sort eq 'model' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/device-categories?sort=producer&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="producer"/>
                                        <i class="material-icons">${param.sort eq 'producer' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/device-categories?sort=origin&directionc=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="origin"/>
                                        <i class="material-icons">${param.sort eq 'origin' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/device-categories?sort=type&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="type"/>
                                        <i class="material-icons">${param.sort eq 'type' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/device-categories?sort=createdAt&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="created.at"/>
                                        <i class="material-icons">${param.sort eq 'createdAt' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/device-categories?sort=updatedAt&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="updated.at"/>
                                        <i class="material-icons">${param.sort eq 'updatedAt' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-secondary opacity-7"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${deviceCategories}" var="deviceCategory">
                                <tr>
                                    <td class="ps-3">
                                        <input type="checkbox" name="selectedIds" value="${deviceCategory.id}"
                                               class="selectItem">
                                    </td>
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
                </form>
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

