<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>

<html>
<head>
	<title>拟转正管理</title>
	<meta name="decorator" content="default"/>
	<%@include file="/WEB-INF/views/include/fileUp.jsp" %>
	<script type="text/javascript">
    $(document).ready(function () {
        //$("#name").focus();
        $("#inputForm").validate({
            submitHandler: function (form) {
                loading('正在提交，请稍等...');
                form.submit();
            },
            errorContainer: "#messageBox",
            errorPlacement: function (error, element) {
                $("#messageBox").text("输入有误，请先更正。");
                if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
                    error.appendTo(element.parent().parent());
                } else {
                    error.insertAfter(element);
                }
            }
        });
        
		//图片类型上传
        var maps = {};
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
                mime_types: [
                         {title: "图片类型",extensions: "jpg,gif,png"}
                       ]
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
                        maps[file.id] = $("#fileType").val();
                        var newhtml = "<div class='controls' id='" + file.id + "div'>" +
                            "<label>文件路径：<input readonly='true' id='" + file.id + "url' name='imgUrls' maxlength='255' value='待生成' /></label>" +
                            "<label>文件名：<input readonly='true' id='" + file.name + "' name='imgNames' maxlength='255' value=" + file.name + " /></label>" +
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
        
        //文件类型上传
        var map = {};
        var upload = new plupload.Uploader({
            runtimes: 'html5,flash,silverlight,html4',
            browse_button: 'pickfile', // you can pass in id...
            container: document.getElementById('container'), // ... or DOM Element itself
            url: '${ctx}/sys/fileUp/upFile',
            flash_swf_url: '${ctxStatic}/plupload/2.3.1/Moxie.swf',
            silverlight_xap_url: '${ctxStatic}/plupload/2.3.1/Moxie.xap',
            chunk_size: '1mb',
            filters: {
                max_file_size: '10mb',
                prevent_duplicates: true,
                mime_types: [
                         {title: "文件类型",extensions: "docx"}
                       ]
            },
            init: {
                PostInit: function () {
                    document.getElementById('uploadfile').onclick = function () {
                        upload.start();
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
                        $("#viewfile").append(newhtml);
                    });
                },
                Error: function (up, err) {
                    alert(err.message);
                }
            }
        });
        upload.init();
      /*  $("#viewImg").on("click", "button", function () {
            function del(id) {
                uploads.removeFile(id);
                $("#" + id + "div").remove();
                map[id] = "";
            }

            del($(this).attr('v-fid'));
        });
        $("#viewfile").on("click", "button", function () {
            function del(id) {
                upload.removeFile(id);
                $("#" + id + "div").remove();
                map[id] = "";
            }

            del($(this).attr('v-fid'));
        }); */
       
        //思想汇报上传
        var mapss = {};
        var uploadss = new plupload.Uploader({
            runtimes: 'html5,flash,silverlight,html4',
            browse_button: 'pickfiless', // you can pass in id...
            container: document.getElementById('container'), // ... or DOM Element itself
            url: '${ctx}/sys/fileUp/upFile',
            flash_swf_url: '${ctxStatic}/plupload/2.3.1/Moxie.swf',
            silverlight_xap_url: '${ctxStatic}/plupload/2.3.1/Moxie.xap',
            chunk_size: '1mb',
            filters: {
                max_file_size: '10mb',
                prevent_duplicates: true,
                mime_types: [
                         {title: "汇报文件类型",extensions: "docx"}
                       ]
            },
            init: {
                PostInit: function () {
                    document.getElementById('uploadfiless').onclick = function () {
                        uploadss.start();
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
                            "<label>文件路径：<input readonly='true' id='" + file.id + "url' name='fileUrlss' maxlength='255' value='待生成' /></label>" +
                            "<label>文件名：<input readonly='true' id='" + file.name + "' name='fileNamess' maxlength='255' value=" + file.name + " /></label>" +
                            "<button id='" + file.id + "btn' v-fid='" + file.id + "' class='btn btn-default' type='button'>删除</button></div>";
                        $("#viewfiless").append(newhtml);
                    });
                },
                Error: function (up, err) {
                    alert(err.message);
                }
            }
        });
        uploadss.init();
        $("#viewImg").on("click", "button", function () {
            function del(id) {
                uploads.removeFile(id);
                $("#" + id + "div").remove();
                map[id] = "";
            }

            del($(this).attr('v-fid'));
        });
        $("#viewfile").on("click", "button", function () {
            function del(id) {
                upload.removeFile(id);
                $("#" + id + "div").remove();
                map[id] = "";
            }

            del($(this).attr('v-fid'));
        });
        $("#viewfiless").on("click", "button", function () {
            function del(id) {
                uploadss.removeFile(id);
                $("#" + id + "div").remove();
                map[id] = "";
            }

            del($(this).attr('v-fid'));
        });
    });
</script>
</head>
<body>
<div id="container">
	<form:form id="inputForm" modelAttribute="sPmPositiveApp" action="${ctx}/partyManage/sPmPositiveApp/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>	
		<center><h3>拟转正申请</h3></center>
		<h4>转正申请书上传：</h4>	
		<hr/>

		<div class="control-group">
            <label class="control-label" style="font-size:20px;">图片上传</label>
            <span style="color:red;font-size:12px;">(注意：默认第一张为封面，最佳分辨率为900*600,不超过20M）</span>
        </div>
        
		<div class="controls-group" id="viewImg">
		<%-- <c:forEach items="${sPmPartyMen.imgUrls}" var="imgUrl">
		<div class="controls" >
		 <label>文件路径:<input readonly="true" value="${imgUrl}" /></label>
		</div>
		</c:forEach> --%>
        </div>
        <div class="controls-group" >
            <div class="controls">
                <input id="pickfiles" class="btn btn-primary" type="button" value="选择图片"/>
                <input id="uploadfiles" class="btn btn-primary" type="button" value="上传"/>
            </div>
        </div>
        
        <hr/>
        <div class="control-group">
            <label class="control-label" style="font-size:20px;">附件上传</label>
            <span style="color:red;font-size:12px;">(单文件最大20M）</span>
        </div>
        <div class="controls-group" id="viewfile">
        </div>
        <div class="controls-group">
            <div class="controls">
                <input id="pickfile" class="btn btn-primary" type="button" value="选择文件"/>
                <input id="uploadfile" class="btn btn-primary" type="button" value="上传"/>
            </div>
        </div>
		<hr/>
		
		<h4>思想汇报上传：</h4>	
		<hr/>
		
		<div class="control-group">
            <label class="control-label" style="font-size:20px;">附件上传</label>
            <span style="color:red;font-size:12px;">(单文件最大20M）</span>
        </div>
        <div class="controls-group" id="viewfiless">
        </div>
        <div class="controls-group">
            <div class="controls">
                <input id="pickfiless" class="btn btn-primary" type="button" value="添加思想汇报"/>
                <input id="uploadfiless" class="btn btn-primary" type="button" value="上传"/>
            </div>
        </div>
		<hr/>
		

      

        <div class="controls-group">
            <div class="controls">
                <label>
                    上传操作人：<input readonly="readonly" name="uploader" value="${sPmPositiveApp.uploader}" maxlength="255" class="input-xlarge"/>
                </label>
                <label>
                    上传时间：<input name="uploadTime" type="text" readonly="readonly" maxlength="20"
                                class="input-medium Wdate "
                                value="<fmt:formatDate value="${sPmPositiveApp.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
                </label>
            </div>
        </div>
        
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</div>
</body>
</html>