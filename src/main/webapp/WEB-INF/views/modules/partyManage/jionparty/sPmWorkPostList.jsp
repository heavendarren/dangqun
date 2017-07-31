<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title></title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#workPostModals").load("${ctx}/partyManage/sPmWorkPost/form");
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
<table id="workPostTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>工作岗位</th>
        <th>开始时间</th>
        <th>截止日期</th>
        <th>个人身份</th>
        <th>新阶层</th>
        <th>一线情况</th>
        <th>操作</th>
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
            <td>
                <a href="#workPostModal" role="button" class="btn" data-toggle="modal">加行</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<%--<div id="myModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="myModalLabel">工作岗位</h3>
    </div>
    <div id="myModals" class="modal-body">

    </div>
</div>--%>
<div id="workPostModal" class="modal hide fade" tabindex="-1" role="dialog" aria-labelledby="workPostModalLabel"
     aria-hidden="true">
    <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
        <h3 id="workPostModalLabel">工作岗位</h3>
    </div>
    <div id="workPostModals" class="modal-body">

    </div>
</div>
<div class="pagination">${page}</div>
</body>
</html>