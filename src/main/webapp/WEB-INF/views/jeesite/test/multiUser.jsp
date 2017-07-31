<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>用户多选</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/multiUser/demo">用户多选</a></li>
	</ul><br/>
	<form:form id="inputForm"  method="post" class="form-horizontal">
		<div class="control-group">
			<label class="control-label">参会人员:</label>
			<div class="controls">
				<input id="userDataRelation"  type="hidden" />
				<ol id="userSelectList" style="width: 400px;"></ol>
				<ol class="clearfix"></ol>
				<a id="relationButton" href="javascript:" class="btn ">添加人员</a>
				<script type="text/javascript">
					var userSelect = [];
					function userSelectAddOrDel(id,title){
						var isExtents = false, index = 0;
						for (var i=0; i<userSelect.length; i++){
							if (userSelect[i][0]==id){
								isExtents = true;
								index = i;
							}
						}
						if(isExtents){
							userSelect.splice(index,1);
						}else{
							userSelect.push([id,title]);
						}
						userSelectRefresh();
					}
					function userSelectRefresh(){
						$("#userDataRelation").val("");
						$("#userSelectList").children().remove();
						for (var i=0; i<userSelect.length; i++){
							$("#userSelectList").append("<li  style='float: left;list-style-type:none;width: 25%;'>"+userSelect[i][1]+"<a href=\"javascript:\" onclick=\"userSelectAddOrDel('"+userSelect[i][0]+"','"+userSelect[i][1]+"');\">×</a></li>");
							$("#userDataRelation").val($("#userDataRelation").val()+userSelect[i][0]+",");
						}
					}
					//$.getJSON("${ctx}/cms/article/findByIds",{ids:$("#userDataRelation").val()},function(data){
					//	for (var i=0; i<data.length; i++){
					//		userSelect.push([data[i][1],data[i][2]]);
					//	}
					//	userSelectRefresh();
					//});
					$("#relationButton").click(function(){
						top.$.jBox.open("iframe:${ctx}/sys/multiUser/userList?pageSize=10&flag=userSelect", "选择用户",$(top.document).width()-220,$(top.document).height()-180,{
							buttons:{"确定":true}, loaded:function(h){
								$(".jbox-content", top.document).css("overflow-y","hidden");
							}
						});
					});
				</script>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">未参会人员:</label>
			<div class="controls">
				<input id="userDataRelation1"  type="hidden" />
				<ol id="userSelectList1" style="width: 400px;"></ol>
				<ol class="clearfix"></ol>
				<a id="relationButton1" href="javascript:" class="btn ">添加人员</a>
				<script type="text/javascript">
					var userSelect1 = [];
					function userSelect1AddOrDel(id,title){
						var isExtents = false, index = 0;
						for (var i=0; i<userSelect1.length; i++){
							if (userSelect1[i][0]==id){
								isExtents = true;
								index = i;
							}
						}
						if(isExtents){
							userSelect1.splice(index,1);
						}else{
							userSelect1.push([id,title]);
						}
						userSelect1Refresh();
					}
					function userSelect1Refresh(){
						$("#userDataRelation1").val("");
						$("#userSelectList1").children().remove();
						for (var i=0; i<userSelect1.length; i++){
							$("#userSelectList1").append("<li  style='float: left;list-style-type:none;width: 25%;'>"+userSelect1[i][1]+"<a href=\"javascript:\" onclick=\"userSelect1AddOrDel('"+userSelect1[i][0]+"','"+userSelect1[i][1]+"');\">×</a></li>");
							$("#userDataRelation1").val($("#userDataRelation1").val()+userSelect1[i][0]+",");
						}
					}
					//$.getJSON("${ctx}/cms/article/findByIds",{ids:$("#userDataRelation1").val()},function(data){
					//	for (var i=0; i<data.length; i++){
					//		userSelect1.push([data[i][1],data[i][2]]);
					//	}
					//	userSelect1Refresh();
					//});
					$("#relationButton1").click(function(){
						top.$.jBox.open("iframe:${ctx}/sys/multiUser/userList?pageSize=10&flag=userSelect1", "选择用户",$(top.document).width()-220,$(top.document).height()-180,{
							buttons:{"确定":true}, loaded:function(h){
								$(".jbox-content", top.document).css("overflow-y","hidden");
							}
						});
					});
				</script>
			</div>
		</div>
	</form:form>
</body>
</html>