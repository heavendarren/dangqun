<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>补录信息</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#supRecordModals").load("${ctx}/partyManage/sPmSupRecord/form");
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
<table id="supRecordTable" class="table table-striped table-bordered table-condensed">
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
    <c:forEach items="${page.list}" var="sPmSupRecord">
        <tr>
            <td>
                    <%--${sPmSupRecord.fileUrl}--%>
                <c:choose>
                    <c:when test="${sPmSupRecord.fileUrl == null }">
                        <a href="#" role="button" class="btn">下载</a></c:when>
                    <c:otherwise>
                        <a href="${ctx}/partyManage/public/downfile?fileUrl=${sPmSupRecord.fileUrl}"
                           role="button"
                           class="btn">下载</a>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                    ${sPmSupRecord.fileName}
            </td>
            <td>
                    ${sPmSupRecord.uploader}
            </td>
            <td>
                <fmt:formatDate value="${sPmSupRecord.uploadTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                <a href="#supRecordModal" role="button" class="btn" data-toggle="modal">添加上传文件</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div id="supRecordModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="supRecordModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="supRecordModalLabel">补录信息</h3>
    </div>
    <div id="supRecordModals" class="modal-body">

    </div>
</div>
<%--<div class="pagination">${page}</div>--%>
<c:choose>
    <c:when test="${recordId == null }">
        <a href="${ctx}/partyManage/DQRecord/record" role="button" class="btn">返回</a></c:when>
    <c:otherwise>
        <a href="${ctx}/partyManage/DQRecord/schedule/?id=${recordId}" role="button" class="btn">返回</a>
    </c:otherwise>
</c:choose>
</body>
</html>