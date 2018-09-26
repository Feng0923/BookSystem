<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/16/016
  Time: 16:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>客户反馈</title>
    <link rel="stylesheet" href="boostrap/bootstrap.css">
    <script src="boostrap/bootstrap.js"></script>
    <script src="jquery-3.3.1.min.js"></script>
</head>
<body>
<ul class="nav nav-pills">
    <li role="presentation" ><a href="admin_bookList.jsp">上架书籍情况</a></li>
    <li role="presentation" ><a href="admin_insert.jsp">录入图书</a></li>
    <li role="presentation"><a href="admin_order.jsp">订单详情</a></li>
    <li role="presentation"class="active"><a href="admin_suggestion.jsp">客户反馈</a></li>
    <li role="presentation"><a href="admin_discount.jsp">折扣管理</a></li>
    <li role="presentation"  ><a href="admin_market.jsp">销售情况</a></li>

    <li role="presentation"><a href="user_login.jsp">退出登录</a></li>
</ul>
<div class="form-group" style="margin-top: 20px">
    <table class="table table-bordered">
        <thead   id="thead">
            <th class="col-md-2">账号</th>
            <th class="col-md-4">反馈</th>
            <th class="col-md-4">时间</th>
        </thead>
        <tbody id="content">

        </tbody>
    </table>
</div>

<script type="text/javascript">
    $.ajax({
        url:"admin_suggestion",
        type:"post",
        data:{
            "request":"all",
        },
        dataType:"json",
        success:function (result) {
            $("#content").empty();
            for (var i = 0;i<result.length;i++){
                var id = result[i].user_id;
                var content = result[i].content;
                var time = result[i].time;
                $("#content").append("<tr><td>"+id+"</td><td>\""+content+"\"</td><td>"+time+"</td></tr>");
            }
        }
    })
</script>
</body>
</html>
