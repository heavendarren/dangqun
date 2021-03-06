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
		<li><a href="${ctx}/partyManage/sPmOpinion/list">表单添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="sPmOpinion" action="${ctx}/partyManage/sPmOpinion/" method="post" class="breadcrumb form-search">
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
				<th>备注信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sPmOpinion">
			<tr>
				<td><a href="${ctx}/partyManage/sPmOpinion/form?id=${sPmOpinion.id}">
					${sPmOpinion.remarks}
				</a></td>
				<%-- <shiro:hasPermission name="partyManage:sPmOpinion:edit"></shiro:hasPermission> --%>
				<td>
    				<a href="${ctx}/partyManage/sPmOpinion/form?id=${sPmOpinion.id}">修改</a>
					<a href="${ctx}/partyManage/sPmOpinion/delete?id=${sPmOpinion.id}" onclick="return confirmx('确认要删除该表单吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>