<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title></title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#contactSettingsModals").load("${ctx}/partyManage/sPmContactSettings/form");
        });
        function page(n, s) {
            $("#pageNo").val(n);
            $("#pageSize").val(s);
            $("#searchForm").submit();
            return false;
        }
    </script>
</head>
<body>
<sys:message content="${message}"/>
<table id="contactSettingsTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>联系人编号</th>
        <th>姓名</th>
        <th>工作单位</th>
        <th>下载</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="sPmContactSettings">
        <tr>
            <td>
                    <%--${sPmContactSettings.contactId}--%>
                    ${sPmContactSettings.id}
            </td>
            <td>
                    ${sPmContactSettings.name}
            </td>
            <td>
                    ${sPmContactSettings.postPlace}
            </td>
            <td>
                <c:choose>
                    <c:when test="${sPmContactSettings.id == null }">
                        <a href="#" role="button" class="btn">培养联系人登记表下载</a></c:when>
                    <c:otherwise>
                        <a href="${ctx}/partyManage/public/contactsetting?id=${sPmContactSettings.id}" role="button"
                           class="btn">培养联系人登记表下载</a>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="#contactSettingsModal" role="button" class="btn" data-toggle="modal">加行</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div id="contactSettingsModal" class="modal hide fade" tabindex="-1" role="dialog"
     aria-labelledby="contactSettingsModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="contactSettingsModalLabel">培养联系人设置</h3>
    </div>
    <div id="contactSettingsModals" class="modal-body">

    </div>
</div>
<div class="pagination">${page}</div>
</body>
</html>