<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
    <head>
        <title>政治审查</title>
        <meta name="decorator" content="default"/>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#AutoForm").load("${ctx}/partyManage/sPmAuto/form");
            	$("#ProveForm").load("${ctx}/partyManage/sPmProve/form");
            	$("#ReportForm").load("${ctx}/partyManage/sPmReport/form");
            	$("#CheckForm").load("${ctx}/partyManage/sPmCheck/form");
            });
        </script>
    </head>
<body>
	<ul id="myTab" class="nav nav-tabs">
	    <li class="active"><a href="#AutoForm" data-toggle="tab">自传表</a></li>
	    <li><a href="#ProveForm" data-toggle="tab">证明材料表</a></li>
	    <li><a href="#ReportForm" data-toggle="tab">政审报告表</a></li>
	    <li><a href="#CheckForm" data-toggle="tab">发展对象政审表</a></li>
	</ul>
    
    <div id="myTabContent" class="tab-content">
	    <div class="tab-pane fade in active" id="AutoForm">
	
	    </div>
	    <div class="tab-pane fade" id="ProveForm">
	
	    </div>
	    <div class="tab-pane fade" id="ReportForm">
	
	    </div>
	   <div class="tab-pane fade" id="CheckForm">
	
	    </div>
	    
    </div>
</body>
</html>
