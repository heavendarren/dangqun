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
<table id="eduDegreeTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>教育类别</th>
        <th>学历</th>
        <th>入学日期</th>
        <th>毕业日期</th>
        <th>毕业院校</th>
        <th>专业</th>
        <th>学位</th>
        <th>学位授予日期</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="sPmEduDegree">
        <tr>
            <td>
                    ${sPmEduDegree.educationType}
            </td>
            <td>
                    ${fns:getDictLabel(sPmEduDegree.education, 'WHCD', '')}
            </td>
            <td>
                <fmt:formatDate value="${sPmEduDegree.entranceDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                <fmt:formatDate value="${sPmEduDegree.graduationDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>
                    ${sPmEduDegree.graduatedUniversity}
            </td>
            <td>
                    ${sPmEduDegree.major}
            </td>
            <td>
                    ${fns:getDictLabel(sPmEduDegree.academicDegree, 'XW', '')}
            </td>
            <td>
                <fmt:formatDate value="${sPmEduDegree.degreeGrantDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>