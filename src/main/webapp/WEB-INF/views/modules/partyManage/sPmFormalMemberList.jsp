<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>按期转为中共正式党员的报告管理</title>
<meta name="decorator" content="default" />
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
	<form:form id="searchForm" modelAttribute="sPmFormalMember"
		action="${ctx}/partyManage/sPmFormalMember/" method="post"
		class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}" />
		<input id="pageSize" name="pageSize" type="hidden"
			value="${page.pageSize}" />
	</form:form>
	<sys:message content="${message}" />
	<table id="contentTable"
		class="table table-striped table-bordered table-condensed">
		
		<thead>
			<tr>
				<th>编号</th>
				<th>文件名</th>
				<th>文件下载</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${page.list}" var="sPmFormalMember" varStatus="status">
				<tr>
					<td>${status.index + 1}</td>
					<td>${sPmFormalMember.fileName}</td>
					<td><c:choose>
							<c:when test="${sPmFormalMember.fileUrl == null }">
								<a href="#" role="button" class="btn">下载</a>
							</c:when>
							<c:otherwise>
								<a
									href="${ctx}/partyManage/public/downfile?fileUrl=${sPmFormalMember.fileUrl}"
									role="button" class="btn">下载</a>
							</c:otherwise>
						</c:choose></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>