<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>积极分子教育培养</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {

            var appRole = $("#appRole").val();
            if (appRole == 0) {
                $("#ContactSettingsDetail").load("${ctx}/partyManage/sPmContactSettings/detail");
                $("#ThoughtReportDetail").load("${ctx}/partyManage/sPmThoughtReport/details");
                $("#InvestigateSituationDetail").load("${ctx}/partyManage/sPmInvestigateSituation/details");
                $("#DevelopmentOptionDetail").load("${ctx}/partyManage/sPmDevelopmentOption/detail");
            } else if (appRole == 1) {
                $("#ThoughtReportDetail1").load("${ctx}/partyManage/sPmThoughtReport/details");
            }
        });
    </script>
</head>
<body>
<input hidden id="appRole" value="${appRole}"/>
<c:choose>
    <c:when test="${appRole == 0}">
        <ul id="myTab" class="nav nav-tabs">
            <li class="active"><a href="#ContactSettingsDetail" data-toggle="tab">培养联系人设置</a></li>
            <li><a href="#ThoughtReportDetail" data-toggle="tab">思想汇报</a></li>
            <li><a href="#InvestigateSituationDetail" data-toggle="tab">培养考察情况</a></li>
            <li><a href="#DevelopmentOptionDetail" data-toggle="tab">确定为发展对象意见</a></li>
        </ul>
        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="ContactSettingsDetail">

            </div>
            <div class="tab-pane fade" id="ThoughtReportDetail">

            </div>
            <div class="tab-pane fade" id="InvestigateSituationDetail">

            </div>
            <div class="tab-pane fade" id="DevelopmentOptionDetail">

            </div>
        </div>
    </c:when>
    <c:otherwise>
        <c:if test="${appRole == 1}">
            <ul id="myTab1" class="nav nav-tabs">
                <li class="active"><a href="#ThoughtReportDetail1" data-toggle="tab">思想汇报</a></li>
            </ul>
            <div id="myTabContent1" class="tab-content">
                <div class="tab-pane fade in active" id="ThoughtReportDetail1">

                </div>
            </div>
        </c:if>
    </c:otherwise>
</c:choose>


</body>
</html>
