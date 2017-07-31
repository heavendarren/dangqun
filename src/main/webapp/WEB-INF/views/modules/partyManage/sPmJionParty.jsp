<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>入党申请</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            <%--$("#BasicForm").load("${ctx}/partyManage/sPmBasic/form");--%>
            <%--$("#AppSituationForm").load("${ctx}/partyManage/sPmAppSituation/form");--%>
            <%--$("#WorkPostList").load("${ctx}/partyManage/sPmWorkPost/list");--%>
            <%--$("#JobresList").load("${ctx}/partyManage/sPmJobres/list");--%>
            <%--$("#EduDegreeList").load("${ctx}/partyManage/sPmEduDegree/list");--%>
            <%--$("#FamilyList").load("${ctx}/partyManage/sPmFamily/list");--%>
            <%--$("#TxdzForm").load("${ctx}/partyManage/sPmTxdz/form");--%>
            <%--$("#JszwList").load("${ctx}/partyManage/sPmJszw/list");--%>
            <%--$("#AppUploadForm").load("${ctx}/partyManage/sPmAppUpload/form");--%>
        });
    </script>
</head>
<body>
<ul id="myTab" class="nav nav-tabs">
    <li class="active"><a href="#BasicForm" data-toggle="tab">基本情况</a></li>
    <li><a href="#AppSituationForm" data-toggle="tab">入党申请人情况</a></li>
    <li><a href="#WorkPostList" data-toggle="tab">工作岗位</a></li>
    <li><a href="#JobresList" data-toggle="tab">工作简历</a></li>
    <li><a href="#EduDegreeList" data-toggle="tab">学位学历</a></li>
    <li><a href="#FamilyList" data-toggle="tab">家庭成员</a></li>
    <li><a href="#TxdzForm" data-toggle="tab">通讯地址</a></li>
    <li><a href="#JszwList" data-toggle="tab">技术职务</a></li>
    <li><a href="#AppUploadForm" data-toggle="tab">申请书上传</a></li>
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
    <iframe width="100%" height="1500px" frameborder="0" scrolling="auto" src="${ctx}/partyManage/sPmBasic/form"
            class="tab-pane fade in active" id="BasicForm">

    </iframe>
    <iframe width="100%" height="1500px" frameborder="0" scrolling="auto" src="${ctx}/partyManage/sPmAppSituation/form"
            class="tab-pane fade" id="AppSituationForm">

    </iframe>
    <iframe width="100%" height="1500px" frameborder="0" scrolling="auto" src="${ctx}/partyManage/sPmWorkPost/list"
            class="tab-pane fade" id="WorkPostList">

    </iframe>
    <iframe width="100%" height="1500px" frameborder="0" scrolling="auto" src="${ctx}/partyManage/sPmJobres/list"
            class="tab-pane fade" id="JobresList">

    </iframe>
    <iframe width="100%" height="1500px" frameborder="0" scrolling="auto" src="${ctx}/partyManage/sPmEduDegree/list"
            class="tab-pane fade" id="EduDegreeList">

    </iframe>
    <iframe width="100%" height="1500px" frameborder="0" scrolling="auto" src="${ctx}/partyManage/sPmFamily/list"
            class="tab-pane fade" id="FamilyList">

    </iframe>
    <iframe width="100%" height="1500px" frameborder="0" scrolling="auto" src="${ctx}/partyManage/sPmTxdz/form"
            class="tab-pane fade" id="TxdzForm">

    </iframe>
    <iframe width="100%" height="1500px" frameborder="0" scrolling="auto" src="${ctx}/partyManage/sPmJszw/list"
            class="tab-pane fade" id="JszwList">

    </iframe>
    <iframe width="100%" height="1500px" frameborder="0" scrolling="auto" src="${ctx}/partyManage/sPmAppUpload/form"
            class="tab-pane fade" id="AppUploadForm">

    </iframe>
</div>
<%--<input id="sPmBasicBtnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
</body>
</html>
