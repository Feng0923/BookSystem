<%@ page import="Manager.AdminManager" %>
<%@ page import="Bean.Order" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/14/014
  Time: 19:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>订单详情</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="boostrap/bootstrap.css">
    <script src="boostrap/bootstrap.js"></script>
    <script src="jquery-3.3.1.min.js"></script>
    <script src="order_state.js"></script>
    <link rel="stylesheet" href="button.css">
</head>
<body>
    <div>
        <ul class="nav nav-pills">
            <li role="presentation" ><a href="admin_bookList.jsp">上架书籍情况</a></li>
            <li role="presentation" ><a href="admin_insert.jsp">录入图书</a></li>
            <li role="presentation"class="active"><a href="admin_order.jsp">订单详情</a></li>
            <li role="presentation"><a href="admin_suggestion.jsp">客户反馈</a></li>
            <li role="presentation"><a href="admin_discount.jsp">折扣管理</a></li>
            <li role="presentation"  ><a href="admin_market.jsp">销售情况</a></li>

            <li role="presentation"><a href="user_login.jsp">退出登录</a></li>
        </ul>
        <div id="content" style="margin:10px 10px">
            <div class="form-inline "style="margin-top: 20px " >
                <div class="form-group">
                    <span class="glyphicon glyphicon-list-alt" aria-hidden="true"/>
                    <input type="text" class="form-control"name="order_number" id="order_number" placeholder="订单号">
                </div>
                <button onclick="getData()" class="btn btn-default" ><span class="glyphicon glyphicon-search" aria-hidden="true"/> </button>
                <button onclick="getNotAssign()" class="btn btn-default" >查询未发货订单</button>
                <button onclick="getRefunding()" class="btn btn-default" >查询退款订单</button>
            </div>
            <table id="record" class="table table-bordered table-hover " style="margin-top: 20px" >
                <thead>
                    <th  >订单号</th>
                    <th  >书名</th>
                    <th  >作者</th>
                    <th  >单价</th>
                    <th  >数量</th>
                    <th>总额</th>
                    <th  >时间</th>
                    <th>姓名</th>
                    <th>电话</th>
                    <th>地址</th>
                    <th  >状态</th>

                </thead>
                <tbody id="orders">

                </tbody>
            </table>
        </div>
    </div>

<script type="text/javascript">
    function getRefunding() {
        $.ajax({
            url:"admin_order",
            type:"post",
            data:{
                "request":"refunding"
            },
            dataType:"json",
            success:function (result) {
                getOrders(result);
            }
        })
    }
    function getData() {
        var order_number = $("#order_number").val();
        if (order_number == ""){
            window.location.reload();
        }else {
            $.ajax({
                url:"admin_order",
                type:"post",
                data:{
                    "request":"order_number",
                    "order_number":order_number
                },
                dataType:"json",
                success:function (result) {
                    getOrders(result);
                }
            })
        }

    }
    function getNotAssign() {
        $.ajax({
            url:"admin_order",
            type:"post",
            data:{
                "request":"notAssign",
            },
            dataType:"json",
            success:function (result) {
                getOrders(result);
            }
        })
    }

    function getOrders(result) {
        var orders = $("#orders");
        orders.empty();
        for(var i = 0;i<result.length;i++){
            var order_number = result[i].order_number;
            var title = result[i].title;
            var author = result[i].author;
            var prize = result[i].prize;
            var time = result[i].time;
            var count = result[i].count;
            var state = result[i].state;
            var amount = result[i].amount;
            var name = result[i].name;
            var phone = result[i].phone;
            var address = result[i].address;
            orders.append("<tr><td>"+order_number+"</td><td>"+title+"</td><td>"+author+"</td><td>"+prize+"</td><td>"+count+"</td><td>"+amount+"</td><td>"+time+"</td><td>"+name+"</td><td>"+phone+"</td><td>"+address+"</td><td>"+getState(state)+"</td><td><button class='btn btn-default' onclick='assign("+order_number+")'>发货</button ><button onclick='refunded("+order_number+")' style='margin-left: 10px' class='btn btn-default'>退款确认</button></td ></tr>")
        }
    }

        $.ajax({
            url:"admin_order",
            type:"post",
            data:{
                "request":"all"
            },
            dataType:"json",
            success:function (result) {
                getOrders(result);
            }
        })
    
    function assign(number) {

        $.ajax({
            url:"admin_assign",
            data:{
                "order_number":number
            },
            type:"post",
            success:function (result) {
                if (result == "true"){
                    // 发货成功
                    window.location.reload()
                }else{
                    // 失败
                }
            }
        })
    }
    function refunded(order_number) {

        if (order_number!= ""){
            $.ajax({
                url:"/refunded",
                type:"post",
                data:{
                    "order_number":order_number
                },
                success:function (result) {
                    if (result.trim() == "true".trim()){
                        window.location.reload();
                    }
                }
            })
        }
    }
</script>
</body>
</html>
