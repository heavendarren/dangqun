<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title></title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#jszwModals").load("${ctx}/partyManage/sPmJszw/form");
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
<table id="jszwTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>专业技术资格</th>
        <th>获得资格日期</th>
        <th>专业技术职务</th>
        <th>任聘起始日期</th>
        <th>任聘截止日期</th>
        <th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="sPmJszw">
        <tr>
            <td>
                    ${sPmJszw.proQua}
            </td>
            <td>
                <fmt:formatDate value="${sPmJszw.dateElig}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                    ${sPmJszw.techPos}
            </td>
            <td>
                <fmt:formatDate value="${sPmJszw.appoStart}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                <fmt:formatDate value="${sPmJszw.dateTer}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                <a href="#jszwModal" role="button" class="btn" data-toggle="modal">加行</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div id="jszwModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="jszwModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="jszwModalLabel">技术职务</h3>
    </div>
    <div id="jszwModals" class="modal-body">

    </div>
</div>
<div class="pagination">${page}</div>
</body>
</html>