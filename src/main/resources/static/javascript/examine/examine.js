$(function () {
    var i = 1;

    $('.table-sort').dataTable({
        "ordering": false,
        bLengthChange: false,

        "ajax": {
            "url": "http://localhost:8080/backer/api/userApplication/getUserApplication",
            "header": {
                "token": getCookie("token")
            },
            "dataSrc": function (data) {
                $("#totleSize").html(data.data.length)
                return data.data
            }
        },
        columns: [{"data": null},
            {"data": null},
            {"data": null},
            {"data": "userPhone"},
            {"data": "postCode"},
            {"data": "postName"},
            {"data": "hireDepartment"},
            {"data": null},
            {"data": "checkReport"},
            {"data": null}],
        columnDefs: [
            {
                targets: 0,
                render: function (data, type, row) {
                    var html = "<input type='checkbox' data-id='" + data.id + "'>"
                    return html
                }
            }, {
                targets: 1,
                render: function (data, type, row) {
                    if (i % 11 == 0) {
                        i = 1;
                    }
                    return i++;
                }
            }, {
                targets: 2,
                render: function (data, type, row) {

                    var html = "<a href='javascript:void(0)' onclick='showOne(" + data.uid + ")'>" + data.userName + "</a>";
                    return html;
                }
            }, {
                targets: 7,
                render: function (data, type, row) {
                    var html = "";
                    if (data.paymentStatus == 0) {
                        html = "<span style='color: firebrick;font-weight: bold'>未支付</span>"
                    } else if (data.paymentStatus == 1 && data.checkResult == 0) {
                        html = "<span style='color: sandybrown;font-weight: bold'>审核中</span>"
                    } else if (data.paymentStatus == 1 && data.checkResult == 1) {
                        html = "<span style='color: green;font-weight: bold'>已通过</span>"
                    } else if (data.paymentStatus == 1 && data.checkResult == 2) {
                        html = "<span style='color: darkred;font-weight: bold'>不通过</span>"
                    }
                    return html;
                }
            }, {
                targets: 9,
                render: function (data, type, row) {

                    var html = '<a style="text-decoration:none" class="ml-5" onclick="showOnePostApplication(' + data.id + ')" ' +
                        'href="javascript:;" title="查看"><i class="Hui-iconfont">&#xe683;</i></a>';

                    if (data.paymentStatus != 0 && data.checkResult == 0) {
                        html = html +
                            '<a style="text-decoration:none" class="ml-5" onclick="pass(' + data.id + ')" href="javascript:;" title="通过">' +
                            '<i class="Hui-iconfont">&#xe6a7;</i></a> ' +
                            '<a style="text-decoration:none" class="ml-5" onclick="fail(' + data.id + ')" ' +
                            'href="javascript:;" title="不通过"><i class="Hui-iconfont">&#xe6a6;</i></a>'
                    }

                    return html
                }
            }
        ]
    });
});

function showOnePostApplication(id) {
    layer_show("投递简历详情", "examine-show.html?id=" + id, 800, 600);
}


function showOne(id) {
    layer_show("应聘者详情", "member-show.html?id=" + id, 800, 600);
}

function pass(id) {
    layer.confirm('是否提交通过？', function (index) {
        $.ajax({
            type: 'POST',
            url: 'http://localhost:8080/backer/api/userApplication/changeStatus',
            dataType: 'json',
            data: {
                id: id,
                checkResult: 1
            },
            "header": {
                "token": getCookie("token")
            },
            success: function (data) {
                if (data.code == 0) {
                    layer.msg('已审批通过!', {icon: 1, time: 1000});
                    setTimeout(function () {
                        window.location.reload();
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index);
                    }, 1500)
                } else {
                    layer.msg('服务器异常!', {icon: 2, time: 1000});
                }

            },
            error: function (data) {
                layer.msg('服务器异常!', {icon: 2, time: 1000});
            },
        });
    });
}

function fail(id) {
    layer.open({
        type: 1,
        title: '审核不通过',
        skin: 'layui-layer-rim',
        area: ['500px', 'auto', 'overfolow-'],
        content: '<div class="row cl">' +
            '<label class="form-label col-xs-4 col-sm-3" style="text-align: center">不通过原因：</label>' +
            ' <div class="formControls col-xs-8 col-sm-9">' +
            '<textarea style="width: 300px" name="" cols="" rows="" class="textarea" placeholder="说点什么...100个字符以内" dragonfly="true"\n' +
            '                          onKeyUp="$.Huitextarealength(this,100)" id="checkReport"></textarea>' +
            ' <p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>' +
            ' </div>' +
            ' </div>'
        ,
        btn: ['提交', '取消'],
        btn1: function (index, layero) {
            $.ajax({
                type: 'POST',
                url: 'http://localhost:8080/backer/api/userApplication/changeStatus',
                dataType: 'json',
                data: {
                    id: id,
                    checkResult: 2,
                    checkReport: $("#checkReport").val()
                },
                "header": {
                    "token": getCookie("token")
                },
                success: function (data) {
                    if (data.code == 0) {
                        layer.msg('已审批不通过!', {icon: 1, time: 1000});
                        setTimeout(function () {
                            window.location.reload();
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        }, 1500)
                    } else {
                        layer.msg('服务器异常!', {icon: 2, time: 1000});
                    }

                },
                error: function (data) {
                    layer.msg('服务器异常!', {icon: 2, time: 1000});
                },
            });
        },
        btn2: function (index, layero) {
            layer.close(index);
        }
    })
}