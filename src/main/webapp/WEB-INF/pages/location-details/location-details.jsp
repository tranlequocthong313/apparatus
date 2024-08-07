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
                        <spring:message code="location.details.title"/>
                    </h6>
                    <a class="btn bg-gradient-dark mb-0 mx-2" href="<c:url value='/location-details/create' />">
                        <i class="material-icons text-sm">add</i>&nbsp;&nbsp;
                        <spring:message code="add.new.location.detail"/>
                    </a>
                </div>
            </div>
            <div class="card-body px-0 pb-2">
                <!-- Search Form -->
                <form action="<c:url value='/location-details' />" method="get" class="mx-4">
                    <div class="row">
                        <div class="col-md-6 mb-1">
                            <div class="input-group input-group-outline">
                                <input type="text" name="q" id="search" value="${searchQuery}" class="form-control"
                                       placeholder="<spring:message code='search' />" aria-label="Search"/>
                            </div>
                        </div>
                        <div class="col-md-4 mb-3">
                            <div class="input-group input-group-outline">
                                <select name="location" id="location" class="form-control">
                                    <option value=""><spring:message code="all.locations"/></option>
                                    <c:forEach items="${locations}" var="location">
                                        <option value="${location.id}" ${locationParam == location.id ? 'selected' : ''}>
                                                ${location.building}
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
                <form id="bulkActionForm" action="<c:url value='/location-details/bulk-action' />" method="post">
                    <div class="mx-4 d-flex  mb-1">
                        <div class="col-md-2">
                            <div class="input-group input-group-outline">
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
                                    <a href="<c:url value='/location-details?sort=id&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="id"/>
                                        <i class="material-icons">${param.sort eq 'id' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                    <a href="<c:url value='/location-details?sort=floor&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="floor"/>
                                        <i class="material-icons">${param.sort eq 'floor' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                    <a href="<c:url value='/location-details?sort=room&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="room"/>
                                        <i class="material-icons">${param.sort eq 'room' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                    <a href="<c:url value='/location-details?sort=note&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="note"/>
                                        <i class="material-icons">${param.sort eq 'note' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                    <a href="<c:url value='/location-details?sort=type&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="location"/>
                                        <i class="material-icons">${param.sort eq 'type' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                    <a href="<c:url value='/location-details?sort=createdAt&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="created.at"/>
                                        <i class="material-icons">${param.sort eq 'createdAt' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                    <a href="<c:url value='/location-details?sort=updatedAt&direction=${param.direction eq \'asc\' ? \'desc\' : \'asc\'}' />">
                                        <spring:message code="updated.at"/>
                                        <i class="material-icons">${param.sort eq 'updatedAt' && param.direction eq 'asc' ? 'arrow_upward' : 'arrow_downward'}</i>
                                    </a>
                                </th>
                                <th class="text-secondary opacity-7"></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${locationDetails}" var="locationDetail">
                                <tr>
                                    <td class="ps-3">
                                        <input type="checkbox" name="selectedIds" value="${locationDetail.id}"
                                               class="selectItem">
                                    </td>
                                    <td class="ps-3">
                                        <p class="text-xs font-weight-bold mb-0">${locationDetail.id}</p>
                                    </td>
                                    <td>
                                        <p class="text-xs font-weight-bold mb-0">${locationDetail.floor}</p>
                                    </td>
                                    <td>
                                        <p class="text-xs font-weight-bold mb-0">${locationDetail.room}</p>
                                    </td>
                                    <td>
                                        <p class="text-xs font-weight-bold mb-0">${locationDetail.note}</p>
                                    </td>
                                    <td>
                                        <p class="text-xs font-weight-bold mb-0">${locationDetail.location.building}</p>
                                    </td>
                                    <td>
                                        <p class="text-xs font-weight-bold mb-0">${locationDetail.createdAt}</p>
                                    </td>
                                    <td>
                                        <p class="text-xs font-weight-bold mb-0">${locationDetail.updatedAt}</p>
                                    </td>
                                    <td class="align-middle">
                                        <a href="<c:url value='/location-details/${locationDetail.id}/update' />"
                                           class="text-dark font-weight-bold text-xs" data-toggle="tooltip"
                                           data-original-title="<spring:message code='edit' />">
                                            <spring:message code="edit"/>
                                        </a>
                                        |
                                        <a href="<c:url value='/location-details/${locationDetail.id}/delete' />"
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
                           href="<c:url value='/location-details?page=${currentPage - 1}&location=${param.location}'/>"
                           aria-label="Previous">
                            <span aria-hidden="true">&laquo;</span>
                        </a>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${totalPages}" var="page">
                    <li class="page-item ${page == currentPage ? 'active' : ''}">
                        <a class="page-link"
                           href="<c:url value='/location-details?page=${page}&location=${param.location}'/>">${page}</a>
                    </li>
                </c:forEach>
                <c:if test="${currentPage < totalPages}">
                    <li class="page-item">
                        <a class="page-link"
                           href="<c:url value='/location-details?page=${currentPage + 1}&location=${param.location}'/>"
                           aria-label="Next">
                            <span aria-hidden="true">&raquo;</span>
                        </a>
                    </li>
                </c:if>
            </ul>
        </div>
    </div>
</div>

<script>
    document.addEventListener('DOMContentLoaded', function () {
        const selectAllCheckbox = document.getElementById('selectAll');
        const itemCheckboxes = document.querySelectorAll('.selectItem');
        const bulkActionSelect = document.getElementById('bulkActionSelect');
        const executeBulkActionButton = document.getElementById('executeBulkAction');

        selectAllCheckbox.addEventListener('change', function () {
            itemCheckboxes.forEach(checkbox => checkbox.checked = selectAllCheckbox.checked);
            executeBulkActionButton.disabled = !selectAllCheckbox.checked;
        });

        itemCheckboxes.forEach(checkbox => {
            checkbox.addEventListener('change', function () {
                const anyChecked = Array.from(itemCheckboxes).some(checkbox => checkbox.checked);
                executeBulkActionButton.disabled = !anyChecked;
            });
        });

        bulkActionSelect.addEventListener('change', function () {
            executeBulkActionButton.disabled = !bulkActionSelect.value;
        });
    });
</script>

