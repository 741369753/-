<%--
  Created by IntelliJ IDEA.
  User: aizhishang
  Date: 2020/8/30
  Time: 19:32
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
    <legend>商品详情</legend>
</fieldset>

<form class="layui-form" action="" lay-filter="customerForm">
    <input type="hidden" name="customerId">

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">商品名称</label>
            <div class="layui-input-inline">
                <input type="text" name="goodName" lay-verify="" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">商户名称</label>
            <div class="layui-input-inline">
                <input type="text" name="customerName" lay-verify="" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>


    <div class="layui-form-item">
        <label class="layui-form-label">推广说明</label>
        <div class="layui-input-block">
            <input type="text" name="promoteDesc" lay-verify="" autocomplete="off" placeholder="请输入标题" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">文案说明</label>
        <div class="layui-input-block">
            <input type="text" name="copyDesc" lay-verify="" autocomplete="off" placeholder="请输入标题" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">跳转链接</label>
            <div class="layui-input-inline">
                <input type="tel" name="forwardLink" lay-verify="" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">排序编号</label>
            <div class="layui-input-inline">
                <input type="text" name="orderNo" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">商品分类</label>
            <div class="layui-input-inline">
                <input type="tel" name="typeName" lay-verify="" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">标签信息</label>
            <div class="layui-input-inline">
                <input type="text" name="tags" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>


    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">状态</label>
            <div class="layui-input-inline">
                <input type="tel" name="state" lay-verify="" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">添加时间</label>
            <div class="layui-input-inline">
                <input type="text" name="createTime" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">是否置顶</label>
            <div class="layui-input-inline">
                <input type="tel" name="toped" lay-verify="" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">置顶时间</label>
            <div class="layui-input-inline">
                <input type="text" name="topedTime" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">是否推荐</label>
            <div class="layui-input-inline">
                <input type="tel" name="recomed" lay-verify="" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">推荐时间</label>
            <div class="layui-input-inline">
                <input type="text" name="recomedTime" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">站内文案</label>
            <div class="layui-input-inline">
                <input type="tel" name="spcId" lay-verify="" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">空间文案</label>
            <div class="layui-input-inline">
                <input type="text" name="zonId" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-inline">
            <label class="layui-form-label">购买人数作弊值</label>
            <div class="layui-input-inline">
                <input type="tel" name="sellNum" lay-verify="" autocomplete="off" class="layui-input">
            </div>
        </div>
        <div class="layui-inline">
            <label class="layui-form-label">客服QQ</label>
            <div class="layui-input-inline">
                <input type="text" name="kfqq" autocomplete="off" class="layui-input">
            </div>
        </div>
    </div>

    <div class="layui-form-item">
        <label class="layui-form-label">产品网址</label>
        <div class="layui-input-block">
            <input type="text" name="website" lay-verify="" autocomplete="off" placeholder="请输入标题" class="layui-input">
        </div>
    </div>

    <div class="layui-form-item">
        <div class="layui-input-block">
            <button type="submit" id="submitbtn" class="layui-btn" lay-submit="" lay-filter="submitbtn">审核</button>
            <button type="button" id="colsebtn" class="layui-btn  layui-btn-primary" lay-submit="">关闭</button>
        </div>
    </div>

</form>

<div class="layui-form-item">
    <label class="layui-form-label">商品图片</label>
    <div class="layui-input-block layui-carousel" id="test10">
        <div carousel-item="" id="carousel_div">
        </div>
    </div>
</div>

<script src="layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use(['form', 'layedit', 'laydate','carousel'], function(){
        var form = layui.form
            ,layer = layui.layer
            ,layedit = layui.layedit
            ,laydate = layui.laydate
            ,carousel = layui.carousel
            ,$ = layui.jquery;


        var rowData = parent.getRowData();


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
            layer.msg('开关checked：'+ (this.checked ? 'true' : 'false'), {
                offset: '6px'
            });
            layer.tips('温馨提示：请注意开关状态的文字可以随意定义，而不仅仅是ON|OFF', data.othis)
        });

        //监听提交
        form.on('submit(submitbtn)', function(data){

            // rowData = data.field;
            //
            // console.log("kkkk"+JSON.stringify(rowData))
            // if (rowData.customer){
            //     rowData.role.roleCode=data.field.roleName;
            //     rowData.role.roleName=selectData[data.field.roleName];
            // }else {
            //     rowData.role = {"roleCode":data.field.roleName,"roleName":$('#roleSelect').find("option:selected").text()};
            // }
            //
            // if (rowData.goodsType){
            //     rowData.userExpInfo.userId = data.field.userId;
            //     rowData.userExpInfo.nickName=data.field.nickName;
            //     rowData.userExpInfo.sex=data.field.sex;
            //     rowData.userExpInfo.email=data.field.email;
            //     rowData.userExpInfo.qqNum=data.field.qqNum;
            //     rowData.userExpInfo.telephone=data.field.telephone;
            // }else {
            //     rowData.userExpInfo={
            //         "userId":data.field.userId,
            //         "nickName":data.field.nickName,
            //         "sex":data.field.sex,
            //         "email":data.field.email,
            //         "qqNum":data.field.qqNum,
            //         "telephone":data.field.telephone,
            //     }
            // }

            console.log(rowData.goodId)
            var url = null;
            if (rowData==null){
                url="";
            }else {
                url="goods/updateState";
            }
            console.log(url)
            $.ajax({
                "url":url,
                "type":"get",
                "data":{
                    "goodId":rowData.goodId,
                    "state":1
                },
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


        var html = "";
        if (rowData.goodPic1!=null||rowData.goodPic1!=""){
            html+='<div><img src="'+rowData.goodPic1+'"></div>';
        }
        if (rowData.goodPic2!=null||rowData.goodPic2!=""){
            html+='<div><img src="'+rowData.goodPic2+'"></div>';
        }
        if (rowData.goodPic3!=null||rowData.goodPic3!=""){
            html+='<div><img src="'+rowData.goodPic3+'"></div>';
        }
        $('#carousel_div').html(html);

        if (rowData.state!=0) {
            $('#submitbtn').remove();
        }

        // form.render();

        if (rowData!=null){
            form.val('customerForm', {
                "goodName":rowData.goodName,
                "promoteDesc":rowData.promoteDesc,
                "copyDesc":rowData.copyDesc,
                "forwardLink":rowData.forwardLink,
                "orderNo":rowData.orderNo,
                "tags":rowData.tags,
                "state":rowData.state,
                "createTime":rowData.createTime,
                "toped":rowData.toped,
                "recomed":rowData.recomed,
                "topedTime":rowData.topedTime,
                "recomedTime":rowData.recomedTime,
                "spcId":rowData.spcId,
                "zonId":rowData.zonId,
                "sellNum":rowData.sellNum,
                "website":rowData.website,
                "kfqq":rowData.kfqq,
                "typeName":rowData.goodsType?rowData.goodsType.typeName:"",
                "customerName":rowData.customer?rowData.customer.customerName:"",
            });
        }


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

        //常规轮播
        carousel.render({
            elem: '#test1'
            ,arrow: 'always'
        });

        //改变下时间间隔、动画类型、高度
        carousel.render({
            elem: '#test2'
            ,interval: 1800
            ,anim: 'fade'
            ,height: '120px'
        });

        //设定各种参数
        var ins3 = carousel.render({
            elem: '#test3'
        });
        //图片轮播
        carousel.render({
            elem: '#test10'
            ,width: '778px'
            ,height: '440px'
            ,interval: 5000
        });

        //事件
        carousel.on('change(test4)', function(res){
            console.log(res)
        });

        var  active = {
            set: function(othis){
                var THIS = 'layui-bg-normal'
                    ,key = othis.data('key')
                    ,options = {};

                othis.css('background-color', '#5FB878').siblings().removeAttr('style');
                options[key] = othis.data('value');
                ins3.reload(options);
            }
        };

    });
</script>

</body>
</html>