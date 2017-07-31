<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>拟接收预备党员的公示管理</title>
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
		<c:forEach items="${page.list}" var="sPmPrepPartyPublic">
			<tr>
				<td><a href="${ctx}/partyManage/sPmPrepPartyPublic/form?id=${sPmPrepPartyPublic.id}">
					<fmt:formatDate value="${sPmPrepPartyPublic.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</a></td>
				<td>
					${sPmPrepPartyPublic.remarks}
				</td>
				<td>
    				<a href="${ctx}/partyManage/sPmPrepPartyPublic/form?id=${sPmPrepPartyPublic.id}">修改</a>
					<a href="${ctx}/partyManage/sPmPrepPartyPublic/delete?id=${sPmPrepPartyPublic.id}" onclick="return confirmx('确认要删除该拟接收预备党员的公示吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>