<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>预备党员转正</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#minutesFormDetail").load("${ctx}/partyManage/regular/sPmMinutes/detail");
            $("#pmssFormDetail").load("${ctx}/partyManage/regular/sPmPmsS/detail");
            $("#pbFormDetail").load("${ctx}/partyManage/regular/sPmPb/detail");
            $("#pcFormDetail").load("${ctx}/partyManage/regular/sPmPc/detail");
            $("#talkingFormDetail").load("${ctx}/partyManage/regular/sPmTalking/detail");
        });
    </script>
</head>
<body>
<div class="control-group">
    <ul id="myTab" class="nav nav-tabs">
        <li class="active"><a href="#minutesFormDetail" data-toggle="tab">支部大会会议记录</a></li>
        <li><a href="#pmssFormDetail" data-toggle="tab">预备党员转正表决汇总表</a></li>
        <li><a href="#pbFormDetail" data-toggle="tab">党总支会议记录</a></li>
        <li><a href="#pcFormDetail" data-toggle="tab">党委会议记录</a></li>
        <li><a href="#talkingFormDetail" data-toggle="tab">党员转正谈话纪实</a></li>
    </ul>
    <div id="myTabContent" class="tab-content">
        <div class="tab-pane fade in active" id="minutesFormDetail">

        </div>
        <div class="tab-pane fade" id="pmssFormDetail">

        </div>
        <div class="tab-pane fade" id="pbFormDetail">

        </div>
        <div class="tab-pane fade" id="pcFormDetail">

        </div>
        <div class="tab-pane fade" id="talkingFormDetail">

        </div>
    </div>
</div>
</body>
</html>
