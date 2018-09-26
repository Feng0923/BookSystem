<%@ page import="java.util.ArrayList" %>
<%@ page import="Bean.Book" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="org.json.JSONException" %>
<%@ page import="org.json.JSONArray" %>
<%@ page import="com.google.gson.JsonObject" %>
<%@ page import="com.google.gson.JsonArray" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/10/010
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>购买书籍</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="boostrap/bootstrap.css">
    <script src="boostrap/bootstrap.js"></script>
    <script src="jquery-3.3.1.min.js"></script>
    <script src="boostrap/dropdown.js"></script>

</head>
<body>
<div id="mainbox">
    <ul class="nav nav-pills">
        <li role="presentation"class="active"><a href="/user_search.jsp">购买书籍</a></li>
        <li role="presentation"><a href="/user_record.jsp">我的订单</a></li>
        <li role="presentation"><a href="/user_suggestion.jsp">反馈意见</a></li>
        <li role="presentation" ><a href="/user_information.jsp">个人信息</a></li>
        <%--<li role="presentation"><a href="/user_presale.jsp">我的预售</a></li>--%>
        <li role="presentation"><a href="/user_login.jsp">退出登录</a></li>
    </ul>
    <div id="content" style="margin-left: 10px">
        <form class="form-inline "style="margin-top: 20px " onsubmit="return false">
            <div class="form-group">
                <span class="glyphicon glyphicon-book" aria-hidden="true"/>
                <input type="text" class="form-control"name="title" id="title" placeholder="书名">
            </div>
            <div class="form-group">
                <span class="glyphicon glyphicon-user" aria-hidden="true"/>
                <input type="text" class="form-control" name="author" id="author" placeholder="作者">
            </div>
            <button onclick="search()" class="btn btn-default" ><span class="glyphicon glyphicon-search" aria-hidden="true"/> </button>
        </form>
        <table class="table table-bordered">
            <tr>
                <th>书名</th>
                <th>作者</th>
                <th>价格</th>
                <th>剩余量</th>
                <th>购买</th>
            </tr>
            <tbody id="books">

            </tbody>
        </table>
        <div id="warning" class=" alert alert-danger alert-dismissable fade in col-md-3 " role="alert" style="display: none; position: absolute;left: 50% ;top:50%;transform: translate(-50%,-50%)">

        </div>
        <div id="success" class="alert alert-success" role="alert" style="display: none; position: absolute;left: 50% ;top:50%;transform: translate(-50%,-50%)">

        </div>


    </div>
</div>
<script type="text/javascript">

    function showBooks(result) {
        $("#books").empty();
        for (var i = 0;i<result.length;i++){
            var isbn = result[i].isbn;
            var title = result[i].title;
            var author = result[i].author;
            var prize = result[i].prize;
            var rest = result[i].rest;
            $("#books").append("<tr><td>"+title+"</td><td>"+author+"</td><td>"+prize+"</td><td>"+ rest+"</td><td><a  class='btn btn-default'  onclick='shop("+isbn+","+i+","+rest+")'>购买</a><input id="+i+" type='number'  style='width: 50px' min='0' max=\""+rest+"\" ></td></tr>")
        }
    }

    function shop(isbn,position,rest) {
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
        var count = $("#"+position).val();
        if (count == ""||count==0){
            $("#warning").text("购买失败，请选择购买数量!");
            $("#warning").fadeIn("fast");
            $("#warning").fadeOut(1000);
            return;
        }else if(count>rest){
            $("#warning").text("超出剩余数量!");
            $("#warning").fadeIn("fast");
            $("#warning").fadeOut(1000);
        }else if (count>0){
            $.ajax({
                url:"/shop",
                type:"post",
                data:{
                    "user_id":id,
                    "isbn":isbn,
                    "count":count,
                },
                success:function (result) {
                    if (result.trim() == "false".trim()){
                        $("#warning").text("购买失败，您的余额不足!!!");
                        $("#warning").fadeIn("fast");
                        $("#warning").fadeOut(3000);
                    }else if(result.trim() == ("true").trim()){
                        $("#success").text("购买成功");
                        $("#success").fadeIn("fast");
                        $("#success").fadeOut(3000);
                    }
                }

            })
        }
    }
    function search() {
        var title = $("#title").val();
        var author = $("#author").val();

        $.ajax({
            url:"/search",
            type:"post",
            data:{
                "request":"target",
                "title":title,
                "author":author
            },
            dataType:'json',
            success:function (result) {
                showBooks(result);
            }
        })
    }

    $.ajax({
        url:"/search",
        type:"post",
        data:{
            "request":"all"
        },
        dataType:"json",
        success:function (result) {
            showBooks(result);
        }
    })


</script>
</body>
</html>
