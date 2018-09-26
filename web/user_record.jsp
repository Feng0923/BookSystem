<%@ page import="java.util.ArrayList" %>
<%@ page import="Bean.Order" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/10/010
  Time: 18:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购买记录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="boostrap/bootstrap.css">
    <script src="boostrap/bootstrap.js"></script>
    <script src="jquery-3.3.1.min.js"></script>
    <script src="order_state.js"></script>
</head>
<body>
<div id="mainbox">
    <ul class="nav nav-pills">
        <li role="presentation"><a href="/user_search.jsp">购买书籍</a></li>
        <li role="presentation"class="active"><a href="/user_record.jsp">我的订单</a></li>
        <li role="presentation"><a href="/user_suggestion.jsp">反馈意见</a></li>
        <li role="presentation" ><a href="/user_information.jsp">个人信息</a></li>
        <%--<li role="presentation"><a href="/user_presale.jsp">我的预售</a></li>--%>
        <li role="presentation"><a href="/user_login.jsp">退出登录</a></li>
    </ul>
    <div id="content" style="margin:10px 10px">
        <table id="record" class="table table-bordered table-hover ">
            <tr>
                <th >订单号</th>
                <th >书名</th>
                <th >作者</th>
                <th >单价</th>
                <th >数量</th>
                <th>总额</th>
                <th >时间</th>
                <th >状态</th>
                <th><th>
            </tr>
            <tbody id="orders">

            </tbody>
        </table>
    </div>
</div>

<script type="text/javascript">

</script>
<script type="text/javascript">




    var cookies = document.cookie;
    var cookieArray = cookies.split(";");
    var id ;
    for (var i = 0;i<cookieArray.length;i++){
        var map = cookieArray[i].split("=");
        var p = $.trim(map[0]);
        if (p.trim() == ("user_id").trim()){
            id = map[1];
        }
    }


    $.ajax({
        url:"/record",
        type:"post",
        data:{
            "user_id":id
        },
        dataType:"json",
        success:function (result) {
            $("#orders").empty();
            var result = eval(result);
            for (var i = 0 ;i<result.length;i++){
                var order_number = result[i].order_number;
                var title = result[i].title;
                var author = result[i].author;
                var prize = result[i].prize;
                var time = result[i].time;
                var state = result[i].state;
                var count = result[i].count;
                var amount = result[i].amount;
                $("#orders").append("<tr><td>"+order_number+"</td><td>"+title+"</td><td>"+author+"</td><td>"+prize+"</td><td>"+count+"</td><td>"+amount +"</td><td>"+time+"</td><td>"+getState(state)+"</td><td><button onclick='finish("+order_number+")' class='btn btn-default'>收货确认</button><a onclick='re("+order_number+")' class='btn btn-default'>退款 </a></td></tr>");
            }
        }
    })

    function finish(order_number) {
        if (order_number!= ""){
            $.ajax({
                url:"/finish",
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
    function re(order_nubmer) {
        if (order_nubmer!= ""){
            $.ajax({
                url:"/refund",
                type:"post",
                data:{
                    "order_number":order_nubmer
                },
                success:function (result) {
                    if (result.trim() == "true".trim()){
                        // alert(order_nubmer)
                        window.location.reload();
                    }
                }
            })
        }
    }
</script>


</body>
</html>
