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

<table class="layui-hide" id="userTable" lay-filter="userTable"></table>

<script type="text/html" id="toolbarDemo">
    <div class="layui-btn-container">
        <button class="layui-btn layui-btn-sm" lay-event="add">添加商户</button>
    </div>
</script>

<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    {{#  if(d.enabled == 1){ }}
    <a class="layui-btn layui-btn-warm layui-btn-xs"  lay-event="forzen">冻结</a>
    {{#  } }}
    {{#  if(d.enabled == 0){ }}
    <a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="unforzen">解冻</a>
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

        // $.ajax({
        //     "url":"role/rolelistjson",
        //     "type":"get",
        //     "success":function (res){
        //         if (res.code==10000){
        //             selectHtml+= '<div><select>';
        //             $.each(res.data,function (index,value){
        //                 selectHtml+='<option value="'+value.roleCode+'">'+value.roleName+'</option>';
        //             })
        //             selectHtml+='</select></div>';
        //             $('#roleSelect').html(selectHtml);
        //
        //             console.log(selectHtml)
        //         }
        //     }
        // })

        table.render({
            elem: '#userTable'
            ,url:'userinfo/getAll'
            ,toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            ,defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                ,layEvent: 'LAYTABLE_TIPS'
                ,icon: 'layui-icon-tips'
            }]
            ,title: '用户数据表'
            ,cols: [[
                {type: 'checkbox', fixed: 'left'}
                ,{field:'userId', title:'用户编号', width:120, fixed: 'left', unresize: true, sort: true}
                ,{field:'name', title:'用户名称', width:120, edit: 'text'}
                ,{field:'account', title:'用户帐号', width:120, edit: 'text', sort: true}
                ,{field:'enabled', title:'启用状态', width:100,
                    templet: '<div>{{d.enabled==0?"未启用":"启用"}}</div>'
                }
                ,{field:'loginTime', title:'最后登录时间', width:170,
                    templet: '<div>{{layui.util.toDateString(d.loginTime, "yyyy-MM-dd HH:mm:ss")}}</div>'
                }
                ,{field:'d.role.roleName', title:'角色', width:120,
                    templet:'<div>{{d.role ? d.role.roleName: ""}}</div>'
                    // templet:selectHtml
                }
                ,{field:'d.userExpInfo.sex', title:'性别', width:60,
                    templet:'<div>{{d.userExpInfo ? d.userExpInfo.sex: ""}}</div>'
                    // templet:selectHtml
                }
                ,{field:'d.userExpInfo.nickName', title:'昵称', width:120,
                    templet:'<div>{{d.userExpInfo ? d.userExpInfo.nickName: ""}}</div>'
                    // templet:selectHtml
                }
                ,{field:'d.userExpInfo.email', title:'邮箱', width:220,
                    templet:'<div>{{d.userExpInfo ? d.userExpInfo.email: ""}}</div>'
                    // templet:selectHtml
                }
                ,{field:'d.userExpInfo.qqNum', title:'QQ号', width:120,
                    templet:'<div>{{d.userExpInfo ? d.userExpInfo.qqNum: ""}}</div>'
                    // templet:selectHtml
                }
                ,{field:'d.userExpInfo.telephone', title:'电话', width:120,
                    templet:'<div>{{d.userExpInfo ? d.userExpInfo.telephone: ""}}</div>'
                    // templet:selectHtml
                }
                ,{field:'d.userExpInfo.loginNum', title:'登录次数', width:80,
                    templet:'<div>{{d.userExpInfo ? d.userExpInfo.loginNum: ""}}</div>'
                    // templet:selectHtml
                }
                ,{field:'remark', title:'备注', width:200}
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
            // console.log(obj)
            if(obj.event === 'del'){
                layer.confirm('真的删除行么', function(index){
                    // console.log(obj.tr.data('index'));
                    // obj.del();
                    // var oldData = table.cache["test"];
                    // console.log(oldData)
                    // oldData.splice(obj.tr.data('index'),1);
                    // console.log(oldData)
                    $.get("userinfo/deletUser",{"userId":data.userId},function (res){
                        if (res.code!=10000){
                            layer.alert(res.msg);
                        }
                        table.reload('userTable');
                    })
                    layer.close(index);
                });
            } else if(obj.event === 'edit'){
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
                        rowData =null;
                    }
                });
                layer.full(index);
            }else if(obj.event === 'forzen'){
                layer.confirm("是否冻结商户？",function (index){
                    $.get("userinfo/updateUsserEnabled",{'userId':data.userId,'enabled':0},function (res){
                        if (res.code==10000){
                            var newData = obj.data;
                            newData.enabled= res.data.enabled;
                            // obj.update(newData);
                            layer.closeAll();
                            table.reload('userTable');
                        }else {
                            layer.alert(res.msg);
                        }
                    })
                })
            }else if(obj.event === 'unforzen'){
                layer.confirm("是否解除冻结？",function (index){
                    $.get("userinfo/updateUsserEnabled",{'userId':data.userId,'enabled':1},function (res){
                        if (res.code==10000){
                            var newData = obj.data;
                            newData.enabled= res.data.enabled;
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