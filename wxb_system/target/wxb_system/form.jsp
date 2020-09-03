<%--
  Created by IntelliJ IDEA.
  User: aizhishang
  Date: 2020/8/27
  Time: 1:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath()%>/">
</head>
<body>
<div id="myform" style="margin-right: 40px;margin-top: 20px">
    <form class="layui-form" action="role/add">
        <input type="hidden" name="roleCode">
        <div class="layui-form-item">
            <label class="layui-form-label">角色名称</label>
            <div class="layui-input-block">
                <input type="text" name="roleName" required  lay-verify="required" placeholder="角色名称" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色描述</label>
            <div class="layui-input-block">
                <input type="text" name="roleDesc" required  lay-verify="required" placeholder="角色描述" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色排序</label>
            <div class="layui-input-block">
                <input type="number" name="roleOrder" required  lay-verify="required" placeholder="角色排序" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">角色类型</label>
            <div class="layui-input-block">
                <select id="roleType" name="roleType" lay-verify="required">
                    <option id="type1" value="1" selected>业务角色</option>
                    <option id="type2" value="2">管理角色</option>
                </select>
            </div>
        </div>
        <%--    <div class="layui-form-item">--%>
        <%--        <label class="layui-form-label">角色类型</label>--%>
        <%--        <div class="layui-input-block">--%>
        <%--            <input type="radio" name="roleType" value="1" title="业务角色" checked>--%>
        <%--            <input type="radio" name="roleType" value="2" title="管理角色">--%>
        <%--        </div>--%>
        <%--    </div>--%>
        <div class="layui-form-item">
            <div class="layui-input-block">
                <button class="layui-btn" lay-submit lay-filter="formDemo">立即提交</button>
                <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                <button type="button" onclick="closeForm()" class="layui-btn layui-btn-primary">关闭</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
