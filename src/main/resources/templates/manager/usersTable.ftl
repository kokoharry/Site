﻿<!DOCTYPE html>
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
</head>
<body style="background-color: #EDEDED">
<input type="hidden" id="menuCode" name="menuCode" />
<!-- 模态弹出窗内容 -->
<div class="modal" id="mymodal-adddata" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">用户添加</h4>
            </div>
            <div class="modal-body">
                <form id="userAddForm">
                    <div class="form-group">
                        <label for="userName" class="control-label">用户名:</label>
                        <input type="text" class="form-control" id="userName">
                    </div>
                    <div class="form-group">
                        <label for="realName" class="control-label">真实姓名:</label>
                        <input type="text" class="form-control" id="realName">
                    </div>
                    <div class="form-group">
                        <label for="password" class="control-label">密码:</label>
                        <input type="password" class="form-control" id="password">
                    </div>
                    <div class="form-group">
                        <label for="password2" class="control-label">再次密码:</label>
                        <input type="password" class="form-control" id="password2">
                    </div>
                    <div class="form-group">
                        <label for="telphone" class="control-label">联系电话:</label>
                        <input type="text" class="form-control" id="telphone">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="addUser()">保存</button>
                <div class="alert alert-danger alert-dismissable" id="alert-false">
                    <button type="button" class="close" data-dismiss="alert"
                            aria-hidden="true">
                        &times;
                    </button>
                    添加失败！
                </div>
            </div>
        </div>
    </div>
</div>

<!-- 模态弹出窗内容 -->
<div class="modal" id="mymodal-editdata" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">用户编辑</h4>
            </div>
            <div class="modal-body">
                <form id="userEditForm">
                    <div class="form-group">
                        <label for="id-edit" class="control-label">用户ID:</label>
                        <input type="text" class="form-control" id="id-edit" disabled="true">
                    </div>
                    <div class="form-group">
                        <label for="userName-edit" class="control-label">用户名:</label>
                        <input type="text" class="form-control" id="userName-edit">
                    </div>
                    <div class="form-group">
                        <label for="realName-edit" class="control-label">真实姓名:</label>
                        <input type="text" class="form-control" id="realName-edit">
                    </div>
                    <div class="form-group">
                        <label for="telphone-edit" class="control-label">联系电话:</label>
                        <input type="text" class="form-control" id="telphone-edit">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="editUserServer()">保存</button>
                <div class="alert alert-danger alert-dismissable" id="edit-alert-false">
                    <button type="button" class="close" data-dismiss="alert"
                            aria-hidden="true">
                        &times;
                    </button>
                    修改失败！
                </div>
            </div>
        </div>
    </div>
</div>

    <div class="header" style="padding-top: 20px;">
        <ol class="breadcrumb" style="margin-bottom: 0px;">
            <li><a href="#">系统管理</a></li>
            <li class="active">用户管理</li>
        </ol>
    </div>
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                         系统用户信息表
                    </div>
                    <div class="panel-body">
                        <div class="table-responsive" style="overflow-x:visible">
                            <table  id="userTables" class="table table-bordered table-striped table-hover" style="text-align:center">
                                <thead>
                                    <tr>
                                        <th style="text-align:center"></th>
                                        <th style="text-align:center"></th>
                                        <th style="text-align:center"></th>
                                        <th style="text-align:center"></th>
                                        <th style="text-align:center"></th>
                                        <th style="text-align:center"></th>
                                        <th style="text-align:center"></th>
                                        <th style="text-align:center"></th>
                                    </tr>
                                </thead>
                            </table>
                            <div class="alert alert-success alert-dismissable" id="alert-success">
                                <button type="button" class="close" data-dismiss="alert"
                                        aria-hidden="true">
                                    &times;
                                </button>
                                <label id="alert-msg"></label>
                            </div>
                        </div>
                    </div>
                </div>
                <!--End Advanced Tables -->
            </div>
        </div>
    </div>
    <!-- JS Scripts-->
    <!-- jQuery Js -->
    <script src="/assets/js/jquery-1.10.2.js"></script>
      <!-- Bootstrap Js -->
    <script src="/assets/js/bootstrap.min.js"></script>
    <!-- Metis Menu Js -->
    <script src="/assets/js/jquery.metisMenu.js"></script>
     <!-- DATA TABLE SCRIPTS -->
    <script src="/assets/js/dataTables/jquery.dataTables.js"></script>
    <script src="/assets/js/dataTables/dataTables.bootstrap.js"></script>
        <script type="text/javascript">
            var lang = {
                "sProcessing": "处理中...",
                "sLengthMenu": "每页 _MENU_ 项",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "当前显示第 _START_ 至 _END_ 项，共 _TOTAL_ 项。",
                "sInfoEmpty": "当前显示第 0 至 0 项，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页",
                    "sJump": "跳转"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            };
            $(document).ready(function () {

                $('#alert-success').hide();
                $('#alert-false').hide();
                $('#edit-alert-false').hide();
                $("#userTables").DataTable({
//                    "dom": '<"toolbar">frtip',
                    "dom": "<'row'<'col-xs-2'l><'#mytool.col-xs-4'><'col-xs-6'f>r>t" +
                    "<'row'<'col-xs-6'i><'col-xs-6'p>>",
                    serverSide: true,  //启用服务器端分页
                    showRowNumber: true,
                    language:lang,  //提示信息
                    searching: false,//搜索
                    bSort:false,
                    ajax: function (data, callback, settings) {
                        //封装请求参数
                        var param = {};
                        param.limit = data.length;//页面显示记录条数，在页面显示每页显示多少项的时候
                        param.start = data.start;//开始的记录序号
                        param.page = (data.start / data.length)+1;//当前页码
                        param.orderby=data.orderBys;
                        param.menuCode=$("#menuCode").val();
                        //ajax请求数据
                        $.ajax({
                            type: "post",
                            url: "/system/users",
                            cache: false,  //禁用缓存
                            data: param,  //传入组装的参数
                            dataType: "json",
                            success: function (result) {
                                //封装返回数据
                                var returnData = {};
                                returnData.draw = data.draw;//这里直接自行返回了draw计数器,应该由后台返回
                                returnData.recordsTotal = result.total;//返回数据全部记录
                                returnData.recordsFiltered = result.total;//后台不实现过滤功能，每次查询均视作全部结果
                                returnData.data = result.data;//返回的数据列表
                                callback(returnData);
                            }
                        });
                    },
                    columns: [
                        {
                            target:0,
                            data: "id",
                            title: "用户ID"
                        },
                        {
                            target:1,
                            data: "userName",
                            title: "用户名称"
                        },
                        {
                            target:2,
                            data: "password",
                            title: "用户密码"
                        },
                        {
                            target:3,
                            data: "telphone",
                            title: "用户电话"
                        },
                        {
                            target:5,
                            data: "updateTime",
                            title: "更新时间"
                        },
                        {
                            target:4,
                            data: "createType",
                            title: "创建途径",
                            render:function(data){
                                if(data==0){
                                    return "数据库方式";
                                }else if(data==1){
                                    return "网页方式";
                                }
                                return "其他";
                            }
                        },
                        {
                            target:5,
                            data: "updateType",
                            title: "更新途径",
                            render:function(data){
                                if(data==0){
                                    return "数据库方式";
                                }else if(data==1){
                                    return "网页方式";
                                }
                                return "其他";
                            }
                        },
                        {
                            target:6,
                            data: null,
                            title: "操作",
                            render:function(data,type,row,meta){
                                var html =
<#if permission?? && permission?seq_contains("U")>
                                        "<button type='button' class='btn btn-info' style='padding: 1px;'" +
                                        " onclick='editUser(\""+row.id+"\")'>编辑</button>" + "&nbsp;" +
                                        "&nbsp;" + "<button type='button' class='btn btn-warning' style='padding: 1px;'" +
                                        " onclick='editUser(\""+row.id+"\")'>重置密码</button>"+
</#if>
<#if permission?? && permission?seq_contains("D")>
                                        "&nbsp;<button" +
                                        " type='button' class='btn btn-info' style='padding: 1px;'" +
                                        " onclick='deleteUser(\""+row.id+"\")'>删除</button>"+ "&nbsp;" +
</#if>
                                "";
                                return html;
                            }
                        }
                    ],
                    initComplete:function(){
                        <#if permission?? && permission?seq_contains("C")>
                        $("#mytool").append('<button type="button" class="btn btn-info btn-sm" data-toggle="modal" ' +
                                'data-whatever="@mdo" data-target="#mymodal-adddata">添加</button>');
                        </#if>
                    }
                });
                //初始化清除浏览器代填数据
                document.getElementById("userAddForm").reset()
            });

            $("#mymodal-adddata").on("hide.bs.modal",function(){
                document.getElementById("userAddForm").reset()
            });

            $("#mymodal-editdata").on("hide.bs.modal",function(){
                document.getElementById("userEditForm").reset()
            });

            function deleteUser(id){
                $.ajax({
                    type: 'POST',
                    url: '/system/userDel' ,
                    data: {"id":id,"menuCode":$("#menuCode").val()} ,
                    dataType: 'json',
                    async : false, //默认为true 异步
                    success:function(data){
                        if(data > 0){
                            $("#alert-msg").html("删除成功！");
                            $('#alert-success').show();
                            $("#userTables").dataTable().fnDraw();
                        }
                    }
                });
            }

            function editUser(id){
                var alldata=$('#userTables').dataTable().fnGetData();
                var obj;
                for(var i=0;i<alldata.length;i++){
                    if(alldata[i].id==id){
                        obj = alldata[i];
                        break;
                    }
                }
                $("#id-edit").val(obj.id);
                $("#userName-edit").val(obj.userName);
                $("#realName-edit").val(obj.realName);
                $("#telphone-edit").val(obj.telphone);
                $("#mymodal-editdata").modal("show");
            }

            function editUserServer(){
                var userParam = {
                    "id":$("#id-edit").val(),
                    "userName":$("#userName-edit").val(),
                    "realName":$("#realName-edit").val(),
                    "telphone":$("#telphone-edit").val(),
                    "menuCode":$("#menuCode").val()
                };
                $.ajax({
                    type: 'POST',
                    url: '/system/userEdit' ,
                    data: userParam ,
                    dataType: 'json',
                    async : false, //默认为true 异步
                    success:function(data){
                        if(data > 0){
                            $("#alert-msg").html("修改成功！");
                            $('#alert-success').show();
                            $("#userTables").dataTable().fnDraw();
                        }else{
                            $('#edit-alert-false').show();
                        }
                    }
                });
            }

            function checkForm(){
                return true;
            }


            function addUser(){
                if(checkForm()){
                    var userParam = {
                        "userName":$("#userName").val(),
                        "realName":$("#realName").val(),
                        "password":$("#password").val(),
                        "telphone":$("#telphone").val(),
                        "menuCode":$("#menuCode").val()
                    };
                    $.ajax({
                        type: 'POST',
                        url: '/system/userAdd' ,
                        data: userParam ,
                        dataType: 'json',
                        async : false, //默认为true 异步
                        success:function(data){
                           if(data >= 0){
                               $('#mymodal-adddata').modal('hide');
                               $("#alert-msg").html("添加成功！");
                               $('#alert-success').show();
                               $("#userTables").dataTable().fnDraw();
                           }else{
                               $('#alert-false').show();
                           }
                        }
                    });
                }
            }

    </script>
</body>
</html>
