<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title></title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#familyModals").load("${ctx}/partyManage/sPmFamily/form");
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
        <th>操作</th>
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
            <td>
                <a href="#familyModal" role="button" class="btn" data-toggle="modal">加行</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div id="familyModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="familyModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="familyModalLabel">家庭成员</h3>
    </div>
    <div id="familyModals" class="modal-body">

    </div>
</div>
<div class="pagination">${page}</div>
</body>
</html>