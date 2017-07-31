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
<table id="jszwTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>专业技术资格</th>
        <th>获得资格日期</th>
        <th>专业技术职务</th>
        <th>任聘起始日期</th>
        <th>任聘截止日期</th>
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
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
