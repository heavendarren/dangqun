
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>党员纪实补录列表</title>
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
		<c:if test="${stage == 0}">
		<li class="active"><a href="${ctx}/partyManage/DQRecord/makeup/?stage=0">入党申请列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/makeup/?stage=1">入党积极分子列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/makeup/?stage=2">党员发展对象列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/makeup/?stage=3">预备党员列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/makeup/?stage=4">党员转正列表</a></li>
		</c:if>
		
		<c:if test="${stage == 1}">
		<li><a href="${ctx}/partyManage/DQRecord/makeup/?stage=0">入党申请列表</a></li>
		<li class="active"><a href="${ctx}/partyManage/DQRecord/makeup/?stage=1">入党积极分子列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/makeup/?stage=2">党员发展对象列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/makeup/?stage=3">预备党员列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/makeup/?stage=4">党员转正列表</a></li>
		</c:if>
		
		<c:if test="${stage == 2}">
		<li><a href="${ctx}/partyManage/DQRecord/makeup/?stage=0">入党申请列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/makeup/?stage=1">入党积极分子列表</a></li>
		<li class="active"><a href="${ctx}/partyManage/DQRecord/makeup/?stage=2">党员发展对象列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/makeup/?stage=3">预备党员列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/makeup/?stage=4">党员转正列表</a></li>
		</c:if>
		
		<c:if test="${stage == 3}">
		<li><a href="${ctx}/partyManage/DQRecord/makeup/?stage=0">入党申请列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/makeup/?stage=1">入党积极分子列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/makeup/?stage=2">党员发展对象列表</a></li>
		<li class="active"><a href="${ctx}/partyManage/DQRecord/makeup/?stage=3">预备党员列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/makeup/?stage=4">党员转正列表</a></li>
		</c:if>
		
		<c:if test="${stage == 4}">
		<li><a href="${ctx}/partyManage/DQRecord/makeup/?stage=0">入党申请列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/makeup/?stage=1">入党积极分子列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/makeup/?stage=2">党员发展对象列表</a></li>
		<li><a href="${ctx}/partyManage/DQRecord/makeup/?stage=3">预备党员列表</a></li>
		<li class="active"><a href="${ctx}/partyManage/DQRecord/makeup/?stage=4">党员转正列表</a></li>
		</c:if>
	</ul>
	
	<form:form id="searchForm" modelAttribute="testAudit" action="${ctx}/partyManage/DQRecord/find" method="post" 
class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<label>姓名：</label><input name="name" type="text"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
		<th style='text-align: center;'>序号</th>
		<th style='text-align: center;'>姓名</th>
		<th style='text-align: center;'>所属党组织</th>
		<c:if test="${zbsj == 'zb'}">
			<th style='text-align: center;'>当前流程节点</th>
			<th style='text-align: center;'>补录到节点</th>
			<th style='text-align: center;'>补录</th>
		</c:if>
		</tr></thead>
		<tbody>
		<c:forEach items="${page.list}" var="record" varStatus="status"><input type="hidden" id="pnode" name="bulunode"/>
			<tr>
				<td id="sort" style='text-align: center;'>${status.index + 1}</td>
				<td style='text-align: center;'><a href="${ctx}/partyManage/DQRecord/schedule/?id=${record.id}">${record.name}</a></td>
				<td style='text-align: center;'>${record.orgName}</td>
				<c:if test="${zbsj == 'zb'}">
					<td style='text-align: center;'><c:if test="${record.nowNode == 1}">1-申请入党</c:if>
						<c:if test="${record.nowNode == 2}">2-谈话纪实</c:if><c:if test="${record.nowNode == 3}">3-积极分子推荐</c:if><c:if test="${record.nowNode == 4}">4-积极分子公示</c:if>
						<c:if test="${record.nowNode == 5}">5-支委会</c:if><c:if test="${record.nowNode == 6}">6-积极分子备案</c:if><c:if test="${record.nowNode == 7}">7-积极分子教育培养</c:if>
						<c:if test="${record.nowNode == 8}">8-发展对象公示</c:if><c:if test="${record.nowNode == 9}">9-推荐发展对象</c:if><c:if test="${record.nowNode == 10}">10-发展对象审核备案</c:if>
						<c:if test="${record.nowNode == 11}">11-确定入党介绍人</c:if><c:if test="${record.nowNode == 12}">12-政治审查</c:if><c:if test="${record.nowNode == 13}">13-发展对象短期集中培训</c:if>
						<c:if test="${record.nowNode == 14}">14-预备党员接收报告</c:if><c:if test="${record.nowNode == 15}">15-发展党员工作预审</c:if><c:if test="${record.nowNode == 16}">16-拟接收预备党员的公示</c:if>
						<c:if test="${record.nowNode == 17}">17-入党志愿书</c:if><c:if test="${record.nowNode == 18}">18-预备党员接收讨论</c:if><c:if test="${record.nowNode == 19}">19-预备党员备案</c:if>
						<c:if test="${record.nowNode == 20}">20-入党宣誓活动纪实</c:if><c:if test="${record.nowNode == 21}">21-预备党员考察纪实</c:if><c:if test="${record.nowNode == 22}">22-预备党员转正申请</c:if>
						<c:if test="${record.nowNode == 23}">23-预备党员转正预审</c:if><c:if test="${record.nowNode == 24}">24-拟进行转正公示</c:if><c:if test="${record.nowNode == 25}">25-预备党员转正</c:if>
					</td>
					<td style='text-align: center;'>
						<c:if test="${record.makeupflag == '1'}">
							<c:if test="${record.makenode == 2}">2-谈话纪实</c:if><c:if test="${record.makenode == 3}">3-积极分子推荐</c:if><c:if test="${record.makenode == 4}">4-积极分子公示</c:if>
							<c:if test="${record.makenode == 5}">5-支委会</c:if><c:if test="${record.makenode == 6}">6-积极分子备案</c:if><c:if test="${record.makenode == 7}">7-积极分子教育培养</c:if>
							<c:if test="${record.makenode == 8}">8-发展对象公示</c:if><c:if test="${record.makenode == 9}">9-推荐发展对象</c:if><c:if test="${record.makenode == 10}">10-发展对象审核备案</c:if>
							<c:if test="${record.makenode == 11}">11-确定入党介绍人</c:if><c:if test="${record.makenode == 12}">12-政治审查</c:if><c:if test="${record.makenode == 13}">13-发展对象短期集中培训</c:if>
							<c:if test="${record.makenode == 14}">14-预备党员接收报告</c:if><c:if test="${record.makenode == 15}">15-发展党员工作预审</c:if><c:if test="${record.makenode == 16}">16-拟接收预备党员的公示</c:if>
							<c:if test="${record.makenode == 17}">17-入党志愿书</c:if><c:if test="${record.makenode == 18}">18-预备党员接收讨论</c:if><c:if test="${record.makenode == 19}">19-预备党员备案</c:if>
							<c:if test="${record.makenode == 20}">20-入党宣誓活动纪实</c:if><c:if test="${record.makenode == 21}">21-预备党员考察纪实</c:if><c:if test="${record.makenode == 22}">22-预备党员转正申请</c:if>
							<c:if test="${record.makenode == 23}">23-预备党员转正预审</c:if><c:if test="${record.makenode == 24}">24-拟进行转正公示</c:if><c:if test="${record.makenode == 25}">25-预备党员转正</c:if>
						</c:if>
						<c:if test="${record.makeupflag != '1'}">
							<select id="node${status.index}" onchange="select(this.id)">
								<option value="2">2-谈话纪实</option>
								<option value="3">3-积极分子推荐</option>
								<option value="4">4-积极分子公示</option>
								<option value="5">5-支委会</option>
								<option value="6">6-积极分子备案</option>
								<option value="7">7-积极分子教育培养</option>
								<option value="8">8-发展对象公示</option>
								<option value="9">9-推荐发展对象</option>
								<option value="10">10-发展对象审核备案</option>
								<option value="11">11-确定入党介绍人</option>
								<option value="12">12-政治审查</option>
								<option value="13">13-发展对象短期集中培训</option>
								<option value="14">14-预备党员接收报告</option>
								<option value="15">15-发展党员工作预审</option>
								<option value="16">16-拟接收预备党员的公示</option>
								<option value="17">17-入党志愿书</option>
								<option value="18">18-预备党员接收讨论</option>
								<option value="19">19-预备党员备案</option>
								<option value="20">20-入党宣誓活动纪实</option>
								<option value="21">21-预备党员考察纪实</option>
								<option value="22">22-预备党员转正申请</option>
								<option value="23">23-预备党员转正预审</option>
								<option value="24">24-拟进行转正公示</option>
								<option value="25">25-预备党员转正</option>
							</select>
						</c:if>
					</td>
					<script type="text/javascript">
				        function select(id){
				        	var aa1 = document.getElementById("a${status.index}");
				        	var node = document.getElementById(id).value;
							aa1.href = "${ctx}/partyManage/DQRecord/bulu/?recordid=${record.id}&&bulunode=" + node;
				        }
					</script>
					<td style='text-align: center;'>
						<c:choose>
							<c:when test="${record.endflag == 1}">已完成</c:when>
							<c:otherwise>
								<c:if test="${record.makeupflag != '1'}">
									<a id="a${status.index}" href=""
									 data-toggle="modal" role="button" class="btn" onclick="return confirm('是否对该对象进行补录?');">补录</a>
								</c:if>
								<c:if test="${record.makeupflag == '1'}">补录</c:if>
							</c:otherwise>
						</c:choose>
					</td>
				</c:if>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>