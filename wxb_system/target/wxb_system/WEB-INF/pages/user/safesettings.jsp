<%--
  Created by IntelliJ IDEA.
  User: aizhishang
  Date: 2020/8/29
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Layui</title>
    <base href="<%=request.getContextPath()%>/">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css"  media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 50px;">
    <legend>基本资料</legend>
</fieldset>

<form class="layui-form" action="" lay-filter="userForm">
    <div class="layui-form-item">
        <label class="layui-form-label">原密码</label>
        <div class="layui-input-block">
            <input type="password" name="oldPassword" lay-verify="pass" placeholder="请输入原密码" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">新密码</label>
        <div class="layui-input-block">
            <input type="password" name="password" lay-verify="pass" placeholder="请输入新密码" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">确认密码</label>
        <div class="layui-input-block">
            <input type="password" name="passwordAgain" lay-verify="confirmPass" placeholder="请再次输入密码" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="submitbtn">立即提交</button>
        </div>
    </div>
</form>

</form>

<script src="layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use(['form', 'layedit', 'laydate'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate
            ,$ = layui.jquery;

        //日期
        laydate.render({
            elem: '#date'
        });
        laydate.render({
            elem: '#date1'
        });

        //创建一个编辑器
        var editIndex = layedit.build('LAY_demo_editor');

        //自定义验证规则
        form.verify({
            nickName: function(value){
                if(value.length < 2){
                    return '昵称至少得2个字符';
                }
            }
            ,pass: [
                /^[\S]{6,12}$/
                ,'密码必须6到12位，且不能出现空格'
            ]
            ,confirmPass:function (value){
                if ($('input[name=password]').val()!=value){
                    return '两次输入密码不一致';
                }
            }
            ,content: function(value){
                layedit.sync(editIndex);
            }
        });

        //监听提交
        form.on('submit(submitbtn)', function(data){
            $.ajax({
                "url":"userinfo/safe",
                "type":"post",
                "data":data.field,
                "success":function (res){
                    if (res.code==10000){
                        layer.alert("修改成功",function (){
                            window.location.reload();
                        });

                    }else {
                        layer.alert(res.msg);
                    }
                }
            })
            return false;
        });

        $.get("userinfo/getUserInfo","",function (res){
            if (res.code==10000){
                console.log(res.data)
                //表单赋值
                form.val('userForm', JSON.parse(JSON.stringify(res.data)));
            }
        })

    });
</script>

</body>
</html>

