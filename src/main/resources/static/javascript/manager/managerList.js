$(function () {

    var i = 1;

    $(".table-sort").dataTable({
        "ordering": false,
        bLengthChange: false,

        "ajax": {
            "url": "http://localhost:80/backer/api/manager/managerList",
            "headers": {
                "token": getCookie("token")
            },
            "dataSrc": function (data) {
                $("#totalSize").html(data.data.length)
                return data.data
            }
        },
        columns: [{"data": null},
            {"data": null},
            {"data": "loginAccount"},
            {"data": "managerPhone"},
            {"data": "managerEmail"},
            {"data": "createTime"},
            {"data": null},
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
                targets: 6,
                render: function (data, type, row) {
                    var str = "";
                    if (data.status == 0) {
                        str = "<span style='color: darkred;font-weight: bold'>已禁用</span>"
                    } else if (data.status == 1) {
                        str = "<span style='color: green;font-weight: bold'>已启用</span>"
                    } else {
                        str = "<span style='color: darkred;font-weight: bold'>状态异常</span>"
                    }
                    return str;
                }
            }, {
                targets: 7,
                render: function (data, type, row) {
                    var html = ''
                    if (data.status == 0) {
                        html = html + '<a style="text-decoration:none" class="ml-5" onclick="admin_start(' + data.id + ',1)" ' +
                            'href="javascript:;" title="启用"> <i class="Hui-iconfont">&#xe6dc;</i></a> '
                    } else if (data.status == 1) {
                        html = html + '<a style="text-decoration:none" class="ml-5" onclick="admin_stop(' + data.id + ',0)" ' +
                            'href="javascript:;" title="禁用"><i class="Hui-iconfont">&#xe6de;</i></a>'
                    }
                    html = html + '<a style="text-decoration:none" class="ml-5" onclick="admin_edit(' + data.id + ')" ' +
                        'href="javascript:;" title="修改"> <i class="Hui-iconfont">&#xe60c;</i></a> ' +
                        '<a style="text-decoration:none" class="ml-5" onclick="admin_del(' + data.id + ')" ' +
                        'href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>'
                    return html
                }
            }
        ]
    })
})


/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/

/*管理员-增加*/
function admin_add(title, url, w, h) {
    layer_show(title, url, w, h);
}

/*管理员-删除*/
function admin_del(id) {
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            url: 'http://localhost:80/backer/api/manager/deleteManager',
            type: 'POST', //GET
            async: true,    //或false,是否异步
            headers: {
                "token": getCookie("token")
            },
            data: {
                id: id
            },
            timeout: 50000,    //超时时间
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (data) {
                //console.log(data)；
                if(data.code == 0){
                    layer.msg('管理员已删除成功!', {icon: 1, time: 1000});
                    setTimeout(function () {
                        window.location.reload();
                        var index = parent.layer.getFrameIndex(window.name);
                        layer.close(index);
                    }, 1500)
                }else{
                    layer.msg('管理员删除失败', {icon: 2, time: 1000});
                }
            },
            error: function () {
                alert("服务器异常，请稍后再试！")
            }
        })
    });
}

/*管理员-编辑*/
function admin_edit(id) {
    layer_show("管理员信息修改", "admin-revise.html?id="+id, 800, 600);
}

/*管理员-停用*/
function admin_stop(id, status) {
    layer.confirm('确认要停用吗？', function (index) {

        $.ajax({
            url: 'http://localhost:80/backer/api/manager/updateStatus',
            type: 'POST', //GET
            async: true,    //或false,是否异步
            headers: {
                "token": getCookie("token")
            },
            data: {
                id: id,
                status: status
            },
            timeout: 50000,    //超时时间
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (data) {
                //console.log(data)；
                if(data.code == 0){
                    layer.msg('管理员已禁用成功!', {icon: 1, time: 1000});
                    setTimeout(function () {
                        window.location.reload();
                        var index = parent.layer.getFrameIndex(window.name);
                        layer.close(index);
                    }, 1500)
                }else{
                    layer.msg('管理员禁用失败', {icon: 2, time: 1000});
                }
            },
            error: function () {
                alert("服务器异常，请稍后再试！")
            }
        })

    });
}

/*管理员-启用*/
function admin_start(id, status) {
    layer.confirm('确认要启用吗？', function (index) {
        //此处请求后台程序，下方是成功后的前台处理……
        $.ajax({
            url: 'http://localhost:80/backer/api/manager/updateStatus',
            type: 'POST', //GET
            async: true,    //或false,是否异步
            headers: {
                "token": getCookie("token")
            },
            data: {
                id: id,
                status: status
            },
            timeout: 50000,    //超时时间
            dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
            success: function (data) {
                //console.log(data)；
                if(data.code == 0){
                    layer.msg('管理员已启用成功!', {icon: 1, time: 1000});
                    setTimeout(function () {
                        window.location.reload();
                        var index = parent.layer.getFrameIndex(window.name);
                        layer.close(index);
                    }, 1500)
                }else{
                    layer.msg('管理员启用失败', {icon: 2, time: 1000});
                }
            },
            error: function () {
                alert("服务器异常，请稍后再试！")
            }
        })

        layer.msg('已启用!', {icon: 6, time: 1000});
    });
}