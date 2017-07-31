<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>发展对象审核备案管理</title>
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
		<c:forEach items="${page.list}" var="sPmExamineRecord">
			<tr>
				<td><a href="${ctx}/partyManage/sPmExamineRecord/form?id=${sPmExamineRecord.id}">
					<fmt:formatDate value="${sPmExamineRecord.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${sPmExamineRecord.remarks}
				</td>
				<td>
    				<a href="${ctx}/partyManage/sPmExamineRecord/form?id=${sPmExamineRecord.id}">修改</a>
					<a href="${ctx}/partyManage/sPmExamineRecord/delete?id=${sPmExamineRecord.id}" onclick="return confirmx('确认要删除该发展对象审核备案吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>