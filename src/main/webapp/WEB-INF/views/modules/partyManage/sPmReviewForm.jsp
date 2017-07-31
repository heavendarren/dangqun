<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>表单管理</title>
	<%@include file="/WEB-INF/views/include/fileUp.jsp" %>
	<meta name="decorator" content="default"/>
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
                        var filea = "<a href='${ctx}/partyManage/public/downfile?fileUrl=" + res.fileUrl + "' role='button' class='btn'>下载</a>";
                        $("#" + file.id + "btn").after(filea);
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
		<li class="active"><a href="${ctx}/partyManage/sPmReview/form?id=${sPmReview.id}"></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sPmReview" action="${ctx}/partyManage/sPmReview/save" method="post" class="form-horizontal">
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
			<label class="control-label">民族：</label>
			<div class="controls">
				<form:input path="nation" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">出生年月：</label>
			<div class="controls">
				<input name="dateBirth" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sPmReview.dateBirth}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文化程度：</label>
			<div class="controls">
				<form:input path="degree" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">参加工作时间：</label>
			<div class="controls">
				<input name="workHours" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sPmReview.workHours}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请入党时间：</label>
			<div class="controls">
				<input name="partyTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sPmReview.partyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">确定发展对象时间：</label>
			<div class="controls">
				<input name="objectTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sPmReview.objectTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">确定积极分子问题：</label>
			<div class="controls">
				<input name="activistTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sPmReview.activistTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">短期集中培训时间：</label>
			<div class="controls">
				<input name="shortTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sPmReview.shortTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所在支部预审意见：</label>
			<div class="controls">
				<form:input path="branOpin" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">党总支部预审意见：</label>
			<div class="controls">
				<form:input path="genOpin" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上级党（工）委预审意见：</label>
			<div class="controls">
				<form:input path="partyOpin" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">审卷人：</label>
			<div class="controls">
				<form:input path="trial" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">领卷人：</label>
			<div class="controls">
				<form:input path="collar" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<%-- <div class="control-group">
			<label class="control-label">更新时间：</label>
			<div class="controls">
				<input name="updateUpdate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sPmReview.updateUpdate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div> --%>
		<%-- <div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">流程数据id：</label>
			<div class="controls">
				<form:input path="proId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div> --%>
		<div id="container">
			<div class="controls-group" id="viewImg">
	            <%-- <label class="control-label">文件路径：</label>
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
	            <a href="${ctx}/partyManage/download/review" role="button" class="btn">发展党员工作预审表下载</a>
	        </div>
    	</div>
		
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>