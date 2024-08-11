<%--
  Created by IntelliJ IDEA.
  User: tranlequocthong313
  Date: 06/08/2024
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
	<div class="form-container">
		<c:choose>
			<c:when test="${threadCategory.id != null}">
				<h2 class="form-heading mb-4"><spring:message code="update.threadCategory"/></h2>
				<c:url value='/thread-categories/${threadCategory.id}/update' var="action"/>
			</c:when>
			<c:otherwise>
				<h2 class="form-heading mb-4"><spring:message code="create.threadCategory"/></h2>
				<c:url value='/thread-categories/create' var="action"/>
			</c:otherwise>
		</c:choose>

		<form:form method="POST" modelAttribute="threadCategory" action="${action}" role="form"
				   enctype="multipart/form-data">
			<label class="form-label" for="name"><spring:message code="name"/></label>
			<div class="input-group input-group-outline mb-3">
				<form:input path="name" id="name" class="form-control"/>
				<form:errors path="name" cssClass="text-danger"/>
			</div>
			<label class="form-label" for="forumCategory"><spring:message
				code="forumCategory"/></label>
			<div class="input-group input-group-outline mb-3">
				<form:select path="forumCategory.id" id="forumCategory" class="form-control">
					<form:option value="">
						<spring:message code='forumCategory'/>
					</form:option>
					<form:options items="${forums}" itemValue="id" itemLabel="name"/>
				</form:select>
				<form:errors path="forumCategory.id" cssClass="text-danger"/>
			</div>
			<div class="text-center">
				<c:choose>
					<c:when test="${threadCategory.id != null}">
						<button type="submit" class="btn btn-lg bg-gradient-primary w-100 mt-4 mb-2">
							<spring:message code="update"/>
						</button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn btn-lg bg-gradient-primary w-100 mt-4 mb-2">
							<spring:message code="create"/>
						</button>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="text-center">
				<a href="<c:url value='/thread-categories' />" class="btn btn-secondary w-100 mt-2 mb-0">
					<spring:message code="cancel"/>
				</a>
			</div>
		</form:form>
	</div>
</div>

