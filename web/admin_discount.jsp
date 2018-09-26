<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/18/018
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>折扣管理</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="jquery-3.3.1.min.js"></script>
    <script src="boostrap/bootstrap.js"></script>
    <link rel="stylesheet" href="boostrap/bootstrap.css">
    <script src="level.js"></script>
</head>
<body>
<div>
    <ul class="nav nav-pills">
        <li role="presentation" ><a href="admin_bookList.jsp">上架书籍情况</a></li>
        <li role="presentation" ><a href="admin_insert.jsp">录入图书</a></li>
        <li role="presentation"><a href="admin_order.jsp">订单详情</a></li>
        <li role="presentation"><a href="admin_suggestion.jsp">客户反馈</a></li>
        <li role="presentation"class="active"><a href="admin_discount.jsp">折扣管理</a></li>
        <li role="presentation" ><a href="admin_market.jsp">销售情况</a></li>

        <li role="presentation"><a href="user_login.jsp">退出登录</a></li>
    </ul>

    <table class="table table-bordered " style="width: 50% ;margin: 20px">
        <thead  >
            <th>等级</th>
            <th>折扣</th>
        </thead>
        <tbody id="discount">

        </tbody>
    </table>
    <div class="form-group  ">
        <div class="  "style="margin : 20px">
            <button class=" btn btn-default col-xs-2" onclick="rewrite()">修改</button>
        </div>

        <div class="  "style="margin-top: 20px">
            <button class="col-xs-2  btn btn-default" onclick="save()">保存</button>
        </div>

    </div>
    <div id="warning" class=" alert alert-danger alert-dismissable fade in col-md-3 " role="alert" style="display: none; position: absolute;left: 50% ;top:50%;transform: translate(-50%,-50%)">

    </div>
</div>

<script type="text/javascript">
    function rewrite() {
        for (var i = 0;i<2;i++){
            $("#discount"+i).show();
        }
    }
    function save() {
        var discount0 = $("#discount0").val();
        var discount1 = $("#discount1").val();
        $.ajax({
            url:"discount",
            type:"post",
            data:{
                "request":"save",
                "discount0":discount0,
                "discount1":discount1
            },
            success:function (result) {
                if(result.trim() == "true".trim()){
                    window.location.reload();
                }else if (result.trim() == "number".trim()){
                    //失败
                    $("#warning").text("修改失败,请输入正确的格式!");
                    $("#warning").fadeIn("fast");
                    $("#warning").fadeOut(1500);
                }else {
                    $("#warning").text("修改失败!");
                    $("#warning").fadeIn("fast");
                    $("#warning").fadeOut(1200);
                }

            }
        })
    }
</script>
<script>

    $.ajax({
        url:"discount",
        type:"post",
        data:{
            "request":"all",

        },
        dataType:"json",
        success:function (result) {
            $("#discount").empty();
            for(var i = 0;i<result.length;i++){
                var level = result[i].level;
                var discount_size = result[i].discount_size;
                $("#discount").append("<tr><td>"+getLevel(level)+"</td><td><p class='col-xs-1'>"+discount_size+"</p><input class='col-xs-3 col-xs-offset-2 ' id='discount"+i+"' style='display: none'></td></tr>")
            }
        }
    })
</script>
</body>
</html>
