<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/12/012
  Time: 12:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="boostrap/bootstrap.min.js"></script>
    <link rel="stylesheet" href="boostrap/bootstrap.min.css">
    <script src="jquery-3.3.1.min.js"></script>
    <script src="boostrap/dropdown.js"></script>
    <script src="bootstrap-select.js"></script>
    <link rel="stylesheet" href="bootstrap-select.css">
    <script src="MD5Utils.js"></script>
</head>
<body>


<form class="form-horizontal col-md-offset-3 " style="margin-top: 50px" method="post" onsubmit="return false" >
    <div class="form-group">
        <label for="user_id" class="col-sm-2 control-label"><span style="margin-right: 10px" class="glyphicon glyphicon-user" aria-hidden="true"></span>账号:</label>
        <div class="col-sm-4" >
            <input type="email" name="user_id"class="form-control" id="user_id" placeholder="Email" onblur="remind('user_id','msg_id','帐号')">
        </div>
    </div>
    <p id="msg_id"  class="col-md-offset-2" ></p>
    <div class="form-group">
        <label for="name" class="col-sm-2 control-label"><span style="margin-right: 10px" class="glyphicon glyphicon-edit" aria-hidden="true"></span>姓名:</label>
        <div class="col-sm-4" >
            <input type="text" class="form-control" id="name" placeholder="name" name="name" onblur="remind('name','msg_name','姓名')">
        </div>
    </div>
    <p id="msg_name"  class="col-md-offset-2" ></p>

    <div class="form-group">
        <label for="password" class="col-sm-2 control-label"><span style="margin-right: 10px" class="glyphicon glyphicon-lock" aria-hidden="true"></span>密码:</label>
        <div class="col-sm-4 ">
            <input type="password" class="form-control" id="password" placeholder ="Password" name="password" onblur="remind('password','passwordh','密码')">
        </div>
    </div>
    <p type="hidden" name="passwordh" id="passwordh" class="col-md-offset-2"></p>

    <div class="form-group">
        <label for="password1" class="col-sm-2 control-label"><span style="margin-right: 10px" class="glyphicon glyphicon-lock" aria-hidden="true"></span>确认密码:</label>
        <div class="col-sm-4 ">
            <input type="password" class="form-control" id="password1" placeholder ="Password" onblur="conf()">
        </div>
    </div>

    <p type="hidden" name="passwordh" id="password1h" class="col-md-offset-2"></p>
    <div class="form-group">
        <label for="phone" class="col-sm-2 control-label"><span style="margin-right: 10px" class="glyphicon glyphicon-phone" aria-hidden="true"></span>手机号:</label>
        <div class="col-sm-4 ">
            <input type="phone" class="form-control" id="phone" placeholder ="phone" onblur="remind('phone','msg_phone','手机号')">
        </div>
    </div>

    <p id="msg_phone"  class="col-md-offset-2" ></p>

    <div class="form-group">
        <label for="ad" class="col-sm-2 control-label"><span style="margin-right: 10px" class="glyphicon glyphicon-home" aria-hidden="true"></span>地址:</label>
        <div id="ad">
            <div class="col-md-3">
                <select id="province" name="province" class="selectpicker"onchange="selectprovince(this);" data-live-search="true"></select>
            </div>
            <div class="col-md-3">
                <select id="city" name="city" class="selectpicker"></select>
            </div>
            <div class="col-md-3 ">
                <input name="address" type="text" class="form-control" id="address" placeholder ="详细地址" onblur="remind('address','msg_address','地址')">
            </div>
        </div>
        <p id="msg_address"  class="col-md-offset-2" ></p>
    </div>


    <p id="msg_pwd"  class="col-md-offset-2" ></p>
    <div class="form-group ">
        <div class="col-xs-6 col-md-offset-2">
            <label class="  text-right " style="padding:12px 12px">已有账号,返回<a href="user_login.jsp" >登录</a></label>
        </div>
        <div class="col-xs-2" >
            <button onclick="submitForm()" class="btn btn-default text-left  " id="btn_login" >注册</button>
        </div>
    </div>
    <div id="warning" class=" alert alert-danger alert-dismissable fade in col-md-3 " role="alert" style="display: none; position: absolute;left: 50% ;top:50%;transform: translate(-50%,-50%)">

    </div>

</form>

<script src="address.js"></script>
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
        var h = hex_md5(d);
        var name  = $("#name").val();
        var phone = $("#phone").val();
        var address = $("#province").val()+$("#city").val()+$("#address").val();
        $.ajax({
            url:"/register",
            type:"post",
            data:{
                "user_id":id,
                "password":h,
                "name":name,
                "phone":phone,
                "address":address
            },
            success:function (result) {
                if (result.trim() == "true".trim()){
                    window.location.href = "/user_login.jsp";
                }else if (result.trim() == "false".trim()){
                    $("#warning").text("该账号已注册!");
                    $("#warning").fadeIn("fast");
                    $("#warning").fadeOut(3000);
                }
            }

        })
        return true;
    }

    function conf() {
        var password = $("#password").val()
        var password1 = $("#password1").val();
        if (password != password1){
            $("#password1h").text("两次密码不一致!");
            $("#password1h").attr("hidden",false);
            $("#password1h").attr("style","color:red");
            $("#btn_submit").attr("disable",true);
        }else{
            $("#password1h").attr("hidden",true);
            $("#btn_submit").attr("disable",false );
        }
    }


</script>
</body>
</html>
