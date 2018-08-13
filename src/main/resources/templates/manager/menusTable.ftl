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
    <!-- JS Scripts-->
    <!-- jQuery Js -->
    <script src="/assets/js/jquery-1.10.2.js"></script>
    <script src="/mutilSelect/js/jquery-ui.js"></script>
    <!-- Bootstrap Js -->
    <script src="/assets/js/bootstrap.min.js"></script>
    <!-- Metis Menu Js -->
    <script src="/assets/js/jquery.metisMenu.js"></script>
    <!-- DATA TABLE SCRIPTS -->
    <script src="/assets/js/dataTables/jquery.dataTables.js"></script>
    <script src="/assets/js/dataTables/dataTables.bootstrap.js"></script>
    <!-- TABLE STYLES-->
    <link href="/assets/js/dataTables/dataTables.bootstrap.css" rel="stylesheet" />
    <script src="/tree/bootstrap-treeview.js" type="text/javascript"></script>
    <link href="/tree/bootstrap-treeview.css" rel="stylesheet" type="text/css" />
    <script src="/mutilSelect/js/fieldChooser.min.js" type="text/javascript"></script>
    <link href="/mutilSelect/css/style.css" rel="stylesheet" type="text/css" />
</head>
<body style="background-color: #EDEDED">
<input type="hidden" id="menuCode" name="menuCode" />
<input type="hidden" id="delId" name="delId" />
<!-- 模态弹出窗内容 -->
<div class="modal" id="mymodal-adddata" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">菜单添加</h4>
            </div>
            <div class="modal-body">
                <form id="menuAddForm" class="form-horizontal">
                    <div class="form-group">
                        <label for="menuCodeForm" class="col-sm-2 control-label">菜单编码:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="menuCodeForm">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="menuName" class="col-sm-2 control-label">菜单名称:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="menuName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="menuHref" class="col-sm-2 control-label">菜单链接:</label>
                        <div class="col-sm-10">
                        <input type="text" class="form-control" id="menuHref">
                        </div>
                    </div>
                    <div class="form-group">
                            <label for="menuIcon" class="col-sm-2 control-label">菜单图标:</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="menuIcon">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="menuParentCode" class="col-sm-2 control-label">父菜单:</label>
                        <div class="col-sm-10">
                            <div id="tree"></div>
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="addMenu()">保存</button>
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

<div class="modal" id="mymodal-grantdata" tabindex="-1" role="dialog" aria-labelledby="mySmallModalLabel"
     aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">×</span>
                    <span class="sr-only">Close</span>
                </button>
                <h4 class="modal-title">菜单授权</h4>
            </div>
            <div class="modal-body">
                <form id="menuGrantForm" class="form-horizontal">
                    <div class="form-group">
                        <label for="menuNameGrant" class="col-sm-2 control-label">父菜单:</label>
                        <div class="col-sm-10">
                            <input type="hidden" id="menuCodeGrant" name="menuCodeGrant">
                            <input type="text" class="form-control" id="menuNameGrant" name="menuNameGrant" disabled />
                        </div>
                    </div>
                    <div class="form-group">
                        <#--<div id="pickList"></div>-->
                            <div id="fieldChooser" tabIndex="1">
                                <div style="text-align: center">
                                    <label>所有角色</label>
                                    <div id="sourceFields" >
                                    </div>
                                </div>
                                <div style="text-align: center">
                                    <label>选中角色</label>
                                    <div id="destinationFields">
                                    </div>
                                </div>
                            </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="grantMenu()">保存</button>
                <div class="alert alert-danger alert-dismissable" id="alert-grant-false">
                    <button type="button" class="close" data-dismiss="alert"
                            aria-hidden="true">
                        &times;
                    </button>
                    授权失败！请联系管理员
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
                <h4 class="modal-title">角色编辑</h4>
            </div>
            <div class="modal-body">
                <form id="userEditForm">
                    <div class="form-group">
                        <label for="id-edit" class="control-label">角色ID:</label>
                        <input type="text" class="form-control" id="id-edit" disabled="true">
                    </div>
                    <div class="form-group">
                        <label for="roleCode-edit" class="control-label">角色编码:</label>
                        <input type="text" class="form-control" id="roleCode-edit" disabled="true">
                    </div>
                    <div class="form-group">
                        <label for="roleName-edit" class="control-label">角色名称:</label>
                        <input type="text" class="form-control" id="roleName-edit">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="editRoleServer()">保存</button>
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
        <li class="active">菜单管理</li>
    </ol>
</div>
<div id="page-inner">
    <div class="row">
        <div class="col-md-12">
            <!-- Advanced Tables -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    系统菜单信息表
                </div>
                <div class="panel-body">
                    <div class="table-responsive" style="overflow-x:visible">
                        <table  id="menuTable" class="table table-bordered table-striped table-hover" style="text-align:center">
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

<!-- 信息删除确认 -->
<div class="modal fade" id="delcfmModel">
    <div class="modal-dialog">
        <div class="modal-content message_align">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                <h4 class="modal-title">提示信息</h4>
            </div>
            <div class="modal-body">
                <p>您确认要删除吗？</p>
            </div>
            <div class="modal-footer">
                <input type="hidden" id="url"/>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="button"  onclick="deleteMenu()" class="btn btn-success" data-dismiss="modal">确定</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script type="text/javascript">

    $(document).ready(function () {

    });

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
        $('#alert-grant-false').hide();
        $('#edit-alert-false').hide();
        $("#menuTable").DataTable({
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
                    url: "/system/menus",
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
                    title: "菜单ID"
                },
                {
                    target:1,
                    data: "menuCode",
                    title: "菜单编码"
                },
                {
                    target:2,
                    data: "menuName",
                    title: "菜单名称"
                },
                {
                    target:3,
                    data: "menuHref",
                    title: "菜单链接"
                },
                {
                    target:4,
                    data: "menuParentCode",
                    title: "父菜单编码"
                },
                {
                    target:5,
                    data: "menuIcon",
                    title: "菜单图标"
                },
                {
                    target:6,
                    data: "updateTime",
                    title: "更新时间"
                },
                {
                    target:7,
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
                    target:8,
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
                    target:9,
                    data: null,
                    title: "操作",
                    render:function(data,type,row,meta){
                        var html =
                        <#if permission?? && permission?seq_contains("U")>
                        "<button type='button' class='btn btn-info' style='padding: 1px;'" +
                        " onclick='editRole(\""+row.id+"\")'>编辑</button>" + "&nbsp;" +
                        "&nbsp;"+
                        "<button type='button' class='btn btn-info' style='padding: 1px;'" +
                        " onclick='grantRole(\""+row.id+"\")'>分配角色</button>" + "&nbsp;" +
                        "&nbsp;"+
                        </#if>
                        <#if permission?? && permission?seq_contains("D")>
                        "<button" +
                        " type='button' class='btn btn-info' style='padding: 1px;'" +
                        " onclick='deleteMenuConfirm(\""+row.id+"\")'>删除</button>"+ "&nbsp;" +
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
        document.getElementById("menuAddForm").reset()

        getTree();
    });

    function getTree() {
        // Some logic to retrieve, or generate tree structure
        $.ajax({
            type: 'POST',
            url: '/system/getParentMenus' ,
            data: null ,
            dataType: 'json',
            async : false, //默认为true 异步
            success:function(data){
                if(data != null){
                    $('#tree').treeview({
                        data:data,
                        showTags:true
                    });
                }
            }
        });
    }

    $("#mymodal-adddata").on("hide.bs.modal",function(){
        document.getElementById("menuAddForm").reset();
    });

    $("#mymodal-editdata").on("hide.bs.modal",function(){
        document.getElementById("userEditForm").reset();
    });

    $("#mymodal-adddata").on("show.bs.modal",function(){
        getTree();
    });


    function deleteMenuConfirm(id){
        $("#delId").val(id);
        $('#delcfmModel').modal();
    }

    function deleteMenu(){
        if($("#delId").val() == null || $("#delId").val() == ''){
            return;
        }
        $.ajax({
            type: 'POST',
            url: '/system/menuDel' ,
            data: {"id":$("#delId").val(),"menuCode":$("#menuCode").val()} ,
            dataType: 'json',
            async : false, //默认为true 异步
            success:function(data){
                if(data > 0){
                    $("#alert-msg").html("删除成功！");
                    $('#alert-success').show();
                    $("#menuTable").dataTable().fnDraw();
                }
            }
        });
    }

    function editRole(id){
        var alldata=$('#roleTable').dataTable().fnGetData();
        var obj;
        for(var i=0;i<alldata.length;i++){
            if(alldata[i].id==id){
                obj = alldata[i];
                break;
            }
        }
        $("#id-edit").val(obj.id);
        $("#roleCode-edit").val(obj.roleCode);
        $("#roleName-edit").val(obj.roleName);
        $("#mymodal-editdata").modal("show");
    }

    function grantRole(id){

        $("#destinationFields").empty();
        $("#sourceFields").empty();
        var alldata=$('#menuTable').dataTable().fnGetData();
        var obj;
        for(var i=0;i<alldata.length;i++){
            if(alldata[i].id==id){
                obj = alldata[i];
                break;
            }
        }
        $("#menuNameGrant").val(obj.menuName);
        $("#menuCodeGrant").val(obj.menuCode);

        $.ajax({
            type: 'POST',
            url: '/system/getMenuRoles' ,
            data: {"menuCode":obj.menuCode} ,
            dataType: 'json',
            async : false, //默认为true 异步
            success:function(data){
                if(data != null){
                    if(data.other != null && data.other.length > 0) {
                        for (var i=0;i<data.other.length;i++) {
                            $("#sourceFields").append('<div>' + data.other[i].roleCode + ':' + data.other[i].roleName + '</div>');
                        }
                    }
                    if(data.have != null && data.have.length > 0) {
                        for (var i=0;i<data.have.length;i++) {
                            $("#destinationFields").append('<div>' + data.have[i].roleCode + ':' + data.have[i].roleName + '</div>');
                        }
                    }

                    var $sourceFields = $("#sourceFields");
                    var $destinationFields = $("#destinationFields");
                    var $chooser = $("#fieldChooser").fieldChooser(sourceFields, destinationFields);
                }
            }
        });
        //清理数据
        $("#mymodal-grantdata").modal("show");
    }

    function editRoleServer(){
        var param = {
            "id":$("#id-edit").val(),
            "roleName":$("#roleName-edit").val(),
            "menuCode":$("#menuCode").val()
        };
        $.ajax({
            type: 'POST',
            url: '/system/roleEdit' ,
            data: param ,
            dataType: 'json',
            async : false, //默认为true 异步
            success:function(data){
                if(data > 0){
                    $("#alert-msg").html("修改成功！");
                    $('#alert-success').show();
                    $('#mymodal-editdata').modal('hide');
                    $("#roleTable").dataTable().fnDraw();
                }else{
                    $('#edit-alert-false').show();
                }
            }
        });
    }

    function checkForm(){
        return true;
    }

    function grantMenu(){
        var roleCodes = "";
        var array = $("#destinationFields").children();
        for(var i=0;i<array.length;i++){
            roleCodes += array[i].innerText.split(":")[0]+",";
        }

        $.ajax({
            type: 'POST',
            url: '/system/grantMenuRoles' ,
            data: {
                "menuCode":$("#menuCodeGrant").val(),
                "roleCodes": roleCodes
            } ,
            dataType: 'json',
            async : false, //默认为true 异步
            success:function(data){
                if(data > 0){
                    $('#mymodal-grantdata').modal('hide');
                    $("#alert-msg").html("授权成功！需要重新登录才能生效！");
                    $('#alert-success').show();
                }else{
                    $('#alert-grant-false').show();
                }
            }
        });
    }


    function addMenu(){
        var arr = $('#tree').treeview("getSelected");
        var parentCode = (arr!=null&&arr.length>0)?arr[0].href:null;
        if(checkForm()){
            var param = {
                "menuName":$("#menuName").val(),
                "menuCode":$("#menuCodeForm").val(),
                "menuIcon":$("#menuIcon").val(),
                "menuParentCode":parentCode,
                "menuHref":$("#menuHref").val()
            };
            $.ajax({
                type: 'POST',
                url: '/system/menuAdd' ,
                data: param ,
                dataType: 'json',
                async : false, //默认为true 异步
                success:function(data){
                    if(data >= 0){
                        $('#mymodal-adddata').modal('hide');
                        $("#alert-msg").html("添加成功！");
                        $('#alert-success').show();
                        $("#menuTable").dataTable().fnDraw();
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
