<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
    <head>
        <title>推荐发展对象</title>
        <meta name="decorator" content="default"/>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#MassDetail").load("${ctx}/partyManage/sPmMass/detail");
            	$("#OpinionDetail").load("${ctx}/partyManage/sPmOpinion/detail");
            	$("#MinutesFormDetail").load("${ctx}/partyManage/sPmMinutes/detail");
            });
        </script>
    </head>
<body>
	<ul id="myTab" class="nav nav-tabs">
	    <li class="active"><a href="#MassDetail" data-toggle="tab">群众意见表</a></li>
	    <li><a href="#OpinionDetail" data-toggle="tab">党员意见表</a></li>
	    <li><a href="#MinutesFormDetail" data-toggle="tab">支部大会记录</a></li>
	</ul>
    
    <div id="myTabContent" class="tab-content">
	    <div class="tab-pane fade in active" id="MassDetail">
	
	    </div>
	    <div class="tab-pane fade" id="OpinionDetail">
	
	    </div>
	    <div class="tab-pane fade" id="MinutesFormDetail">
	
	    </div>
	    
    </div>
</body>
</html>
