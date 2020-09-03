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

<table class="layui-hide" id="userTable" lay-filter="userTable"></table>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">添加商户</button>
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="detail">详情</a>
<%--    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">审核</a>--%>
    {{#  if(d.state == 1){ }}
    <a class="layui-btn layui-btn-warm layui-btn-xs"  lay-event="down">下架</a>
    {{#  } }}
    {{#  if(d.state == 2){ }}
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="up">上架</a>
    {{#  } }}
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
            elem: '#userTable'
            ,url:'goods/getgoodslist'
            ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,title: '用户数据表'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'goodId', title:'商品编号', width:120, fixed: 'left', unresize: true, sort: true}
                ,{field:'goodName', title:'商品名称', width:120, edit: 'text'}
                , {
                    field: 'd.customer.customerName', title: '所属商户', width: 120, edit: 'text', sort: true,
                    templet: '<div>{{d.customer ? d.customer.customerName: ""}}</div>'
                }
                ,{field:'d.goodsType.typeName', title:'商品分类', width:120, edit: 'text', sort: true,
                    templet: '<div>{{d.goodsType ? d.goodsType.typeName: ""}}</div>'
                }
                ,{field:'tags', title:'标签信息', width:120, edit: 'text', sort: true}
                ,{field:'state', title:'状态', width:120, edit: 'text', sort: true,
                    templet: '<div>{{d.state==0?"待审核":d.state==1?"已上架":"已下架"}}</div>'
                }
                ,{field:'createTime', title:'添加时间', width:170, edit: 'text', sort: true,
                    templet: '<div>{{layui.util.toDateString(d.createTime, "yyyy-MM-dd HH:mm:ss")}}</div>'
                }
                ,{field:'toped', title:'置顶', width:120, edit: 'text', sort: true}
                ,{field:'topedTime', title:'置顶时间', width:170,
                    templet: '<div>{{layui.util.toDateString(d.topedTime, "yyyy-MM-dd HH:mm:ss")}}</div>'
                }
                ,{field:'recomed', title:'推荐', width:120, edit: 'text', sort: true}
                ,{field:'recomedTime', title:'推荐时间', width:170,
                    templet: '<div>{{layui.util.toDateString(d.recomedTime, "yyyy-MM-dd HH:mm:ss")}}</div>'
                }
                ,{field:'orderNo', title:'排序编号', width:100, edit: 'text', sort: true}
                ,{field:'sellNum', title:'作弊值', width:120, edit: 'text', sort: true}
                ,{fixed: 'right', title:'操作', toolbar: '#barDemo', width:170}
            ]]
            ,page: true
        });


        //头工具栏事件
        table.on('toolbar(userTable)', function(obj){
            var checkStatus = table.checkStatus(obj.config.id);
            switch(obj.event){
                case 'add':
                    rowData=null;
                    var data = checkStatus.data;
                    layer.alert(JSON.stringify(data));
                    var index = layer.open({
                        type: 2 //此处以iframe举例
                        ,title: '编辑'
                        // ,area: ['700px', '500px']
                        ,shade: 0.3
                        ,maxmin: false
                        ,content: 'userinfo/usereditpage'
                        ,zIndex: layer.zIndex //重点1
                        ,success: function(layero){
                            layer.setTop(layero); //重点2
                        }
                        ,end:function (){
                            table.reload('userTable');
                        }
                    });
                    layer.full(index);
                    break;
                //自定义头工具栏右侧图标 - 提示
                case 'LAYTABLE_TIPS':
                    layer.alert('这是工具栏右侧自定义的一个图标按钮');
                    break;
            };
        });


        //监听行工具事件
        table.on('tool(userTable)', function(obj){
            var data = obj.data;
            rowData = data;
            if(obj.event === 'detail'){
                var index = layer.open({
                    type: 2 //此处以iframe举例
                    ,title: '编辑'
                    ,shade: 0.3
                    ,maxmin: false
                    ,content: 'goods/goodsDeatil'
                    ,zIndex: layer.zIndex //重点1
                    ,success: function(layero){
                        layer.setTop(layero); //重点2
                    }
                    ,end:function (){
                        table.reload('userTable');
                        rowData=null;
                    }
                });
                layer.full(index);
            } else if(obj.event === 'detail'){
                rowData = data;
                // console.log("ssss"+JSON.stringify(rowData));
                var index = layer.open({
                    type: 2 //此处以iframe举例
                    ,title: '编辑'
                    // ,area: ['700px', '500px']
                    ,shade: 0.3
                    ,maxmin: false
                    // ,offset: [ //为了演示，随机坐标
                    //     ($(window).height()-300)
                    //     ,($(window).width()-390)
                    // ]
                    ,content: 'userinfo/usereditpage'
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
                        // console.log(rowData);
                        // console.log("jjjj"+JSON.stringify(rowData))
                        // obj.update(JSON.parse(JSON.stringify(rowData)));
                        table.reload('userTable');
                        rowData=null;
                    }
                });
                layer.full(index);
            }else if(obj.event === 'down'){
                layer.confirm("是否下架？",function (index){
                    $.get("goods/updateState",{'goodId':data.goodId,'state':2},function (res){
                        if (res.code==10000){
                            // obj.update(newData);
                            layer.closeAll();
                            table.reload('userTable');
                        }else {
                            layer.alert(res.msg);
                        }
                    })
                })
            }else if(obj.event === 'up'){
                layer.confirm("是否上架？",function (index){
                    $.get("goods/updateState",{'goodId':data.goodId,'state':1},function (res){
                        if (res.code==10000){
                            // obj.update(newData);
                            layer.closeAll();
                            table.reload('userTable');
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