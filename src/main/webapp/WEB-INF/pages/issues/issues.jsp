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
                        <spring:message code="issue.list.title"/>
                    </h6>
                </div>
            </div>
            <div class="card-body px-0 pb-2">
                <!-- Search Form -->
                <form action="<c:url value='/issues' />" method="get" class="mx-4">
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
                                    <option value=""><spring:message code="all.issue.severity"/></option>
                                    <c:forEach items="${severities}" var="se">
                                        <option value="${se}" ${severity == se ? 'selected' : ''}>
                                            <spring:message code="severity.${se.toLowerCase()}"/>
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


                <form id="bulkActionForm" action="<c:url value='/issues/bulk-action' />" method="post">

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
                                    <a href="<c:url value='/issues?sort=id&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="issue.id"/>
                                        <i class="material-icons">${param.sort eq 'id' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <spring:message code="device"/>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-3">
                                    <a href="<c:url value='/issues?sort=title&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="issue.title"/>
                                        <i class="material-icons">${param.sort eq 'title' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/issues?sort=description&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="issue.description"/>
                                        <i class="material-icons">${param.sort eq 'description' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/issues?sort=occurredAt&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="issue.occurredAt"/>
                                        <i class="material-icons">${param.sort eq 'occurredAt' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/issues?sort=cost&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="issue.cost"/>
                                        <i class="material-icons">${param.sort eq 'cost' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/issues?sort=done&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="issue.done"/>
                                        <i class="material-icons">${param.sort eq 'done' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <spring:message code="creator"/>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/issues?sort=note&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="note"/>
                                        <i class="material-icons">${param.sort eq 'note' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/issues?sort=severity&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="issue.severity"/>
                                        <i class="material-icons">${param.sort eq 'severity' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/issues?sort=createdAt&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="created.at"/>
                                        <i class="material-icons">${param.sort eq 'createdAt' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/issues?sort=updatedAt&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="updated.at"/>
                                        <i class="material-icons">${param.sort eq 'updatedAt' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-secondary opacity-7">
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${issues}" var="issue">
                                <tr>
                                    <td class="ps-3">
                                        <input type="checkbox" name="selectedIds" value="${issue.id}"
                                               class="selectItem">
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${issue.id}</p>
                                    </td>
                                    <td>
                                        <p class="text-xs font-weight-bold mb-0">${issue.device.id}</p>
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${issue.title}</p>
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${issue.description}</p>
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${issue.occurredAt}</p>
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${issue.cost}</p>
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${issue.done}</p>
                                    </td>
                                    <td>
                                        <p class="text-xs font-weight-bold mb-0">${issue.user.fullName}</p>
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${issue.note}</p>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${issue.severity == 'LOW'}">
                                                <p class="text-xs font-weight-bold mb-0 text-success">${issue.severity}</p>
                                            </c:when>
                                            <c:when test="${issue.severity == 'MEDIUM'}">
                                                <p class="text-xs font-weight-bold mb-0 text-warning">${issue.severity}</p>
                                            </c:when>
                                            <c:otherwise>
                                                <p class="text-xs font-weight-bold mb-0 text-danger">${issue.severity}</p>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <p class="text-xs font-weight-bold mb-0">${issue.createdAt}</p>
                                    </td>
                                    <td>
                                        <p class="text-xs font-weight-bold mb-0">${issue.updatedAt}</p>
                                    </td>
                                    <td class="align-middle">
                                        <a href="<c:url value='/issues/${issue.id}/update' />"
                                           class="text-dark font-weight-bold text-xs" data-toggle="tooltip"
                                           data-original-title="<spring:message code='edit' />">
                                            <spring:message code="edit"/>
                                        </a>
                                        |
                                        <a href="<c:url value='/issues/${issue.id}/delete' />"
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


