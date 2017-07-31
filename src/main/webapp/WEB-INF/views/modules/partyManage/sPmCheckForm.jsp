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
            // Custom example logic
            var uploader = new plupload.Uploader({
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
                    FileUploaded: function (up, file, info) {
                        var res = $.parseJSON(info.response);
                        if (res.status) {
                            $(".file_url").val(res.fileUrl);
                            $(".file_name").val(file.name);
                            var filea = "<a href='${ctx}/partyManage/public/downfile?fileUrl=" + res.fileUrl + "' role='button' class='btn'>下载</a>";
                            $(".file_name").after(filea);
                        }
                    },
                    FilesAdded: function (up, files) {
                        uploader.start();
                    },
                    UploadProgress: function (up, file) {

                    },
                    Error: function (up, err) {
                        alert(err.message);
                    }
                }
            });
            uploader.init();
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/partyManage/sPmCheck/form?id=${sPmCheck.id}"></a></li>
	</ul><br/>
<div id="container">	
	<form:form id="inputForm" modelAttribute="sPmCheck" action="${ctx}/partyManage/sPmCheck/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">姓名：</label>
			<div class="controls">
				<form:input path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">性别：</label>
			<div class="controls">
				<form:input path="sex" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出生年月：</label>
			<div class="controls">
				<input name="dateBirth" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sPmCheck.dateBirth}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请入党时间：</label>
			<div class="controls">
				<input name="apcaTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sPmCheck.apcaTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">现任职务：</label>
			<div class="controls">
				<form:input path="incumbent" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">在重大政治斗争中的表现：</label>
			<div class="controls">
				<form:input path="political" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">遵纪守法及遵守社会公德情况：</label>
			<div class="controls">
				<form:input path="abideLaw" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">其他需要说明的问题：</label>
			<div class="controls">
				<form:input path="problem" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">支部意见：</label>
			<div class="controls">
				<form:input path="branchOpinion" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">基层党委意见：</label>
			<div class="controls">
				<form:input path="partyOpinion" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="64" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">流程数据id：</label>
			<div class="controls">
				<form:input path="proId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div> --%>
		
		<div class="controls-group" id="viewImg">
            <label class="control-label">文件路径：</label>
            <div class="controls">
                <form:input readonly="true" path="fileUrl" htmlEscape="false" maxlength="255" class="input-xlarge file_url"/>
            </div>
            <label class="control-label">文件名：</label>
            <div class="controls">
                <form:input readonly="true" path="fileName" htmlEscape="false" maxlength="255" class="input-xlarge file_name"/>
            </div>
            <label class="control-label">上传操作人：</label>
            <div class="controls">
                <form:input readonly="true" path="uploader" htmlEscape="false" maxlength="255" class="input-xlarge uploader"/>
            </div>
            <label class="control-label">上传时间：</label>
            <div class="controls">
                <input name="uploadTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                       value="<fmt:formatDate value="${sPmCheck.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            </div>
		</div>

		<div class="controls-group" id="fileOperat">
            <div class="controls">
			    <input id="pickfiles" class="btn btn-primary" type="button" value="选择文件"/>
            </div>
			<%--<input id="uploadfiles" class="btn btn-primary" type="button" value="上传"/>--%>
		</div>
		
		<div class="control-group">
	        <label class="control-label">请先保存,之后再下载：</label>
	        <div class="controls">
	            <a href="${ctx}/partyManage/download/check" role="button" class="btn">发展对象政审表下载</a>
	        </div>
    	</div>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</div>
</body>
</html>