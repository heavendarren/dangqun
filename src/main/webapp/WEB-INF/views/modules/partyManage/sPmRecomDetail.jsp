<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
    <head>
        <title>推荐发展对象</title>
        <meta name="decorator" content="default"/>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#MassDetail").load("${ctx}/partyManage/sPmMass/detail");
            	$("#OpinDetail").load("${ctx}/partyManage/sPmOpinion/detail");
            });
        </script>
    </head>
<body>
	<ul id="myTab" class="nav nav-tabs">
	    <li class="active"><a href="#MassDetail" data-toggle="tab">群众意见</a></li>
	    <li><a href="#OpinDetail" data-toggle="tab">党员意见</a></li>
	</ul>
    
    <div id="myTabContent" class="tab-content">
	    
	    <div class="tab-pane fade in active" id="MassDetail">
	
	    </div>
	    <div class="tab-pane fade" id="OpinDetail">
	
	    </div>
	    
    </div>
</body>
</html>
