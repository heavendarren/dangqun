<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
    <head>
        <title>政治审查</title>
        <meta name="decorator" content="default"/>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#AutoDetail").load("${ctx}/partyManage/sPmAuto/detail");
            	$("#ProveDetail").load("${ctx}/partyManage/sPmProve/detail");
            	$("#ReportDetail").load("${ctx}/partyManage/sPmReport/detail");
            	$("#CheckDetail").load("${ctx}/partyManage/sPmCheck/detail");
            });
        </script>
    </head>
<body>
	<ul id="myTab" class="nav nav-tabs">
	    <li class="active"><a href="#AutoDetail" data-toggle="tab">自传表</a></li>
	    <li><a href="#ProveDetail" data-toggle="tab">证明材料表</a></li>
	    <li><a href="#ReportDetail" data-toggle="tab">政审报告表</a></li>
	    <li><a href="#CheckDetail" data-toggle="tab">发展对象政审表</a></li>
	</ul>
    
    <div id="myTabContent" class="tab-content">
	    <div class="tab-pane fade in active" id="AutoDetail">
	
	    </div>
	    <div class="tab-pane fade" id="ProveDetail">
	
	    </div>
	    <div class="tab-pane fade" id="ReportDetail">
	
	    </div>
	   <div class="tab-pane fade" id="CheckDetail">
	
	    </div>
	    
    </div>
</body>
</html>
