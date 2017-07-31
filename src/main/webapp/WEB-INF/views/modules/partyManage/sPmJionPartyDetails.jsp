<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>入党申请</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#BasicFormDetails").load("${ctx}/partyManage/sPmBasic/detail");
            $("#AppSituationFormDetails").load("${ctx}/partyManage/sPmAppSituation/detail");
            $("#WorkPostListDetails").load("${ctx}/partyManage/sPmWorkPost/detail");
            $("#JobresListDetails").load("${ctx}/partyManage/sPmJobres/detail");
            $("#EduDegreeListDetails").load("${ctx}/partyManage/sPmEduDegree/detail");
            $("#FamilyListDetails").load("${ctx}/partyManage/sPmFamily/detail");
            $("#TxdzFormDetails").load("${ctx}/partyManage/sPmTxdz/detail");
            $("#JszwListDetails").load("${ctx}/partyManage/sPmJszw/detail");
            $("#AppUploadFormDetails").load("${ctx}/partyManage/sPmAppUpload/detail");
        });
    </script>
</head>
<body>
<ul id="myTab" class="nav nav-tabs">
    <li class="active"><a href="#BasicFormDetails" data-toggle="tab">基本情况</a></li>
    <li><a href="#AppSituationFormDetails" data-toggle="tab">入党申请人情况</a></li>
    <li><a href="#WorkPostListDetails" data-toggle="tab">工作岗位</a></li>
    <li><a href="#JobresListDetails" data-toggle="tab">工作简历</a></li>
    <li><a href="#EduDegreeListDetails" data-toggle="tab">学位学历</a></li>
    <li><a href="#FamilyListDetails" data-toggle="tab">家庭成员</a></li>
    <li><a href="#TxdzFormDetails" data-toggle="tab">通讯地址</a></li>
    <li><a href="#JszwListDetails" data-toggle="tab">技术职务</a></li>
    <li><a href="#AppUploadFormDetails" data-toggle="tab">申请书上传</a></li>
</ul>
<div id="myTabContent" class="tab-content">
    <div class="tab-pane fade in active" id="BasicFormDetails">

    </div>
    <div class="tab-pane fade" id="AppSituationFormDetails">

    </div>
    <div class="tab-pane fade" id="WorkPostListDetails">

    </div>
    <div class="tab-pane fade" id="JobresListDetails">

    </div>
    <div class="tab-pane fade" id="EduDegreeListDetails">

    </div>
    <div class="tab-pane fade" id="FamilyListDetails">

    </div>
    <div class="tab-pane fade" id="TxdzFormDetails">

    </div>
    <div class="tab-pane fade" id="JszwListDetails">

    </div>
    <div class="tab-pane fade" id="AppUploadFormDetails">

    </div>
</div>
</body>
</html>
