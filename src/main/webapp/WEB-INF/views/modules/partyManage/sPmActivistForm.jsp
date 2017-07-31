<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
    <head>
        <title>积极分子推荐</title>
        <meta name="decorator" content="default"/>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#MrsForm").load("${ctx}/partyManage/sPmMrs/form");
            	$("#PrfForm").load("${ctx}/partyManage/sPmPrf/form");
            	$("#PmsForm").load("${ctx}/partyManage/sPmPms/form");
            	$("#ContentForm").load("${ctx}/partyManage/sPmContent/form");
            });
        </script>
    </head>
<body>
	<ul id="myTab" class="nav nav-tabs">
	    <li class="active"><a href="#MrsForm" data-toggle="tab">群众推荐表</a></li>
	    <li><a href="#PrfForm" data-toggle="tab">群团推荐表</a></li>
	    <li><a href="#PmsForm" data-toggle="tab">党员推荐表</a></li>
	    <li><a href="#ContentForm" data-toggle="tab">支部大会记录</a></li>
	</ul>
    
    <div id="myTabContent" class="tab-content">
	    <div class="tab-pane fade in active" id="MrsForm">
	
	    </div>
	    <div class="tab-pane fade" id="PrfForm">
	
	    </div>
	    <div class="tab-pane fade" id="PmsForm">
	
	    </div>
	   <div class="tab-pane fade" id="ContentForm">
	
	    </div>
	    
    </div>
</body>
</html>
