<%--
  Created by IntelliJ IDEA.
  User: aizhishang
  Date: 2020/8/27
  Time: 15:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <base href="<%=request.getContextPath()%>/">
    <meta charset="utf-8">
    <title>Layui</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="layui/css/layui.css"  media="all">
    <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>

<div id="test7" class="demo-tree"></div>

<div id="btnDiv" class="layui-form-item" style="display: none">
    <div class="layui-input-block">
        <button lay-active="update" type="button" class="layui-btn">修改</button>
        <button lay-active="close" type="button" class="layui-btn layui-btn-primary">关闭</button>
    </div>
</div>

<script src="layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use(['tree', 'util'], function(){
        var tree = layui.tree
            ,$ = layui.jquery
            ,layer = layui.layer
            ,util = layui.util

            //模拟数据2
            ,menuData=null;


        //按钮事件
        util.event('lay-demo', {
            getChecked: function(othis){
                var checkedData = tree.getChecked('demoId1'); //获取选中节点的数据

                layer.alert(JSON.stringify(checkedData), {shade:0});
                console.log(checkedData);
            }
            ,setChecked: function(){
                tree.setChecked('demoId1', [12, 16]); //勾选指定节点
            }
            ,reload: function(){
                //重载实例
                tree.reload('demoId1', {

                });

            }
        });

        var roleId=window.location.search.substr(1);
        $.post("role/queryMenu",{'roleId':roleId},function (res){
            if (res.code==10000){
                menuData = res.data;
                // console.log(JSON.stringify(menuData))
                //开启复选框
                tree.render({
                    elem: '#test7'
                    ,data: menuData
                    ,showCheckbox: true
                    ,id:'roleMenuTree'
                });
                $('#btnDiv').show();
            }else {
                layer.alert(res.msg)
            }
        })

        util.event('lay-active', {
            update: function(){

                layer.confirm("是否存保修改？",{title:'提示'},function (index){
                    var checkedData = tree.getChecked('roleMenuTree');

                    var codeArr = [];

                    getMenuCode(codeArr,checkedData);

                    var jsonStr = {'codes':codeArr,'roleId':roleId};
                    console.log(JSON.stringify(jsonStr))
                    $.ajax({
                        "url":"role/updateMenu",
                        "type":"post",
                        "contentType":"application/json",
                        "data":JSON.stringify(jsonStr),
                        "success":function (res){
                            if(res.code==10000){
                                layer.alert("修改成功",function (index){
                                    layer.close(index);
                                });
                                window.parent.layer.close(index);
                            }else {
                                layer.alert(res.msg);
                            }
                        }
                    })

                    layer.close(index);
                })
            }
            ,close: function(){
                var index = parent.layer.getFrameIndex(window.name);
                window.parent.layer.close(index);
            }
        });

        function getMenuCode(codeArr,menuDatas){
            $.each(menuDatas,function (index,menu){
                if (menu.id!=null){
                    codeArr.push(menu.id);
                }
                var child = menu.children;
                if (child!=null&&child.length>0){
                    getMenuCode(codeArr,child);
                }
            })
        }
    });


</script>

</body>
</html>
