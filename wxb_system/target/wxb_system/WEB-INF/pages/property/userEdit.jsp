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
    <legend>用户信息修改</legend>
</fieldset>

<form class="layui-form" action="" lay-filter="customerForm">
    <input type="hidden" name="userId">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">用户名称</label>
            <div class="layui-input-inline">
                <input type="text" name="name" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">帐号</label>
            <div class="layui-input-inline">
                <input type="text" name="account" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">密码</label>
            <div class="layui-input-inline">
                <input type="text" name="password" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">备注</label>
            <div class="layui-input-inline">
                <input type="text" name="remark" lay-verify="required" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">角色</label>
            <div class="layui-input-inline">
                <select id="roleSelect" lay-verify="required" name="roleName" lay-verify="required" class="layui-input">
                    <option value=""></option>
                    <option value="0">北京</option>
                    <option value="1">上海</option>
                    <option value="2">广州</option>
                    <option value="3">深圳</option>
                    <option value="4">杭州</option>
                </select>
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-inline">
                <input type="checkbox" name="enabled" lay-filter="switchTest" lay-skin="switch" lay-text="启用|冻结" value="1">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">昵称</label>
        <div class="layui-input-block">
            <input type="text" name="nickName" lay-verify="nickName" placeholder="请输入昵称" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">性别</label>
        <div class="layui-input-block">
            <input type="radio" name="sex" value="男" title="男" checked="">
            <input type="radio" name="sex" value="女" title="女">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">邮箱</label>
        <div class="layui-input-block">
            <input type="text" name="email" lay-verify="" autocomplete="off" placeholder="请输入邮箱" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">QQ号码</label>
        <div class="layui-input-block">
            <input type="text" name="qqNum" autocomplete="off" placeholder="请输入QQ号" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">手机号</label>
        <div class="layui-input-block">
            <input type="text" name="telephone" lay-verify="" autocomplete="off" placeholder="请输入手机号" class="layui-input">
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


        var rowData = parent.getRowData();
        var selectData = {};

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

        //监听指定开关
        form.on('switch(switchTest)', function(data){
            // if (this.checked){
            //     this.value = 1;
            // }else {
            //     this.value = 0;
            // }
        });

        //监听提交
        form.on('submit(submitbtn)', function(data){
            // console.log(JSON.stringify(data.field))
            var url = null;
            if (rowData==null){
                url="userinfo/insertUser";
            }else {
                url="userinfo/updateUserInfo";
            }
            // console.log(url)

            if (!data.field.enabled){
                data.field.enabled=0;
            }

            rowData = data.field;

            console.log("kkkk"+JSON.stringify(rowData))
            if (rowData.role){
                rowData.role.roleCode=data.field.roleName;
                rowData.role.roleName=selectData[data.field.roleName];
            }else {
                rowData.role = {"roleCode":data.field.roleName,"roleName":$('#roleSelect').find("option:selected").text()};
            }

            delete rowData.roleName;

            if (rowData.userExpInfo){
                rowData.userExpInfo.userId = data.field.userId;
                rowData.userExpInfo.nickName=data.field.nickName;
                rowData.userExpInfo.sex=data.field.sex;
                rowData.userExpInfo.email=data.field.email;
                rowData.userExpInfo.qqNum=data.field.qqNum;
                rowData.userExpInfo.telephone=data.field.telephone;
            }else {
                rowData.userExpInfo={
                    "userId":data.field.userId,
                    "nickName":data.field.nickName,
                    "sex":data.field.sex,
                    "email":data.field.email,
                    "qqNum":data.field.qqNum,
                    "telephone":data.field.telephone,
                }
            }

            // console.log("dfdf"+JSON.stringify(rowData))
            $.ajax({
                "url":url,
                "type":"post",
                "contentType":"application/json",
                "data":JSON.stringify(rowData),
                "success":function (res){
                    if (res.code==10000){
                        layer.alert("成功",function (){
                            layer.closeAll();

                            parent.setRowData(rowData)
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

        // console.log(rowData);

        $.get("role/rolelistjson","",function (res){
            if (res.code ==10000){
                selectData = res.data;
                var selectOption = "";
                $.each(res.data,function (index,value){
                    selectOption+='<option value="'+value.roleCode+'">'+value.roleName+'</option>'
                })
                $('#roleSelect').html(selectOption);
                form.render();

                if (rowData!=null){
                    form.val('customerForm', {
                        "userId":rowData.userId,
                        "name":rowData.name,
                        "account":rowData.account,
                        "password":rowData.password,
                        "remark":rowData.remark,
                        "enabled":rowData.enabled,
                        "roleName":rowData.role?rowData.role.roleCode:"",
                        "nickName":rowData.userExpInfo?rowData.userExpInfo.nickName:"",
                        "sex":rowData.userExpInfo?rowData.userExpInfo.sex:"",
                        "email":rowData.userExpInfo?rowData.userExpInfo.email:"",
                        "qqNum":rowData.userExpInfo?rowData.userExpInfo.qqNum:"",
                        "telephone":rowData.userExpInfo?rowData.userExpInfo.telephone:""
                    });
                }

            }
        })



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