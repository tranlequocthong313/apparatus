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
			<c:when test="${maintenance.id != null}">
				<h2 class="form-heading mb-4"><spring:message code="update.maintenance.category"/></h2>
				<c:url value='/maintenances/${maintenance.id}/update' var="action"/>
			</c:when>
			<c:otherwise>
				<h2 class="form-heading mb-4"><spring:message code="create.maintenance.category"/></h2>
				<c:url value='/maintenances/create' var="action"/>
			</c:otherwise>
		</c:choose>

		<form:form method="POST" modelAttribute="maintenance" action="${action}" role="form"
				   enctype="multipart/form-data">
			<label class="form-label" for="summary"><spring:message code="maintenance.summary"/></label>
			<div class="input-group input-group-outline mb-3">
				<form:input path="summary" id="summary" class="form-control"/>
				<form:errors path="summary" cssClass="text-danger"/>
			</div>
			<label class="form-label" for="description"><spring:message code="maintenance.description"/></label>
			<div class="input-group input-group-outline mb-3">
				<form:input path="description" id="description" class="form-control"/>
				<form:errors path="description" cssClass="text-danger"/>
			</div>
			<label class="form-label" for="link"><spring:message code="maintenance.link"/></label>
			<div class="input-group input-group-outline mb-3">
				<form:input path="link" id="link" class="form-control"/>
				<form:errors path="link" cssClass="text-danger"/>
			</div>
			<label class="form-label" for="device"><spring:message code="device"/></label>
			<c:if test="${maintenance.device != null}">
				<div class="input-group input-group-outline mb-3">
					<input value="${maintenance.device.id} - ${maintenance.device.deviceCategory.name}"
						   class="form-control"
						   id="device"
						   disabled type="text"/>
					<form:hidden path="device.id"/>
				</div>
			</c:if>
			<label class="form-label" for="type"><spring:message code="maintenance.type"/></label>
			<div class="input-group input-group-outline mb-3">
				<form:select path="type" id="type" class="form-control">
					<form:option value="">
						<spring:message code='maintenance.type'/>
					</form:option>
					<form:options items="${types}"/>
				</form:select>
				<form:errors path="type" cssClass="text-danger"/>
			</div>
			<label class="form-label"><spring:message code="maintenance.participant"/></label>
			<div class="form-group col-md-12">
				<div class="col-md-7">
					<form:select path="userSet" items="${users}" multiple="true" itemValue="id"
								 itemLabel="fullName" class="form-control input-sm"/>
					<div class="has-error">
						<form:errors path="userSet" class="help-inline"/>
					</div>
				</div>
			</div>
			<label class="form-label" for="image"><spring:message code="maintenance.image"/></label>
			<div class="input-group input-group-outline mb-3">
				<input type="file" name="img" id="image" class="form-control" accept="image/*"/>
				<form:errors path="image" cssClass="text-danger"/>
			</div>
			<!-- All Day Checkbox -->
			<div class="form-check form-switch d-flex align-items-center mb-3">
				<form:checkbox class="form-check-input" id="allDay"
							   name="allDay" path="allDay"/>
				<label class="form-check-label mb-0 ms-3" for="allDay"><spring:message
					code="maintenance.allDay"/></label>
			</div>

			<!-- Start Date -->
			<label class="form-label" for="startDate"><spring:message code="maintenance.start"/></label>
			<div class="input-group input-group-outline mb-3">
				<form:input path="startDate" id="startDate" class="form-control" type="date"/>
				<form:errors path="startDate" cssClass="text-danger"/>
			</div>
			<div class="input-group input-group-outline mb-3">
				<form:input path="startDateTime" id="startDateTime" class="form-control" type="datetime-local"/>
				<form:errors path="startDateTime" cssClass="text-danger"/>
			</div>

			<!-- End Date -->
			<label class="form-label" for="endDate"><spring:message code="maintenance.end"/></label>
			<div class="input-group input-group-outline mb-3">
				<form:input path="endDate" id="endDate" class="form-control" type="date"/>
				<form:errors path="endDate" cssClass="text-danger"/>
			</div>
			<div class="input-group input-group-outline mb-3">
				<form:input path="endDateTime" id="endDateTime" class="form-control" type="datetime-local"/>
				<form:errors path="endDateTime" cssClass="text-danger"/>
			</div>

			<!-- Recurrence Type -->
			<label class="form-label" for="recurrenceType"><spring:message code="maintenance.recurrenceType"/></label>
			<div class="input-group input-group-outline mb-3">
				<form:select path="recurrenceType" id="recurrenceType" class="form-control">
					<form:option value="">
						<spring:message code='maintenance.recurrenceType'/>
					</form:option>
					<form:options items="${recurrences}"/>
				</form:select>
				<form:errors path="recurrenceType" cssClass="text-danger"/>
			</div>

			<!-- Repeat Every -->
			<div id="repeatEveryDiv">
				<label class="form-label" for="repeatEvery"><spring:message code="maintenance.repeatEvery"/></label>
				<div class="input-group input-group-outline mb-3">
					<form:input path="repeatEvery" id="repeatEvery" class="form-control" type="number"/>
					<form:errors path="repeatEvery" cssClass="text-danger"/>
				</div>
			</div>

			<!-- Days of Week -->
			<div id="daysOfWeekDiv">
				<label class="form-label"><spring:message code="daysOfWeek.select"/></label>
				<div class="form-group col-md-12">
					<div class="col-md-7">
						<form:select path="daysOfWeekSet" items="${daysOfWeeks}" multiple="true" itemValue="id"
									 itemLabel="day" class="form-control input-sm"/>
						<div class="has-error">
							<form:errors path="daysOfWeekSet" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>

			<!-- End Recurrence Type -->
			<div id="endRecurrenceTypeDiv">
				<label class="form-label" for="endRecurrenceType"><spring:message
					code="maintenance.endRecurrenceType"/></label>
				<div class="input-group input-group-outline mb-3">
					<form:select path="endRecurrenceType" id="endRecurrenceType" class="form-control">
						<form:option value="">
							<spring:message code='maintenance.endRecurrenceType'/>
						</form:option>
						<form:options items="${endRecurrenceTypes}"/>
					</form:select>
					<form:errors path="endRecurrenceType" cssClass="text-danger"/>
				</div>
			</div>

			<!-- Occurrence Count -->
			<div id="occurrenceCountDiv">
				<label class="form-label" for="occurrenceCount"><spring:message
					code="maintenance.occurrenceCount"/></label>
				<div class="input-group input-group-outline mb-3">
					<form:input path="occurrenceCount" id="occurrenceCount" class="form-control" type="number"/>
					<form:errors path="occurrenceCount" cssClass="text-danger"/>
				</div>
			</div>

			<!-- End Date Recurrence -->
			<div id="endDateRecurrenceDiv">
				<label class="form-label" for="endDateRecurrence"><spring:message
					code="maintenance.endDateRecurrence"/></label>
				<div class="input-group input-group-outline mb-3">
					<form:input path="endDateRecurrence" id="endDateRecurrence" class="form-control" type="date"/>
					<form:errors path="endDateRecurrence" cssClass="text-danger"/>
				</div>
			</div>

			<div class="text-center">
				<c:choose>
					<c:when test="${maintenance.id != null}">
						<button type="submit" class="btn btn-lg bg-gradient-primary w-100 mt-4 mb-2">
							<spring:message code="update.maintenance"/>
						</button>
					</c:when>
					<c:otherwise>
						<button type="submit" class="btn btn-lg bg-gradient-primary w-100 mt-4 mb-2">
							<spring:message code="create.maintenance"/>
						</button>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="text-center">
				<a href="<c:url value='/maintenances' />" class="btn btn-secondary w-100 mt-2 mb-0">
					<spring:message code="cancel"/>
				</a>
			</div>
		</form:form>

	</div>
</div>

<script type="text/javascript">
	document.addEventListener("DOMContentLoaded", function () {
		const recurrenceTypeSelect = document.getElementById("recurrenceType");
		const endRecurrenceTypeSelect = document.getElementById("endRecurrenceType");
		const repeatEveryDiv = document.getElementById("repeatEveryDiv");
		const daysOfWeekDiv = document.getElementById("daysOfWeekDiv");
		const endRecurrenceTypeDiv = document.getElementById("endRecurrenceTypeDiv");
		const occurrenceCountDiv = document.getElementById("occurrenceCountDiv");
		const endDateRecurrenceDiv = document.getElementById("endDateRecurrenceDiv");
		const startDate = document.getElementById("startDate");
		const endDate = document.getElementById("endDate");
		const startDateTime = document.getElementById("startDateTime");
		const endDateTime = document.getElementById("endDateTime");
		const allDay = document.getElementById('allDay');

		function toggleDateTimeTimeFields() {
			startDate.style.display = 'none'
			endDate.style.display = 'none'
			startDateTime.style.display = 'none'
			endDateTime.style.display = 'none'
			if (allDay.checked) {
				startDate.style.display = 'block'
				endDate.style.display = 'block'
			} else {
				startDateTime.style.display = 'block'
				endDateTime.style.display = 'block'
			}
		}

		// Function to toggle recurrence fields based on recurrenceType
		function toggleRecurrenceFields() {
			const recurrenceType = recurrenceTypeSelect.value;
			// Hide all by default
			repeatEveryDiv.style.display = 'none';
			daysOfWeekDiv.style.display = 'none';
			endRecurrenceTypeDiv.style.display = 'none';
			occurrenceCountDiv.style.display = 'none';
			endDateRecurrenceDiv.style.display = 'none';

			if (recurrenceType === "DAILY" || recurrenceType === "MONTHLY" || recurrenceType === "YEARLY") {
				repeatEveryDiv.style.display = 'block';
				endRecurrenceTypeDiv.style.display = 'block';
			} else if (recurrenceType === "WEEKLY") {
				repeatEveryDiv.style.display = 'block';
				daysOfWeekDiv.style.display = 'block';
				endRecurrenceTypeDiv.style.display = 'block';
			}
		}

		function toggleEndRecurrenceFields() {
			const endRecurrenceType = endRecurrenceTypeSelect.value;
			if (endRecurrenceType === "ON_DATE") {
				endDateRecurrenceDiv.style.display = 'block';
				occurrenceCountDiv.style.display = 'none';
			} else if (endRecurrenceType === "AFTER_OCCURRENCES") {
				endDateRecurrenceDiv.style.display = 'none';
				occurrenceCountDiv.style.display = 'block';
			} else if (endRecurrenceType === "NEVER") {
				endDateRecurrenceDiv.style.display = 'none';
				occurrenceCountDiv.style.display = 'none';
			}
		}

		// Initial calls to set the correct display
		toggleEndRecurrenceFields()
		// toggleDateTimeInputs();
		toggleRecurrenceFields();
		toggleDateTimeTimeFields()

		// Event listeners
		allDay.addEventListener("change", toggleDateTimeTimeFields);
		recurrenceTypeSelect.addEventListener("change", toggleRecurrenceFields);
		endRecurrenceTypeSelect.addEventListener("change", toggleEndRecurrenceFields);
	});
</script>
