<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
<title>介绍人管理</title>
	<meta name="decorator" content="default"/>
<script type="text/javascript">
    $(document).ready(function () {
        $("#myModals").load("${ctx}/partyManage/sPmParthMenber/form");
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
<table id="myTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>介绍人人编号</th>
        <th>姓名</th>
        <th>工作单位</th>
        <%--<th>下载</th>
        --%><th>操作</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="sPmParthMenber">
        <tr>
            <td>
                    ${sPmParthMenber.id}
            </td>
            <td>
                    ${sPmParthMenber.name}
            </td>
            <td>
                    ${sPmParthMenber.postPlace}
            </td><%--
            <td>
                <c:choose>
                    <c:when test="${sPmParthMenber.id == null }">
                        <a href="#" role="button" class="btn">介绍人登记表下载</a></c:when>
                    <c:otherwise>
                        <a href="${ctx}/partyManage/public/parthMenber?id=${sPmParthMenber.id}" role="button"
                           class="btn">介绍人登记表下载</a>
                    </c:otherwise>
                </c:choose>
            </td>
            --%><td>
                <a href="#myModal" role="button" class="btn" data-toggle="modal">加行</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div id="myModals" class="modal-body">
    </div>
</div>
<div class="pagination">${page}</div>
</body>
</html>