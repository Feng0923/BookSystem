<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/12/012
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="boostrap/bootstrap.min.js"></script>
    <link rel="stylesheet" href="boostrap/bootstrap.min.css">
    <script src="jquery-3.3.1.min.js"></script>
    <script src="MD5Utils.js"></script>
</head>
<body>
        <form class="form-horizontal col-md-offset-3 " style="margin-top: 50px"  onsubmit="return false " >
            <div class="form-group row">
                <label for="user_id" class="col-sm-2 control-label"><span style="margin-right: 10px" class="glyphicon glyphicon-user" aria-hidden="true"></span>账号:</label>

                <div class="col-sm-4" >
                    <input name="user_id" onblur="remind('user_id','msg_id','账号')" type="email" class="form-control" id="user_id" placeholder="Email">
                </div>
            </div>
            <p id="msg_id"  class="col-md-offset-2" ></p>
            <div class="form-group">

                <label for="password" class="col-sm-2 control-label"><span style="margin-right: 10px" class="glyphicon glyphicon-lock" aria-hidden="true"></span>密码:</label>

                <div class="col-sm-4" >
                    <input name="password" type="password" onblur="remind('user_id','msg_pwd','密码')" class="form-control" id="password" placeholder="password">
                </div>
            </div>
            <p id="msg_pwd"  class="col-md-offset-2" ></p>
            <div class="form-group ">
                <div class="  ">
                    <label class=" text-right  col-xs-4" style="padding-top: 10px" ><a href="user_register.jsp" >新用户注册</a></label>
                </div>
                <div class="col-xs-1 col-lg-offset-1 " >
                    <button onclick="submitForm()" class="btn btn-default text-left  "  id="btn_login">登录</button>
                </div>
            </div>
        </form>
        <div id="warning" class=" alert alert-danger alert-dismissable fade in col-md-3 " role="alert" style="display: none; position: absolute;left: 50% ;top:50%;transform: translate(-50%,-50%)">
             你的帐号或密码错误
        </div>

<script type="text/javascript">
    function remind(id,msg_id,message) {

        var content = $.trim($("#"+id).val());
        var msg = $("#"+msg_id);
        if (content==""){
            $("#"+msg_id).attr("hidden",false);
            $("#"+msg_id).attr("style","color:red");
            $("#"+msg_id).html(message+"不能为空！");
            $("#btn_submit").attr("disable",true);
        }else{
            msg.attr("hidden",true);
            $("#btn_submit").attr("disable",false );
        }
    }

    function submitForm() {
        var id = $("#user_id").val();
        var d = $("#password").val();
        var p = hex_md5(d);

        if (id == ''){
            $("#warning").text("请输入账号");
            $("#warning").fadeIn("fast");
            $("#warning").fadeOut(1000);
        }else if(d == ''){
            $("#warning").text("请输入密码");
            $("#warning").fadeIn("fast");
            $("#warning").fadeOut(1000);
        }else{
            $.ajax({
                url:"login",
                type:"post",
                data:{
                    "user_id":id,
                    "password":p
                },
                success:function (result) {
                    if (result == "false"){
                        $("#warning").text("帐号或密码错误");
                        $("#warning").fadeIn("fast");
                        $("#warning").fadeOut(3000);
                    }else if(result == "admin"){
                        document.cookie = "admin_id = "+id+";path = '/'";
                        window.location.href = "admin_bookList.jsp";
                    }else{
                        //
                        document.cookie = "user_id="+id+" ;path='/'";
                        window.location.href = "user_search.jsp";
                        return false;
                    }
                },
                error:function (xhr,status,error) {
                    alert(xhr.responseText,xhr.status,xhr.readyState,status);
                }
            })
        }

    }

</script>
</body>

</html>
