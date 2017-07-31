<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@include file="/WEB-INF/views/include/fileUp.jsp"%>

	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>

	
	<form:form id="inputForm" modelAttribute="sPmMinutesS" action="${ctx}/partyManage/sPmMinutesS/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<%-- <div class="control-group">
			<label class="control-label">数据流程ID：</label>
			<div class="controls">
				<form:input path="proId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div> --%>
		<div class="control-group">
			<label class="control-label">会议议题：</label>
			<div class="controls">
				<form:input path="conTop" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">时间：</label>
			<div class="controls">
				<input name="conTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sPmMinutesS.conTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">地点：</label>
			<%-- <div class="controls">
				<form:input path="place" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div> --%>
			<div class="controls">
            	<sys:treeselect2 id="sPmMinutesSPlace" name="sPmMinutesS" value="${sPmMinutesS.place}"
                             labelName="placeName"
                             labelValue="${fns:getDictLabel(sPmMinutesS.place, 'JG', '')}"
                             title="地点" url="/sys/codeTree/treeData?type=JG" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        	</div>
		</div>
		<div class="control-group">
				<label class="control-label">主持人：</label>
				<div class="controls">
					<sys:treeselect2 id="sPmMinutesSHost" name="sPmMinutesS" value="${sPmMinutesS.host}"
                             labelName="hostName"
                             labelValue="${fns:getDictLabel(sPmMinutesS.host, 'CYLB', '')}"
                             title="主持人" url="/sys/codeTree/treeData?type=CYLB" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
				</div>
		</div>
		<div class="control-group">
				<label class="control-label">记录人：</label>
				<div class="controls">
					<sys:treeselect2 id="sPmMinutesSnoteTaker" name="sPmMinutesS" value="${sPmMinutesS.noteTaker}"
                             labelName="noteTakerName"
                             labelValue="${fns:getDictLabel(sPmMinutesS.noteTaker, 'CYLB', '')}"
                             title="记录人" url="/sys/codeTree/treeData?type=CYLB" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
				</div>
		</div>
		<div class="control-group">
			<label class="control-label">入党介绍人：</label>
			<div class="controls">
				<form:input path="perPar" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">列席人：</label>
			<%-- <div class="controls">
				<form:input path="perAtt" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div> --%>
			<div class="controls">
            	<sys:treeselect2 id="sPmMinutesSperAtt" name="sPmMinutesS" value="${sPmMinutesS.perAtt}"
                             labelName="perAttName"
                             labelValue="${fns:getDictLabel(sPmMinutesS.perAtt, 'CYLB', '')}"
                             title="列席人" url="/sys/codeTree/treeData?type=CYLB" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        	</div>
		</div>
		<div class="control-group">
			<label class="control-label">应到会有表决权的党员人数：</label>
			<div class="controls">
				<form:input path="arrNum" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实到会有表决权的党员人数：</label>
			<div class="controls">
				<form:input path="actNum" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
				<label class="control-label">参加人员名单：</label>
				<div class="controls">
					<input id="userDataRelation" type="hidden" />
					<ol id="userSelectList" style="width: 400px;"></ol>
					<ol class="clearfix"></ol>
					<a id="relationButton" href="javascript:" class="btn ">添加人员</a>
					<script type="text/javascript">
						var userSelect = [];
						function userSelectAddOrDel(id, title) {
							var isExtents = false, index = 0;
							for ( var i = 0; i < userSelect.length; i++) {
								if (userSelect[i][0] == id) {
									isExtents = true;
									index = i;
								}
							}
							if (isExtents) {
								userSelect.splice(index, 1);
							} else {
								userSelect.push([ id, title ]);
							}
							userSelectRefresh();
						}
						function userSelectRefresh() {
							$("#userDataRelation").val("");
							$("#userSelectList").children().remove();
							for ( var i = 0; i < userSelect.length; i++) {
								$("#userSelectList")
										.append(
												"<li  style='float: left;list-style-type:none;width: 25%;'>"
														+ userSelect[i][1]
														+ "<input type='hidden' name='attenList' value='"+userSelect[i][0]+
							"' /><a href=\"javascript:\" onclick=\"userSelectAddOrDel('"
														+ userSelect[i][0]
														+ "','"
														+ userSelect[i][1]
														+ "');\">×</a></li>");
								$("#userDataRelation").val(
										$("#userDataRelation").val()
												+ userSelect[i][0] + ",");
							}
						}
						$("#relationButton")
								.click(
										function() {
											top.$.jBox
													.open(
															"iframe:${ctx}/sys/multiUser/userList?pageSize=10&flag=userSelect",
															"选择用户",
															$(top.document)
																	.width() - 220,
															$(top.document)
																	.height() - 180,
															{
																buttons : {
																	"确定" : true
																},
																loaded : function(
																		h) {
																	$(
																			".jbox-content",
																			top.document)
																			.css(
																					"overflow-y",
																					"hidden");
																}
															});
										});
					</script>
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">缺席人员名单：</label>
				<div class="controls">
					<input id="userDataRelation1" type="hidden" />
					<ol id="userSelectList1" style="width: 300px;"></ol>
					<ol class="clearfix"></ol>
					<a id="relationButton1" href="javascript:" class="btn ">添加人员</a>
					<script type="text/javascript">
						var userSelect1 = [];
						function userSelect1AddOrDel(id, title) {
							var isExtents = false, index = 0;
							for ( var i = 0; i < userSelect1.length; i++) {
								if (userSelect1[i][0] == id) {
									isExtents = true;
									index = i;
								}
							}
							if (isExtents) {
								userSelect1.splice(index, 1);
							} else {
								userSelect1.push([ id, title ]);
							}
							userSelect1Refresh();
						}
						function userSelect1Refresh() {
							$("#userDataRelation1").val("");
							$("#userSelectList1").children().remove();
							for ( var i = 0; i < userSelect1.length; i++) {
								$("#userSelectList1")
										.append(
												"<li  style='float: left;list-style-type:none;width: 25%;'>"
														+ userSelect1[i][1]
														+ "<input type='hidden' name='absList' value='"+userSelect1[i][0]+
							"'/><a href=\"javascript:\" onclick=\"userSelect1AddOrDel('"
														+ userSelect1[i][0]
														+ "','"
														+ userSelect1[i][1]
														+ "');\">×</a></li>");
								$("#userDataRelation1").val(
										$("#userDataRelation1").val()
												+ userSelect1[i][0] + ",");
							}
						}
						$("#relationButton1")
								.click(
										function() {
											top.$.jBox
													.open(
															"iframe:${ctx}/sys/multiUser/userList?pageSize=10&flag=userSelect1",
															"选择用户",
															$(top.document)
																	.width() - 220,
															$(top.document)
																	.height() - 180,
															{
																buttons : {
																	"确定" : true
																},
																loaded : function(
																		h) {
																	$(
																			".jbox-content",
																			top.document)
																			.css(
																					"overflow-y",
																					"hidden");
																}
															});
										});
					</script>
				</div>
			</div>
		<div class="control-group">
			<label class="control-label">会议内容：</label>
			<div class="controls">
				<form:input path="meetMin" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div> --%>
		<div class="form-actions">
			<input id="SpmMinutessSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="SpmMinutessCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
