<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/11/011
  Time: 19:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>反馈</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="boostrap/bootstrap.css">
    <script src="boostrap/bootstrap.js"></script>
    <script src="jquery-3.3.1.min.js"></script>
</head>
<body>
<div id="mainbox">

    <ul class="nav nav-pills">
        <li role="presentation"><a href="/user_search.jsp">购买书籍</a></li>
        <li role="presentation"><a href="/user_record.jsp">我的订单</a></li>
        <li role="presentation"class="active"><a href="/user_suggestion.jsp">反馈意见</a></li>
        <li role="presentation" ><a href="/user_information.jsp">个人信息</a></li>
        <%--<li role="presentation"><a href="/user_presale.jsp">我的预售</a></li>--%>
        <li role="presentation"><a href="/user_login.jsp">退出登录</a></li>
    </ul>
    <div id="content"  style="margin-left: 10px">
        <form method="post" style="margin:20px 10px " onsubmit="return false">
            <div class="form-group"  >
                <textarea name="suggesion" class="form-control" placeholder="请填写您的建议"  id="suggestion"></textarea>
            </div>
            <div >
                <button class="btn btn-default" onclick="submitSuggestion()">提交</button>
            </div>
        </form>
    </div>
    <div id="success" class="alert alert-success" role="alert" style="display: none; position: absolute;left: 50% ;top:50%;transform: translate(-50%,-50%)">

    </div>
</div>
    <script type="text/javascript">
        function getUserid() {
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
            return id;
        }
        function submitSuggestion() {
            var id = getUserid();
            var suggestion = $("#suggestion").val();
            $.ajax({
                url:"/suggestion",
                type:"post",
                data:{
                    "suggestion":suggestion,
                    "user_id":id
                },
                success:function (result) {
                    if (result.trim() == "true".trim()){
                        $("#success").text("反馈成功！");
                        $("#success").fadeIn("fast");
                        $("#success").fadeOut(3000);
                    }
                }
            })
        }
    </script>
</body>
</html>
