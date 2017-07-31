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
<table id="workPostTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>工作岗位</th>
        <th>开始时间</th>
        <th>截止日期</th>
        <th>个人身份</th>
        <th>新阶层</th>
        <th>一线情况</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="sPmWorkPost">
        <tr>
            <td>
                    ${sPmWorkPost.workPost}
            </td>
            <td>
                <fmt:formatDate value="${sPmWorkPost.beginTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                <fmt:formatDate value="${sPmWorkPost.endTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                    ${fns:getDictLabel(sPmWorkPost.personIdentity, 'ZYFL', '')}
            </td>
            <td>
                    ${fns:getDictLabel(sPmWorkPost.newStratum, 'NEWCLASS', '')}
            </td>
            <td>
                    ${fns:getDictLabel(sPmWorkPost.firstCondition, 'YXQK', '')}
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
