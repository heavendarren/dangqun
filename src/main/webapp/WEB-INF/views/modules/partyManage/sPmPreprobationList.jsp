<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>预备党员转正预审</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#introduceOpinion").load("${ctx}/partyManage/sPmIntroduceOpinion/form");
            $("#meetingMinutes").load("${ctx}/partyManage/sPmMeetingMinutes/form");
            $("#massOpinion").load("${ctx}/partyManage/sPmMassOpinion/form");
            $("#formalMember").load("${ctx}/partyManage/sPmFormalMember/form");
            $("#positivePre").load("${ctx}/partyManage/sPmPositivePre/form");
            $("#auditChecklist").load("${ctx}/partyManage/sPmAuditChecklist/form");
        });
    </script>
</head>
<body>
<a href="#" role="button" class="btn">会议小助手</a>
<div class="control-group">
    <ul id="myTab" class="nav nav-tabs">
        <li class="active"><a href="#introduceOpinion" data-toggle="tab">入党介绍人和党小组意见表</a></li>
        <li><a href="#meetingMinutes" data-toggle="tab">征求党员意见会议纪要</a></li>
        <li><a href="#massOpinion" data-toggle="tab">征求群众意见会议纪要</a></li>
        <li><a href="#formalMember" data-toggle="tab">按期转为中共正式党员的报告</a></li>
        <li><a href="#positivePre" data-toggle="tab">转正工作预审表</a></li>
        <li><a href="#auditChecklist" data-toggle="tab">转正审查审核</a></li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <iframe width="100%" height="1000px" frameborder="0" scrolling="auto" src="${ctx}/partyManage/sPmIntroduceOpinion/form" class="tab-pane fade in active" id="introduceOpinion">
        </iframe>
        <iframe width="100%" height="1000px" frameborder="0" scrolling="auto" src="${ctx}/partyManage/sPmMeetingMinutes/form" class="tab-pane fade in active" id="meetingMinutes">
        </iframe>
       	<iframe width="100%" height="1000px" frameborder="0" scrolling="auto" src="${ctx}/partyManage/sPmMassOpinion/form" class="tab-pane fade in active" id="massOpinion">
        </iframe>
        <iframe width="100%" height="1000px" frameborder="0" scrolling="auto" src="${ctx}/partyManage/sPmFormalMember/form" class="tab-pane fade in active" id="formalMember">
        </iframe>
        <iframe width="100%" height="1000px" frameborder="0" scrolling="auto" src="${ctx}/partyManage/sPmPositivePre/form" class="tab-pane fade in active" id="positivePre">
        </iframe>
       <iframe width="100%" height="1000px" frameborder="0" scrolling="auto" src="${ctx}/partyManage/sPmAuditChecklist/form" class="tab-pane fade in active" id="auditChecklist">
        </iframe>
    </div>
</div>
</body>
</html>
