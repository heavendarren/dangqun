<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>预备党员考察纪实表</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/fileUp.jsp" %>
	<script type="text/javascript">
        $(document).ready(function() {
            //$("#name").focus();
            $("#menu-list-box li").click(function(){
                $("#menu-list-box li").removeClass("active");
                $(this).addClass("active");
                $('#type').val(this.type);
                $.ajax({
                    type:"post",
                    url:"${ctx}/partyManage/sPmProbationary/ajaxDetail",
                    data:{proId:"${proId}",type:$('#type').val()},
                    dataType:"json",
                    success:function(data){
                        $('#name').val(data.name);
                        $('#sex').val(data.sex);
                        $('#plaOf').val(data.plaOf);

                        $('#dateBirth').val(data.dateBirth);
                        $('#nation').val(data.nation);
                        $('#degEdu').val(data.degEdu);
                        $('#joinTime').val(data.joinTime);
                        $('#apcaTime').val(data.apcaTime);
                        $('#incumbent').val(data.incumbent);
                        $('#situation').val(data.situation);
                        $('#introducer').val(data.introducer);
                        $('#partyGroup').val(data.partyGroup);
                    }
                });


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

	<li <c:if test="${sPmProbationary.type == '1'}">class="active" </c:if> type="1"><a href="${ctx}/partyManage/sPmProbationary/detail?proId=${proId}&type=1">第一季度考察表</a></li>
	<li <c:if test="${sPmProbationary.type == '2'}">class="active" </c:if> type="2"><a href="${ctx}/partyManage/sPmProbationary/detail?proId=${proId}&type=2">第二季度考察表</a></li>
	<li <c:if test="${sPmProbationary.type == '3'}">class="active" </c:if> type="3"><a href="${ctx}/partyManage/sPmProbationary/detail?proId=${proId}&type=3">第三季度考察表</a></li>
	<li <c:if test="${sPmProbationary.type == '4'}">class="active" </c:if> type="4"><a href="${ctx}/partyManage/sPmProbationary/detail?proId=${proId}&type=4">第四季度考察表</a></li>

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
			<form:input readonly="true" path="name" htmlEscape="false" maxlength="255" class="input-xlarge "/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">性别：</label>
		<div class="controls">
			<form:input readonly="true" path="sex" htmlEscape="false" maxlength="255" class="input-xlarge "/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">籍贯：</label>
		<div class="controls">
			<form:input readonly="true" path="plaOf" htmlEscape="false" maxlength="255" class="input-xlarge "/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">出生年月：</label>
		<div class="controls">
			<input disabled="disabled" name="dateBirth" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">民族：</label>
		<div class="controls">
			<form:input readonly="true" path="nation" htmlEscape="false" maxlength="255" class="input-xlarge "/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">文化程度：</label>
		<div class="controls">
			<form:input readonly="true" path="degEdu" htmlEscape="false" maxlength="255" class="input-xlarge "/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">参加工作时间：</label>
		<div class="controls">
			<input disabled="disabled" name="joinTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
				   value="<fmt:formatDate value="${sPmProbationary.joinTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">入党时间：</label>
		<div class="controls">
			<input disabled="disabled" name="apcaTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
				   value="<fmt:formatDate value="${sPmProbationary.apcaTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
				   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">现任职务职称：</label>
		<div class="controls">
			<form:input readonly="true" path="incumbent" htmlEscape="false" maxlength="255" class="input-xlarge "/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">个人学习工作简要情况：</label>
		<div class="controls">
			<form:input readonly="true" path="situation" htmlEscape="false" maxlength="255" class="input-xlarge "/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">入党介绍人意见：</label>
		<div class="controls">
			<form:input readonly="true" path="introducer" htmlEscape="false" maxlength="255" class="input-xlarge "/>
		</div>
	</div>
	<div class="control-group">
		<label class="control-label">党小组意见：</label>
		<div class="controls">
			<form:input readonly="true" path="partyGroup" htmlEscape="false" maxlength="255" class="input-xlarge "/>
		</div>
	</div>


	<div id="container">
		<div class="controls-group" id="viewImg">
			<label class="control-label">文件名：</label>
			<div class="controls">
				<div hidden class="controls">
					<form:input readonly="true" path="fileUrl" htmlEscape="false" maxlength="255"
								class="input-xlarge file_url"/>
				</div>
				<form:input readonly="true" path="fileName" htmlEscape="false" maxlength="255"
							class="input-xlarge file_name"/>
				<c:choose>
					<c:when test="${sPmProbationary.fileUrl == null }">
						<a href="#" role="button" class="btn">下载</a></c:when>
					<c:otherwise>
						<a href="${ctx}/partyManage/public/downfile?fileUrl=${sPmProbationary.fileUrl}"
						   role="button"
						   class="btn">下载</a>
					</c:otherwise>
				</c:choose>
			</div>
			<label class="control-label">上传操作人：</label>
			<div class="controls">
				<form:input readonly="true" path="uploader" htmlEscape="false" maxlength="255"
							class="input-xlarge uploader"/>
			</div>
			<label class="control-label">上传时间：</label>
			<div class="controls">
				<input name="uploadTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${sPmProbationary.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
			</div>
		</div>
	</div>


	<input id="type" name="type" type="hidden" value="1" />
	<div class="form-actions">
		<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
	</div>
</form:form>
</body>
</html>