<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
    <head>
        <title>积极分子推荐</title>
        <meta name="decorator" content="default"/>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#MrsDetail").load("${ctx}/partyManage/sPmMrs/detail");
            	$("#PrfDetail").load("${ctx}/partyManage/sPmPrf/detail");
            	$("#PmsDetail").load("${ctx}/partyManage/sPmPms/detail");
            	$("#ContentDetail").load("${ctx}/partyManage/sPmContent/detail");
            });
        </script>
    </head>
<body>
	<ul id="myTab" class="nav nav-tabs">
	    <li class="active"><a href="#MrsDetail" data-toggle="tab">群众推荐表</a></li>
	    <li><a href="#PrfDetail" data-toggle="tab">群团推荐表</a></li>
	    <li><a href="#PmsDetail" data-toggle="tab">党员推荐表</a></li>
	    <li><a href="#ContentDetail" data-toggle="tab">支部大会记录</a></li>
	</ul>
    
    <div id="myTabContent" class="tab-content">
	    <div class="tab-pane fade in active" id="MrsDetail">
	
	    </div>
	    <div class="tab-pane fade" id="PrfDetail">
	
	    </div>
	    <div class="tab-pane fade" id="PmsDetail">
	
	    </div>
	   <div class="tab-pane fade" id="ContentDetail">
	
	    </div>
	    
    </div>
</body>
</html>
