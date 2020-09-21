<%--
  Created by IntelliJ IDEA.
  User: aizhishang
  Date: 2020/8/30
  Time: 18:26
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

<table class="layui-hide" id="typeTable" lay-filter="typeTable"></table>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">添加分类</button>
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="detail">修改</a>
<%--    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">审核</a>--%>
    <a class="layui-btn layui-btn-warm layui-btn-xs"  lay-event="delete">删除</a>
</script>

<script type="text/html" id="roleNameTip">
    <%--    {{#  if(d.id < 100){ }}--%>
    <%--    <a href="/detail/{{d.id}}" class="layui-table-link">{{d.title}}</a>--%>
    <%--    {{#  } else { }}--%>
    <%--    {{d.title}}(普通用户)--%>
    <%--    {{#  } }}--%>
    <select id="roleSelect">
        <option value=""></option>
    </select>
</script>
<script src="layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->

<script>

    var rowData = null;

    function getRowData(){
        return rowData;
    }

    function setRowData(data){
        rowData=data;
    }

    var selectHtml = '';

    layui.use('table', function(){
        var table = layui.table,
            $=layui.jquery;

        table.render({
            elem: '#typeTable'
            ,url:'goodsType/getgoodsType'
            ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,title: '用户数据表'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'typeId', title:'分类编号', width:120, fixed: 'left', unresize: true, sort: true}
                ,{field:'typeName', title:'分类名称', width:150, edit: 'text'}
                ,{field:'parentId', title:'父级编号', width:120, edit: 'text'}
                ,{field:'typeTag', title:'标签', width:150, edit: 'text'}
                ,{field:'orderNo', title:'排序', width:120, edit: 'text'}
                ,{field:'alisaCode', title:'分类助记码', width:120, edit: 'text'}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:200}
            ]]
            ,page: true
        });


        //头工具栏事件
        table.on('toolbar(typeTable)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'add':
                    rowData=null;
                    var data = checkStatus.data;
                    // layer.alert(JSON.stringify(data));
                    var index = layer.open({
                        type: 2 //此处以iframe举例
                        ,title: '添加'
                        // ,area: ['700px', '500px']
                        ,shade: 0.3
                        ,maxmin: false
                        ,area: ['500px', '500px']
                        ,content: 'goodsType/goodsTypeEdit'
                        ,zIndex: layer.zIndex //重点1
                        ,success: function(layero){
                            layer.setTop(layero); //重点2
                        }
                        ,end:function (){
                            table.reload('typeTable');
                        }
                    });
                    // layer.full(index);
                    break;
                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            };
        });


        //监听行工具事件
        table.on('tool(typeTable)', function(obj){
            var data = obj.data;
            rowData = data;
            if(obj.event === 'detail'){
                var index = layer.open({
                    type: 2 //此处以iframe举例
                    ,title: '编辑'
                    ,shade: 0.3
                    ,maxmin: false
                    ,area: ['500px', '480px']
                    ,content: 'goodsType/goodsTypeEdit'
                    ,zIndex: layer.zIndex //重点1
                    ,success: function(layero){
                        layer.setTop(layero); //重点2
                    }
                    ,end:function (){
                        table.reload('typeTable');
                        rowData=null;
                    }
                });
                // layer.full(index);
            } else if(obj.event === 'delete'){
                layer.confirm("是否删除？",function (index){
                    $.get("goodsType/deleteType",{'typeId':data.typeId},function (res){
                        if (res.code==10000){
                            // obj.update(newData);
                            layer.closeAll();
                            table.reload('typeTable');
                        }else {
                            layer.alert(res.msg);
                        }
                    })
                })
            }
        });
    });
</script>

</body>
</html>