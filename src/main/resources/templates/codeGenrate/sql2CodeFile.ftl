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
            <li class="active">sql文件提交Code生成</li>
        </ol>
    </div>
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                         请提交本地的sql文件
                    </div>
                    <div class="panel-body">
                        <form class="form-horizontal" action="/codeGenrate/uploadSql" enctype="multipart/form-data"
                              method="post">
                            <input type="hidden" id="genrateScope" name="genrateScope"/>
                            <div class="form-group">
                                <label for="" class="col-sm-2 control-label">sql文件</label>
                                <div class="col-sm-10">
                                    <input id="inputSql" name="inputSql" type="file" class="file">
                                    <p class="help-block">支持txt,sql格式，大小不超过2.0M</p>
                                </div>
                            </div>
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

    $("#inputSql").fileinput({
        showUpload : false,
        autoReplace : true,
        maxFileCount : 1,
        allowedFileExtensions : [ "txt", "sql"]
    })

    function getCheckBoxVal(name){
        var chk_value='';
        //遍历每一个名字为nodes的复选框，其中选中的执行函数
        $('input[name="'+name+'"]:checked').each(function(){
            chk_value+=','+$(this).val();
        });
        return chk_value;
    }
</script>
</body>
</html>
