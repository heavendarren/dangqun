<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<%@include file="/WEB-INF/views/include/fileUp.jsp" %>
<script type="text/javascript">
    $(document).ready(
        function () {

        });
</script>

<div id="container">

    <form:form id="inputForm" modelAttribute="sPmMinutes"
               action="${ctx}/partyManage/regular/sPmMinutes/save" method="post"
               class="form-horizontal">
        <form:hidden path="id"/>
        <sys:message content="${message}"/>
        <div class="control-group">
            <label class="control-label">会议议题：</label>
            <div class="controls">
                <form:input path="conTop" htmlEscape="false" maxlength="255"
                            class="input-xlarge "/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">时间：</label>
            <div class="controls">
                <input name="conTime" type="text" readonly="readonly"
                       maxlength="20" class="input-medium Wdate "
                       value="<fmt:formatDate value="${sPmMinutes.conTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                       onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">地点：</label>
            <div class="controls">
                <form:input path="place" htmlEscape="false" maxlength="100"
                            class="input-xlarge "/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">主持人：</label>
            <div class="controls">
                <sys:treeselect id="sPmMinutesHost" name="host" value="${sPmMinutes.host}"
                                labelName="hostName" labelValue="${sPmMinutes.hostName}"
                                title="用户" url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true"
                                notAllowSelectParent="true"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">记录人：</label>
            <div class="controls">
                <sys:treeselect id="sPmMinutesNoteTaker" name="noteTaker"
                                value="${sPmMinutes.noteTaker}" labelName="noteTakerName"
                                labelValue="${sPmMinutes.noteTakerName}" title="用户"
                                url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true"
                                notAllowSelectParent="true"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">应到人数：</label>
            <span style="color:red;font-size:12px;">(注意：只能输入数字）</span>
            <div class="controls">
                <form:input path="arrNum" htmlEscape="false" maxlength="18"
                            class="input-xlarge  digits"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">实到人数：</label>
            <span style="color:red;font-size:12px;">(注意：只能输入数字）</span>
            <div class="controls">
                <form:input path="actNum" htmlEscape="false" maxlength="18"
                            class="input-xlarge  digits"/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">参加人员名单：</label>
            <div class="controls">
                <input id="idsList" value="${sPmMinutes.attenList}" type="hidden"/>
                <input id="userDataRelation" type="hidden"/>
                <ol id="userSelectList" style="width: 400px;">
                </ol>
                <ol class="clearfix"></ol>
                <a id="relationButton" href="javascript:" class="btn ">添加人员</a>
                <script type="text/javascript">
                    var userSelect = [];
                    var idList = $("#idsList").val();
                    if (idList != null || idList != "") {
                        $.getJSON("${ctx}/partyManage/regular/findByIds", {ids: idList}, function (data) {
                            for (var i = 0; i < data.length; i++) {
                                userSelect.push([data[i][1], data[i][2]]);
                            }
                            userSelectRefresh();
                        });
                    }

                    function userSelectAddOrDel(id, title) {
                        var isExtents = false, index = 0;
                        for (var i = 0; i < userSelect.length; i++) {
                            if (userSelect[i][0] == id) {
                                isExtents = true;
                                index = i;
                            }
                        }
                        if (isExtents) {
                            userSelect.splice(index, 1);
                        } else {
                            userSelect.push([id, title]);
                        }
                        userSelectRefresh();
                    }
                    function userSelectRefresh() {
                        $("#userDataRelation").val("");
                        $("#userSelectList").children().remove();
                        for (var i = 0; i < userSelect.length; i++) {
                            $("#userSelectList")
                                .append(
                                    "<li  style='float: left;list-style-type:none;width: 25%;'>"
                                    + userSelect[i][1]
                                    + "<input type='hidden' name='attenList' value='" + userSelect[i][0] +
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
                            function () {
                                top.$.jBox
                                    .open(
                                        "iframe:${ctx}/sys/multiUser/userList?pageSize=10&flag=userSelect",
                                        "选择用户",
                                        $(top.document)
                                            .width() - 220,
                                        $(top.document)
                                            .height() - 180,
                                        {
                                            buttons: {
                                                "确定": true
                                            },
                                            loaded: function (h) {
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
                <input id="idsList1" value="${sPmMinutes.absList}" type="hidden"/>
                <input id="userDataRelation1" type="hidden"/>
                <ol id="userSelectList1" style="width: 300px;"></ol>
                <ol class="clearfix"></ol>
                <a id="relationButton1" href="javascript:" class="btn ">添加人员</a>
                <script type="text/javascript">
                    var userSelect1 = [];
                    var idList1 = $("#idsList1").val();
                    if (idList1 != null || idList1 != "") {
                        $.getJSON("${ctx}/partyManage/regular/findByIds", {ids: idList1}, function (data) {
                            for (var i = 0; i < data.length; i++) {
                                userSelect1.push([data[i][1], data[i][2]]);
                            }
                            userSelect1Refresh();
                        });
                    }

                    function userSelect1AddOrDel(id, title) {
                        var isExtents = false, index = 0;
                        for (var i = 0; i < userSelect1.length; i++) {
                            if (userSelect1[i][0] == id) {
                                isExtents = true;
                                index = i;
                            }
                        }
                        if (isExtents) {
                            userSelect1.splice(index, 1);
                        } else {
                            userSelect1.push([id, title]);
                        }
                        userSelect1Refresh();
                    }
                    function userSelect1Refresh() {
                        $("#userDataRelation1").val("");
                        $("#userSelectList1").children().remove();
                        for (var i = 0; i < userSelect1.length; i++) {
                            $("#userSelectList1")
                                .append(
                                    "<li  style='float: left;list-style-type:none;width: 25%;'>"
                                    + userSelect1[i][1]
                                    + "<input type='hidden' name='absList' value='" + userSelect1[i][0] +
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
                            function () {
                                top.$.jBox
                                    .open(
                                        "iframe:${ctx}/sys/multiUser/userList?pageSize=10&flag=userSelect1",
                                        "选择用户",
                                        $(top.document)
                                            .width() - 220,
                                        $(top.document)
                                            .height() - 180,
                                        {
                                            buttons: {
                                                "确定": true
                                            },
                                            loaded: function (h) {
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
            <label class="control-label">缺席原因：</label>
            <div class="controls">
                <form:input path="absReason" htmlEscape="false" maxlength="255"
                            class="input-xlarge "/>
            </div>
        </div>
        <div class="control-group">
            <label class="control-label">会议纪要：</label>
            <div class="controls">
                <form:textarea path="meetMin" htmlEscape="false" rows="4"
                               maxlength="255" class="input-xxlarge "/>
            </div>
        </div>
        <div class="controls-group" id="viewImg">
            <label class="control-label">文件名：</label>
            <div class="controls">
                <div hidden>
                    <form:input readonly="true" path="fileUrl" htmlEscape="false" maxlength="255"
                                class="input-xlarge file_url"/>
                </div>
                <form:input readonly="true" path="fileName" htmlEscape="false" maxlength="255"
                            class="input-xlarge file_name"/>
                <c:choose>
                    <c:when test="${sPmMinutes.fileUrl == null }">
                        <a href="#" role="button" class="btn">下载</a></c:when>
                    <c:otherwise>
                        <a href="${ctx}/partyManage/public/downfile?fileUrl=${sPmMinutes.fileUrl}" role="button"
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
                       value="<fmt:formatDate value="${sPmMinutes.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"/>
            </div>
        </div>
        <div class="controls-group" id="fileOperat">
            <div class="controls">
                <input id="sPmMinutesFiles" class="btn btn-primary" type="button" value="选择文件"/>
            </div>
        </div>

        <div class="form-actions">
            <input id="sPmMinutesBtnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
                <%--<input id="sPmMinutesBtnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
        </div>
    </form:form>
</div>
<script type="text/javascript">
    var map = {};
    var uploaderes = new plupload.Uploader({
        runtimes: 'html5,flash,silverlight,html4',
        browse_button: 'sPmMinutesFiles',
        container: document.getElementById('container'),
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
                    //var img = "<img src='" + res.fileUrl + "'/>";
                    //$("#viewImg").append(img);
                    $(".file_url").val(res.fileUrl);
                    $(".file_name").val(file.name);
                    $(".file_name").next(".btn").remove();
                    var filea = "<a href='${ctx}/partyManage/public/downfile?fileUrl=" + res.fileUrl + "' role='button' class='btn'>下载</a>";
                    $(".file_name").after(filea);
                }
            },
            FilesAdded: function (up, files) {
                uploaderes.start();
            },
            UploadProgress: function (up, file) {

            },
            Error: function (up, err) {
                alert(err.message);
            }
        }
    });
    uploaderes.init();
</script>
