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
</head>
<body style="background-color: #EDEDED">
<input type="hidden" id="menuCode" name="menuCode" />




    <div class="header" style="padding-top: 20px;">
        <ol class="breadcrumb" style="margin-bottom: 0px;">
            <li><a href="#">人口信息管理</a></li>
            <li class="active">信息搜索</li>
        </ol>
    </div>
    <div id="page-inner">
        <div class="row">
            <div class="col-md-12">
                <!-- Advanced Tables -->
                <div class="panel panel-default">
                    <div class="panel-heading">
                        信息搜索
                    </div>
                    <div class="panel-body">
                        <div id="sreachConditions">
                            <form id="sreachForm">
                                <div class="form-group col-md-3">
                                    <label for="id-edit" class="control-label">用户ID:</label>
                                    <input type="text" class="form-control" id="id-edit" disabled="true">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="userName-edit" class="control-label">用户名:</label>
                                    <input type="text" class="form-control" id="userName-edit">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="realName-edit" class="control-label">真实姓名:</label>
                                    <input type="text" class="form-control" id="realName-edit">
                                </div>
                                <div class="form-group col-md-3">
                                    <label for="telphone-edit" class="control-label">联系电话:</label>
                                    <input type="text" class="form-control" id="telphone-edit">
                                </div>
                            </form>
                        </div>
                        <div class="table-responsive" style="overflow-x:visible">
                            <table  id="peopleTable" class="table table-bordered table-striped table-hover" style="text-align:center">
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
                                        <th style="text-align:center"></th>
                                        <th style="text-align:center"></th>
                                        <th style="text-align:center"></th>
                                    </tr>
                                </thead>
                            </table>
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
                $("#peopleTable").DataTable({
//                    "dom": '<"toolbar">frtip',
//                     "dom": "<'row'<'col-xs-2'l><'#mytool.col-xs-4'><'col-xs-6'f>r>t" +
//                     "<'row'<'col-xs-6'i><'col-xs-6'p>>",
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
                            url: "/people/sreach",
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
                            data: "insuranceCardNo",
                            title: "社保卡号"
                        },
                        {
                            target:1,
                            data: "name",
                            title: "姓名"
                        },
                        {
                            target:2,
                            data: "genderCode",
                            title: "性别",
                            render:function(data){
                                if(data==2){
                                    return "男";
                                }else if(data==1){
                                    return "女";
                                }
                                return "其他";
                            }
                        },
                        {
                            target:3,
                            data: "birthday",
                            title: "生日"
                        },
                        {
                            target:4,
                            data: "nationRaw",
                            title: "民族"
                        },
                        {
                            target:5,
                            data: "idCardNo",
                            title: "身份证号"
                        },
                        {
                            target:6,
                            data: "maritalStatusRaw",
                            title: "婚姻"
                        },
                        {
                            target:7,
                            data: "telephone",
                            title: "电话"
                        },
                        {
                            target:8,
                            data: "address",
                            title: "住址"
                        },
                        {
                            target:9,
                            data: "contacts",
                            title: "联系人"
                        },
                        {
                            target:10,
                            data: "relationshipRaw",
                            title: "联系人关系"
                        },
                        {
                            target:11,
                            data: "unitName",
                            title: "工作单位"
                        },
                        {
                            target:12,
                            data: "occupationRaw",
                            title: "职业"
                        }
                    ]
                });
            });

    </script>
</body>
</html>
