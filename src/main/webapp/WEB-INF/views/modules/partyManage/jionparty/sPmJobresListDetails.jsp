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
<%--<div class="pagination">${page}</div>--%>