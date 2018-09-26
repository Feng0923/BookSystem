
<%@ page import="Manager.UserManager" %>
<%@ page import="com.sun.org.apache.regexp.internal.RE" %><%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/10/010
  Time: 16:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的信息</title>

    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="boostrap/bootstrap.css">
    <script src="boostrap/bootstrap.js"></script>
    <script src="jquery-3.3.1.min.js"></script>
    <script src="boostrap/dropdown.js"></script>
    <script src="bootstrap-select.js"></script>
    <link rel="stylesheet" href="bootstrap-select.css">
    <script src="address.js"></script>
    <script src="MD5Utils.js"></script>
    <script src="level.js"></script>
</head>
<body>

<div >
    <ul class="nav nav-pills">
        <li role="presentation"><a href="/user_search.jsp">购买书籍</a></li>
        <li role="presentation"><a href="/user_record.jsp">我的订单</a></li>
        <li role="presentation"><a href="/user_suggestion.jsp">反馈意见</a></li>
        <li role="presentation" class="active"><a href="/user_information.jsp">个人信息</a></li>
        <%--<li role="presentation"><a href="/user_presale.jsp">我的预售</a></li>--%>
        <li role="presentation"><a href="/user_login.jsp">退出登录</a></li>
    </ul>

    <div class=" row" >
        <div class="col-md-1 col-md-offset-1 " style="margin-top: 20px">
            <img src="image/user.png" >
        </div>
        <div class="col-md-5 col-md-offset-1"style="margin-top: 20px">
            <table id="information_table" class="table table-bordered " >
                <tbody id="information"></tbody>
            </table>

        </div>
    </div>
    <div class="form-group  ">
        <div class=" col-md-offset-3 "style="margin-top: 20px">
            <button class=" btn btn-default col-xs-2" onclick="rewrite()">修改</button>
        </div>

        <div class="  "style="margin-top: 20px">
            <button class="col-xs-2  btn btn-default" onclick="save()">保存</button>
        </div>

    </div>

    <div id="success" class="alert alert-success" role="alert" style="display: none; position: absolute;left: 50% ;top:50%;transform: translate(-50%,-50%)">

    </div>
    <div id="warning" class=" alert alert-danger alert-dismissable fade in col-md-3 " role="alert" style="display: none; position: absolute;left: 50% ;top:50%;transform: translate(-50%,-50%)">

    </div>
    <%--<div id="content" class="col-sm-6">--%>

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
    function rewrite() {
        // $("#name").parent().text("");
        $("#name").show();
        // $("#address").parent().text("");
        $("#address").show();
        // $("#phone").parent().text("");
        $("#phone").show();

        $("#password").show();

        $("#cm").show();
    }
    
    function up() {
        var id = getUserid();
        if (id!=""){
            $.ajax({
                url:"/vip",
                data:{
                    "user_id":id
                },
                type:"post",
                success:function (result) {
                    if (result.trim() == "true".trim()){
                        $("#success").text("升级成功");
                        $("#success").fadeIn("fase");
                        $("#success").fadeOut(1000);
                        window.location.reload();
                    }else if(result.trim() == "false".trim()){
                        $("#warning").text("升级失败");
                        $("#warning").fadeIn("fase");
                        $("#warning").fadeOut(1000);
                    }
                }
            })
        }
    }

    function save() {
        var name = $("#name").val();
        var address = "";
        if ($("#ad").val() != ""){
            var address = $("#province").val()+$("#city").val()+$("#ad").val();
        }
        var phone = $("#phone").val();
        var id = getUserid();
        var password = $("#password").val();
        if (password!=""){
            password = hex_md5(password);
        }
        $.ajax({
            url:"information",
            type:"post",
            data:{
                "request":"rewrite",
                "user_id":id,
                "password":password,
                "name":name,
                "address":address,
                "phone":phone
            },
            success:function (result) {
                if (result.trim() == "true".trim()){
                    window.location.reload();
                    $("#success").text("保存成功！");
                    $("#success").fadeIn("fast");
                    $("#success").fadeOut(1000);
                    return;
                }
            }
        })
    }
    function charge() {
        $("#money").show();
        var money = $("#money").val();
        if (money == ""){
            $("#warning").text("充值失败");
            $("#warning").fadeIn("fast");
            $("#warning").fadeOut(1000);
        }else {
            var id = getUserid();
            $.ajax({
                url:"charge",
                type:"post",
                data:{
                    "user_id":id,
                    "money":money
                },
                success:function (result) {
                    if (result.trim() == "true".trim()){
                        window.location.reload();
                    }else if(result.trim() == "false".trim()){

                    }

                }
            })
        }
    }
</script>

<script type="text/javascript">
    
   var id = getUserid();
    $.ajax({
        url:"/information",
        type:"post",
        data:{
            "request":"all",
            "user_id":id
        },
        dataType:"json",
        success:function (o) {
            $("#information").empty();
            var result = eval(o);
            var name = result.name;
            var address = result.address;
            var phone = result.phone;
            var money = result.money;
            var level = result.level;
            $("#information").append("<tr><td>帐号</td><td>"+id+"</td></tr>");
            $("#information").append("<tr><td>密码</td><td>"+"*******"+"<input id='password' type='password' class='form-control' placeholder='密码' style='display: none'></td></tr>");
            $("#information").append("<tr><td>姓名</td><td>"+name+"<input id='name' type='text' class='form-control' placeholder='姓名' style='display: none'></td></tr>");
            $("#information").append("<tr><td>地址</td><td>"+address+"<div id = 'address'class='row form-inline' style='display: none'>     " +
                "            <select id=\"province\" name=\"province\" class=\"selectpicker\"onchange=\"selectprovince(this);\" data-live-search=\"true\"></select>\n" +
                "            <select id=\"city\" name=\"city\" class=\"selectpicker\"></select>\n" +
                "            <input id = 'ad'name=\"address\" type=\"text\" class=\"form-control\" id=\"address\" placeholder =\"详细地址\" onblur=\"remind('address','msg_address','地址')\">\n" +
                "</div>  </td></tr>");
            $("#information").append("<tr><td>手机号</td><td>"+phone+"<input id='phone' type='phone' class='form-control' placeholder='phone' style='display: none'></td></tr>");
            $("#information").append("<tr><td>余额</td><td>"+money+"<div id='cm' class='form-inline' style='display: none'><input id='money' class='form-control' placeholder='money'  ><button   id='charge' class='btn btn-default' onclick='charge()'>充值</button></div></td></tr>");
            $("#information").append("<tr><td>等级</td> <td><div class='form-inline row'> <p class='col-xs-5' style='padding-top: 5px'>"+getLevel(level)+
                "          </p><button class=\" col-xs-6 btn btn-default \" onclick=\"up()\">升级会员(￥20)</button>" +
                "         </td></div></tr>");

            $(".selectpicker").selectpicker("refresh");
            $(".selectpicker").selectpicker("render");
            jQuery.getScript("address.js");
        }
    })
</script>

</body>
</html>
