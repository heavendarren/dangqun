
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>党员发展纪实列表</title>
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
		<c:if test="${stage == 0}">
		<li class="active"><a href="${ctx}/partyManage/DQRecord/record/?stage=0">入党申请列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/record/?stage=1">入党积极分子列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/record/?stage=2">党员发展对象列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/record/?stage=3">预备党员列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/record/?stage=4">党员转正列表</a></li>
		</c:if>
		
		<c:if test="${stage == 1}">
		<li><a href="${ctx}/partyManage/DQRecord/record/?stage=0">入党申请列表</a></li>
		<li class="active"><a href="${ctx}/partyManage/DQRecord/record/?stage=1">入党积极分子列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/record/?stage=2">党员发展对象列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/record/?stage=3">预备党员列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/record/?stage=4">党员转正列表</a></li>
		</c:if>
		
		<c:if test="${stage == 2}">
		<li><a href="${ctx}/partyManage/DQRecord/record/?stage=0">入党申请列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/record/?stage=1">入党积极分子列表</a></li>
		<li class="active"><a href="${ctx}/partyManage/DQRecord/record/?stage=2">党员发展对象列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/record/?stage=3">预备党员列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/record/?stage=4">党员转正列表</a></li>
		</c:if>
		
		<c:if test="${stage == 3}">
		<li><a href="${ctx}/partyManage/DQRecord/record/?stage=0">入党申请列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/record/?stage=1">入党积极分子列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/record/?stage=2">党员发展对象列表</a></li>
		<li class="active"><a href="${ctx}/partyManage/DQRecord/record/?stage=3">预备党员列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/record/?stage=4">党员转正列表</a></li>
		</c:if>
		
		<c:if test="${stage == 4}">
		<li><a href="${ctx}/partyManage/DQRecord/record/?stage=0">入党申请列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/record/?stage=1">入党积极分子列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/record/?stage=2">党员发展对象列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/record/?stage=3">预备党员列表</a></li>
		<li class="active"><a href="${ctx}/partyManage/DQRecord/record/?stage=4">党员转正列表</a></li>
		</c:if>
	</ul>
	
	<form:form id="searchForm" modelAttribute="testAudit" action="${ctx}/partyManage/DQRecord/find" method="post" 
class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>姓名：</label><input name="name" type="text"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
		<th style='text-align: center;'>序号</th>
		<th style='text-align: center;'>姓名</th>
		<th style='text-align: center;'>所属党组织</th>
		<th style='text-align: center;'>
			<c:if test="${stage == 0}">
				申请入党时间
			</c:if>
			<c:if test="${stage == 1}">
				确定积极分子时间
			</c:if>
			<c:if test="${stage == 2}">
				确定发展对象时间
			</c:if>
			<c:if test="${stage == 3}">
				确定预备党员时间
			</c:if>
			<c:if test="${stage == 4}">
				党员转正时间
			</c:if>
		</th>
		<th style='text-align: center;'>状态</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="record" varStatus="status">
			<tr>
				<td style='text-align: center;'>${status.index + 1}</td>
				<td style='text-align: center;'><a href="${ctx}/partyManage/DQRecord/schedule/?id=${record.id}">${record.name}</a></td>
				<td style='text-align: center;'>${record.orgName}</td>
				<td style='text-align: center;'>${record.applicationTime}</td>
				<td style='text-align: center;'>
					<c:if test="${record.state == '0'}">
						待办
					</c:if>
					<c:if test="${record.state == '1'}">
						进行中
					</c:if>
					<c:if test="${record.state == '2'}">
						终止
					</c:if>
					<c:if test="${(stage == 4) && (record.state == '3')}">
						完成
					</c:if>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>