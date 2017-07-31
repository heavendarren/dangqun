<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>预备党员转正预审</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
		   $(document).ready(function () {
		       $("#introduceOpinion").load("${ctx}/partyManage/sPmIntroduceOpinion/list");
		       $("#meetingMinutes").load("${ctx}/partyManage/sPmMeetingMinutes/list");
		       $("#massOpinion").load("${ctx}/partyManage/sPmMassOpinion/list");
		       $("#formalMember").load("${ctx}/partyManage/sPmFormalMember/list");
		       $("#positivePre").load("${ctx}/partyManage/sPmPositivePre/list");
		       $("#auditChecklist").load("${ctx}/partyManage/sPmAuditChecklist/list");
		   });
    </script>
</head>
<body>
<div class="control-group">
    <ul id="myTab" class="nav nav-tabs">
        <li class="active"><a href="#introduceOpinion" data-toggle="tab">入党介绍人和党小组意见</a></li>
        <li><a href="#meetingMinutes" data-toggle="tab">征求党员意见会议纪要</a></li>
        <li><a href="#massOpinion" data-toggle="tab">征求群众意见会议纪要</a></li>
        <li><a href="#formalMember" data-toggle="tab">按期转为中共正式党员的报告</a></li>
        <li><a href="#positivePre" data-toggle="tab">转正工作预审表</a></li>
        <li><a href="#auditChecklist" data-toggle="tab">转正审查审核</a></li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="introduceOpinion">
        </div>
        <div class="tab-pane fade" id="meetingMinutes">
        </div>
        <div class="tab-pane fade" id="massOpinion">
        </div>
        <div class="tab-pane fade" id="formalMember">
        </div>
        <div class="tab-pane fade" id="positivePre">
        </div>
         <div class="tab-pane fade" id="auditChecklist">
        </div>
    </div>
</div>
</body>
</html>