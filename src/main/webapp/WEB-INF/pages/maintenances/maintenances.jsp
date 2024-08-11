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
                        <spring:message code="maintenance.list.title"/>
                    </h6>
                </div>
            </div>
            <div class="card-body px-0 pb-2">
                <!-- Search Form -->
                <form action="<c:url value='/maintenances' />" method="get" class="mx-4">
                    <div class="row">
                        <div class="col-md-4 mb-1">
                            <div class="input-group input-group-outline">
                                <input type="text" name="q" id="search" value="${searchQuery}" class="form-control"
                                       placeholder="<spring:message code='search' />" aria-label="Search"/>
                            </div>
                        </div>
                        <div class="col-md-2 mb-3">
                            <div class="input-group input-group-outline">
                                <select name="type" id="maintenanceType" class="form-control">
                                    <option value=""><spring:message code="all.maintenance.type"/></option>
                                    <c:forEach items="${types}" var="t">
                                        <option value="${t}" ${type == t ? 'selected' : ''}>
                                            <spring:message code="maintenance.type.${t.toLowerCase()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-2 mb-3">
                            <div class="input-group input-group-outline">
                                <select name="recurrence" id="recurrenceType" class="form-control">
                                    <option value=""><spring:message code="all.maintenance.recurrenceType"/></option>
                                    <c:forEach items="${recurrences}" var="r">
                                        <option value="${r}" ${recurrence == r ? 'selected' : ''}>
                                            <spring:message code="maintenance.recurrence.type.${r.toLowerCase()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-2 mb-3">
                            <div class="input-group input-group-outline">
                                <select name="endrecurrence" id="endRecurrenceType" class="form-control">
                                    <option value=""><spring:message code="all.maintenance.endRecurrenceType"/></option>
                                    <c:forEach items="${endRecurrences}" var="er">
                                        <option value="${er}" ${endrecurrence == er ? 'selected' : ''}>
                                            <spring:message code="maintenance.endrecurrence.type.${er.toLowerCase()}"/>
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="col-md-2 mb-3">
                            <div class="input-group input-group-outline">
                                <select name="devicetype" id="deviceType" class="form-control">
                                    <option value=""><spring:message code="all.device.types"/></option>
                                    <c:forEach items="${deviceTypes}" var="dt">
                                        <option value="${dt.id}" ${devicetype == dt.id ? 'selected' : ''}>
                                                ${dt.name}
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


                <form id="bulkActionForm" action="<c:url value='/maintenances/bulk-action' />" method="post">

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
                                    <a href="<c:url value='/maintenances?sort=id&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="maintenance.id"/>
                                        <i class="material-icons">${param.sort eq 'id' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/maintenances?sort=summary&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="maintenance.summary"/>
                                        <i class="material-icons">${param.sort eq 'summary' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/maintenances?sort=description&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="maintenance.description"/>
                                        <i class="material-icons">${param.sort eq 'description' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/maintenances?sort=device&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="maintenance.device"/>
                                        <i class="material-icons">${param.sort eq 'device' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/maintenances?sort=link&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="maintenance.link"/>
                                        <i class="material-icons">${param.sort eq 'link' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/maintenances?sort=type&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="maintenance.type"/>
                                        <i class="material-icons">${param.sort eq 'type' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/maintenances?sort=allDay&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="maintenance.allDay"/>
                                        <i class="material-icons">${param.sort eq 'allDay' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
								<th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
									<a href="<c:url value='/maintenances?sort=startDateTime&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
										<spring:message code="maintenance.start"/>
										<i class="material-icons">${param.sort eq 'startDateTime' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
									</a>
								</th>
								<th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
									<a href="<c:url value='/maintenances?sort=endDateTime&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
										<spring:message code="maintenance.end"/>
										<i class="material-icons">${param.sort eq 'endDateTime' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
									</a>
								</th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/maintenances?sort=startDate&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="maintenance.start"/>
                                        <i class="material-icons">${param.sort eq 'startDate' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/maintenances?sort=endDate&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="maintenance.end"/>
                                        <i class="material-icons">${param.sort eq 'endDate' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/maintenances?sort=repeatEvery&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="maintenance.repeatEvery"/>
                                        <i class="material-icons">${param.sort eq 'repeatEvery' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/maintenances?sort=recurrenceType&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="maintenance.recurrenceType"/>
                                        <i class="material-icons">${param.sort eq 'recurrenceType' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/maintenances?sort=endRecurrenceType&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="maintenance.endRecurrenceType"/>
                                        <i class="material-icons">${param.sort eq 'endRecurrenceType' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/maintenances?sort=endDateRecurrence&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="maintenance.endDateRecurrence"/>
                                        <i class="material-icons">${param.sort eq 'endDateRecurrence' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/maintenances?sort=occurrenceCount&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="maintenance.occurrenceCount"/>
                                        <i class="material-icons">${param.sort eq 'occurrenceCount' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/maintenances?sort=createdAt&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="maintenance.createdAt"/>
                                        <i class="material-icons">${param.sort eq 'createdAt' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/maintenances?sort=updatedAt&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="maintenance.updatedAt"/>
                                        <i class="material-icons">${param.sort eq 'updatedAt' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-secondary opacity-7">
                                </th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${maintenances}" var="maintenance">
                                <tr>
                                    <td class="ps-3">
                                        <input type="checkbox" name="selectedIds" value="${maintenance.id}"
                                               class="selectItem">
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${maintenance.id}</p>
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${maintenance.summary}</p>
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${maintenance.description}</p>
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${maintenance.device.deviceCategory.name}</p>
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${maintenance.link}</p>
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${maintenance.type}</p>
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${maintenance.allDay}</p>
                                    </td>
									<td class="psg-3">
										<p class="text-xs font-weight-bold mb-0">${maintenance.startDateTime}</p>
									</td>
									<td class="psg-3">
										<p class="text-xs font-weight-bold mb-0">${maintenance.endDateTime}</p>
									</td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${maintenance.startDate}</p>
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${maintenance.endDate}</p>
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${maintenance.repeatEvery}</p>
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${maintenance.recurrenceType}</p>
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${maintenance.endRecurrenceType}</p>
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${maintenance.endDateRecurrence}</p>
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${maintenance.occurrenceCount}</p>
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${maintenance.createdAt}</p>
                                    </td>
                                    <td class="psg-3">
                                        <p class="text-xs font-weight-bold mb-0">${maintenance.updatedAt}</p>
                                    </td>
                                    <td class="align-middle">
                                        <a href="<c:url value='/maintenances/${maintenance.id}/update' />"
                                           class="text-dark font-weight-bold text-xs" data-toggle="tooltip"
                                           data-original-title="<spring:message code='edit' />">
                                            <spring:message code="edit"/>
                                        </a>
                                        |
                                        <a href="<c:url value='/maintenances/${maintenance.id}/delete' />"
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


