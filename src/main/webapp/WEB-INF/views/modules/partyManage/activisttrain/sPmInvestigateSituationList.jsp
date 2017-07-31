<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title></title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#investigateSituationModals").load("${ctx}/partyManage/sPmInvestigateSituation/form");
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
<a href="#investigateSituationModal" role="button" class="btn" data-toggle="modal">新增</a>
<table id="investigateSituationTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>编号</th>
        <th>提交时间</th>
        <th>附件</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="sPmInvestigateSituation">
        <tr>
            <td>
                    ${sPmInvestigateSituation.id}
            </td>
            <td>
                <fmt:formatDate value="${sPmInvestigateSituation.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                <c:choose>
                    <c:when test="${sPmInvestigateSituation.fileUrl == null }">
                        <a href="#" role="button" class="btn">下载</a></c:when>
                    <c:otherwise>
                        <a href="${ctx}/partyManage/public/downfile?fileUrl=${sPmInvestigateSituation.fileUrl}"
                           role="button"
                           class="btn">下载</a>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>
                <a href="#investigateSituationDetail${sPmInvestigateSituation.id}" role="button" class="btn" data-toggle="modal">查看</a>
                <div id="investigateSituationDetail${sPmInvestigateSituation.id}" class="modal hide fade" tabindex="-1" role="dialog"
                     aria-labelledby="investigateSituationDetail${sPmInvestigateSituation.id}Label"
                     aria-hidden="true">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h3 id="investigateSituationDetail${sPmInvestigateSituation.id}Label">培养考察情况</h3>
                    </div>
                    <iframe id="investigateSituationDetails${sPmInvestigateSituation.id}"
                            src="${ctx}/partyManage/sPmInvestigateSituation/detail?id=${sPmInvestigateSituation.id}"
                            class="modal-body" frameborder="0" scrolling="auto" width="90%" height="220px">

                    </iframe>
                </div>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div id="investigateSituationModal" class="modal hide fade" tabindex="-1" role="dialog"
     aria-labelledby="investigateSituationModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="investigateSituationModalLabel">培养考察情况</h3>
    </div>
    <div id="investigateSituationModals" class="modal-body">

    </div>
</div>
<div class="pagination">${page}</div>
</body>
</html>