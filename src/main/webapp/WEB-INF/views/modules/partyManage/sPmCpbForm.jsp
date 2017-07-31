<%--
  Created by IntelliJ IDEA.
  User: zhc
  Date: 2017/4/17
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
    <head>
        <title>Title</title>
        <meta name="decorator" content="default"/>
        <script type="text/javascript">
            $(document).ready(function() {
                //loadPage();
                $("#sPmAuto").load("sPmAuto/form");
                $("#sPmProve").load("sPmProve/form");
                $("#sPmReport").load("sPmReport/form");
                $("#sPmCheck").load("sPmCheck/form");
            });
        </script>
    </head>
<body>
    <ul class="nav nav-tabs" role="tablist">
        <li role="presentation" class="active"><a href="#sPmAuto" role="tab" data-toggle="tab">自传</a></li>
        <li role="presentation"><a href="#sPmProve" role="tab" data-toggle="tab">证明材料</a></li>
        <li role="presentation"><a href="#sPmReport" role="tab" data-toggle="tab">政审报告</a></li>
        <li role="presentation"><a href="#sPmCheck" role="tab" data-toggle="tab">发展对象政审表</a></li>
    </ul>
    <div class="tab-content">
        <div role="tabpanel" class="tab-pane active" id="sPmAuto">
            <%-- <iframe width="100%" height="100%" src="${ctx}/partyManage/sPmMrs/form"></iframe> --%>
        </div>
        <div role="tabpanel" class="tab-pane" id="sPmProve">
			<%-- <iframe width="100%" height="100%" src="${ctx}/partyManage/sPmPrf/form"></iframe>  --%>       
		</div>
        <div role="tabpanel" class="tab-pane" id="sPmReport">
            <%--<iframe width="100%" height="100%" src="${ctx}/partyManage/jionparty/sPmWorkPost/list"></iframe>--%>
        </div>
        <div role="tabpanel" class="tab-pane" id="sPmCheck">
            <%--<iframe width="100%" height="100%" src="${ctx}/partyManage/jionparty/sPmJobres/form"></iframe>--%>
        </div>
    </div>
</body>
</html>
