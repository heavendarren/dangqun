<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
  <head>
	<title>入党进度列表</title>
	<meta name="decorator" content="default"/>
  </head>
  <script type="text/javascript">
		$(document).ready(function() {
		});
		
	</script>
  <body>
	 <ul class="nav nav-tabs">
	  	<li class="active"><a href="#" data-toggle="tab">入党进度列表</a></li>
	 </ul>
	 <div>
		<div style="height: 30px; float: left; margin: auto;" >
			<label style="margin-left: 20px;"><b>姓名：</b></label>&nbsp;${record.name}
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<label><b>所属党组织：</b></label>&nbsp;${record.orgName}
		</div>
		<div style="float: right;">
			<c:if test="${list[0].makeupflag != 1}">
				<a href="${ctx}/partyManage/DQRecord/record/?stage=${record.stage}" role="button" class="btn">返回上一页</a>
			</c:if>
			<c:if test="${list[0].makeupflag == 1}">
				<a href="${ctx}/partyManage/DQRecord/makeup/?stage=${record.stage}" role="button" class="btn">返回上一页</a>
			</c:if>
        </div>
	 </div>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead><tr>
		<th style='text-align: center;'>阶段</th>
		<th style='text-align: center;'>序号</th>
		<th style='text-align: center;'>环节</th>
		<th style='text-align: center;'>详细</th>
		<th style='text-align: center;'>进度</th>
		<th style='text-align: center;'>审核时间</th>
		<th style='text-align: center;'>审核党组织</th>
		<th style='text-align: center;'>审核结果</th>
		<th style='text-align: center;'>操作</th>
		</tr></thead>
		<tbody>
<!-- 申请入党以及积极分子阶段 -->
			<tr>
				<td rowspan="7"  style='text-align: center;'>
					入<br/>
					党<br/>
					积<br/>
					极<br/>
					分<br/>
					子<br/>
					的<br/>
					确<br/>
					定<br/>
					和<br/>
					培<br/>
					养<br/>
					教<br/>
					育
				</td>
				<td style='text-align: center;'>壹</td>
				<td style='text-align: center;'>申请入党</td>
				<td style='text-align: center;'>
					<a href="${ctx}/partyManage/sPmBasic/jionparty/detail/?proId=${list[0].id}" role="button" class="btn">查看</a>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[0].makeupflag == 1}">
						补录
					</c:if>
					<c:if test="${list[0].makeupflag != 1}">
						<c:choose>
							<c:when test="${list[0].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[0].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[0].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>${list[0].checkDate}</td>
				<td style='text-align: center;'>
						${list[0].officeName}
				</td>
				<td style='text-align: center;'>
						<c:choose>
							<c:when test="${list[0].checkflag == 0}">通过</c:when>
							<c:when test="${list[0].nowState == '0'}">
								<c:if test="${list[0].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[0].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[0].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
				</td>
				<td style='text-align: center;'>
					<c:if test="${(list[0].node == 1) && (list[0].checkflag != 0)}">
						<c:if test="${record.state == 0}">
							<c:if test="${(list[0].fillflag == 0) && (list[0].submitflag == 0)}">
								<a href="${ctx}/partyManage/sPmBasic/jionparty/form/?proId=${list[0].id}" role="button" class="btn">填报</a>
							</c:if>
							<%-- <c:if test="${(list[0].fillflag == 1) && (list[0].checkflag != 3) && (list[0].submitflag != 1)}">
								<a href="${ctx}/partyManage/sPmBasic/jionparty/form/?proId=${list[0].id}" role="button" class="btn">修改</a>
							</c:if> --%>
							<c:if test="${list[0].submitflag == 0}">
								<a href="${ctx}/partyManage/DQRecord/submit/?pdid=${list[0].id}&&recordid=${record.id}" role="button" class="btn">提交</a>
							</c:if>
						</c:if>
						<c:if test="${(record.state == 3) && (list[0].checkflag != 0) && (list[0].submitflag == 1)}">
							<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[0].id}&&recordid=${record.id}&&checkresult=0"
							 role="button" class="btn" onclick="return confirm('结束本环节，进入下一环节！');">审核</a>
							<a href="${ctx}/partyManage/DQRecord/stop/?pdid=${list[0].id}&&recordid=${record.id}" role="button" class="btn"
							 onclick="return confirm('终止该对象的入党进程！');">终止</a>
							<a href="#tuihui1" data-toggle="modal" role="button" class="btn">退回</a>
						</c:if>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style='text-align: center;'>贰</td>
				<td style='text-align: center;'>谈话纪实</td>
				<td style='text-align: center;'>
					<c:if test="${(list[1].node == 2)}">
						<c:if test="${list[1].makeupflag != 1}">
							<a href="${ctx}/partyManage/sPmTalking/detail/?proId=${list[1].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[1].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[1].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
				<c:if test="${list[1].node == 2}">
						<c:choose>
							<c:when test="${list[1].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[1].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[1].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
				</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[1].node == 2}">
						${list[1].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[1].node == 2}">
						${list[1].officeName}
					</c:if>
				</td>
				<td style='text-align: center;'>
				<c:if test="${list[1].node == 2}">
						<c:choose>
							<c:when test="${list[1].checkflag == 0}">通过</c:when>
							<c:when test="${list[1].nowState == '0'}">
								<c:if test="${list[1].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[1].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[1].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
				</c:if>
				</td>
				<td style='text-align: center;'>
				<c:if test="${list[1].node == 2}">
					<c:if test="${list[1].makeupflag != 1}">
						<c:if test="${record.state == 3}">
							<c:if test="${(list[1].fillflag == 0) && (list[1].checkflag != 0)}">
								<a href="${ctx}/partyManage/sPmTalking/form/?proId=${list[1].id}" role="button" class="btn">填报</a>
							</c:if>
							<c:if test="${(list[1].fillflag == 1) && (list[1].checkflag != 0) && (list[1].checkflag != 3)}">
								<a href="${ctx}/partyManage/sPmTalking/list/?proId=${list[1].id}" role="button" class="btn">修改</a>
							</c:if>
						</c:if>
					</c:if>
					<c:if test="${list[1].makeupflag == 1}">
						<c:if test="${record.state == 3}">
								<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[1].id}" role="button" class="btn">补录</a>
						</c:if>
					</c:if>
				</c:if>
				</td>
			</tr>
			<tr>
				<td style='text-align: center;'>叁</td>
				<td style='text-align: center;'>积极分子推荐</td>
				<td style='text-align: center;'>
					<c:if test="${list[2].node == 3}">
						<c:if test="${list[2].makeupflag != 1}">
							<a href="${ctx}/partyManage/sPmMrs/active/detail/?proId=${list[2].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[2].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[2].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
				<c:if test="${list[2].node == 3}">
						<c:choose>
							<c:when test="${list[2].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[2].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[2].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
				</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[2].node == 3}">
						${list[2].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[2].node == 3}">
						${list[2].officeName}
					</c:if>
					
				</td>
				<td style='text-align: center;'>
				<c:if test="${list[2].node == 3}">
						<c:choose>
							<c:when test="${list[2].checkflag == 0}">通过</c:when>
							<c:when test="${list[2].nowState == '0'}">
								<c:if test="${list[2].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[2].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[2].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
				</c:if>
				</td>
				<td style='text-align: center;'>
				<c:if test="${list[2].node == 3}">
					<c:if test="${list[2].makeupflag != 1}">
						<c:if test="${record.state == 3}">
							<c:if test="${list[2].checkflag != 0}">
									<c:if test="${list[2].submitflag == 0}">
										<a href="${ctx}/partyManage/recomActive/index/?proId=${list[2].id}" role="button" class="btn">填报</a>
										<a href="${ctx}/partyManage/DQRecord/submit/?pdid=${list[2].id}&&recordid=${record.id}" role="button" class="btn">提交</a>
									</c:if>
							</c:if>
						</c:if>
						<c:if test="${(record.state == 5) && (list[2].checkflag != 0) && (list[2].submitflag == 1)}">
							<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[2].id}&&recordid=${record.id}&&checkresult=0"
							 role="button" class="btn" onclick="return confirm('结束本环节，进入下一环节！');">审核</a>
							<a href="${ctx}/partyManage/DQRecord/stop/?pdid=${list[2].id}&&recordid=${record.id}" role="button" class="btn"
							 onclick="return confirm('终止该对象的入党进程！');">终止</a>
							<a href="#tuihuisan" data-toggle="modal" role="button" class="btn">退回</a>
						</c:if>
					</c:if>
					<c:if test="${(record.state == 3) && (list[2].makeupflag == 1)}">
							<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[2].id}" role="button" class="btn">补录</a>
					</c:if>
				</c:if>
				</td>
			</tr>
			<tr>
				<td style='text-align: center;'>肆</td>
				<td style='text-align: center;'>积极分子公示</td>
				<td style='text-align: center;'>
					<c:if test="${list[3].node == 4}">
						<c:if test="${list[3].makeupflag != 1}">
							<a href="${ctx}/partyManage/sPmPublicInformation/detail/?proId=${list[3].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[3].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[3].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
				<c:if test="${list[3].node == 4}">
						<c:choose>
							<c:when test="${list[3].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[3].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[3].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
				</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[3].node == 4}">
						${list[3].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[3].node == 4}">
						${list[3].officeName}
					</c:if>
					
				</td>
				<td style='text-align: center;'>
				<c:if test="${list[3].node == 4}">
						<c:choose>
							<c:when test="${list[3].checkflag == 0}">通过</c:when>
							<c:when test="${list[3].nowState == '0'}">
								<c:if test="${list[3].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[3].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[3].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
				</c:if>
				</td>
				<td style='text-align: center;'>
				<c:if test="${list[3].node == 4}">
					<c:if test="${list[3].makeupflag != 1}">
						<c:if test="${(record.state == 3) && (list[3].checkflag != 0)}">
								<%-- <c:if test="${list[3].fillflag == 0 }"> --%>
								<a href="${ctx}/partyManage/sPmPublicInformation/list/?proId=${list[3].id}" role="button" class="btn">发布公示</a>
								<%-- </c:if> --%>
								<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[3].id}&&recordid=${record.id}&&checkresult=0"
								 role="button" class="btn" onclick="return confirm('结束本环节，进入下一环节！');">完成公示</a>
							
						</c:if>
					</c:if>
					<c:if test="${(record.state == 3) && (list[3].makeupflag == 1)}">
						<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[3].id}" role="button" class="btn">补录</a>
					</c:if>
				</c:if>
				</td>
			</tr>
			<tr>
				<td style='text-align: center;'>伍</td>
				<td style='text-align: center;'>支委会</td>
				<td style='text-align: center;'>
					<c:if test="${list[4].node == 5}">
						<c:if test="${list[4].makeupflag != 1}">
							<a href="${ctx}/partyManage/sPmMinutes/detail/?proId=${list[4].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[4].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[4].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
				<c:if test="${list[4].node == 5}">
						<c:choose>
							<c:when test="${list[4].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[4].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[4].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
				</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[4].node == 5}">
						${list[4].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[4].node == 5}">
						${list[4].officeName}
					</c:if>
					
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[4].node == 5}">
						<c:choose>
							<c:when test="${list[4].checkflag == 0}">通过</c:when>
							<c:when test="${list[4].nowState == '0'}">
								<c:if test="${list[4].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[4].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[4].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[4].node == 5}">
						<c:if test="${list[4].makeupflag != 1}">
							<c:if test="${record.state == 3}">
									<c:if test="${(list[4].fillflag == 0) && (list[4].checkflag != 0)}">
										<a href="${ctx}/partyManage/sPmMinutes/form/?proId=${list[4].id}" role="button" class="btn">填报</a>
									</c:if>
							</c:if>
							<c:if test="${(record.state == 5) && (list[4].checkflag != 0) && (list[4].fillflag == 1)}">
								<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[4].id}&&recordid=${record.id}&&checkresult=0"
								 role="button" class="btn" onclick="return confirm('结束本环节，进入下一环节！');">归档完成</a>
							</c:if>
						</c:if>
						<c:if test="${(record.state == 3)&&(list[4].makeupflag == 1)}">
							<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[4].id}" role="button" class="btn">补录</a>
						</c:if>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style='text-align: center;'>陆</td>
				<td style='text-align: center;'>积极分子备案</td>
				<td style='text-align: center;'>
					<c:if test="${list[5].node == 6}">
						<c:if test="${list[5].makeupflag != 1}">
							<a href="${ctx}/partyManage/sPmActivi/detail/?proId=${list[5].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[5].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[5].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
				<c:if test="${list[5].node == 6}">
						<c:choose>
							<c:when test="${list[5].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[5].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[5].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
				</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[5].node == 6}">
						${list[5].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[5].node == 6}">
						${list[5].officeName}
					</c:if>
					
				</td>
				<td style='text-align: center;'>
				<c:if test="${list[5].node == 6}">
						<c:choose>
							<c:when test="${list[5].checkflag == 0}">通过</c:when>
							<c:when test="${list[5].nowState == '0'}">
								<c:if test="${list[5].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[5].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[5].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
				</c:if>
				</td>
				<td style='text-align: center;'>
				<c:if test="${list[5].node == 6}">
					<c:if test="${list[5].makeupflag != 1}">
						<c:if test="${record.state == 3}">
							<c:if test="${(list[5].checkflag != 0) && (list[5].fillflag == 0)}">
								<a href="${ctx}/partyManage/sPmActivi/form/?proId=${list[5].id}" role="button" class="btn">填报</a>
								<a href="${ctx}/partyManage/DQRecord/submit/?pdid=${list[5].id}&&recordid=${record.id}" role="button" class="btn">提交</a>
							</c:if>
						</c:if>
						<c:if test="${(record.state == 4) && (list[5].checkflag != 0) && (list[5].fillflag == 1) && (list[5].nowState == '0')}">
							<a href="${ctx}/partyManage/DQRecord/finish/?pdid=${list[5].id}&&recordid=${record.id}" role="button" class="btn">审核</a>
							<a href="${ctx}/partyManage/DQRecord/stop/?pdid=${list[5].id}&&recordid=${record.id}" role="button" class="btn"
							 onclick="return confirm('终止该对象的入党进程！');">终止</a>
							<a href="#tuihuift" data-toggle="modal" role="button" class="btn">退回</a>
						</c:if>
						<c:if test="${(record.state == 5) && (list[5].checkflag != 0) && (list[5].fillflag == 1)}">
							<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[5].id}&&recordid=${record.id}&&checkresult=0"
							 role="button" class="btn" onclick="return confirm('结束本环节，进入下一环节！');">审核</a>
							<a href="${ctx}/partyManage/DQRecord/stop/?pdid=${list[5].id}&&recordid=${record.id}" role="button" class="btn"
							 onclick="return confirm('终止该对象的入党进程！');">终止</a>
							<a href="#tuihui2" data-toggle="modal" role="button" class="btn">退回</a>
						</c:if>
					</c:if>
					<c:if test="${(record.state == 3)&&(list[5].makeupflag == 1)}">
						<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[5].id}" role="button" class="btn">补录</a>
					</c:if>
				</c:if>
				</td>
			</tr>
			<tr>
				<td style='text-align: center;'>柒</td>
				<td style='text-align: center;'>积极分子教育培养</td>
				<td style='text-align: center;'>
					<c:if test="${list[6].node == 7}">
						<c:if test="${list[6].makeupflag != 1}">
							<a href="${ctx}/partyManage/sPmContactSettings/activisttrain/detail/?proId=${list[6].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[6].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[6].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[6].node == 7}">
						<c:choose>
							<c:when test="${list[6].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[1].submitflag == 1}">
									进行中
								</c:if>
								<c:if test="${list[1].submitflag == 0}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[6].node == 7}">
						${list[6].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[6].node == 7}">
						${list[6].officeName}
					</c:if>
					
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[6].node == 7}">
						<c:choose>
							<c:when test="${list[6].checkflag == 0}">通过</c:when>
							<c:when test="${list[6].nowState == '0'}">
								<c:if test="${list[6].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[6].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[6].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[6].node == 7}">
						<c:if test="${list[6].makeupflag != 1}">
							<c:if test="${(record.state == 0) && (list[6].fillflag == 0) && (list[6].checkflag != 0)}">
								<a href="${ctx}/partyManage/sPmContactSettings/activisttrain/form/?proId=${list[6].id}" role="button" class="btn">思想汇报</a>
							</c:if>
							<c:if test="${(record.state == 3) && (list[6].submitflag == 0) && (list[6].checkflag != 0)}">
								<a href="${ctx}/partyManage/sPmContactSettings/activisttrain/form/?proId=${list[6].id}" role="button" class="btn">填报</a>
							</c:if>
							<c:if test="${(record.state == 5) && (list[6].submitflag == 1) && (list[6].checkflag != 0)}">
								<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[6].id}&&recordid=${record.id}&&checkresult=0"
								 role="button" class="btn" onclick="return confirm('结束本环节，进入下一环节！');">审核</a>
							<a href="${ctx}/partyManage/DQRecord/stop/?pdid=${list[6].id}&&recordid=${record.id}" role="button" class="btn"
							 onclick="return confirm('终止该对象的入党进程！');">终止</a>
								<a href="#tuihuiep" data-toggle="modal" role="button" class="btn">退回</a>
							</c:if>
						</c:if>
						<c:if test="${(record.state == 3) && (list[6].makeupflag == 1)}">
							<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[6].id}" role="button" class="btn">补录</a>
						</c:if>
					</c:if>
				</td>
			</tr>
<!-- 发展对象阶段 -->
			<tr>
				<td  rowspan="6" style='text-align: center;'>
					党<br/>
					员<br/>
					发<br/>
					展<br/>
					对<br/>
					象<br/>
					决<br/>
					定<br/>
					和<br/>
					培<br/>
					训
				</td>
				<td style='text-align: center;'>捌</td>
				<td style='text-align: center;'>发展对象公示</td>
				<td style='text-align: center;'>
					<c:if test="${list[7].node == 8}">
						<c:if test="${list[7].makeupflag != 1}">
							<a href="${ctx}/partyManage/sPmAnnouncement/detail/?proId=${list[7].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[7].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[7].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
				<c:if test="${list[7].node == 8}">
						<c:choose>
							<c:when test="${list[7].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[7].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[7].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
				</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[7].node == 8}">
						${list[7].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[7].node == 8}">
						${list[7].officeName}
					</c:if>
					
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[7].node == 8}">
						<c:choose>
							<c:when test="${list[7].checkflag == 0}">通过</c:when>
							<c:when test="${list[7].nowState == '0'}">
								<c:if test="${list[7].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[7].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[7].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[7].node == 8}">
						<c:if test="${list[7].makeupflag != 1}">
							<c:if test="${(record.state == 3) && (list[7].checkflag != 0)}">
									<c:if test="${list[7].fillflag == 0 }">
										<a href="${ctx}/partyManage/sPmAnnouncement/list/?proId=${list[7].id}" role="button" class="btn">发布公示</a>
									</c:if>
									<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[7].id}&&recordid=${record.id}&&checkresult=0"
									 role="button" class="btn" onclick="return confirm('结束本环节，进入下一环节！');">完成公示</a>
								
							</c:if>
						</c:if>
						<c:if test="${(record.state == 3) && (list[7].makeupflag == 1)}">
							<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[7].id}" role="button" class="btn">补录</a>
						</c:if>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style='text-align: center;'>玖</td>
				<td style='text-align: center;'>推荐发展对象</td>
				<td style='text-align: center;'>
					<c:if test="${list[8].node == 9}">
						<c:if test="${list[8].makeupflag != 1}">
							<a href="${ctx}/partyManage/sPmMass/recom/detail/?proId=${list[8].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[8].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[8].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
				<c:if test="${list[8].node == 9}">
						<c:choose>
							<c:when test="${list[8].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[8].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[8].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
				</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[8].node == 9}">
						${list[8].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[8].node == 9}">
						${list[8].officeName}
					</c:if>
					
				</td>
				<td style='text-align: center;'>
				<c:if test="${list[8].node == 9}">
						<c:choose>
							<c:when test="${list[8].checkflag == 0}">通过</c:when>
							<c:when test="${list[8].nowState == '0'}">
								<c:if test="${list[8].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[8].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[8].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
				</c:if>
				</td>
				<td style='text-align: center;'>
				<c:if test="${list[8].node == 9}">
					<c:if test="${list[8].makeupflag != 1}">
						<c:if test="${record.state == 3}">
							<c:if test="${(list[8].checkflag != 0) && (list[8].submitflag == 0)}">
									<c:if test="${list[8].fillflag == 0}">
										<a href="${ctx}/partyManage/develop/index/?proId=${list[8].id}" role="button" class="btn">填报</a>
										<a href="${ctx}/partyManage/DQRecord/submit/?pdid=${list[8].id}&&recordid=${record.id}" role="button" class="btn">提交</a>
									</c:if>
							</c:if>
						</c:if>
						<c:if test="${(record.state == 5) && (list[8].checkflag != 0) && (list[8].submitflag == 1)}">
							<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[8].id}&&recordid=${record.id}&&checkresult=0"
							 role="button" class="btn" onclick="return confirm('结束本环节，进入下一环节！');">归档完成</a>
						</c:if>
					</c:if>
					<c:if test="${(record.state == 3) && (list[8].makeupflag == 1)}">
						<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[8].id}" role="button" class="btn">补录</a>
					</c:if>
				</c:if>
				</td>
			</tr>
			<tr>
				<td style='text-align: center;'>拾</td>
				<td style='text-align: center;'>发展对象审核备案</td>
				<td style='text-align: center;'>
					<c:if test="${list[9].node == 10}">
						<c:if test="${list[9].makeupflag != 1}">
							<a href="${ctx}/partyManage/sPmExamineRecord/detail/?proId=${list[9].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[9].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[9].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[9].node == 10}">
						<c:choose>
							<c:when test="${list[9].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[9].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[9].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[9].node == 10}">
						${list[9].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[9].node == 10}">
						${list[9].officeName}
					</c:if>
					
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[9].node == 10}">
						<c:choose>
							<c:when test="${list[9].checkflag == 0}">通过</c:when>
							<c:when test="${list[9].nowState == '0'}">
								<c:if test="${list[9].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[9].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[9].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[9].node == 10}">
						<c:if test="${list[9].makeupflag != 1}">
							<c:if test="${(record.state == 3) && (list[9].checkflag != 0) && (list[9].fillflag == 0)}">
								<a href="${ctx}/partyManage/sPmExamineRecord/form/?proId=${list[9].id}" role="button" class="btn">填报</a>
							</c:if>
							<c:if test="${(record.state == 4) && (list[9].checkflag != 0) && (list[9].fillflag == 1) && (list[9].nowState == '0')}">
								<a href="${ctx}/partyManage/DQRecord/finish/?pdid=${list[9].id}&&recordid=${record.id}" role="button" class="btn">审核</a>
								<a href="${ctx}/partyManage/DQRecord/stop/?pdid=${list[9].id}&&recordid=${record.id}" role="button" class="btn"
								 onclick="return confirm('终止该对象的入党进程！');">终止</a>
								<a href="#tuihuitt" data-toggle="modal" role="button" class="btn">退回</a>
							</c:if>
							<c:if test="${(record.state == 5) && (list[9].checkflag != 0) && (list[9].fillflag == 1)}">
								<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[9].id}&&recordid=${record.id}&&checkresult=0"
								 role="button" class="btn" onclick="return confirm('结束本环节，进入下一环节！');">审核</a>
							<a href="${ctx}/partyManage/DQRecord/stop/?pdid=${list[9].id}&&recordid=${record.id}" role="button" class="btn"
							 onclick="return confirm('终止该对象的入党进程！');">终止</a>
								<a href="#tuihui3" data-toggle="modal" role="button" class="btn">退回</a>
							</c:if>
						</c:if>
						<c:if test="${(record.state == 3) && (list[9].makeupflag == 1)}">
							<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[9].id}" role="button" class="btn">补录</a>
						</c:if>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style='text-align: center;'>壹拾壹</td>
				<td style='text-align: center;'>确定入党介绍人</td>
				<td style='text-align: center;'>
					<c:if test="${list[10].node == 11}">
						<c:if test="${list[10].makeupflag != 1}">
						<a href="${ctx}/partyManage/sPmParthMenber/detail/?proId=${list[10].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[10].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[10].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[10].node == 11}">
						<c:choose>
							<c:when test="${list[10].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[10].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[10].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[10].node == 11}">
						${list[10].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[10].node == 11}">
						${list[10].officeName}
					</c:if>
					
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[10].node == 11}">
						<c:choose>
							<c:when test="${list[10].checkflag == 0}">通过</c:when>
							<c:when test="${list[10].nowState == '0'}">
								<c:if test="${list[10].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[10].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[10].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[10].node == 11}" >
						<c:if test="${list[10].makeupflag != 1}">
							<c:if test="${(record.state == 3) && (list[10].checkflag != 0) && (list[10].submitflag == 0)}">
								<a href="${ctx}/partyManage/sPmParthMenber/list/?proId=${list[10].id}" role="button" class="btn">填报</a>
								<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[10].id}&&recordid=${record.id}&&checkresult=0"
								 role="button" class="btn" onclick="return confirm('结束本环节，进入下一环节！');">提交通过</a>
							</c:if>
						</c:if>
						<c:if test="${(record.state == 3) && (list[10].makeupflag == 1)}">
							<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[10].id}" role="button" class="btn">补录</a>
						</c:if>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style='text-align: center;'>壹拾贰</td>
				<td style='text-align: center;'>政治审查</td>
				<td style='text-align: center;'>
					<c:if test="${list[11].node == 12}">
						<c:if test="${list[11].makeupflag != 1}">
						<a href="${ctx}/partyManage/sPmAuto/political/detail/?proId=${list[11].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[11].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[11].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[11].node == 12}">
						<c:choose>
							<c:when test="${list[11].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[11].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[11].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[11].node == 12}">
						${list[11].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[11].node == 12}">
						${list[11].officeName}
					</c:if>
					
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[11].node == 12}">
						<c:choose>
							<c:when test="${list[11].checkflag == 0}">通过</c:when>
							<c:when test="${list[11].nowState == '0'}">
								<c:if test="${list[11].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[11].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[11].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[11].node == 12}" >
						<c:if test="${list[11].makeupflag != 1}">
							<c:if test="${(record.state == 3) && (list[11].checkflag != 0) && (list[11].submitflag == 0)}">
								<a href="${ctx}/partyManage/political/index/?proId=${list[11].id}" role="button" class="btn">填报</a>
								<a href="${ctx}/partyManage/DQRecord/submit/?pdid=${list[11].id}&&recordid=${record.id}" role="button" class="btn">提交</a>
							</c:if>
							<c:if test="${(record.state == 5) && (list[11].checkflag != 0) && (list[11].submitflag == 1)}">
								<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[11].id}&&recordid=${record.id}&&checkresult=0"
								 role="button" class="btn" onclick="return confirm('结束本环节，进入下一环节！');">审核</a>
								<a href="${ctx}/partyManage/DQRecord/stop/?pdid=${list[11].id}&&recordid=${record.id}" role="button" class="btn"
								 onclick="return confirm('终止该对象的入党进程！');">终止</a>
								<a href="#tuihui4" data-toggle="modal" role="button" class="btn">退回</a>
							</c:if>
						</c:if>
						<c:if test="${(record.state == 3) && (list[11].makeupflag == 1)}">
							<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[11].id}" role="button" class="btn">补录</a>
						</c:if>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style='text-align: center;'>壹拾叁</td>
				<td style='text-align: center;'>发展对象短期集中培训</td>
				<td style='text-align: center;'>
					<c:if test="${list[12].node == 13}">
						<c:if test="${list[12].makeupflag != 1}">
						<a href="${ctx}/partyManage/sPmTermTrain/detail/?proId=${list[12].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[12].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[12].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[12].node == 13}">
						<c:choose>
							<c:when test="${list[12].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[12].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[12].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[12].node == 13}">
						${list[12].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[12].node == 13}">
						${list[12].officeName}
					</c:if>
					
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[12].node == 13}">
						<c:choose>
							<c:when test="${list[12].checkflag == 0}">通过</c:when>
							<c:when test="${list[12].nowState == '0'}">
								<c:if test="${list[12].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[12].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[12].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[12].node == 13}" >
						<c:if test="${list[12].makeupflag != 1}">
							<c:if test="${(record.state == 5) && (list[12].checkflag != 0)}">
								<a href="${ctx}/partyManage/sPmTermTrain/form/?proId=${list[12].id}" role="button" class="btn">填报</a>
							</c:if>
						</c:if>
						<c:if test="${(record.state == 3) && (list[12].makeupflag == 1)}">
							<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[12].id}" role="button" class="btn">补录</a>
						</c:if>
					</c:if>
				</td>
			</tr>
<!-- 预备党员阶段 -->			
			<tr>
				<td  rowspan="8" style='text-align: center;'>
					预<br/>
					备<br/>
					党<br/>
					员<br/>
					接<br/>
					收<br/>
					及<br/>
					审<br/>
					核
				</td>
				<td style='text-align: center;'>壹拾肆</td>
				<td style='text-align: center;'>预备党员接收报告</td>
				<td style='text-align: center;'>
					<c:if test="${list[13].node == 14}">
						<c:if test="${list[13].makeupflag != 1}">
						<a href="${ctx}/partyManage/sPmPresentation/detail/?proId=${list[13].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[13].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[13].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[13].node == 14}">
						<c:choose>
							<c:when test="${list[13].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[13].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[13].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[13].node == 14}">
						${list[13].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[13].node == 14}">
						${list[13].officeName}
					</c:if>
					
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[13].node == 14}">
						<c:choose>
							<c:when test="${list[13].checkflag == 0}">通过</c:when>
							<c:when test="${list[13].nowState == '0'}">
								<c:if test="${list[13].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[13].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[13].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[13].node == 14}" >
						<c:if test="${list[13].makeupflag != 1}">
							<c:if test="${(record.state == 3) && (list[13].checkflag != 0) && (list[13].fillflag == 0) && (list[13].submitflag == 0)}">
								<a href="${ctx}/partyManage/sPmPresentation/form/?proId=${list[13].id}" role="button" class="btn">填报</a>
							</c:if>
							<c:if test="${(record.state == 3) && (list[13].checkflag != 0) && (list[13].fillflag == 1) && (list[13].submitflag == 0)}">
								<a href="${ctx}/partyManage/sPmPresentation/from/?proId=${list[13].id}" role="button" class="btn">修改</a>
							</c:if>
							<c:if test="${(record.state == 3) &&(list[13].checkflag != 0) && (list[13].submitflag == 0)}">
								<a href="${ctx}/partyManage/DQRecord/submit/?pdid=${list[13].id}&&recordid=${record.id}" role="button" class="btn">提交</a>
							</c:if>
							<c:if test="${(record.state == 5) && (list[13].checkflag != 0) && (list[13].submitflag == 1)}">
								<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[13].id}&&recordid=${record.id}&&checkresult=0"
								 role="button" class="btn" onclick="return confirm('结束本环节，进入下一环节！');">审核</a>
							<a href="${ctx}/partyManage/DQRecord/stop/?pdid=${list[13].id}&&recordid=${record.id}" role="button" class="btn"
							 onclick="return confirm('终止该对象的入党进程！');">终止</a>
								<a href="#tuihui5" data-toggle="modal" role="button" class="btn">退回</a>
							</c:if>
						</c:if>
						<c:if test="${(record.state == 3) && (list[13].makeupflag == 1)}">
							<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[13].id}" role="button" class="btn">补录</a>
						</c:if>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style='text-align: center;'>壹拾伍</td>
				<td style='text-align: center;'>发展党员工作预审</td>
				<td style='text-align: center;'>
					<c:if test="${list[14].node == 15}">
						<c:if test="${list[14].makeupflag != 1}">
						<a href="${ctx}/partyManage/sPmReview/detail/?proId=${list[14].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[14].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[14].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[14].node == 15}">
						<c:choose>
							<c:when test="${list[14].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[14].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[14].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[14].node == 15}">
						${list[14].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[14].node == 15}">
						${list[14].officeName}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[14].node == 15}">
						<c:choose>
							<c:when test="${list[14].checkflag == 0}">通过</c:when>
							<c:when test="${list[14].nowState == '0'}">
								<c:if test="${list[14].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[14].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[14].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[14].node == 15}" >
						<c:if test="${list[14].makeupflag != 1}">
							<c:if test="${(record.state == 3) && (list[14].checkflag != 0) && (list[14].fillflag == 0) && (list[14].submitflag == 0)}">
								<a href="${ctx}/partyManage/sPmReview/form/?proId=${list[14].id}" role="button" class="btn">填报</a>
							</c:if>
							<c:if test="${(record.state == 3) && (list[14].checkflag != 0) && (list[14].submitflag == 0)}">
								<a href="${ctx}/partyManage/DQRecord/submit/?pdid=${list[14].id}&&recordid=${record.id}" role="button" class="btn">提交</a>
							</c:if>
							<c:if test="${(record.state == 4) && (list[14].checkflag != 0) && (list[14].submitflag == 1) && (list[14].nowState == '0')}">
								<a href="${ctx}/partyManage/DQRecord/finish/?pdid=${list[14].id}&&recordid=${record.id}" role="button" class="btn">审核</a>
							<a href="${ctx}/partyManage/DQRecord/stop/?pdid=${list[14].id}&&recordid=${record.id}" role="button" class="btn"
							 onclick="return confirm('终止该对象的入党进程！');">终止</a>
								<a href="#tuihuitf" data-toggle="modal" role="button" class="btn">退回</a>
							</c:if>
							<c:if test="${(record.state == 5) && (list[14].checkflag != 0) && (list[14].submitflag == 1)}">
								<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[14].id}&&recordid=${record.id}&&checkresult=0"
								 role="button" class="btn" onclick="return confirm('结束本环节，进入下一环节！');">审核</a>
							<a href="${ctx}/partyManage/DQRecord/stop/?pdid=${list[14].id}&&recordid=${record.id}" role="button" class="btn"
							 onclick="return confirm('终止该对象的入党进程！');">终止</a>
								<a href="#tuihui6" data-toggle="modal" role="button" class="btn">退回</a>
							</c:if>
						</c:if>
						<c:if test="${(record.state == 3) && (list[14].makeupflag == 1)}">
							<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[14].id}" role="button" class="btn">补录</a>
						</c:if>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style='text-align: center;'>壹拾陆</td>
				<td style='text-align: center;'>拟接收预备党员的公示</td>
				<td style='text-align: center;'>
					 <c:if test="${list[15].node == 16}" >
						<c:if test="${list[15].makeupflag != 1}">
						<a href="${ctx}/partyManage/sPmPrepPartyPublic/detail/?proId=${list[15].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[15].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[15].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[15].node == 16}">
						<c:choose>
							<c:when test="${list[15].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[15].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[15].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[15].node == 16}">
						${list[15].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[15].node == 16}">
						${list[15].officeName}
					</c:if>
					
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[15].node == 16}">
						<c:choose>
							<c:when test="${list[15].checkflag == 0}">通过</c:when>
							<c:when test="${list[15].nowState == '0'}">
								<c:if test="${list[15].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[15].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[15].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[15].node == 16}" >
						<c:if test="${list[15].makeupflag != 1}">
							<c:if test="${(record.state == 3)&&(list[15].checkflag != 0) && (list[15].submitflag == 0)}">
								<a href="${ctx}/partyManage/sPmPrepPartyPublic/form/?proId=${list[15].id}"  role="button" class="btn">填报</a>
								<a href="${ctx}/partyManage/DQRecord/submit/?pdid=${list[15].id}&&recordid=${record.id}" role="button" class="btn">提交</a>
							</c:if>
							<c:if test="${(record.state == 5)&&(list[15].checkflag != 0) && (list[15].submitflag == 1)}">
								<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[15].id}&&recordid=${record.id}&&checkresult=0"
								 role="button" class="btn" onclick="return confirm('结束本环节，进入下一环节！');">审核</a>
							<a href="${ctx}/partyManage/DQRecord/stop/?pdid=${list[15].id}&&recordid=${record.id}" role="button" class="btn"
							 onclick="return confirm('终止该对象的入党进程！');">终止</a>
								<a href="#tuihui7" data-toggle="modal"  role="button" class="btn">退回</a>
							</c:if>
						</c:if>
						<c:if test="${(record.state == 3) && (list[15].makeupflag == 1)}">
							<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[15].id}" role="button" class="btn">补录</a>
						</c:if>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style='text-align: center;'>壹拾柒</td>
				<td style='text-align: center;'>入党志愿书</td>
				<td style='text-align: center;'>
					<c:if test="${list[16].node == 17}">
						<c:if test="${list[16].makeupflag != 1}">
						<a href="${ctx}/partyManage/sPmPartyMen/list/?proId=${list[16].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[16].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[16].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[16].node == 17}">
						<c:choose>
							<c:when test="${list[16].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[16].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[16].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[16].node == 17}">
						${list[16].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[16].node == 17}">
						${list[16].officeName}
					</c:if>
					
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[16].node == 17}">
						<c:choose>
							<c:when test="${list[16].checkflag == 0}">通过</c:when>
							<c:when test="${list[16].nowState == '0'}">
								<c:if test="${list[16].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[16].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[16].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[16].node == 17}" >
						<c:if test="${list[16].makeupflag != 1}">
							<c:if test="${(record.state == 3)&&(list[16].checkflag != 0) && (list[16].submitflag == 0)}">
								<a href="${ctx}/partyManage/sPmPartyMen/form/?proId=${list[16].id}" role="button" class="btn">填报</a>
								<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[16].id}&&recordid=${record.id}&&checkresult=0"
								 role="button" class="btn" onclick="return confirm('结束本环节，进入下一环节！');">提交通过</a>
							</c:if>
						</c:if>
						<c:if test="${(record.state == 3) && (list[16].makeupflag == 1)}">
							<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[16].id}" role="button" class="btn">补录</a>
						</c:if>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style='text-align: center;'>壹拾捌</td>
				<td style='text-align: center;'>预备党员接收讨论</td>
				<td style='text-align: center;'>
					<c:if test="${list[17].node == 18}">
						<c:if test="${list[17].makeupflag != 1}">
						<a href="${ctx}/partyManage/sPmMinutesS/discuss/detail/?proId=${list[17].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[17].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[17].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[17].node == 18}">
						<c:choose>
							<c:when test="${list[17].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[17].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[17].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[17].node == 18}">
						${list[17].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[17].node == 18}">
						${list[17].officeName}
					</c:if>
					
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[17].node == 18}">
						<c:choose>
							<c:when test="${list[17].checkflag == 0}">通过</c:when>
							<c:when test="${list[17].nowState == '0'}">
								<c:if test="${list[17].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[17].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[17].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[17].node == 18}">
						<c:if test="${list[17].makeupflag != 1}">
							<c:if test="${(record.state == 3)&&(list[17].checkflag != 0)&& (list[17].submitflag == 0)}">
								<a href="${ctx}/partyManage/discuss/index/?proId=${list[17].id}" role="button" class="btn">填报</a>
								<a href="${ctx}/partyManage/DQRecord/submit/?pdid=${list[17].id}&&recordid=${record.id}" role="button" class="btn">提交</a>
							</c:if>
							<c:if test="${(record.state == 5)&&(list[17].checkflag != 0) && (list[17].submitflag == 1)}">
								<a href="#tuihuiel" data-toggle="modal" role="button" class="btn">退回</a>
								<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[17].id}&&recordid=${record.id}&&checkresult=0"
								 role="button" class="btn" onclick="return confirm('结束本环节，进入下一环节！');">审核</a>
							<a href="${ctx}/partyManage/DQRecord/stop/?pdid=${list[17].id}&&recordid=${record.id}" role="button" class="btn"
							 onclick="return confirm('终止该对象的入党进程！');">终止</a>
							</c:if>
						</c:if>
						<c:if test="${(record.state == 3) && (list[17].makeupflag == 1)}">
							<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[17].id}" role="button" class="btn">补录</a>
						</c:if>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style='text-align: center;'>壹拾玖</td>
				<td style='text-align: center;'>预备党员备案</td>
				<td style='text-align: center;'>
					<c:if test="${list[18].node == 19}">
						<c:if test="${list[18].makeupflag != 1}">
						<a href="${ctx}/partyManage/sPmBeforePartyRecord/detail/?proId=${list[18].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[18].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[18].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[18].node == 19}">
						<c:choose>
							<c:when test="${list[18].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[18].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[18].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[18].node == 19}">
						${list[18].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[18].node == 19}">
						${list[18].officeName}
					</c:if>
					
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[18].node == 19}">
						<c:choose>
							<c:when test="${list[18].checkflag == 0}">通过</c:when>
							<c:when test="${list[18].nowState == '0'}">
								<c:if test="${list[18].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[18].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[18].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[18].node == 19}" >
						<c:if test="${list[18].makeupflag != 1}">
							<c:if test="${(record.state == 5)&&(list[18].checkflag != 0)}">
								<a href="${ctx}/partyManage/sPmBeforePartyRecord/form/?proId=${list[18].id}" role="button" class="btn">填报</a>
								<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[18].id}&&recordid=${record.id}&&checkresult=0"
								 role="button" class="btn" onclick="return confirm('结束本环节，进入下一环节！');">提交通过</a>
							</c:if>
						</c:if>
						<c:if test="${(record.state == 3) && (list[18].makeupflag == 1)}">
							<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[18].id}" role="button" class="btn">补录</a>
						</c:if>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style='text-align: center;'>贰拾</td>
				<td style='text-align: center;'>入党宣誓活动纪实</td>
				<td style='text-align: center;'>
					<c:if test="${(list[19].node == 20)}">
						<c:if test="${list[19].makeupflag != 1}">
						<a href="${ctx}/partyManage/sPmOath/detail/?proId=${list[19].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[19].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[19].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[19].node == 20}">
						<c:choose>
							<c:when test="${list[19].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[19].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[19].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[19].node == 20}">
						${list[19].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[19].node == 20}">
						${list[19].officeName}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[19].node == 20}">
						<c:choose>
							<c:when test="${list[19].checkflag == 0}">通过</c:when>
							<c:when test="${list[19].nowState == '0'}">
								<c:if test="${list[19].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[19].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[19].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[19].node == 20}">
					<c:if test="${list[19].makeupflag != 1}">
						<c:if test="${(record.state == 3)&&(list[19].checkflag != 0)}">
							<a href="${ctx}/partyManage/sPmOath/form/?proId=${list[19].id}" role="button" class="btn">填报</a>
						</c:if>
					</c:if>
					<c:if test="${(record.state == 3) && (list[19].makeupflag == 1)}">
						<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[19].id}" role="button" class="btn">补录</a>
					</c:if>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style='text-align: center;'>贰拾壹</td>
				<td style='text-align: center;'>预备党员考察纪实</td>
				<td style='text-align: center;'>
					<c:if test="${(list[20].node == 21)}">
						<c:if test="${list[20].makeupflag != 1}">
						<a href="${ctx}/partyManage/sPmProbationary/detail/?proId=${list[20].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[20].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[20].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[20].node == 21}">
						<c:choose>
							<c:when test="${list[20].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[20].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[20].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[20].node == 21}">
						${list[20].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[20].node == 21}">
						${list[20].officeName}
					</c:if>
					
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[20].node == 21}">
						<c:choose>
							<c:when test="${list[20].checkflag == 0}">通过</c:when>
							<c:when test="${list[20].nowState == '0'}">
								<c:if test="${list[20].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[20].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[20].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[20].node == 21}" >
						<c:if test="${list[20].makeupflag != 1}">
							<c:if test="${(record.state == 0)&&(list[20].checkflag != 0)}">
								<a href="${ctx}/partyManage/sPmProbationary/form/?proId=${list[20].id}" role="button" class="btn">填报</a>
								<%-- <a href="${ctx}/partyManage/DQRecord/submit/?pdid=${list[20].id}&&recordid=${record.id}" role="button" class="btn">提交</a> --%>
							</c:if>
							<%-- <c:if test="${(record.state == 3)&&(list[20].checkflag != 0) && (list[20].submitflag == 0)}">
								<a href="${ctx}/partyManage/sPmProbationary/form/?proId=${list[20].id}" role="button" class="btn">填报</a>
								<a href="${ctx}/partyManage/DQRecord/submit/?pdid=${list[20].id}&&recordid=${record.id}" role="button" class="btn">提交</a>
							</c:if> --%>
							<c:if test="${(record.state == 3)&&(list[20].checkflag != 0)}">
								<!-- <a href="#weitongguo2" data-toggle="modal" role="button" class="btn">退回</a> -->
								<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[20].id}&&recordid=${record.id}&&checkresult=0"
								 role="button" class="btn" onclick="return confirm('结束本环节，进入下一环节！');">审核</a>
							<a href="${ctx}/partyManage/DQRecord/stop/?pdid=${list[20].id}&&recordid=${record.id}" role="button" class="btn"
							 onclick="return confirm('终止该对象的入党进程！');">终止</a>
							</c:if>
						</c:if>
						<c:if test="${(record.state == 3) && (list[20].makeupflag == 1)}">
							<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[20].id}" role="button" class="btn">补录</a>
						</c:if>
					</c:if>
				</td>
			</tr>
<!-- 预备党员转正阶段 -->
			<tr>
				<td rowspan="5" style='text-align: center;'>
					预<br/>
					备<br/>
					党<br/>
					员<br/>
					转<br/>
					正<br/>
					申<br/>
					请<br/>
					审<br/>
					核
				</td>
				<td style='text-align: center;'>贰拾贰</td>
				<td style='text-align: center;'>预备党员转正申请</td>
				<td style='text-align: center;'>
					<c:if test="${(list[21].node == 22)}">
						<c:if test="${list[21].makeupflag != 1}">
						<a href="${ctx}/partyManage/sPmPositiveApp/list/?proId=${list[21].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[21].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[21].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[21].node == 22}">
						<c:choose>
							<c:when test="${list[21].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[21].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[21].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[21].node == 22}">
						${list[21].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[21].node == 22}">
						${list[21].officeName}
					</c:if>
					
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[21].node == 22}">
						<c:choose>
							<c:when test="${list[21].checkflag == 0}">通过</c:when>
							<c:when test="${list[21].nowState == '0'}">
								<c:if test="${list[21].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[21].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[21].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[21].node == 22}" >
						<c:if test="${list[21].makeupflag != 1}">
							<c:if test="${(record.state == 0) && (list[21].checkflag != 0) && (list[21].submitflag == 0)}">
								<a href="${ctx}/partyManage/sPmPositiveApp/form/?proId=${list[21].id}" role="button" class="btn">填报</a>
								<a href="${ctx}/partyManage/DQRecord/submit/?pdid=${list[21].id}&&recordid=${record.id}" role="button" class="btn">提交</a>
							</c:if>
							<c:if test="${(record.state == 3) && (list[21].checkflag != 0) && (list[21].submitflag == 1)}">
								<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[21].id}&&recordid=${record.id}&&checkresult=0"
								 role="button" class="btn" onclick="return confirm('结束本环节，进入下一环节！');">审核</a>
							<a href="${ctx}/partyManage/DQRecord/stop/?pdid=${list[21].id}&&recordid=${record.id}" role="button" class="btn"
							 onclick="return confirm('终止该对象的入党进程！');">终止</a>
								<a href="#tuihui8" data-toggle="modal" role="button" class="btn">退回</a>
							</c:if>
						</c:if>
						<c:if test="${(record.state == 3) && (list[21].makeupflag == 1)}">
							<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[21].id}" role="button" class="btn">补录</a>
						</c:if>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style='text-align: center;'>贰拾叁</td>
				<td style='text-align: center;'>预备党员转正预审</td>
				<td style='text-align: center;'>
					<c:if test="${(list[22].node == 23)}">
						<c:if test="${list[22].makeupflag != 1}">
							<a href="${ctx}/partyManage/sPmIntroduceOpinion/preDetail/?proId=${list[22].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[22].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[22].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[22].node == 23}">
						<c:choose>
							<c:when test="${list[22].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[22].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[22].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[22].node == 23}">
						${list[22].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[22].node == 23}">
						${list[22].officeName}
					</c:if>
					
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[22].node == 23}">
						<c:choose>
							<c:when test="${list[22].checkflag == 0}">通过</c:when>
							<c:when test="${list[22].nowState == '0'}">
								<c:if test="${list[22].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[22].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[22].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[22].node == 23}" >
						<c:if test="${list[22].makeupflag != 1}">
							<c:if test="${(record.state == 3) && (list[22].checkflag != 0) && (list[22].submitflag == 0)}">
								<a href="${ctx}/partyManage/sPmIntroduceOpinion/pre/?proId=${list[22].id}" role="button" class="btn">填报</a>
								<a href="${ctx}/partyManage/DQRecord/submit/?pdid=${list[22].id}&&recordid=${record.id}" role="button" class="btn">提交</a>
							</c:if>
							<c:if test="${(record.state == 5) && (list[22].checkflag != 0) && (list[22].submitflag == 1)}">
								<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[22].id}&&recordid=${record.id}&&checkresult=0"
								 role="button" class="btn" onclick="return confirm('结束本环节，进入下一环节！');">审核</a>
							<a href="${ctx}/partyManage/DQRecord/stop/?pdid=${list[0].id}&&recordid=${record.id}" role="button" class="btn"
							 onclick="return confirm('终止该对象的入党进程！');">终止</a>
								<a href="#tuihui9" data-toggle="modal" role="button" class="btn">退回</a>
							</c:if>
						</c:if>
						<c:if test="${(record.state == 3) && (list[22].makeupflag == 1)}">
							<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[22].id}" role="button" class="btn">补录</a>
						</c:if>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style='text-align: center;'>贰拾肆</td>
				<td style='text-align: center;'>拟进行转正公示</td>
				<td style='text-align: center;'>
					<c:if test="${(list[23].node == 24)}">
						<c:if test="${list[23].makeupflag != 1}">
						<a href="${ctx}/partyManage/sPmJustPartyPublic/detail/?proId=${list[23].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[23].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[23].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[23].node == 24}">
						<c:choose>
							<c:when test="${list[23].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[23].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[23].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[23].node == 24}">
						${list[23].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[23].node == 24}">
						${list[23].officeName}
					</c:if>
					
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[23].node == 24}">
						<c:choose>
							<c:when test="${list[23].checkflag == 0}">通过</c:when>
							<c:when test="${list[23].nowState == '0'}">
								<c:if test="${list[23].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[23].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[23].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[23].node == 24}" >
					<c:if test="${list[23].makeupflag != 1}">
						<c:if test="${(record.state == 3) && (list[23].checkflag != 0) && (list[23].submitflag == 0)}">
							<a href="${ctx}/partyManage/sPmJustPartyPublic/form/?proId=${list[23].id}" role="button" class="btn">填报</a>
							<a href="${ctx}/partyManage/DQRecord/submit/?pdid=${list[23].id}&&recordid=${record.id}" role="button" class="btn">提交</a>
						</c:if>
						<c:if test="${(record.state == 5) && (list[23].checkflag != 0) && (list[23].submitflag == 1)}">
							<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[23].id}&&recordid=${record.id}&&checkresult=0"
							  role="button" class="btn" onclick="return confirm('结束本环节，进入下一环节！');">审核</a>
							<a href="${ctx}/partyManage/DQRecord/stop/?pdid=${list[0].id}&&recordid=${record.id}" role="button" class="btn"
							 onclick="return confirm('终止该对象的入党进程！');">终止</a>
							<a href="#tuihuiten" data-toggle="modal" role="button" class="btn">退回</a>
						</c:if>
					</c:if>
					<c:if test="${(record.state == 3) && (list[23].makeupflag == 1)}">
						<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[23].id}" role="button" class="btn">补录</a>
					</c:if>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style='text-align: center;'>贰拾伍</td>
				<td style='text-align: center;'>预备党员转正</td>
				<td style='text-align: center;'>
					<c:if test="${list[24].node == 25}">
						<c:if test="${list[24].makeupflag != 1}">
						<a href="${ctx}/partyManage/regular/detail/?proId=${list[24].id}" role="button" class="btn">查看</a>
						</c:if>
						<c:if test="${list[24].makeupflag == 1}">
							<a href="${ctx}/partyManage/sPmSupRecord/detail/?proId=${list[24].id}" role="button" class="btn">查看补录</a>
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[24].node == 25}">
						<c:choose>
							<c:when test="${list[24].status == 'old' }">
									已完成
							</c:when>
							<c:when test="${record.state == 0}">
								<c:if test="${list[24].submitflag == 0}">
									进行中
								</c:if>
								<c:if test="${list[24].submitflag == 1}">
									待处理
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[24].node == 25}">
						${list[24].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[24].node == 25}">
						${list[24].officeName}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[24].node == 25}">
						<c:choose>
							<c:when test="${list[24].checkflag == 0}">通过</c:when>
							<c:when test="${list[24].nowState == '0'}">
								<c:if test="${list[24].checkflag == 2}">
									待处理
								</c:if>
								<c:if test="${list[24].checkflag == 3}">
									终止
								</c:if>
								<c:if test="${list[24].checkflag == 4}">
									退回
								</c:if>
							</c:when>
							<c:otherwise>
								进行中
							</c:otherwise>						
						</c:choose>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[24].node == 25}" >
					<c:if test="${list[24].makeupflag != 1}">
						<c:if test="${(record.state == 3) && (list[24].checkflag != 0) && (list[24].submitflag == 0)}">
							<a href="${ctx}/partyManage/sPmBeforePartyRecord/regular/?proId=${list[24].id}" role="button" class="btn">填报</a>
							<a href="${ctx}/partyManage/DQRecord/submit/?pdid=${list[24].id}&&recordid=${record.id}" role="button" class="btn">提交</a>
						</c:if>
						<c:if test="${(record.state == 5) && (list[24].checkflag != 0) && (list[24].submitflag == 1)}">
							<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[24].id}&&recordid=${record.id}&&checkresult=0" 
							role="button" class="btn" onclick="return confirm('结束本环节，进入下一环节！');">审核</a>
							<a href="${ctx}/partyManage/DQRecord/stop/?pdid=${list[0].id}&&recordid=${record.id}" role="button" class="btn"
							 onclick="return confirm('终止该对象的入党进程！');">终止</a>
							<a href="#tuihuiend" data-toggle="modal" role="button" class="btn">退回</a>
						</c:if>
					</c:if>
					<c:if test="${(record.state == 3) && (list[24].makeupflag == 1)}">
						<a href="${ctx}/partyManage/sPmSupRecord/list/?proId=${list[24].id}" role="button" class="btn">补录</a>
					</c:if>
					</c:if>
				</td>
			</tr>
			<tr>
				<td style='text-align: center;'>贰拾陆</td>
				<td style='text-align: center;'>入党材料归档</td>
				<td style='text-align: center;'></td>
				<td style='text-align: center;'>
					<c:if test="${list[25].status == 'old'}">
						已完成
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[25].node == 26}">
						${list[25].checkDate}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[25].node == 26}">
						${list[25].officeName}
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[25].node == 26}">
						<c:if test="${list[25].checkflag == 0}">
							通过
						</c:if>
					</c:if>
				</td>
				<td style='text-align: center;'>
					<c:if test="${list[25].node == 26}" >
						<c:if test="${(record.state == 5) && (list[25].checkflag != 0)}">
							<a href="${ctx}/partyManage/DQRecord/check/?pdid=${list[25].id}&&recordid=${record.id}&&checkresult=0"
							 role="button" class="btn" onclick="return confirm('所有流程完成，不可变更！');">材料归档确定</a>
						</c:if>
					</c:if>
				</td>
			</tr>
			<tr>
				<td colspan="9"></td>
			</tr>
		</tbody>
	</table>
<div id="weitongguo2" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="weitongguo2Label" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="weitongguo2Label">退回原因</h3>
    </div>
    <div class="modal-body">
    	<form action="${ctx}/partyManage/DQRecord/wtg1/?pdid=${list[20].id}&&recordid=${record.id}&&checkresult=4" method="post">
			原因：<textarea name="wtgtext" rows="4" cols="24" style="width: 90%"></textarea><br/>
			<div style="float: right;">
				<input type="submit" value="确定"/>
			</div>
    	</form>
    </div>
</div>
<div id="tuihui1" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="tuihui1Label" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="tuihui1Label">退回原因</h3>
    </div>
    <div class="modal-body">
		<form action="${ctx}/partyManage/DQRecord/th1/?pdid=${list[0].id}&&recordid=${record.id}&&checkresult=4" method="post">
			原因：<textarea name="tuihui" rows="4" cols="24" style="width: 90%"></textarea><br/>
			<div style="float: right;">
				<input type="submit" value="确定"/>
			</div>
		</form>
    </div>
</div>
<div id="tuihui2" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="tuihui2Label" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="tuihui2Label">退回原因</h3>
    </div>
    <div class="modal-body">
		<form action="${ctx}/partyManage/DQRecord/th1/?pdid=${list[5].id}&&recordid=${record.id}&&checkresult=4" method="post">
			原因：<textarea name="tuihui" rows="4" cols="24" style="width: 90%"></textarea><br/>
			<div style="float: right;">
				<input type="submit" value="确定"/>
			</div>
		</form>
    </div>
</div>
<div id="tuihui3" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="tuihui3Label" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="tuihui3Label">退回原因</h3>
    </div>
    <div class="modal-body">
		<form action="${ctx}/partyManage/DQRecord/th1/?pdid=${list[9].id}&&recordid=${record.id}&&checkresult=4" method="post">
			原因：<textarea name="tuihui" rows="4" cols="24" style="width: 90%"></textarea><br/>
			<div style="float: right;">
				<input type="submit" value="确定"/>
			</div>
		</form>
    </div>
</div>
<div id="tuihui4" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="tuihui4Label" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="tuihui4Label">退回原因</h3>
    </div>
    <div class="modal-body">
		<form action="${ctx}/partyManage/DQRecord/th1/?pdid=${list[11].id}&&recordid=${record.id}&&checkresult=4" method="post">
			原因：<textarea name="tuihui" rows="4" cols="24" style="width: 90%"></textarea><br/>
			<div style="float: right;">
				<input type="submit" value="确定"/>
			</div>
		</form>
    </div>
</div>
<div id="tuihui5" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="tuihui5Label" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="tuihui5Label">退回原因</h3>
    </div>
    <div class="modal-body">
		<form action="${ctx}/partyManage/DQRecord/th1/?pdid=${list[13].id}&&recordid=${record.id}&&checkresult=4" method="post">
			原因：<textarea name="tuihui" rows="4" cols="24" style="width: 90%"></textarea><br/>
			<div style="float: right;">
				<input type="submit" value="确定"/>
			</div>
		</form>
    </div>
</div>
<div id="tuihui6" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="tuihui6Label" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="tuihui6Label">退回原因</h3>
    </div>
    <div class="modal-body">
		<form action="${ctx}/partyManage/DQRecord/th1/?pdid=${list[14].id}&&recordid=${record.id}&&checkresult=4" method="post">
			原因：<textarea name="tuihui" rows="4" cols="24" style="width: 90%"></textarea><br/>
			<div style="float: right;">
				<input type="submit" value="确定"/>
			</div>
		</form>
    </div>
</div>
<div id="tuihui7" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="tuihui7Label" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="tuihui7Label">退回原因</h3>
    </div>
    <div class="modal-body">
		<form action="${ctx}/partyManage/DQRecord/th1/?pdid=${list[15].id}&&recordid=${record.id}&&checkresult=4" method="post">
			原因：<textarea name="tuihui" rows="4" cols="24" style="width: 90%"></textarea><br/>
			<div style="float: right;">
				<input type="submit" value="确定"/>
			</div>
		</form>
    </div>
</div>
<div id="tuihui8" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="tuihui8Label" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="tuihui8Label">退回原因</h3>
    </div>
    <div class="modal-body">
		<form action="${ctx}/partyManage/DQRecord/th1/?pdid=${list[21].id}&&recordid=${record.id}&&checkresult=4" method="post">
			原因：<textarea name="tuihui" rows="4" cols="24" style="width: 90%"></textarea><br/>
			<div style="float: right;">
				<input type="submit" value="确定"/>
			</div>
		</form>
    </div>
</div>
<div id="tuihui9" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="tuihui9Label" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="tuihui9Label">退回原因</h3>
    </div>
    <div class="modal-body">
		<form action="${ctx}/partyManage/DQRecord/th1/?pdid=${list[22].id}&&recordid=${record.id}&&checkresult=4" method="post">
			原因：<textarea name="tuihui" rows="4" cols="24" style="width: 90%"></textarea><br/>
			<div style="float: right;">
				<input type="submit" value="确定"/>
			</div>
		</form>
    </div>
</div>
<div id="tuihuiten" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="tuihuitenLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="tuihuitenLabel">退回原因</h3>
    </div>
    <div class="modal-body">
		<form action="${ctx}/partyManage/DQRecord/th1/?pdid=${list[23].id}&&recordid=${record.id}&&checkresult=4" method="post">
			原因：<textarea name="tuihui" rows="4" cols="24" style="width: 90%"></textarea><br/>
			<div style="float: right;">
				<input type="submit" value="确定"/>
			</div>
		</form>
    </div>
</div>
<div id="tuihuiel" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="tuihuiel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="tuihuielLabel">退回原因</h3>
    </div>
    <div class="modal-body">
		<form action="${ctx}/partyManage/DQRecord/th1/?pdid=${list[17].id}&&recordid=${record.id}&&checkresult=4" method="post">
			原因：<textarea name="tuihui" rows="4" cols="24" style="width: 90%"></textarea><br/>
			<div style="float: right;">
				<input type="submit" value="确定"/>
			</div>
		</form>
    </div>
</div>
<div id="tuihuiep" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="tuihuiep" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="tuihuiepLabel">退回原因</h3>
    </div>
    <div class="modal-body">
		<form action="${ctx}/partyManage/DQRecord/th1/?pdid=${list[6].id}&&recordid=${record.id}&&checkresult=4" method="post">
			原因：<textarea name="tuihui" rows="4" cols="24" style="width: 90%"></textarea><br/>
			<div style="float: right;">
				<input type="submit" value="确定"/>
			</div>
		</form>
    </div>
</div>
<div id="tuihuisan" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="tuihuisan" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="tuihuisanLabel">退回原因</h3>
    </div>
    <div class="modal-body">
		<form action="${ctx}/partyManage/DQRecord/th1/?pdid=${list[2].id}&&recordid=${record.id}&&checkresult=4" method="post">
			原因：<textarea name="tuihui" rows="4" cols="24" style="width: 90%"></textarea><br/>
			<div style="float: right;">
				<input type="submit" value="确定"/>
			</div>
		</form>
    </div>
</div>
<div id="tuihuift" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="tuihuift" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="tuihuiftLabel">退回原因</h3>
    </div>
    <div class="modal-body">
		<form action="${ctx}/partyManage/DQRecord/th1/?pdid=${list[5].id}&&recordid=${record.id}&&checkresult=4" method="post">
			原因：<textarea name="tuihui" rows="4" cols="24" style="width: 90%"></textarea><br/>
			<div style="float: right;">
				<input type="submit" value="确定"/>
			</div>
		</form>
    </div>
</div>
<div id="tuihuitt" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="tuihuitt" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="tuihuittLabel">退回原因</h3>
    </div>
    <div class="modal-body">
		<form action="${ctx}/partyManage/DQRecord/th1/?pdid=${list[9].id}&&recordid=${record.id}&&checkresult=4" method="post">
			原因：<textarea name="tuihui" rows="4" cols="24" style="width: 90%"></textarea><br/>
			<div style="float: right;">
				<input type="submit" value="确定"/>
			</div>
		</form>
    </div>
</div>
<div id="tuihuitf" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="tuihuitf" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="tuihuitfLabel">退回原因</h3>
    </div>
    <div class="modal-body">
		<form action="${ctx}/partyManage/DQRecord/th1/?pdid=${list[14].id}&&recordid=${record.id}&&checkresult=4" method="post">
			原因：<textarea name="tuihui" rows="4" cols="24" style="width: 90%"></textarea><br/>
			<div style="float: right;">
				<input type="submit" value="确定"/>
			</div>
		</form>
    </div>
</div>
<div id="tuihuiend" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="tuihuiend" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="tuihuiendLabel">退回原因</h3>
    </div>
    <div class="modal-body">
		<form action="${ctx}/partyManage/DQRecord/th1/?pdid=${list[24].id}&&recordid=${record.id}&&checkresult=4" method="post">
			原因：<textarea name="tuihui" rows="4" cols="24" style="width: 90%"></textarea><br/>
			<div style="float: right;">
				<input type="submit" value="确定"/>
			</div>
		</form>
    </div>
</div>
</body>
</html>
