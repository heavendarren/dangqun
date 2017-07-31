<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>表单管理</title>
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
		<li class="active"><a href="${ctx}/partyManage/sPmTalkingS/">表单列表</a></li>
		<shiro:hasPermission name="partyManage:sPmTalkingS:edit"><li><a href="${ctx}/partyManage/sPmTalkingS/form">表单添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="sPmTalkingS" action="${ctx}/partyManage/sPmTalkingS/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<!-- <ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul> -->
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>更新时间</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sPmTalkingS">
			<tr>
				<td><a href="${ctx}/partyManage/sPmTalkingS/form?id=${sPmTalkingS.id}">
					<fmt:formatDate value="${sPmTalkingS.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${sPmTalkingS.remarks}
				</td>
				<td>
    				<a href="${ctx}/partyManage/sPmTalkingS/form?id=${sPmTalkingS.id}">修改</a>
					<a href="${ctx}/partyManage/sPmTalkingS/delete?id=${sPmTalkingS.id}" onclick="return confirmx('确认要删除该表单吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>