<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>公示信息管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#myModals").load("${ctx}/partyManage/sPmAnnouncement/form");
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
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>文件下载</th>
        <th>文件名</th>
        <th>上传人员</th>
        <th>上传时间</th>
        <th>添加上传文件</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="sPmAnnouncement">
        <tr>
            <td>
                <c:choose>
                    <c:when test="${sPmAnnouncement.fileUrl == null }">
                        <a href="#" role="button" class="btn">下载</a></c:when>
                    <c:otherwise>
                        <a href="${ctx}/partyManage/public/downfile?fileUrl=${sPmAnnouncement.fileUrl}"
                           role="button"
                           class="btn">下载</a>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                    ${sPmAnnouncement.fileName}
            </td>
            <td>
                    ${sPmAnnouncement.uploader}
            </td>
            <td>
                <fmt:formatDate value="${sPmAnnouncement.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                <a href="#myModal" role="button" class="btn" data-toggle="modal">添加上传文件</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">发展对象公示信息</h3>
    </div>
    <div id="myModals" class="modal-body">

    </div>
</div>
</body>
</html>