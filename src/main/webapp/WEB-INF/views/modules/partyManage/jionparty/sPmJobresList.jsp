<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title></title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#jobresModals").load("${ctx}/partyManage/sPmJobres/form");
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
<a href="#jobresModal" role="button" class="btn" data-toggle="modal">新增工作简历</a>
<table id="jobresTable" class="table table-striped table-bordered table-condensed">
    <tbody>
    <%int i = 1;%>
    <c:forEach items="${page.list}" var="sPmJobres">
        <tr>
            <td>
                工作简历<%=i++%>
            </td>
            <td>
                    ${sPmJobres.resume}
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div id="jobresModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="jobresModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="jobresModalLabel">工作简历</h3>
    </div>
    <div id="jobresModals" class="modal-body">

    </div>
</div>
<%--<div class="pagination">${page}</div>--%>
</body>
</html>