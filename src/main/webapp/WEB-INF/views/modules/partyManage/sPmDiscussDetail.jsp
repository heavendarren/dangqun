<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
    <head>
        <title>预备党员接收讨论</title>
        <meta name="decorator" content="default"/>
        <script type="text/javascript">
            $(document).ready(function() {
                $("#PmssDetail").load("${ctx}/partyManage/sPmPmsS/detail");
            	$("#MinutessDetail").load("${ctx}/partyManage/sPmMinutesS/detail");
            	$("#PbDetail").load("${ctx}/partyManage/sPmPb/detail");
            	$("#PcDetail").load("${ctx}/partyManage/sPmPc/detail");
            	$("#TalkingsDetail").load("${ctx}/partyManage/sPmTalkingS/detail");
            });
        </script>
    </head>
<body>
	<ul id="myTab" class="nav nav-tabs">
	    <li class="active"><a href="#PmssDetail" data-toggle="tab">投票汇总表</a></li>
	    <li><a href="#MinutessDetail" data-toggle="tab">支部大会记录表</a></li>
	    <li><a href=#PbDetail data-toggle="tab">党总支会议记录</a></li>
	    <li><a href="#PcDetail" data-toggle="tab">党委会议记录表</a></li>
	    <li><a href="#TalkingsDetail" data-toggle="tab">谈话记录表</a></li>
	</ul>
    
    <div id="myTabContent" class="tab-content">
	    <div class="tab-pane fade in active" id="PmssDetail">
	
	    </div>
	    <div class="tab-pane fade" id="MinutessDetail">
	
	    </div>
	   <div class="tab-pane fade" id="PbDetail">
	
	    </div>
	    <div class="tab-pane fade" id="PcDetail">
	
	    </div>
	    <div class="tab-pane fade" id="TalkingsDetail">
	
	    </div>
    </div>
</body>
</html>
