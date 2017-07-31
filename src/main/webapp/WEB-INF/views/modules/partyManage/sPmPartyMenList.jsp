<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>入党志愿书管理</title>
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
	
	<form:form id="searchForm" modelAttribute="sPmPartyMen" action="${ctx}/partyManage/sPmPartyMen/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		
	</form:form>
	<sys:message content="${message}"/>
		<c:forEach items="${page.list}" var="item">
		<div class="controls" >
			<label><c:if test="${item.remarks == 'img' }">图片</c:if>
				   <c:if test="${item.remarks == 'file' }">文件</c:if>
				   名称：<input readonly="readonly" value="${item.fileName}" /></label>
		 	<label style="display:none"><c:if test="${item.remarks == 'img' }">图片</c:if>
		 		   <c:if test="${item.remarks == 'file' }">文件</c:if>
		 		   路径：<input readonly="readonly" value="${item.fileUrl}" />
		 	</label><a href="${ctx}/partyManage/public/downfile?fileUrl=${item.fileUrl}" role="button"
                           class="btn">下载</a>
			
		</div>
		</c:forEach>
		
		<div class="pagination">${page}</div>
</body>
</html>