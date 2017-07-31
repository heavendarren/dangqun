<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>


<html>
    <head>
        <title>预备党员接收讨论</title>
        <meta name="decorator" content="default"/>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#PmssForm").load("${ctx}/partyManage/sPmPmsS/form"); 
            	$("#MinutessForm").load("${ctx}/partyManage/sPmMinutesS/form");
            	$("#PbForm").load("${ctx}/partyManage/sPmPb/form");
            	$("#PcForm").load("${ctx}/partyManage/sPmPc/form");
            	$("#TalkingsForm").load("${ctx}/partyManage/sPmTalkingS/form");
            });
        </script>
    </head>
<body>
	<ul id="myTab" class="nav nav-tabs">
	    <li class="active"><a href="#PmssForm" data-toggle="tab">投票汇总表</a></li>
	    <li><a href="#MinutessForm" data-toggle="tab">支部大会记录表</a></li>
	    <li><a href="#PbForm" data-toggle="tab">党总支会议记录</a></li>
	    <li><a href="#PcForm" data-toggle="tab">党委会议记录表</a></li>
	    <li><a href="#TalkingsForm" data-toggle="tab">谈话记录表</a></li>
	</ul>
    
    <div id="myTabContent" class="tab-content">
	    <div class="tab-pane fade in active" id="PmssForm">
	
	    </div>
	    <div class="tab-pane fade" id="MinutessForm">
	
	    </div>
	    <div class="tab-pane fade" id="PbForm">
	
	    </div>
	    <div class="tab-pane fade" id="PcForm">
	
	    </div>
	    <div class="tab-pane fade" id="TalkingsForm">
	
	    </div>
    </div>
</body>
</html>
