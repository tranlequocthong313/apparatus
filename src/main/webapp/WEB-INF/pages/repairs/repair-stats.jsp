<%--
  Created by IntelliJ IDEA.
  User: tranlequocthong313
  Date: 06/08/2024
  Time: 17:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="row">
	<div class="col-12">
		<div class="col-lg-4 col-md-6">
			<div class="nav-wrapper position-relative start-0">
				<ul class="nav bg-gradient-dark border-radius-lg nav-fill p-1" role="tablist">
					<c:forEach items="${statsCategories}" var="statsCategory">
						<li class="nav-item" onclick="tab('/repairs${statsCategory.link}')">
							<a class="nav-link border-radius-lg text-white ${path == statsCategory.link ? 'active' : ''}"
							   href="<c:url value='/repairs${statsCategory.link}' />" role="tab" data-bs-toggle="tab"
							>
								<div class="text-white text-center me-2 d-flex align-items-center justify-content-center">
									<i class="material-icons text-lg position-relative">${statsCategory.icon}</i>
									<span class="ms-1"><spring:message code="${statsCategory.title}"/></span>
								</div>
							</a>
						</li>
					</c:forEach>
				</ul>
			</div>
		</div>
		<div class="card my-4">
			<div class="card-body px-0 pb-2">
				<!-- Search Form -->
				<form action="<c:url value='/repairs${path}' />" method="get" class="mx-4">
					<div class="row">
						<div class="col-md-6 mb-1">
							<div class="input-group input-group-outline">
								<input type="text" name="q" id="search" value="${searchQuery}" class="form-control"
									   placeholder="<spring:message code='device.id' />" aria-label="Search"/>
							</div>
						</div>
						<c:if test="${path == '/device-category/stats'}">
							<div class="col-md-4 mb-3">
								<div class="input-group input-group-outline">
									<select name="category" id="category" class="form-control">
										<option value="">-- <spring:message code="all.device.categories"/> --</option>
										<c:forEach items="${deviceCategories}" var="c">
											<option value="${c.id}" ${category == c.id ? 'selected' : ''}>
													${c.name}
											</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</c:if>
						<c:if test="${path == '/device-type/stats'}">
							<div class="col-md-4 mb-3">
								<div class="input-group input-group-outline">
									<select name="type" id="type" class="form-control">
										<option value="">-- <spring:message code="all.device.types"/> --</option>
										<c:forEach items="${types}" var="t">
											<option value="${t.id}" ${type == t.id ? 'selected' : ''}>
													${t.name}
											</option>
										</c:forEach>
									</select>
								</div>
							</div>
						</c:if>
						<div class="col-md-2 mb-3">
							<div class="input-group input-group-outline">
								<input id="month" class="form-control" type="number" name="month" min="1" max="12" value="${month}"
									   placeholder="<spring:message code="month" />"/>
							</div>
						</div>

						<div class="col-md-2 mb-3">
							<div class="input-group input-group-outline">
								<input id="year" class="form-control" type="number" name="year" min="1900" max="2100" value="${year}"
									   placeholder="<spring:message code="year" />"/>
							</div>
						</div>
						<div class="col-md-2 mb-3">
							<button class="btn btn-primary w-100" type="submit">
								<spring:message code="stats"/>...
							</button>
						</div>
					</div>
				</form>
			</div>
		</div>
		<!-- Statistics Section -->
		<div class="row">
			<div class="col-sm-6">
				<!-- Cost Chart -->
				<div class="card my-4">
					<div class="card-header">
						<h5><spring:message code="cost"/></h5>
					</div>
					<div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2 bg-transparent">
						<div class="bg-gradient-primary shadow-success border-radius-lg py-3 pe-1">
							<div class="chart">
								<canvas id="costChart" class="chart-canvas" height="170"></canvas>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<!-- Repaired By Table -->
				<div class="card my-4">
					<div class="card-body px-0 pb-2">
						<div class="table-responsive p-0">
							<table class="table align-items-center mb-0">
								<thead>
								<tr>
									<th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-3">
										<spring:message code="day"/>
									</th>
									<th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
										<spring:message code="cost"/>
									</th>
								</tr>
								</thead>
								<tbody>
								<c:forEach items="${costs}" var="cost">
									<tr>
										<td class="ps-3">
											<p class="text-xs font-weight-bold mb-0">${cost[0]}</p>
										</td>
										<td>
											<p class="text-xs font-weight-bold mb-0">${cost[1]}</p>
										</td>
									</tr>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<!-- Repaired By Chart -->
				<div class="card my-4">
					<div class="card-header">
						<h5><spring:message code="repairedBy"/></h5>
					</div>
					<div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2 bg-transparent">
						<div class="bg-gradient-success shadow-dark border-radius-lg py-3 pe-1">
							<div class="chart">
								<canvas id="repairedByChart" class="chart-canvas" height="170"></canvas>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-sm-6">
				<!-- Repaired By Table -->
				<div class="card my-4">
					<div class="card-body px-0 pb-2">
						<div class="table-responsive p-0">
							<table class="table align-items-center mb-0">
								<thead>
								<tr>
									<th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-3">
										<spring:message code="repairedBy"/>
									</th>
									<th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
										<spring:message code="count"/>
									</th>
								</tr>
								</thead>
								<tbody>
								<c:forEach items="${repairedBys}" var="repairedBy">
									<c:if test="${repairedBy[0] != null}">
										<tr>
											<td class="ps-3">
												<p class="text-xs font-weight-bold mb-0">${repairedBy[0]}</p>
											</td>
											<td>
												<p class="text-xs font-weight-bold mb-0">${repairedBy[1]}</p>
											</td>
										</tr>
									</c:if>
								</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<script src="${pageContext.request.contextPath}/assets/js/plugins/chartjs.min.js"></script>

<script>
	const costLabels = generateDaysArray();
	const costData = new Array(costLabels.length).fill(0)
	const repairedByLabels = ["INTERNAL", "EXTERNAL"]
	const repairedBysData = new Array(repairedByLabels.length).fill(0)

	<c:forEach items="${costs}" var="c">
	costData[${c[0]}-1] = ${c[1]};
	</c:forEach>

	<c:forEach items="${repairedBys}" var="r">
	repairedBysData[repairedByLabels.indexOf('${r[0]}')] = ${r[1]};
	</c:forEach>

	console.log(costData)
	console.log(repairedBysData)

	const ctxCost = document.getElementById('costChart').getContext('2d');
	new Chart(ctxCost, {
		type: "line",
		data: {
			labels: costLabels,
			datasets: [{
				label: "Repair Cost",
				tension: 0,
				borderWidth: 0,
				pointRadius: 5,
				pointBackgroundColor: "rgba(255, 255, 255, .8)",
				pointBorderColor: "transparent",
				borderColor: "rgba(255, 255, 255, .8)",
				borderColor: "rgba(255, 255, 255, .8)",
				borderWidth: 4,
				backgroundColor: "transparent",
				fill: true,
				data: costData,
				maxBarThickness: 6

			}],
		},
		options: {
			responsive: true,
			maintainAspectRatio: false,
			plugins: {
				legend: {
					display: false,
				}
			},
			interaction: {
				intersect: false,
				mode: 'index',
			},
			scales: {
				y: {
					grid: {
						drawBorder: false,
						display: true,
						drawOnChartArea: true,
						drawTicks: false,
						borderDash: [5, 5],
						color: 'rgba(255, 255, 255, .2)'
					},
					ticks: {
						display: true,
						color: '#f8f9fa',
						padding: 10,
						font: {
							size: 14,
							weight: 300,
							family: "Roboto",
							style: 'normal',
							lineHeight: 2
						},
					}
				},
				x: {
					grid: {
						drawBorder: false,
						display: false,
						drawOnChartArea: false,
						drawTicks: false,
						borderDash: [5, 5]
					},
					ticks: {
						display: true,
						color: '#f8f9fa',
						padding: 10,
						font: {
							size: 14,
							weight: 300,
							family: "Roboto",
							style: 'normal',
							lineHeight: 2
						},
					}
				},
			},
		},
	});


	const ctxRepairedBy = document.getElementById('repairedByChart').getContext('2d');
	new Chart(ctxRepairedBy, {
		type: "line",
		data: {
			labels: repairedByLabels,
			datasets: [{
				label: "Repaired By",
				tension: 0,
				borderWidth: 0,
				pointRadius: 5,
				pointBackgroundColor: "rgba(255, 255, 255, .8)",
				pointBorderColor: "transparent",
				borderColor: "rgba(255, 255, 255, .8)",
				borderWidth: 4,
				backgroundColor: "transparent",
				fill: true,
				data: repairedBysData,
				maxBarThickness: 6

			}],
		},
		options: {
			responsive: true,
			maintainAspectRatio: false,
			plugins: {
				legend: {
					display: false,
				}
			},
			interaction: {
				intersect: false,
				mode: 'index',
			},
			scales: {
				y: {
					grid: {
						drawBorder: false,
						display: true,
						drawOnChartArea: true,
						drawTicks: false,
						borderDash: [5, 5],
						color: 'rgba(255, 255, 255, .2)'
					},
					ticks: {
						display: true,
						padding: 10,
						color: '#f8f9fa',
						font: {
							size: 14,
							weight: 300,
							family: "Roboto",
							style: 'normal',
							lineHeight: 2
						},
					}
				},
				x: {
					grid: {
						drawBorder: false,
						display: false,
						drawOnChartArea: false,
						drawTicks: false,
						borderDash: [5, 5]
					},
					ticks: {
						display: true,
						color: '#f8f9fa',
						padding: 10,
						font: {
							size: 14,
							weight: 300,
							family: "Roboto",
							style: 'normal',
							lineHeight: 2
						},
					}
				},
			},
		},
	});


	function setDefaultMonthYear() {
		const now = new Date();
		const year = now.getFullYear();
		const month = now.getMonth() + 1;

		if (document.getElementById('month').value === "") {
			document.getElementById('month').value = String(month).padStart(2, '0');
		}
		if (document.getElementById('year').value === "") {
			document.getElementById('year').value = year;
		}
	}

	window.addEventListener('DOMContentLoaded', setDefaultMonthYear);

	const tab = (link) => {
		console.log("hello" + link)
		window.location.href = link
	}

	function getDaysInCurrentMonth() {
		const today = new Date();
		const currentYear = today.getFullYear();
		const currentMonth = today.getMonth();

		const firstDayNextMonth = new Date(currentYear, currentMonth + 1, 1);

		const lastDayCurrentMonth = new Date(firstDayNextMonth - 1);

		return lastDayCurrentMonth.getDate();
	}

	function generateDaysArray() {
		const daysInMonth = getDaysInCurrentMonth();
		const daysArray = [];

		for (let day = 1; day <= daysInMonth; day++) {
			daysArray.push(day);
		}

		return daysArray;
	}
</script>
