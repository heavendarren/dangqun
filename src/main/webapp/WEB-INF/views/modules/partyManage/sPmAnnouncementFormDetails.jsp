<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>发展公示信息</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
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
<table id="sPmAnnouncement" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>文件下载</th>
        <th>文件名</th>
        <th>上传人员</th>
        <th>上传时间</th>
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
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>