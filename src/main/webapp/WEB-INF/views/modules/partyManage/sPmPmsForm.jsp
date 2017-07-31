<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>表单管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/fileUp.jsp" %>
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
			var map = {};
        	var uploads = new plupload.Uploader({
            runtimes: 'html5,flash,silverlight,html4',
            browse_button: 'pickfiles', // you can pass in id...
            container: document.getElementById('container'), // ... or DOM Element itself
            url: '${ctx}/sys/fileUp/upFile',
            flash_swf_url: '${ctxStatic}/plupload/2.3.1/Moxie.swf',
            silverlight_xap_url: '${ctxStatic}/plupload/2.3.1/Moxie.xap',
            chunk_size: '1mb',
            filters: {
                max_file_size: '10mb',
                prevent_duplicates: true,
                mime_types: [{
                    title: "图片类型",
                    extensions: "jpg,gif,png"
                }]
            },
            init: {
                PostInit: function () {
                    document.getElementById('uploadfiles').onclick = function () {
                        uploads.start();
                        return false;
                    };
                },
                FileUploaded: function (up, file, info) {
                    var res = $.parseJSON(info.response);
                    if (res.status) {
                        var newhtml = "<span>上传完毕</span>";
                        $("#" + file.id + "btn").parent().append(newhtml);
                        $("#" + file.id + "btn").remove();
                        $("#" + file.id + "url").val(res.fileUrl);
                    }
                },
                FilesAdded: function (up, files) {
                    plupload.each(files, function (file) {
                        map[file.id] = $("#fileType").val();
                        var newhtml = "<div class='controls' id='" + file.id + "div'>" +
                            "<label>文件路径：<input readonly='true' id='" + file.id + "url' name='fileUrls' maxlength='255' value='待生成' /></label>" +
                            "<label>文件名：<input readonly='true' id='" + file.name + "' name='fileNames' maxlength='255' value=" + file.name + " /></label>" +
                            "<button id='" + file.id + "btn' v-fid='" + file.id + "' class='btn btn-default' type='button'>删除</button></div>";
                        $("#viewImg").append(newhtml);
                    });
	                },
	                Error: function (up, err) {
	                    alert(err.message);
	                }
	            }
	        });
	        uploads.init();
	        $("#viewImg").on("click", "button", function () {
	            function del(id) {
	                uploads.removeFile(id);
	                $("#" + id + "div").remove();
	                map[id] = "";
	            }
	
	            del($(this).attr('v-fid'));
	        });
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/partyManage/sPmPms/form"></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sPmPms" action="${ctx}/partyManage/sPmPms/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<input type="hidden" name="proId" value="${sPmPms.proId}"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">推荐时间：</label>
			<div class="controls">
				<input name="recTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sPmPms.recTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">推荐地点：</label>
			<%-- <div class="controls">
				<form:input path="recLoca" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div> --%>
			<div class="controls">
            <sys:treeselect2 id="originPlace" name="originPlace" value="${testData.user.id}" labelName="user.name" labelValue="${testData.user.name}"
                             title="推荐地点" url="/sys/codeTree/treeData?type=JG" cssClass="input-small" allowClear="true" notAllowSelectParent="true"/>
        	</div>
		</div>
		<div class="control-group">
			<label class="control-label">党员总数：</label>
			<div class="controls">
				<form:input path="tnoPm" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参加推荐党员人数：</label>
			<div class="controls">
				<form:input path="numPm" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">发出推荐票：</label>
			<div class="controls">
				<form:input path="lssueRec" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收回推荐票：</label>
			<div class="controls">
				<form:input path="takeRec" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">有效票：</label>
			<div class="controls">
				<form:input path="validVote" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">无效票：</label>
			<div class="controls">
				<form:input path="invalidBall" htmlEscape="false" maxlength="18" class="input-xlarge  digits"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">被推荐人结果：</label>
			<div class="controls">
				<form:input path="resultRec" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">计票人签字：</label>
			<div class="controls">
				<form:input path="staSign" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">监票人签字：</label>
			<div class="controls">
				<form:input path="scrSign" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支部书记签字：</label>
			<div class="controls">
				<form:input path="branSign" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div> --%>
		<div id="container">
			<div class="controls-group" id="viewImg">
	           <%--  <label class="control-label">文件路径：</label>
	            <div class="controls">
	                <form:input readonly="true" path="fileUrl" htmlEscape="false" maxlength="255" class="input-xlarge file_url"/>
	            </div>
	            <label class="control-label">文件名：</label>
	            <div class="controls">
	                <form:input readonly="true" path="fileName" htmlEscape="false" maxlength="255" class="input-xlarge file_name"/>
	            </div> --%>
	            <%-- <label class="control-label">上传操作人：</label>
	            <div class="controls">
	                <form:input readonly="true" path="uploader" htmlEscape="false" maxlength="255" class="input-xlarge uploader"/>
	            </div>
	            <label class="control-label">上传时间：</label>
	            <div class="controls">
	                <input name="uploadTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
	                       value="<fmt:formatDate value="${sPmMrs.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
	            </div>  --%>
			</div>
		</div>
		
		
		<div class="controls-group" id="fileOperat">
            <div class="controls">
			    <input id="pickfiles" class="btn btn-primary" type="button" value="选择文件"/>
			    <input id="uploadfiles" class="btn btn-primary" type="button" value="上传"/>
            </div>
		</div>
		<div class="control-group">
	        <label class="control-label">请先保存,之后再下载：</label>
	        <div class="controls">
	            <a href="${ctx}/partyManage/download/pms" role="button" class="btn">党员推荐汇总表下载</a>
	        </div>
    	</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>