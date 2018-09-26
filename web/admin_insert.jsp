<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/9/14/014
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>录入图书</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="boostrap/bootstrap.css">
    <script src="boostrap/bootstrap.js"></script>
    <script src="jquery-3.3.1.min.js"></script>
    <script src="boostrap/tooltip.js"></script>
    <script src="boostrap/popover.js"></script>
</head>
<body>
    <div>
        <ul class="nav nav-pills">
            <li role="presentation" ><a href="admin_bookList.jsp">上架书籍情况</a></li>
            <li role="presentation" class="active"><a href="admin_insert.jsp">录入图书</a></li>
            <li role="presentation"><a href="admin_order.jsp">订单详情</a></li>
            <li role="presentation"><a href="admin_suggestion.jsp">客户反馈</a></li>
            <li role="presentation"><a href="admin_discount.jsp">折扣管理</a></li>
            <li role="presentation"  ><a href="admin_market.jsp">销售情况</a></li>

            <li role="presentation"><a href="user_login.jsp">退出登录</a></li>
        </ul>
        <form class="form-horizontal col-md-offset-1"style="margin: 20px" onsubmit="return false">
            <div class="form-group">
                <label class="col-sm-2 control-label" for="isbn"><span  class="glyphicon glyphicon-barcode" aria-hidden="true"/> ISBN:</label>
                <div class="col-md-3">
                    <input class="form-control" onblur="remind('isbn','msg_isbn','ISBN')" type="text" placeholder="ISBN" id="isbn" name="isbn">
                </div>

            </div>

            <p  id="msg_isbn" hidden="true" class="col-md-offset-2" ></p>

            <div class="form-group">
                <label class="col-sm-2 control-label" for="title"><span  class="glyphicon glyphicon-book" aria-hidden="true"/> 书名:</label>
                <div class="col-md-3">
                    <input class="form-control" type="text" placeholder="书名" id="title" name="tile" onblur="remind('title','msg_title','书名')">
                </div>
            </div>

                <p  id="msg_title"  hidden="true"class="col-md-offset-2" ></p>

            <div class="form-group">
                <label class="col-sm-2 control-label" for="author"><span  class="glyphicon glyphicon-barcode" aria-hidden="true"/> 作者:</label>
                <div class="col-md-3">
                    <input class="form-control" type="text" placeholder="作者" id="author" name="author" onblur="remind('author','msg_author','作者')">
                </div>
            </div>


                <p  id="msg_author" hidden="true" class="col-md-offset-2" ></p>


            <div class="form-group">
                <label class="col-sm-2 control-label" for="prize"><span  class="glyphicon glyphicon-yen" aria-hidden="true"/> 价格:</label>
                <div class="col-md-3">
                    <input class="form-control" type="text" placeholder="价格" id="prize" name="prize" onblur="remind('prize','msg_prize','价格')">
                </div>
            </div>


                <p  id="msg_prize" hidden="true" class="col-md-offset-2" ></p>


            <div class="form-group">
                <label class="col-sm-2 control-label" for="count"><span  class="glyphicon glyphicon-tasks " aria-hidden="true"/> 数量:</label>
                <div class="col-md-3">
                    <input class="form-control" type="number" min="1"  data-size="" placeholder="数量" id="count" name="count" onblur="remind('count','msg_count','数量')">
                </div>
            </div>

                <p  id="msg_count" hidden="true" class="col-md-offset-2" ></p>


            <div class="form-group">
                <div class="col-md-2 col-md-offset-2"  >
                    <button id="btn_lack" class="btn btn-default"  onclick="lack()" style="color: #ffffff;background-color: #337ab7">查看紧缺书籍</button>
                </div>
                <div class="col-md-2 ">
                    <a id="btn_submit"  class="btn btn-default" onclick="insertBook()" style="color: #ffffff;background-color: #337ab7" data-toggle="popover"  data-trigger="focus" tabindex="0" role="button">录入</a>
                </div>

            </div>
            <table  class="table table-bordered   " style="display: none">
                <tr>
                    <th>ISBN</th>
                    <th>书名</th>
                    <th>作者</th>
                    <th>价格</th>
                    <th>剩余量</th>
                </tr>
                <tbody id="table_lack">
            </table>



        </form>
        <script type="text/javascript">
            
            function insertBook() {

                var isbn = $("#isbn").val();
                var title = $("#title").val();
                var author = $("#author").val();
                var prize = $("#prize").val();
                var count = $("#count").val();

                $.ajax({
                    url:"/bookInsert",
                    type:"post",
                    data:{
                        "isbn":isbn,
                        "title":title,
                        "author":author,
                        "prize":prize,
                        "count":count
                    },
                    success:function (result) {
                        if (result == "true"){
                            $("#btn_submit").attr("data-content","录入成功!");
                            $("#btn_submit").popover("show");
                        }else {
                            $("#btn_submit").attr("data-content","录入失败!");
                            $("#btn_submit").popover("show");
                        }
                    }
                })
            };



            function lack() {
                $.ajax({
                    url:"/bookList",
                    type:"post",
                    data:{
                        "request":"lack",
                    },
                    dataType:"json",
                    success:function (result) {
                        $("#table_lack").empty();
                        for (var i = 0;i<result.length;i++){
                            var isbn = result[i].isbn;
                            var title = result[i].title;
                            var author = result[i].author;
                            var prize = result[i].prize;
                            var rest = result[i].rest;
                            $("#table_lack").append("<tr><td>"+isbn+"</td><td>"+title+"</td><td>"+author+"</td><td>"+prize+"</td><td>"+ rest+"</td><td><a class='btn btn-default '  onclick=\"insertPre("+isbn+",'"+title+"','"+author+"','"+prize+"')\">录入</a></td></tr>");
                        }
                        $("table").show();
                    }
                })
            }

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


        </script>
    </div>
<script type="text/javascript">

    function insertPre(isbn,title,author,prize) {
        $("#isbn").val(isbn);
        $("#title").val(title);
        $("#author").val(author);
        $("#prize").val(prize);
        $("#count").val(1);
    }
</script>


</body>
</html>
