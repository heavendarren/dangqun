<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>

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
    });
</script>

<form:form id="inputForm" modelAttribute="sPmContactSettings" action="${ctx}/partyManage/sPmContactSettings/save"
           method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>
    <div class="control-group">
        <label class="control-label">姓名：</label>
        <div class="controls">
                <%--<form:input path="name" htmlEscape="false" maxlength="64" class="input-xlarge "/>--%>
                <%--<sys:treeselect id="sPmContactSettingses" name="name"
                                value="${sPmContactSettings.name}" labelName="sPmContactSettingsContactNum"
                                labelValue="${sPmContactSettings.contactNum}" title="用户"
                                url="/sys/office/treeData?type=3" cssClass="input-small" allowClear="true"
                                notAllowSelectParent="true"/>--%>
            <input id="sPmContactSettingsesId" name="contactNum" type="hidden"/>
            <input id="sPmContactSettingsesName" name="name" type="text" cssClass="input-small"/>

            <script type="text/javascript">
                $("#sPmContactSettingsesName").click(function () {
                    top.$.jBox.open("iframe:${ctx}/partyManage/sPmContactSettings/contactList",
                        "选择联系人", 300, 420,
                        {
                            ajaxData: {selectIds: $("#sPmContactSettingsesId").val()},
                            buttons: {"确定": "ok", ${allowClear?"\"清除\":\"clear\", ":""}"关闭": true},
                            submit: function (v, h, f) {
                                if (v == "ok") {
                                    var tree = h.find("iframe")[0].contentWindow.tree;//h.find("iframe").contents();
                                    var ids = [], names = [], nodes = [];
                                    if ("${checked}" == "true") {
                                        nodes = tree.getCheckedNodes(true);
                                    } else {
                                        nodes = tree.getSelectedNodes();
                                    }
                                    for (var i = 0; i < nodes.length; i++) {//<c:if test="${checked && notAllowSelectParent}">
                                        if (nodes[i].isParent) {
                                            continue; // 如果为复选框选择，则过滤掉父节点
                                        }//</c:if>
                                        ids.push(nodes[i].id);
                                        names.push(nodes[i].name);//<c:if test="${!checked}">
                                        break; // 如果为非复选框选择，则返回第一个选择  </c:if>
                                    }
                                    $("#sPmContactSettingsesId").val(ids.join(",").replace(/u_/ig, ""));
                                    $("#sPmContactSettingsesName").val(names.join(","));
                                    $.ajax({
                                        url: '${ctx}/partyManage/sPmContactSettings/contactData',
                                        type: 'post',
                                        data: {userId: $("#sPmContactSettingsesId").val()},
                                        success: function (data) {
                                            var contact = data['sPmContactSettings'];
                                            $(".partyBranch").val(contact.partyBranch);
                                            $("#sPmContactSettingsSexId").val(contact.sex);
                                            $("#sPmContactSettingsSexName").val(contact.sexName);
                                        }
                                    })
                                }//<c:if test="${allowClear}">
                                else if (v == "clear") {
                                    $("#sPmContactSettingsesId").val("");
                                    $("#sPmContactSettingsesName").val("");
                                }//</c:if>
                                if (typeof sPmContactSettingsesTreeselectCallBack == 'function') {
                                    sPmContactSettingsesTreeselectCallBack(v, h, f);
                                }
                            }
                        });
                });
            </script>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">性别：</label>
            <%--<div class="controls">
                <form:input path="sex" htmlEscape="false" maxlength="64" class="input-xlarge "/>
            </div>--%>
        <div class="controls">
            <sys:treeselect2 id="sPmContactSettingsSex" name="sex" value="${sPmContactSettings.sex}" labelName="sexName"
                             labelValue="${fns:getDictLabel(sPmContactSettings.sex, 'XB', '')}"
                             title="性别" url="/sys/codeTree/treeData?type=XB" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">籍贯：</label>
            <%--<div class="controls">
                <form:input path="originPlace" htmlEscape="false" maxlength="64" class="input-xlarge "/>
            </div>--%>
        <div class="controls">
            <sys:treeselect2 id="sPmContactSettingsOriginPlace" name="originPlace"
                             value="${sPmContactSettings.originPlace}"
                             labelName="originPlaceName"
                             labelValue="${fns:getDictLabel(sPmContactSettings.originPlace, 'JG', '')}"
                             title="籍贯" url="/sys/codeTree/treeData?type=JG" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">出生年月：</label>
        <div class="controls">
            <input name="birthTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmContactSettings.birthTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">民族：</label>
            <%--<div class="controls">
                <form:input path="nation" htmlEscape="false" maxlength="64" class="input-xlarge "/>
            </div>--%>
        <div class="controls">
            <sys:treeselect2 id="sPmContactSettingsNation" name="nation" value="${sPmContactSettings.nation}"
                             labelName="nationName"
                             labelValue="${fns:getDictLabel(sPmContactSettings.nation, 'MZ', '')}"
                             title="民族" url="/sys/codeTree/treeData?type=MZ" cssClass="input-small" allowClear="true"
                             notAllowSelectParent="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">入党时间：</label>
        <div class="controls">
            <input name="jionTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
                   value="<fmt:formatDate value="${sPmContactSettings.jionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
                   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">学历学位：</label>
        <div class="controls">
            <form:input path="educationDegree" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">所在党支部名称：</label>
        <div class="controls">
            <form:input path="partyBranch" htmlEscape="false" maxlength="64" class="input-xlarge partyBranch"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">工作单位及职务：</label>
        <div class="controls">
            <form:input path="postPlace" htmlEscape="false" maxlength="64" class="input-xlarge "/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">个人简历：</label>
        <div class="controls">
            <form:input path="personResume" htmlEscape="false" maxlength="255" class="input-xlarge "/>
        </div>
    </div>
    <div class="form-actions">
        <input id="sPmContactSettingsBtnSubmit" class="btn btn-primary" type="submit" value="保 存"/>
            <%--<input id="sPmContactSettingsBtnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>--%>
    </div>
</form:form>