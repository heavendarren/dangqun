<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>宣誓活动纪实管理</title>
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
		<li class="active"><a href="${ctx}/入党宣誓/sPmOath/">宣誓活动纪实列表</a></li>
		<li><a href="${ctx}/入党宣誓/sPmOath/form">宣誓活动纪实添加</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="sPmOath" action="${ctx}/入党宣誓/sPmOath/" method="post" class="breadcrumb form-search">
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
				<th>更新时间</th>
				<th>备注信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sPmOath">
			<tr>
				<td><a href="${ctx}/入党宣誓/sPmOath/form?id=${sPmOath.id}">
					<fmt:formatDate value="${sPmOath.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${sPmOath.remarks}
				</td>
				<td>
    				<a href="${ctx}/入党宣誓/sPmOath/form?id=${sPmOath.id}">修改</a>
					<a href="${ctx}/入党宣誓/sPmOath/delete?id=${sPmOath.id}" onclick="return confirmx('确认要删除该宣誓活动纪实吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>