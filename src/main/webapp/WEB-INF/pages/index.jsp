<%--
    Document   : index
    Created on : Jul 31, 2024, 5:44:58?PM
    Author     : tranlequocthong313
--%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="row">
    <!-- Total Devices -->
    <div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
        <div class="card">
            <div class="card-header p-3 pt-2">
                <div class="icon icon-lg icon-shape bg-gradient-dark shadow-dark text-center border-radius-xl mt-n4 position-absolute">
                    <i class="material-icons opacity-10">storage</i>
                </div>
                <div class="text-end pt-1">
                    <p class="text-sm mb-0 text-capitalize">
                        <spring:message code="dashboard.totalDevices"/>
                    </p>
                    <h4 class="mb-0">${totalDevices}</h4>
                </div>
            </div>
        </div>
    </div>
    <!-- Devices with Issues -->
    <div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
        <div class="card">
            <div class="card-header p-3 pt-2">
                <div class="icon icon-lg icon-shape bg-gradient-primary shadow-primary text-center border-radius-xl mt-n4 position-absolute">
                    <i class="material-icons opacity-10">bug_report</i>
                </div>
                <div class="text-end pt-1">
                    <p class="text-sm mb-0 text-capitalize">
                        <spring:message code="dashboard.totalIssues"/>
                    </p>
                    <h4 class="mb-0">${totalIssues}</h4>
                </div>
            </div>
        </div>
    </div>
    <!-- Total Repair Cost -->
    <div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
        <div class="card">
            <div class="card-header p-3 pt-2">
                <div class="icon icon-lg icon-shape bg-gradient-success shadow-success text-center border-radius-xl mt-n4 position-absolute">
                    <i class="material-icons opacity-10">monetization_on</i>
                </div>
                <div class="text-end pt-1">
                    <p class="text-sm mb-0 text-capitalize">
                        <spring:message code="dashboard.totalRepairCost"/>
                    </p>
                    <h4 class="mb-0">${totalRepairCost} VND</h4>
                </div>
            </div>
        </div>
    </div>
    <!-- Total Users -->
    <div class="col-xl-3 col-sm-6 mb-xl-0 mb-4">
        <div class="card">
            <div class="card-header p-3 pt-2">
                <div class="icon icon-lg icon-shape bg-gradient-info shadow-info text-center border-radius-xl mt-n4 position-absolute">
                    <i class="material-icons opacity-10">people</i>
                </div>
                <div class="text-end pt-1">
                    <p class="text-sm mb-0 text-capitalize">
                        <spring:message code="dashboard.totalUsers"/>
                    </p>
                    <h4 class="mb-0">${totalUsers}</h4>
                </div>
            </div>
        </div>
    </div>
</div>


<div class="row mt-4">
    <div class="col-lg-4 col-md-6 mt-4 mb-4">
        <div class="card z-index-2 ">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2 bg-transparent">
                <div class="bg-gradient-primary shadow-primary border-radius-lg py-3 pe-1">
                    <div class="chart">
                        <canvas id="chart-bars" class="chart-canvas" height="170"></canvas>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <h6 class="mb-0 "><spring:message code="issue"/></h6>
                <p class="text-sm "><spring:message code="dashboard.totalIssues.per.month"/></p>
                <hr class="dark horizontal">
                <div class="d-flex ">
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-4 col-md-6 mt-4 mb-4">
        <div class="card z-index-2  ">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2 bg-transparent">
                <div class="bg-gradient-success shadow-success border-radius-lg py-3 pe-1">
                    <div class="chart">
                        <canvas id="chart-line" class="chart-canvas" height="170"></canvas>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <h6 class="mb-0 "><spring:message code="repair"/></h6>
                <p class="text-sm "><spring:message code="dashboard.totalRepairCost.per.month"/></p>
                <hr class="dark horizontal">
                <div class="d-flex ">
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-4 mt-4 mb-3">
        <div class="card z-index-2 ">
            <div class="card-header p-0 position-relative mt-n4 mx-3 z-index-2 bg-transparent">
                <div class="bg-gradient-dark shadow-dark border-radius-lg py-3 pe-1">
                    <div class="chart">
                        <canvas id="chart-line-tasks" class="chart-canvas" height="170"></canvas>
                    </div>
                </div>
            </div>
            <div class="card-body">
                <h6 class="mb-0 "><spring:message code="device.status"/></h6>
                <p class="text-sm "><spring:message code="dashboard.statuses"/></p>
                <hr class="dark horizontal">
                <div class="d-flex ">
                </div>
            </div>
        </div>
    </div>
</div>
<div class="row mb-4">
    <div class="col-lg-8 col-md-6 mb-md-0 mb-4">
        <div class="card">
            <div class="card-header pb-0">
                <div class="row">
                    <div class="col-lg-6 col-7">
                        <h6>Projects</h6>
                        <p class="text-sm mb-0">
                            <i class="fa fa-check text-info" aria-hidden="true"></i>
                            <span class="font-weight-bold ms-1">30 done</span> this month
                        </p>
                    </div>
                    <div class="col-lg-6 col-5 my-auto text-end">
                        <div class="dropdown float-lg-end pe-4">
                            <a class="cursor-pointer" id="dropdownTable" data-bs-toggle="dropdown"
                               aria-expanded="false">
                                <i class="fa fa-ellipsis-v text-secondary"></i>
                            </a>
                            <ul class="dropdown-menu px-2 py-3 ms-sm-n4 ms-n5" aria-labelledby="dropdownTable">
                                <li><a class="dropdown-item border-radius-md" href="javascript:;">Action</a></li>
                                <li><a class="dropdown-item border-radius-md" href="javascript:;">Another action</a>
                                </li>
                                <li><a class="dropdown-item border-radius-md" href="javascript:;">Something else
                                    here</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <div class="card-body px-0 pb-2">
                <div class="table-responsive">
                    <table class="table align-items-center mb-0">
                        <thead>
                        <tr>
                            <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">Companies
                            </th>
                            <th class="text-uppercase text-secondary text-xxs font-weight-bolder opacity-7 ps-2">
                                Members
                            </th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                Budget
                            </th>
                            <th class="text-center text-uppercase text-secondary text-xxs font-weight-bolder opacity-7">
                                Completion
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr>
                            <td>
                                <div class="d-flex px-2 py-1">
                                    <div>
                                        <img src="./assets/img/small-logos/logo-xd.svg" class="avatar avatar-sm me-3"
                                             alt="xd">
                                    </div>
                                    <div class="d-flex flex-column justify-content-center">
                                        <h6 class="mb-0 text-sm">Material XD Version</h6>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="avatar-group mt-2">
                                    <a href="javascript:;" class="avatar avatar-xs rounded-circle"
                                       data-bs-toggle="tooltip" data-bs-placement="bottom" title="Ryan Tompson">
                                        <img src="./assets/img/team-1.jpg" alt="team1">
                                    </a>
                                    <a href="javascript:;" class="avatar avatar-xs rounded-circle"
                                       data-bs-toggle="tooltip" data-bs-placement="bottom" title="Romina Hadid">
                                        <img src="./assets/img/team-2.jpg" alt="team2">
                                    </a>
                                    <a href="javascript:;" class="avatar avatar-xs rounded-circle"
                                       data-bs-toggle="tooltip" data-bs-placement="bottom" title="Alexander Smith">
                                        <img src="./assets/img/team-3.jpg" alt="team3">
                                    </a>
                                    <a href="javascript:;" class="avatar avatar-xs rounded-circle"
                                       data-bs-toggle="tooltip" data-bs-placement="bottom" title="Jessica Doe">
                                        <img src="./assets/img/team-4.jpg" alt="team4">
                                    </a>
                                </div>
                            </td>
                            <td class="align-middle text-center text-sm">
                                <span class="text-xs font-weight-bold"> $14,000 </span>
                            </td>
                            <td class="align-middle">
                                <div class="progress-wrapper w-75 mx-auto">
                                    <div class="progress-info">
                                        <div class="progress-percentage">
                                            <span class="text-xs font-weight-bold">60%</span>
                                        </div>
                                    </div>
                                    <div class="progress">
                                        <div class="progress-bar bg-gradient-info w-60" role="progressbar"
                                             aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="d-flex px-2 py-1">
                                    <div>
                                        <img src="./assets/img/small-logos/logo-atlassian.svg"
                                             class="avatar avatar-sm me-3" alt="atlassian">
                                    </div>
                                    <div class="d-flex flex-column justify-content-center">
                                        <h6 class="mb-0 text-sm">Add Progress Track</h6>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="avatar-group mt-2">
                                    <a href="javascript:;" class="avatar avatar-xs rounded-circle"
                                       data-bs-toggle="tooltip" data-bs-placement="bottom" title="Romina Hadid">
                                        <img src="./assets/img/team-2.jpg" alt="team5">
                                    </a>
                                    <a href="javascript:;" class="avatar avatar-xs rounded-circle"
                                       data-bs-toggle="tooltip" data-bs-placement="bottom" title="Jessica Doe">
                                        <img src="./assets/img/team-4.jpg" alt="team6">
                                    </a>
                                </div>
                            </td>
                            <td class="align-middle text-center text-sm">
                                <span class="text-xs font-weight-bold"> $3,000 </span>
                            </td>
                            <td class="align-middle">
                                <div class="progress-wrapper w-75 mx-auto">
                                    <div class="progress-info">
                                        <div class="progress-percentage">
                                            <span class="text-xs font-weight-bold">10%</span>
                                        </div>
                                    </div>
                                    <div class="progress">
                                        <div class="progress-bar bg-gradient-info w-10" role="progressbar"
                                             aria-valuenow="10" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="d-flex px-2 py-1">
                                    <div>
                                        <img src="./assets/img/small-logos/logo-slack.svg" class="avatar avatar-sm me-3"
                                             alt="team7">
                                    </div>
                                    <div class="d-flex flex-column justify-content-center">
                                        <h6 class="mb-0 text-sm">Fix Platform Errors</h6>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="avatar-group mt-2">
                                    <a href="javascript:;" class="avatar avatar-xs rounded-circle"
                                       data-bs-toggle="tooltip" data-bs-placement="bottom" title="Romina Hadid">
                                        <img src="./assets/img/team-3.jpg" alt="team8">
                                    </a>
                                    <a href="javascript:;" class="avatar avatar-xs rounded-circle"
                                       data-bs-toggle="tooltip" data-bs-placement="bottom" title="Jessica Doe">
                                        <img src="./assets/img/team-1.jpg" alt="team9">
                                    </a>
                                </div>
                            </td>
                            <td class="align-middle text-center text-sm">
                                <span class="text-xs font-weight-bold"> Not set </span>
                            </td>
                            <td class="align-middle">
                                <div class="progress-wrapper w-75 mx-auto">
                                    <div class="progress-info">
                                        <div class="progress-percentage">
                                            <span class="text-xs font-weight-bold">100%</span>
                                        </div>
                                    </div>
                                    <div class="progress">
                                        <div class="progress-bar bg-gradient-success w-100" role="progressbar"
                                             aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="d-flex px-2 py-1">
                                    <div>
                                        <img src="./assets/img/small-logos/logo-spotify.svg"
                                             class="avatar avatar-sm me-3" alt="spotify">
                                    </div>
                                    <div class="d-flex flex-column justify-content-center">
                                        <h6 class="mb-0 text-sm">Launch our Mobile App</h6>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="avatar-group mt-2">
                                    <a href="javascript:;" class="avatar avatar-xs rounded-circle"
                                       data-bs-toggle="tooltip" data-bs-placement="bottom" title="Ryan Tompson">
                                        <img src="./assets/img/team-4.jpg" alt="user1">
                                    </a>
                                    <a href="javascript:;" class="avatar avatar-xs rounded-circle"
                                       data-bs-toggle="tooltip" data-bs-placement="bottom" title="Romina Hadid">
                                        <img src="./assets/img/team-3.jpg" alt="user2">
                                    </a>
                                    <a href="javascript:;" class="avatar avatar-xs rounded-circle"
                                       data-bs-toggle="tooltip" data-bs-placement="bottom" title="Alexander Smith">
                                        <img src="./assets/img/team-4.jpg" alt="user3">
                                    </a>
                                    <a href="javascript:;" class="avatar avatar-xs rounded-circle"
                                       data-bs-toggle="tooltip" data-bs-placement="bottom" title="Jessica Doe">
                                        <img src="./assets/img/team-1.jpg" alt="user4">
                                    </a>
                                </div>
                            </td>
                            <td class="align-middle text-center text-sm">
                                <span class="text-xs font-weight-bold"> $20,500 </span>
                            </td>
                            <td class="align-middle">
                                <div class="progress-wrapper w-75 mx-auto">
                                    <div class="progress-info">
                                        <div class="progress-percentage">
                                            <span class="text-xs font-weight-bold">100%</span>
                                        </div>
                                    </div>
                                    <div class="progress">
                                        <div class="progress-bar bg-gradient-success w-100" role="progressbar"
                                             aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="d-flex px-2 py-1">
                                    <div>
                                        <img src="./assets/img/small-logos/logo-jira.svg" class="avatar avatar-sm me-3"
                                             alt="jira">
                                    </div>
                                    <div class="d-flex flex-column justify-content-center">
                                        <h6 class="mb-0 text-sm">Add the New Pricing Page</h6>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="avatar-group mt-2">
                                    <a href="javascript:;" class="avatar avatar-xs rounded-circle"
                                       data-bs-toggle="tooltip" data-bs-placement="bottom" title="Ryan Tompson">
                                        <img src="./assets/img/team-4.jpg" alt="user5">
                                    </a>
                                </div>
                            </td>
                            <td class="align-middle text-center text-sm">
                                <span class="text-xs font-weight-bold"> $500 </span>
                            </td>
                            <td class="align-middle">
                                <div class="progress-wrapper w-75 mx-auto">
                                    <div class="progress-info">
                                        <div class="progress-percentage">
                                            <span class="text-xs font-weight-bold">25%</span>
                                        </div>
                                    </div>
                                    <div class="progress">
                                        <div class="progress-bar bg-gradient-info w-25" role="progressbar"
                                             aria-valuenow="25" aria-valuemin="0" aria-valuemax="25"></div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <div class="d-flex px-2 py-1">
                                    <div>
                                        <img src="./assets/img/small-logos/logo-invision.svg"
                                             class="avatar avatar-sm me-3" alt="invision">
                                    </div>
                                    <div class="d-flex flex-column justify-content-center">
                                        <h6 class="mb-0 text-sm">Redesign New Online Shop</h6>
                                    </div>
                                </div>
                            </td>
                            <td>
                                <div class="avatar-group mt-2">
                                    <a href="javascript:;" class="avatar avatar-xs rounded-circle"
                                       data-bs-toggle="tooltip" data-bs-placement="bottom" title="Ryan Tompson">
                                        <img src="./assets/img/team-1.jpg" alt="user6">
                                    </a>
                                    <a href="javascript:;" class="avatar avatar-xs rounded-circle"
                                       data-bs-toggle="tooltip" data-bs-placement="bottom" title="Jessica Doe">
                                        <img src="./assets/img/team-4.jpg" alt="user7">
                                    </a>
                                </div>
                            </td>
                            <td class="align-middle text-center text-sm">
                                <span class="text-xs font-weight-bold"> $2,000 </span>
                            </td>
                            <td class="align-middle">
                                <div class="progress-wrapper w-75 mx-auto">
                                    <div class="progress-info">
                                        <div class="progress-percentage">
                                            <span class="text-xs font-weight-bold">40%</span>
                                        </div>
                                    </div>
                                    <div class="progress">
                                        <div class="progress-bar bg-gradient-info w-40" role="progressbar"
                                             aria-valuenow="40" aria-valuemin="0" aria-valuemax="40"></div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
    <div class="col-lg-4 col-md-6">
        <div class="card h-100">
            <div class="card-header pb-0">
                <h6><spring:message code="dashboard.activity.log"/></h6>
                <p class="text-sm">
                    <i class="fa fa-arrow-up text-success" aria-hidden="true"></i>
                    <span class="font-weight-bold"><spring:message code="dashboard.admin.activity"/></span>
                </p>
            </div>
            <div class="card-body p-3">
                <div class="timeline timeline-one-side">
                    <c:forEach items="${activityLogs}" var="log">
                        <div class="timeline-block mb-3">
                        <span class="timeline-step">
                            <img src="${log.user.avatar}" alt="profile_image" class="w-80 border-radius-sm shadow-sm">
                        </span>
                            <div class="timeline-content">
                                <h6 class="text-dark text-sm font-weight-bold mb-0">${log.log}</h6>
                                <p class="text-secondary font-weight-bold text-xs mt-1 mb-0">${log.createdAt}</p>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="./assets/js/plugins/chartjs.min.js"></script>
<script>
    var months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"]
    var statuses = ["OPERATING", "ERROR", "MAINTENANCE", "SOLD"]
    var issueData = new Array(months.length).fill(0);
    var costData = new Array(months.length).fill(0);
    var statusData = new Array(statuses.length).fill(0);

    <c:forEach items="${issuePerMonth}" var="i">
    issueData[${i[0]}-1] = ${i[1]};
    </c:forEach>

    <c:forEach items="${repairCostPerMonth}" var="r">
    costData[${r[0]}-1] = ${r[1]};
    </c:forEach>

    <c:forEach items="${deviceStatuses}" var="s">
    statusData[statuses.indexOf('${s[0]}')] = ${s[1]};
    </c:forEach>
    var ctx = document.getElementById("chart-bars").getContext("2d");

    new Chart(ctx, {
        type: "bar",
        data: {
            labels: months,
            datasets: [{
                label: "Issues",
                tension: 0.4,
                borderWidth: 0,
                borderRadius: 4,
                borderSkipped: false,
                backgroundColor: "rgba(255, 255, 255, .8)",
                data: issueData,
                maxBarThickness: 6
            },],
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
                        suggestedMin: 0,
                        suggestedMax: 500,
                        beginAtZero: true,
                        padding: 10,
                        font: {
                            size: 14,
                            weight: 300,
                            family: "Roboto",
                            style: 'normal',
                            lineHeight: 2
                        },
                        color: "#fff"
                    },
                },
                x: {
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
            },
        },
    });

    var ctx2 = document.getElementById("chart-line").getContext("2d");

    new Chart(ctx2, {
        type: "line",
        data: {
            labels: months,
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

    var ctx3 = document.getElementById("chart-line-tasks").getContext("2d");

    new Chart(ctx3, {
        type: "line",
        data: {
            labels: statuses,
            datasets: [{
                label: "Device Status",
                tension: 0,
                borderWidth: 0,
                pointRadius: 5,
                pointBackgroundColor: "rgba(255, 255, 255, .8)",
                pointBorderColor: "transparent",
                borderColor: "rgba(255, 255, 255, .8)",
                borderWidth: 4,
                backgroundColor: "transparent",
                fill: true,
                data: statusData,
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
</script>
