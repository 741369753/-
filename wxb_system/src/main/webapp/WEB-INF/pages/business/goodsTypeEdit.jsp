<%--
  Created by IntelliJ IDEA.
  User: aizhishang
  Date: 2020/8/28
  Time: 17:28
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

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
    <legend>商品分类</legend>
</fieldset>

<form class="layui-form" action="" lay-filter="customerForm">

    <div class="layui-form-item" id="typeId">
        <label class="layui-form-label">分类ID</label>
        <div class="layui-input-block">
            <input type="text" name="typeId" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
            <label class="layui-form-label">分类名称</label>
            <div class="layui-input-block">
                <input type="text" name="typeName" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">父级编号</label>
        <div class="layui-input-block">
            <input type="text" name="parentId" autocomplete="off" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">标签</label>
        <div class="layui-input-block">
            <input type="text" name="typeTag" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">序号</label>
        <div class="layui-input-block">
            <input type="text" name="orderNo" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">分类助记码</label>
        <div class="layui-input-block">
            <input type="text" name="alisaCode" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" class="layui-btn" lay-submit="" lay-filter="submitbtn">立即提交</button>
            <button type="button" id="colsebtn" class="layui-btn  layui-btn-primary" lay-submit="">关闭</button>
        </div>
    </div>


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
            title: function(value){
                if(value.length < 5){
                    return '标题至少得5个字符啊';
                }
            }
            ,pass: [
                /^[\S]{6,12}$/
                ,'密码必须6到12位，且不能出现空格'
            ]
            ,content: function(value){
                layedit.sync(editIndex);
            }
        });

        if (parent.rowData) {
            $('#typeId').attr("style","display:none");
        }


        //监听提交
        form.on('submit(submitbtn)', function(data){
            console.log(rowData)
            var url = null;
            if (rowData==null){
                url="goodsType/insertType";
            }else {
                url="goodsType/updateType";
            }
            console.log(url)
            $.ajax({
                "url":url,
                "type":"post",
                "contentType":"application/json",
                "data":JSON.stringify(data.field),
                "success":function (res){
                    if (res.code==10000){
                        layer.alert("成功",function (){
                            layer.closeAll();
                            parent.setRowData(data.field)
                            window.parent.layer.close(parent.layer.getFrameIndex(window.name));
                        });
                    }else {
                        layer.alert(res.msg);
                    }
                }
            })
            return false;
        });


        $('#colsebtn').click(function (){
            window.parent.layer.close(parent.layer.getFrameIndex(window.name));
            return false;
        });

        var rowData = parent.getRowData();

        // console.log(JSON.stringify(data))

        form.val('customerForm', JSON.parse(JSON.stringify(rowData)));

        //表单赋值
        // layui.$('#LAY-component-form-setval').on('click', function(){
        //     form.val('example', {
        //         "username": "贤心" // "name": "value"
        //         ,"password": "123456"
        //         ,"interest": 1
        //         ,"like[write]": true //复选框选中状态
        //         ,"close": true //开关状态
        //         ,"sex": "女"
        //         ,"desc": "我爱 layui"
        //     });
        // });

        //表单取值
        layui.$('#LAY-component-form-getval').on('click', function(){
            var data = form.val('example');
            alert(JSON.stringify(data));
        });

        if (rowData==null){
            // $("#addDiv").attr("style","display:none;");

        }else {
            $('#addDiv').remove();
        }

    });
</script>

</body>
</html>