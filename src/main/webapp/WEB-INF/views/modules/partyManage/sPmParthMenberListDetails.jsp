<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
<title>介绍人管理</title>
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
<table id="contactSettingsTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>联系人编号</th>
        <th>姓名</th>
        <th>工作单位</th><%--
        <th>下载</th>
    --%></tr>
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
            </td>
            <%--<td>
                <c:choose>
                    <c:when test="${sPmParthMenber.id == null }">
                        <a href="#" role="button" class="btn">介绍人登记表下载</a></c:when>
                    <c:otherwise>
                        <a href="${ctx}/partyManage/public/parthMenber?id=${sPmParthMenber.id}" role="button"
                           class="btn">介绍人登记表下载</a>
                    </c:otherwise>
                </c:choose>
            </td>
        --%></tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div></body></html>