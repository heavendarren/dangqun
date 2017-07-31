<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>会议记录管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/partyManage/sPmMinutes/">会议记录列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="sPmMinutes" action="${ctx}/partyManage/sPmMinutes/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>编号</th>
				<th>会议议题</th>
				<th>主持人</th>
				<th>记录人</th>
				<th>参加人员名单</th>
				<th>缺勤人员名单</th>
				<th>时间</th>
				<th>地点</th>
				<th>会议纪要</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sPmMinutes" varStatus="status">
			<tr>
				<td>
					${status.index + 1}
				</td>
				<td>
					${sPmMinutes.conTop}
				</td>
				<td>
					${sPmMinutes.hostName}
				</td>
				<td>
					${sPmMinutes.noteTakerName}
				</td>
				<td>
					${sPmMinutes.attenList}
				</td>
				<td>
					${sPmMinutes.absList}
				</td>
				<td>
					${sPmMinutes.conTime}
				</td>
				<td>
					${sPmMinutes.place}
				</td>
				<td>
					${sPmMinutes.meetMin}
				</td>
				<td><a href="${ctx}/partyManage/sPmMinutes/form?id=${sPmMinutes.id}">
					<fmt:formatDate value="${sPmMinutes.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${sPmMinutes.remarks}
				</td>
				<td>
    				<a href="${ctx}/partyManage/sPmMinutes/form?id=${sPmMinutes.id}">修改</a>
					<a href="${ctx}/partyManage/sPmMinutes/delete?id=${sPmMinutes.id}" onclick="return confirmx('确认要删除该会议记录吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>