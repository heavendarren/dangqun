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
<table id="contactSettingsTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>联系人编号</th>
        <th>姓名</th>
        <th>工作单位</th>
        <th>下载</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${page.list}" var="sPmContactSettings">
        <tr>
            <td>
                    <%--${sPmContactSettings.contactId}--%>
                    ${sPmContactSettings.id}
            </td>
            <td>
                    ${sPmContactSettings.name}
            </td>
            <td>
                    ${sPmContactSettings.postPlace}
            </td>
            <td>
                <c:choose>
                    <c:when test="${sPmContactSettings.id == null }">
                        <a href="#" role="button" class="btn">培养联系人登记表下载</a></c:when>
                    <c:otherwise>
                        <a href="${ctx}/partyManage/public/contactsetting?id=${sPmContactSettings.id}" role="button"
                           class="btn">培养联系人登记表下载</a>
                    </c:otherwise>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>