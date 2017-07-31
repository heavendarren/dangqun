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
            $("#menu-list-box li").click(function(){
                $("#menu-list-box li").removeClass("active");
                $(this).addClass("active");
                //alert(this.type);
                $('#type').val(this.type);

            });
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

            var uploader = new plupload.Uploader({
                runtimes : 'html5,flash,silverlight,html4',
                browse_button : 'pickfiles',
                container : document.getElementById('container'),
                url : '${ctx}/sys/fileUp/upFile',
                flash_swf_url : '${ctxStatic}/plupload/2.3.1/Moxie.swf',
                silverlight_xap_url : '${ctxStatic}/plupload/2.3.1/Moxie.xap',
                chunk_size : '1mb',
                filters : {
                    max_file_size : '10mb',
                    prevent_duplicates : true,
                    mime_types : [ {
                        title : "图片类型",
                        extensions : "jpg,gif,png"
                    } ]
                },
                init : {
                    FileUploaded : function(up, file, info) {
                        var res = $.parseJSON(info.response);
                        if (res.status) {
                            //var img = "<img src='" + res.fileUrl + "'/>";
                            //$("#viewImg").append(img);
                            $(".file_url").val(res.fileUrl);
                            $(".file_name").val(file.name);
                            var filea = "<a href='${ctx}/partyManage/public/downfile?fileUrl=" + res.fileUrl + "' role='button' class='btn'>下载</a>";
                            $(".file_name").after(filea);lue = file.name;
                        }
                    },
                    FilesAdded : function(up, files) {
                        uploader.start();
                    },
                    UploadProgress : function(up, file) {

                    },
                    Error : function(up, err) {
                        alert(err.message);
                    }
                }
            });
            uploader.init();


        });
	</script>
</head>
<body>
<ul class="nav nav-tabs" id="menu-list-box">

	<li <c:if test="${sPmProbationary.type == '1'}">class="active" </c:if> type="1"><a href="${ctx}/partyManage/sPmProbationary/form?proId=${proId}&type=1">第一季度考察表</a></li>
	<li <c:if test="${sPmProbationary.type == '2'}">class="active" </c:if> type="2"><a href="${ctx}/partyManage/sPmProbationary/form?proId=${proId}&type=2">第二季度考察表</a></li>
	<li <c:if test="${sPmProbationary.type == '3'}">class="active" </c:if> type="3"><a href="${ctx}/partyManage/sPmProbationary/form?proId=${proId}&type=3">第三季度考察表</a></li>
	<li <c:if test="${sPmProbationary.type == '4'}">class="active" </c:if> type="4"><a href="${ctx}/partyManage/sPmProbationary/form?proId=${proId}&type=4">第四季度考察表</a></li>


	<%--<li class="active" type="1"><a>第一季度考察表</a></li>
	<li type="2"><a>第二季度考察表</a></li>
	<li type="3"><a>第三季度考察表</a></li>
	<li type="4"><a>第四季度考察表</a></li>--%>

</ul><br/>
<form:form id="inputForm" modelAttribute="sPmProbationary" action="${ctx}/partyManage/sPmProbationary/save" method="post" class="form-horizontal">
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
		<label class="control-label">籍贯：</label>
		<div class="controls">
			<form:input path="plaOf" htmlEscape="false" maxlength="255" class="input-xlarge "/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">出生年月：</label>
		<div class="controls">
			<input name="dateBirth" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
				   value="<fmt:formatDate value="${sPmProbationary.dateBirth}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">民族：</label>
		<div class="controls">
			<form:input path="nation" htmlEscape="false" maxlength="255" class="input-xlarge "/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">文化程度：</label>
		<div class="controls">
			<form:input path="degEdu" htmlEscape="false" maxlength="255" class="input-xlarge "/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">参加工作时间：</label>
		<div class="controls">
			<input name="joinTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
				   value="<fmt:formatDate value="${sPmProbationary.joinTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">入党时间：</label>
		<div class="controls">
			<input name="apcaTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
				   value="<fmt:formatDate value="${sPmProbationary.apcaTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">现任职务职称：</label>
		<div class="controls">
			<form:input path="incumbent" htmlEscape="false" maxlength="255" class="input-xlarge "/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">个人学习工作简要情况：</label>
		<div class="controls">
			<form:input path="situation" htmlEscape="false" maxlength="255" class="input-xlarge "/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">入党介绍人意见：</label>
		<div class="controls">
			<form:input path="introducer" htmlEscape="false" maxlength="255" class="input-xlarge "/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">党小组意见：</label>
		<div class="controls">
			<form:input path="partyGroup" htmlEscape="false" maxlength="255" class="input-xlarge "/>
		</div>
	</div>
	<%-- <div class="control-group">
        <label class="control-label">备注信息：</label>
        <div class="controls">
            <form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="64" class="input-xxlarge "/>
        </div>
    </div>

    <div class="control-group">
        <label class="control-label">流程Id：</label>
        <div class="controls">
            <form:input path="proId" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">类型：</label>
        <div class="controls">
            <form:input path="type" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div> --%>
	<div id="container">
		<div class="controls-group" id="viewImg">
			<div class="controls">
				<input id="pickfiles" class="btn btn-primary" type="button"
					   value="选择文件" />
				文件地址:<form:input readonly="true" path="fileUrl" htmlEscape="false"
								 maxlength="255" class="input-xlarge file_url" />
				文件名:<form:input readonly="true" path="fileName" htmlEscape="false"
								maxlength="255" class="input-xlarge file_name"/>
				<form:input readonly="true" path="uploader" htmlEscape="false"
							maxlength="255" class="input-xlarge uploader" type='hidden' />
				<input name="uploadTime" type="text" readonly="readonly"
					   style='display:none;' maxlength="20"
					   value="<fmt:formatDate value="${sPmProbationary.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" />
			</div>
		</div>
	</div>

	<input id="type" name="type" type="hidden" value="1" />


	<div class="form-actions">
		<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>

	<div class="control-group">
		<label class="control-label">请先保存,之后再下载：</label>
		<div class="controls">
			<a href="${ctx}/partyManage/download/probationary?id=${sPmProbationary.id}" role="button" class="btn">预备党员考察纪实表下载</a>
		</div>
	</div>


</form:form>
</body>
</html>