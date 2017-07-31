<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
    <head>
        <title>推荐发展对象</title>
        <meta name="decorator" content="default"/>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#MassForm").load("${ctx}/partyManage/sPmMass/form");
            	$("#OpinionForm").load("${ctx}/partyManage/sPmOpinion/form");
            	$("#MinutesForm").load("${ctx}/partyManage/sPmMinutes/form");
            });
        </script>
    </head>
<body>
	<ul id="myTab" class="nav nav-tabs">
	    <li class="active"><a href="#MassForm" data-toggle="tab">群众意见表</a></li>
	    <li><a href="#OpinionForm" data-toggle="tab">党员意见表</a></li>
	    <li><a href="#MinutesForm" data-toggle="tab">支部大会记录</a></li>
	</ul>
    
    <div id="myTabContent" class="tab-content">
	    <div class="tab-pane fade in active" id="MassForm">
	
	    </div>
	    <div class="tab-pane fade" id="OpinionForm">
	
	    </div>
	    <div class="tab-pane fade" id="MinutesForm">
	
	    </div>
	    
    </div>
</body>
</html>
