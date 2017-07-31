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
<table id="familyTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>成员姓名</th>
        <th>与本人关系</th>
        <th>关系类别</th>
        <th>性别</th>
        <th>出生日期</th>
        <th>政治面貌</th>
        <th>个人身份</th>
        <th>工作单位及职务</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="sPmFamily">
        <tr>
            <td>
                    ${sPmFamily.name}
            </td>
            <td>
                    ${sPmFamily.relWe}
            </td>
            <td>
                    ${fns:getDictLabel(sPmFamily.relCate, 'GSLB', '')}
            </td>
            <td>
                    ${fns:getDictLabel(sPmFamily.sex, 'XB', '')}
            </td>
            <td>
                <fmt:formatDate value="${sPmFamily.birthDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                    ${fns:getDictLabel(sPmFamily.polLook, 'ZZMM', '')}
            </td>
            <td>
                    ${fns:getDictLabel(sPmFamily.identity, 'ZYFL', '')}
            </td>
            <td>
                    ${sPmFamily.workPos}
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
