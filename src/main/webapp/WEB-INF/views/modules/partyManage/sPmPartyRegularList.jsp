<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>预备党员转正</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#minutesForm").load("${ctx}/partyManage/regular/sPmMinutes/form");
            <%--$("#pmssForm").load("${ctx}/partyManage/regular/sPmPmsS/form");--%>
            <%--$("#pbForm").load("${ctx}/partyManage/regular/sPmPb/form");--%>
            <%--$("#pcForm").load("${ctx}/partyManage/regular/sPmPc/form");--%>
            <%--$("#talkingForm").load("${ctx}/partyManage/regular/sPmTalking/form");--%>
        });
    </script>
</head>
<body>
<a href="#" role="button" class="btn">会议小助手</a>
<div class="control-group">
    <ul id="myTab" class="nav nav-tabs">
        <li class="active"><a href="#minutesForm" data-toggle="tab">支部大会会议记录</a></li>
        <li><a href="#pmssForm" data-toggle="tab">预备党员转正表决汇总表</a></li>
        <li><a href="#pbForm" data-toggle="tab">党总支会议记录</a></li>
        <li><a href="#pcForm" data-toggle="tab">党委会议记录</a></li>
        <li><a href="#talkingForm" data-toggle="tab">党员转正谈话纪实</a></li>
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
        <div class="tab-pane fade in active" id="minutesForm">

        </div>
        <iframe width="100%" height="1500px" frameborder="0" scrolling="auto"
                src="${ctx}/partyManage/regular/sPmPmsS/form" class="tab-pane fade" id="pmssForm">

        </iframe>
        <iframe width="100%" height="1500px" frameborder="0" scrolling="auto"
                src="${ctx}/partyManage/regular/sPmPb/form" class="tab-pane fade" id="pbForm">

        </iframe>
        <iframe width="100%" height="1500px" frameborder="0" scrolling="auto"
                src="${ctx}/partyManage/regular/sPmPc/form" class="tab-pane fade" id="pcForm">

        </iframe>
        <iframe width="100%" height="1500px" frameborder="0" scrolling="auto"
                src="${ctx}/partyManage/regular/sPmTalking/form" class="tab-pane fade" id="talkingForm">

        </iframe>
    </div>
</div>
</body>
</html>
