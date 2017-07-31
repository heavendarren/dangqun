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
		<li class="active"><a href="${ctx}/partyManage/sPmCheck/">表单列表</a></li>
		<li><a href="${ctx}/partyManage/sPmCheck/form">表单添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="sPmCheck" action="${ctx}/partyManage/sPmCheck/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<%-- <ul class="ul-form">
			<li><label>姓名：</label>
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<!-- <li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li> -->
		</ul> --%>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>姓名</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sPmCheck">
			<tr>
				<td><a href="${ctx}/partyManage/sPmCheck/form?id=${sPmCheck.id}">
					${sPmCheck.name}
				</a></td>
				<td>
					<fmt:formatDate value="${sPmCheck.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${sPmCheck.remarks}
				</td>
				<td>
    				<a href="${ctx}/partyManage/sPmCheck/form?id=${sPmCheck.id}">修改</a>
					<a href="${ctx}/partyManage/sPmCheck/delete?id=${sPmCheck.id}" onclick="return confirmx('确认要删除该表单吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>