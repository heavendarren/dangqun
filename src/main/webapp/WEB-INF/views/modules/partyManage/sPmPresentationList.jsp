<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>报告管理</title>
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
				<th>编号</th>
				<th>文件名</th>
				<th>上传人员</th>
				<th>上传时间</th>
				<th>备注信息</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sPmPresentation" varStatus="status">
			<tr>
				<td>
					${status.index + 1}
				</td>	
				<td>
					${sPmPresentation.fileName}
				</td>		
				<td>
					${sPmPresentation.uploader}
				</td>
				<td>
					<fmt:formatDate value="${sPmPresentation.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>

				<td>
					${sPmPresentation.remarks}
				</td>
				<td>
    				<a href="${ctx}/partyManage/sPmPresentation/delete?id=${sPmPresentation.id}" onclick="return confirmx('确认要删除该报告吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</body>
</html>