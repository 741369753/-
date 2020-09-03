<%--
  Created by IntelliJ IDEA.
  User: aizhishang
  Date: 2020/8/26
  Time: 23:25
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
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>基本演示</legend>
</fieldset>
<div class="layui-btn-container">
    <button type="button" class="layui-btn layui-btn-sm" lay-demo="getChecked">获取选中节点数据</button>
    <button type="button" class="layui-btn layui-btn-sm" lay-demo="setChecked">勾选指定节点</button>
    <button type="button" class="layui-btn layui-btn-sm" lay-demo="reload">重载实例</button>
</div>

<div id="test12" class="demo-tree-more"></div>

<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>常规用法</legend>
</fieldset>

<div id="test1" class="demo-tree demo-tree-box"></div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>无连接线风格</legend>
</fieldset>
<div id="test13" class="demo-tree-more"></div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>仅节点左侧图标控制收缩</legend>
</fieldset>

<div id="test2" class="demo-tree"></div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>手风琴模式</legend>
</fieldset>

<div id="test4" class="demo-tree"></div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>点击节点新窗口跳转</legend>
</fieldset>

<div id="test5" class="demo-tree"></div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>开启复选框</legend>
</fieldset>

<div id="test7" class="demo-tree"></div>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>开启节点操作图标</legend>
</fieldset>

<div id="test9" class="demo-tree demo-tree-box" style="width: 200px; height: 300px; overflow: scroll;"></div>

<script src="layui/layui.js" charset="utf-8"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use(['tree', 'util'], function(){
        var tree = layui.tree
            ,layer = layui.layer
            ,util = layui.util

            //模拟数据2
            ,data2 = [
                {
                    "title": "系统配置",
                    "id": "010101",
                    "children": [
                        {
                            "title": "网站公告管理",
                            "id": "01010103",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "会员信息管理",
                            "id": "01010104",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "日志信息管理",
                            "id": "01010105",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "全局参数配置",
                            "id": "01010102",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "字典参数配置",
                            "id": "01010101",
                            "checked": true,
                            "children": []
                        }
                    ]
                },
                {
                    "title": "财务管理",
                    "id": "010105",
                    "children": [
                        {
                            "title": "客服提成结算",
                            "id": "01010507",
                            "children": [
                                {
                                    "title": "提成返款单",
                                    "id": "0101050703",
                                    "checked": true,
                                    "children": []
                                },
                                {
                                    "title": "待结算提成",
                                    "id": "0101050701",
                                    "checked": true,
                                    "children": []
                                },
                                {
                                    "title": "已结算提成",
                                    "id": "0101050702",
                                    "checked": true,
                                    "children": []
                                },
                                {
                                    "title": "已返款提成",
                                    "id": "0101050704",
                                    "checked": true,
                                    "children": []
                                }
                            ]
                        },
                        {
                            "title": "订单提成结算",
                            "id": "01010506",
                            "children": [
                                {
                                    "title": "已结算流水",
                                    "id": "0101050605",
                                    "checked": true,
                                    "children": []
                                },
                                {
                                    "title": "财务返款单",
                                    "id": "0101050604",
                                    "checked": true,
                                    "children": []
                                },
                                {
                                    "title": "支付宝记录",
                                    "id": "0101050602",
                                    "checked": true,
                                    "children": []
                                },
                                {
                                    "title": "已返款流水",
                                    "id": "0101050603",
                                    "checked": true,
                                    "children": []
                                },
                                {
                                    "title": "待结算流水",
                                    "id": "0101050601",
                                    "checked": true,
                                    "children": []
                                }
                            ]
                        }
                    ]
                },
                {
                    "title": "权限管理",
                    "id": "010102",
                    "children": [
                        {
                            "title": "菜单信息管理",
                            "id": "01010203",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "用户信息管理",
                            "id": "01010202",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "角色信息管理",
                            "id": "01010201",
                            "checked": true,
                            "children": []
                        }
                    ]
                },
                {
                    "title": "统计报表",
                    "id": "010107",
                    "children": [
                        {
                            "title": "会员订单统计",
                            "id": "01010701",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "商户订单统计",
                            "id": "01010702",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "商品推广统计",
                            "id": "01010705",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "平台访问统计",
                            "id": "01010704",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "客服业绩统计",
                            "id": "01010703",
                            "checked": true,
                            "children": []
                        }
                    ]
                },
                {
                    "title": "业务管理",
                    "id": "010103",
                    "children": [
                        {
                            "title": "商户信息管理",
                            "id": "01010301",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "商品信息管理",
                            "id": "01010302",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "商品文案管理",
                            "id": "01010303",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "商品分类管理",
                            "id": "01010304",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "文案分类管理",
                            "id": "01010305",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "商品类型管理",
                            "id": "01010306",
                            "checked": false,
                            "children": []
                        }
                    ]
                },
                {
                    "title": "订单管理",
                    "id": "010104",
                    "children": [
                        {
                            "title": "所有的订单",
                            "id": "01010401",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "已退货订单",
                            "id": "01010409",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "已取消订单",
                            "id": "01010404",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "待发货订单",
                            "id": "01010403",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "待跟进订单",
                            "id": "01010410",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "已签收订单",
                            "id": "01010408",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "已拒收订单",
                            "id": "01010407",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "已发货订单",
                            "id": "01010405",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "已结算订单",
                            "id": "01010406",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "已下单订单",
                            "id": "01010402",
                            "checked": true,
                            "children": []
                        }
                    ]
                },
                {
                    "title": "客服订单",
                    "id": "010106",
                    "children": [
                        {
                            "title": "待跟进",
                            "id": "01010610",
                            "checked": false,
                            "children": []
                        },
                        {
                            "title": "已结算",
                            "id": "01010605",
                            "checked": false,
                            "children": []
                        },
                        {
                            "title": "已接管",
                            "id": "01010609",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "已签收",
                            "id": "01010607",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "已拒收",
                            "id": "01010606",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "已下单",
                            "id": "01010601",
                            "checked": false,
                            "children": []
                        },
                        {
                            "title": "已发货",
                            "id": "01010604",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "已取消",
                            "id": "01010603",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "待发货",
                            "id": "01010602",
                            "checked": true,
                            "children": []
                        },
                        {
                            "title": "已退货",
                            "id": "01010608",
                            "checked": true,
                            "children": []
                        }
                    ]
                }
            ];


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




        //开启复选框
        tree.render({
            elem: '#test7'
            ,data: data2
            ,showCheckbox: true
        });


    });
</script>

</body>
</html>
