<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/20/020
  Time: 16:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>销售情况</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="boostrap/bootstrap.css">
    <script src="boostrap/bootstrap.js"></script>
    <script src="jquery-3.3.1.min.js"></script>
</head>
<body>
<ul class="nav nav-pills">
    <li role="presentation" ><a href="admin_bookList.jsp">上架书籍情况</a></li>
    <li role="presentation" ><a href="admin_insert.jsp">录入图书</a></li>
    <li role="presentation"><a href="admin_order.jsp">订单详情</a></li>
    <li role="presentation"><a href="admin_suggestion.jsp">客户反馈</a></li>
    <li role="presentation"><a href="admin_discount.jsp">折扣管理</a></li>
    <li role="presentation" class="active"><a href="admin_market.jsp">销售情况</a></li>

    <li role="presentation"><a href="user_login.jsp">退出登录</a></li>
</ul>

<table  class="table table-bordered" style="margin-top: 20px">
    <tr>
        <th>ISBN</th>
        <th>书名</th>
        <th>作者</th>
        <th>价格</th>
        <th>销售量</th>
    </tr>
    <tbody id="booklist">

    </tbody>
</table>
<script type="text/javascript">
    $.ajax({
        url:"/market",
        type:"post",
        data:{
            "request":"all"
        },
        dataType:"json",
        success:function (result) {
            $("#booklist").empty();
            for (var i = 0;i<result.length;i++){
                var isbn = result[i].isbn;
                var title = result[i].title;
                var author = result[i].author;
                var prize = result[i].prize;
                var count = result[i].count;
                $("#booklist").append("<tr><td>"+isbn+"</td><td>"+title+"</td><td>"+author+"</td><td>"+prize +"</td><td>"+count+"</td></tr>")
            }
        }
    })
</script>

</body>
</html>
