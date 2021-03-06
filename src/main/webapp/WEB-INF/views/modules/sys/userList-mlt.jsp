<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
	
	$(document).ready(function() {
		$("input[name=id]").each(function(){
			var ${flag} = null;
				${flag} = top.${flag}||top.mainFrame.${flag};
			for (var i=0; i<${flag}.length; i++){
				if (${flag}[i][0]==$(this).val()){
					this.checked = true;
				}
			}
			$(this).click(function(){
				var id = $(this).val(), title = $(this).attr("title");
				top.mainFrame.${flag}AddOrDel(id, title);
			});
		});
	});
	
	
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/sys/multiUser/userList");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/multiUser/userList">用户列表</a></li>
	</ul>
	<form:form id="searchForm" modelAttribute="user" action="${ctx}/sys/multiUser/userList" method="post" class="breadcrumb form-search ">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="flag" name="flag" type="hidden" value="${flag}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<ul class="ul-form">
			<li><label>归属部门：</label><sys:treeselect id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}" 
				title="部门" url="/sys/office/treeData?type=2" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/></li>
			<li><label>登录名：</label><form:input path="loginName" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li><label>姓&nbsp;&nbsp;&nbsp;名：</label><form:input path="name" htmlEscape="false" maxlength="50" class="input-medium"/></li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询" onclick="return page();"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
		<th style="text-align:center;">选择</th>
		<th class="sort-column login_name">登录名</th>
		<th class="sort-column name">姓名</th>
		<th>归属部门</th>
		<th>电话</th>
		<th>手机</th>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="user">
			<tr>
				<td style="text-align:center;"><input type="checkbox" name="id" value="${user.id}" title="${user.name}" /></td>
				<td>${user.loginName}</td>
				<td>${user.name}</td>
				<td>${user.office.name}</td>
				<td>${user.phone}</td>
				<td>${user.mobile}</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>