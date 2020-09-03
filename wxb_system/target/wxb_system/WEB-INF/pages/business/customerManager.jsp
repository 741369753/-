<%--
  Created by IntelliJ IDEA.
  User: aizhishang
  Date: 2020/8/28
  Time: 14:02
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

<table class="layui-hide" id="test" lay-filter="test"></table>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">添加商户</button>
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    {{#  if(d.state == 1){ }}
    <a class="layui-btn layui-btn-warm layui-btn-xs"  lay-event="forzen">冻结</a>
    {{#  } }}
    {{#  if(d.state == 0){ }}
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="unforzen">解冻</a>
    {{#  } }}
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

    layui.use('table', function(){
        var table = layui.table,
            $=layui.jquery;

        table.render({
            elem: '#test'
            ,url:'customer/getcustomer'
            ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,title: '用户数据表'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'customerId', title:'商户编号', width:120, fixed: 'left', unresize: true, sort: true}
                ,{field:'customerName', title:'商户名称', width:120, edit: 'text'}
                ,{field:'qq', title:'QQ号', width:120, edit: 'text', sort: true}
                ,{field:'wxh', title:'微信号', width:120}
                ,{field:'phone', title:'电话', width:150}
                ,{field:'createtime', title:'创建时间', width:170,
                    templet: '<div>{{layui.util.toDateString(d.createtime, "yyyy-MM-dd HH:mm:ss")}}</div>'
                }
                ,{field:'loginName', title:'帐户', width:120}
                ,{field:'state', title:'状态', width:60}
                ,{field:'level', title:'等级', width:60}
                ,{field:'accBalance', title:'余额', width:100}
                ,{field:'updateTime', title:'修改时间', width:170,
                    templet: '<div>{{layui.util.toDateString(d.updateTime, "yyyy-MM-dd HH:mm:ss")}}</div>'
                }
                ,{field:'website', title:'商户网址', width:120}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:170}
            ]]
            ,page: true
        });

        //头工具栏事件
        table.on('toolbar(test)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'add':
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    layer.open({
                        type: 2 //此处以iframe举例
                        ,title: '编辑'
                        ,area: ['700px', '500px']
                        ,shade: 0.3
                        ,maxmin: true
                        ,content: 'customer/editPage'
                        ,zIndex: layer.zIndex //重点1
                        ,success: function(layero){
                            layer.setTop(layero); //重点2
                            table.reload('test');
                        }
                    });
                    break;
                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            };
        });


        //监听行工具事件
        table.on('tool(test)', function(obj){
            var data = obj.data;
            //console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    // console.log(obj.tr.data('index'));
                    // obj.del();
                    // var oldData = table.cache["test"];
                    // console.log(oldData)
                    // oldData.splice(obj.tr.data('index'),1);
                    // console.log(oldData)
                    $.get("customer/delete",{"id":data.customerId},function (res){
                        table.reload('test');
                    })
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
                rowData = data;
                layer.open({
                    type: 2 //此处以iframe举例
                    ,title: '编辑'
                    ,area: ['700px', '500px']
                    ,shade: 0.3
                    ,maxmin: true
                    // ,offset: [ //为了演示，随机坐标
                    //     ($(window).height()-300)
                    //     ,($(window).width()-390)
                    // ]
                    ,content: 'customer/editPage'
                    // ,yes: function(){
                    //     $(that).click();
                    // }
                    // ,btn2: function(){
                    //     layer.closeAll();
                    // }

                    ,zIndex: layer.zIndex //重点1
                    ,success: function(layero){
                        layer.setTop(layero); //重点2
                    }
                    ,end:function (){
                        obj.update(JSON.parse(JSON.stringify(rowData)));
                        rowData=null;
                    }
                });
            }else if(obj.event === 'forzen'){
                layer.confirm("是否冻结商户？",function (index){
                    $.get("customer/updateState",{'id':data.customerId,'state':0},function (res){
                        if (res.code==10000){
                            var newData = obj.data;
                            newData.state= res.data.state;
                            // obj.update(newData);
                            layer.closeAll();
                            table.reload('test');
                        }
                    })
                })
            }else if(obj.event === 'unforzen'){
                layer.confirm("是否解除冻结？",function (index){
                    $.get("customer/updateState",{'id':data.customerId,'state':1},function (res){
                        if (res.code==10000){
                            var newData = obj.data;
                            newData.state= res.data.state;
                            // obj.update(newData);
                            layer.closeAll();
                            table.reload('test');
                        }
                    })
                })
            }
        });
    });
</script>

</body>
</html>