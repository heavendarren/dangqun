<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>

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

<sys:message content="${message}"/>
<table id="thoughtReportTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>编号</th>
        <th>主题</th>
        <th>更新时间</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="sPmThoughtReport">
        <tr>
            <td>
                    ${sPmThoughtReport.id}
            </td>
            <td>
                    ${sPmThoughtReport.title}
            </td>
            <td>
                <fmt:formatDate value="${sPmThoughtReport.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                <a href="#thoughtReportDetail${sPmThoughtReport.id}" role="button" class="btn" data-toggle="modal">查看</a>
                <div id="thoughtReportDetail${sPmThoughtReport.id}" class="modal hide fade" tabindex="-1" role="dialog"
                     aria-labelledby="thoughtReportDetail${sPmThoughtReport.id}Label"
                     aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="thoughtReportDetail${sPmThoughtReport.id}Label">思想汇报</h3>
                    </div>
                    <iframe id="thoughtReportDetails${sPmThoughtReport.id}"
                            src="${ctx}/partyManage/sPmThoughtReport/detail?id=${sPmThoughtReport.id}"
                            class="modal-body" frameborder="0" scrolling="auto" width="90%" height="320px">

                    </iframe>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
