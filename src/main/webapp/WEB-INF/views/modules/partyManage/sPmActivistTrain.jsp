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
                <%--$("#ContactSettingsList").load("${ctx}/partyManage/sPmContactSettings/list");--%>
                <%--$("#ThoughtReportList").load("${ctx}/partyManage/sPmThoughtReport/list");--%>
                <%--$("#InvestigateSituationList").load("${ctx}/partyManage/sPmInvestigateSituation/list");--%>
                $("#DevelopmentOptionForm").load("${ctx}/partyManage/sPmDevelopmentOption/form");
            }
            /* else if (appRole == 1) {*/
            <%--$("#ThoughtReportList1").load("${ctx}/partyManage/sPmThoughtReport/list");--%>
//            }
        });
    </script>
</head>
<body>
<input hidden id="appRole" value="${appRole}"/>
<c:choose>
    <c:when test="${appRole == 0}">
        <ul id="myTab" class="nav nav-tabs">
            <li class="active"><a href="#ContactSettingsList" data-toggle="tab">培养联系人设置</a></li>
            <li><a href="#ThoughtReportList" data-toggle="tab">思想汇报</a></li>
            <li><a href="#InvestigateSituationList" data-toggle="tab">培养考察情况</a></li>
            <li><a href="#DevelopmentOptionForm" data-toggle="tab">确定为发展对象意见</a></li>
            <c:choose>
                <c:when test="${recordId == null }">
                    <div style="float: right;">
                        <a href="${ctx}/partyManage/DQRecord/record" role="button" class="btn">返回</a>
                    </div>
                </c:when>
                <c:otherwise>
                    <div style="float: right;">
                        <a href="${ctx}/partyManage/DQRecord/schedule/?id=${recordId}" role="button" class="btn">返回</a>
                    </div>
                </c:otherwise>
            </c:choose>
        </ul>
        <div id="myTabContent" class="tab-content">
            <iframe width="100%" height="1500px" frameborder="0" scrolling="auto"
                    src="${ctx}/partyManage/sPmContactSettings/list" class="tab-pane fade in active"
                    id="ContactSettingsList">

            </iframe>
            <iframe width="100%" height="1500px" frameborder="0" scrolling="auto"
                    src="${ctx}/partyManage/sPmThoughtReport/list" class="tab-pane fade" id="ThoughtReportList">

            </iframe>
            <iframe width="100%" height="1500px" frameborder="0" scrolling="auto"
                    src="${ctx}/partyManage/sPmInvestigateSituation/list" class="tab-pane fade"
                    id="InvestigateSituationList">

            </iframe>
            <div class="tab-pane fade" id="DevelopmentOptionForm">

            </div>
        </div>
    </c:when>
    <c:otherwise>
        <c:if test="${appRole == 1}">
            <ul id="myTab1" class="nav nav-tabs">
                <li class="active"><a href="#ThoughtReportList1" data-toggle="tab">思想汇报</a></li>
                <c:choose>
                    <c:when test="${recordId == null }">
                        <div style="float: right;">
                            <a href="${ctx}/partyManage/DQRecord/record" role="button" class="btn">返回</a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <div style="float: right;">
                            <a href="${ctx}/partyManage/DQRecord/schedule/?id=${recordId}" role="button"
                               class="btn">返回</a>
                        </div>
                    </c:otherwise>
                </c:choose>
            </ul>
            <div id="myTabContent1" class="tab-content">
                <iframe width="100%" height="1500px" frameborder="0" scrolling="auto"
                        src="${ctx}/partyManage/sPmThoughtReport/list" class="tab-pane fade in active"
                        id="ThoughtReportList1">

                </iframe>
            </div>
        </c:if>
    </c:otherwise>
</c:choose>

</body>
</html>
