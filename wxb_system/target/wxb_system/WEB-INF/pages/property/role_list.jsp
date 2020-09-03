<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: aizhishang
  Date: 2020/8/26
  Time: 20:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>角色列表</title>
    <base href="<%=request.getContextPath()%>/">
    <meta charset="utf-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css"  media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>角色列表</legend>
</fieldset>

<div class="layui-form" id="layerDemo">
    <table class="layui-table">
        <colgroup>
            <col width="150">
            <col width="150">
            <col width="200">
            <col>
        </colgroup>
        <thead>
        <tr>
            <th>角色名称</th>
            <th>角色描述</th>
            <th>角色类型</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>

            <c:forEach items="${roleList}" var="role">
            <tr>
                <td>${role.roleName}</td>
                <td>${role.roleDesc}</td>
                <td>
                    <c:if test="${role.roleType==1}">
                        业务角色
                    </c:if>
                    <c:if test="${role.roleType==2}">
                        管理角色
                    </c:if>
                </td>
                <td>
                    <button type="button" data-method="add" data-type="auto" class="layui-btn layui-btn-sm"><i class="layui-icon">添加</i></button>
                    <button type="button" data-method="update" data-roleid="${role.roleCode}" data-rolename="${role.roleName}" data-roledesc="${role.roleDesc}" data-roleorder="${role.roleOrder}" data-roletype="${role.roleType}" data-type="auto"  class="layui-btn layui-btn-sm"><i class="layui-icon">修改</i></button>
                    <button type="button" data-method="delete" data-roleid="${role.roleCode}"  class="layui-btn layui-btn-sm"><i class="layui-icon">删除</i></button>
                    <button type="button" data-method="roleTreeNodeList" data-roleid="${role.roleCode}" data-type="auto"  class="layui-btn layui-btn-sm"><i class="layui-icon">授权</i></button>
                </td>
            </tr>
            </c:forEach>

        </tbody>
    </table>
</div>

<div id="myform" style="margin-right: 40px;margin-top: 20px;display: none">
<form id="f_myform" class="layui-form" action="role/add">
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

<script src="layui/layui.js" charset="utf-8"></script>
<script src="js/jquery.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use('layer', function(){ //独立版的layer无需执行这一句
        var $ = layui.jquery, layer = layui.layer; //独立版的layer无需执行这一句

        //触发事件
        var active = {
            setTop: function(){
                console.log("111")
                var that = this;
                //多窗口模式，层叠置顶
                layer.open({
                    type: 2 //此处以iframe举例
                    ,title: '当你选择该窗体时，即会在最顶端'
                    ,area: ['390px', '260px']
                    ,shade: 0
                    ,maxmin: true
                    ,offset: [ //为了演示，随机坐标
                        Math.random()*($(window).height()-300)
                        ,Math.random()*($(window).width()-390)
                    ]
                    ,content: '//layer.layui.com/test/settop.html'
                    ,btn: ['继续弹出', '全部关闭'] //只是为了演示
                    ,yes: function(){
                        $(that).click();
                    }
                    ,btn2: function(){
                        layer.closeAll();
                    }

                    ,zIndex: layer.zIndex //重点1
                    ,success: function(layero){
                        layer.setTop(layero); //重点2
                    }
                });
            }
            ,confirmTrans: function(){
                //配置一个透明的询问框
                layer.msg('大部分参数都是可以公用的<br>合理搭配，展示不一样的风格', {
                    time: 20000, //20s后自动关闭
                    btn: ['明白了', '知道了', '哦']
                });
            }
            ,notice: function(){
                //示范一个公告层
                layer.open({
                    type: 1
                    ,title: false //不显示标题栏
                    ,closeBtn: false
                    ,area: '300px;'
                    ,shade: 0.8
                    ,id: 'LAY_layuipro' //设定一个id，防止重复弹出
                    ,btn: ['火速围观', '残忍拒绝']
                    ,btnAlign: 'c'
                    ,moveType: 1 //拖拽模式，0或者1
                    ,content: '<div style="padding: 50px; line-height: 22px; background-color: #393D49; color: #fff; font-weight: 300;">你知道吗？亲！<br>layer ≠ layui<br><br>layer只是作为Layui的一个弹层模块，由于其用户基数较大，所以常常会有人以为layui是layerui<br><br>layer虽然已被 Layui 收编为内置的弹层模块，但仍然会作为一个独立组件全力维护、升级。<br><br>我们此后的征途是星辰大海 ^_^</div>'
                    ,success: function(layero){
                        var btn = layero.find('.layui-layer-btn');
                        btn.find('.layui-layer-btn0').attr({
                            href: 'http://www.layui.com/'
                            ,target: '_blank'
                        });
                    }
                });
            }
            ,add: function(othis){
                var type = othis.data('type')
                    ,text = othis.text();

                $("#f_myform").attr("action","role/add");

                layer.open({
                    type: 1
                    ,offset: type //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                    ,id: 'layerDemo'+type //防止重复弹出
                    ,content: $('#myform')
                    // ,btn: '关闭全部'
                    ,btnAlign: 'c' //按钮居中
                    ,shade: 0.3 //不显示遮罩
                    ,yes: function(){
                        layer.closeAll();
                    }
                });

                layui.form.render();
            }
            ,update: function(othis){
                var type = othis.data('type')
                    ,text = othis.text();

                $("#f_myform").attr("action","role/update");

                $('input[name="roleCode"]').val(othis.data('roleid'));
                $('input[name="roleName"]').val(othis.data('rolename'));
                $('input[name="roleDesc"]').val(othis.data('roledesc'));
                $('input[name="roleOrder"]').val(othis.data('roleorder'));
                $("#roleType").val(othis.data('roletype'));


                layer.open({
                    type: 1
                    ,offset: type //具体配置参考：http://www.layui.com/doc/modules/layer.html#offset
                    ,id: 'layerDemo'+type //防止重复弹出
                    ,content: $('#myform')
                    // ,btn: '关闭全部'
                    ,btnAlign: 'c' //按钮居中
                    ,shade: 0.3 //不显示遮罩
                    ,yes: function(){
                        layer.closeAll();
                    }
                    ,end: function () {
                        location.reload();
                    }

                });
            }
            ,delete: function(othis) {
                window.location.href="role/delete?roleId="+othis.data('roleid');
            }
            ,roleTreeNodeList:function (othis){
                layer.open({
                    type: 2,
                    area: ['350px', '550px'],
                    content: 'role/roleTreeNodeListPage?'+othis.data('roleid') //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
                });
            }
        };



        $('#layerDemo .layui-btn').on('click', function(){
            var othis = $(this), method = othis.data('method');
            active[method] ? active[method].call(this, othis) : '';

            // if (method=="add"){
            //
            // }else if(method=="update"){
            //
            // }
        });

    });

    function put_data(code,name,desc,order,type){
        $('input[name="roleCode"]').val(code);
        $('input[name="roleName"]').val(name);
        $('input[name="roleDesc"]').val(desc);
        $('input[name="roleOrder"]').val(order);
        $("#roleType").val(type);
        layui.form.render();
    }

    function closeForm(){
        layui.layer.closeAll();
    }
    function deleteRole(id){
        window.location.href="role/delete?roleId="+id;
    }

</script>
<script>
    //Demo
    layui.use('form', function(){
        var form = layui.form;

        //监听提交
        form.on('submit(formDemo)', function(data){
            // layer.msg(JSON.stringify(data.field));
            return true;
        });

    });
</script>
</body>
</html>
