<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
      <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta content="" name="description" />
    <meta content="webthemez" name="author" />
	<!-- Bootstrap Styles-->
    <link href="/assets/css/bootstrap.css" rel="stylesheet" />
     <!-- FontAwesome Styles-->
    <link href="/assets/css/font-awesome.css" rel="stylesheet" />
    <!-- Custom Styles-->
    <link href="/assets/css/custom-styles.css" rel="stylesheet" />
    <!-- TABLE STYLES-->
    <link href="/assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
    <!-- JS Scripts-->
    <!-- jQuery Js -->
    <script src="/assets/js/jquery-1.10.2.js"></script>
    <!-- Bootstrap Js -->
    <script src="/assets/js/bootstrap.min.js"></script>

    <script src="/fileInput/js/fileinput.min.js"></script>
    <link href="/fileInput/css/fileinput.min.css" rel="stylesheet" />
</head>
<body style="background-color: #EDEDED">
<input type="hidden" id="menuCode" name="menuCode" />

    <div class="header" style="padding-top: 20px;">
        <ol class="breadcrumb" style="margin-bottom: 0px;">
            <li><a href="#">代码生成</a></li>
            <li class="active">自定义数据库连接Code生成</li>
        </ol>
    </div>
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                         请填写对应的数据库连接信息
                    </div>
                    <div class="panel-body">
                        <form class="form-horizontal" action="/codeGenrate/table2Code" method="post">
                            <input type="hidden" id="genrateScope" name="genrateScope"/>
                            <div class="form-group">
                                <label for="jdbcUrl" class="col-sm-2 control-label">数据库连接：</label>
                                <div class="col-sm-10">
                                    <input id="jdbcUrl" name="jdbcUrl" type="text" class="form-control">
                                    <p class="help-block">例如：jdbc:mysql://127.0.0.1:3306/test</p>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="userName" class="col-sm-2 control-label">用户名：</label>
                                <div class="col-sm-10">
                                    <input id="userName" name="userName" type="text" class="form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="password" class="col-sm-2 control-label">密 码：</label>
                                <div class="col-sm-10">
                                    <input id="password" name="password" type="password" class="form-password form-control">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="tableName" class="col-sm-2 control-label">选择表名：</label>
                                <div class="col-sm-8">
                                    <select id="tableName" name="tableName" class="form-control">
                                    </select>
                                </div>
                                <div class="col-sm-2">
                                    <button type="button" class="btn btn-primary" onclick="getTables();">获取列表</button>
                                </div>

                            </div>
                            <hr></hr>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label">生成代码范围</label>
                                <div class="col-sm-10">
                                    <div>
                                        <label class="checkbox-inline">
                                            <input type="checkbox" name="genrateCodeScope" id="inlineCheckbox1"
                                                   value="BEAN"> BEAN文件
                                        </label>
                                        <label class="checkbox-inline">
                                            <input type="checkbox" name="genrateCodeScope" id="inlineCheckbox2"
                                                   value="ISERVICE"> service接口文件
                                        </label>
                                        <label class="checkbox-inline">
                                            <input type="checkbox" name="genrateCodeScope" id="inlineCheckbox3"
                                                   value="MAPPER"> mybatis mapper映射接口文件
                                        </label>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group text-center ">
                                <div class="col-md-10 col-md-offset-1">
                                    <button type="submit" class="btn btn-primary btn-lg">提交</button>
                                </div>
                            </div>
                        </form>

                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
<script type="text/javascript">

    $(".checkbox-inline").click(function(){
        $("#genrateScope").val(getCheckBoxVal("genrateCodeScope"));
    });

    function getCheckBoxVal(name){
        var chk_value='';
        //遍历每一个名字为nodes的复选框，其中选中的执行函数
        $('input[name="'+name+'"]:checked').each(function(){
            chk_value+=','+$(this).val();
        });
        return chk_value;
    }

    function getTables(){
        $.ajax({
            type: 'POST',
            url: '/codeGenrate/selectTables' ,
            data: {
                jdbcUrl:$("#jdbcUrl").val(),
                userName:$("#userName").val(),
                password:$("#password").val()
            } ,
            dataType: 'json',
            async : false, //默认为true 异步
            success:function(data){
                if(data != null){
                    $("#tableName").empty();
                    // 实际的应用中，这里的option一般都是用循环生成多个了
                    for(var i=0;i<data.length;i++){
                        var option = $("<option>").val(data[i]).text(data[i]);
                        $("#tableName").append(option);
                    }
                }
            }
        });
    }
</script>
</body>
</html>
