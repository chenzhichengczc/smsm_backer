$(function () {




    var i = 1;

    $('.table-sort').dataTable({
        "ordering": false,
        bLengthChange: false,

        "ajax": {
            "url": "http://106.52.215.30:80/backer/api/information/list",
            "header": {
                "token": getCookie("token")
            },
            "dataSrc": function (data) {
                debugger
                $("#totalSize").html(data.data.length)
                sessionStorage.setItem("information", data.data);
                return data.data
            }
        },
        columns: [{"data": null},
            {"data": null},
            {"data": "informationName"},
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
                targets: 4,
                render: function (data, type, row) {
                    var str = "";
                    if (data.status == 0) {
                        str = "<span style='color: royalblue '>未上线</span>"
                    } else if (data.status == 1) {
                        str = "<span style='color: palevioletred '>上线中</span>"
                    }
                    return str;
                }
            }, {
                targets: 5,
                render: function (data, type, row) {

                    if(data.status == 0){
                        var html =
                            '<a style="text-decoration:none" class="ml-5" data-title="编辑资讯" data-href="article-edit.html?id='+ data.id +'" onclick="Hui_admin_tab(this)" ' +
                            'href="javascript:;" title="修改"><i class="Hui-iconfont">&#xe647;</i></a>' +
                            '<a style="text-decoration:none" class="ml-5" onclick="onlineInformation(' + data.id + ')" ' +
                            'href="javascript:;" title="上线"> <i class="Hui-iconfont">&#xe6dc;</i></a> ' +
                            '<a style="text-decoration:none" class="ml-5" onclick="deleteInformation(' + data.id + ')" ' +
                            'href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>'
                    }else {
                        var html =
                            '<a style="text-decoration:none" class="ml-5" data-title="编辑资讯" data-href="article-edit.html?id='+ data.id +'" onclick="Hui_admin_tab(this)" ' +
                            'href="javascript:;" title="修改"><i class="Hui-iconfont">&#xe647;</i></a>' +
                            '<a style="text-decoration:none" class="ml-5" onclick="offlineInformation(' + data.id + ')" ' +
                            'href="javascript:;" title="下线"><i class="Hui-iconfont">&#xe6de;</i></a>' +
                            '<a style="text-decoration:none" class="ml-5" onclick="deleteInformation(' + data.id + ')" ' +
                            'href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>'
                    }



                    return html
                }
            }
        ]
    });

})


/*资讯-添加*/
function article_add(title, url, w, h) {
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}

/*资讯-编辑*/
function article_edit(title, url, id, w, h) {
    var index = layer.open({
        type: 2,
        title: title,
        content: url
    });
    layer.full(index);
}

/*资讯-删除*/
function deleteInformation(obj, id) {
    debugger
    layer.confirm('确认要删除吗？', function (index) {
        $.ajax({
            type: 'POST',
            url: window.location.protocol + "//" + window.location.hostname + ":" + window.location.port + "/information/delete",
            dataType: 'json',
            success: function (data) {
                $(obj).parents("tr").remove();
                layer.msg('已删除!', {icon: 1, time: 1000});
            },
            error: function (data) {
                console.log(data.msg);
            },
        });
    });
}

/*资讯-审核*/
function article_shenhe(obj, id) {
    layer.confirm('审核文章？', {
            btn: ['通过', '不通过', '取消'],
            shade: false,
            closeBtn: 0
        },
        function () {
            $(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="article_start(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
            $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
            $(obj).remove();
            layer.msg('已发布', {icon: 6, time: 1000});
        },
        function () {
            $(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="article_shenqing(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
            $(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">未通过</span>');
            $(obj).remove();
            layer.msg('未通过', {icon: 5, time: 1000});
        });
}

/*资讯-下架*/
function article_stop(obj, id) {
    layer.confirm('确认要下架吗？', function (index) {
        $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="article_start(this,id)" href="javascript:;" title="发布"><i class="Hui-iconfont">&#xe603;</i></a>');
        $(obj).parents("tr").find(".td-status").html('<span class="label label-defaunt radius">已下架</span>');
        $(obj).remove();
        layer.msg('已下架!', {icon: 5, time: 1000});
    });
}

/*资讯-发布*/
function article_start(obj, id) {
    layer.confirm('确认要发布吗？', function (index) {
        $(obj).parents("tr").find(".td-manage").prepend('<a style="text-decoration:none" onClick="article_stop(this,id)" href="javascript:;" title="下架"><i class="Hui-iconfont">&#xe6de;</i></a>');
        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
        $(obj).remove();
        layer.msg('已发布!', {icon: 6, time: 1000});
    });
}

/*资讯-申请上线*/
function article_shenqing(obj, id) {
    $(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">待审核</span>');
    $(obj).parents("tr").find(".td-manage").html("");
    layer.msg('已提交申请，耐心等待审核!', {icon: 1, time: 2000});
}

function deleteInformation(obj){
    $.ajax({
        url: 'http://106.52.215.30:80/backer/api/information/delete',
        type: 'POST', //GET
        async: false,    //或false,是否异步
        headers:{"token": getCookie("token")},
        data: {
            informationId: obj
        },
        traditional: true,
        timeout: 50000,    //超时时间
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {
            //console.log(data)；

            if (data.code == 0) {
                layer.msg('删除资讯成功', {icon: 1, time: 1000});
                setTimeout(function () {
                    window.location.reload();
                }, 1500)
            } else {
                layer.msg('删除资讯失败', {icon: 2, time: 1000});
            }


        },
        error: function () {
            alert("服务器异常，请稍后再试！")
        }
    })
}

function onlineInformation(obj) {

    $.ajax({
        url: 'http://106.52.215.30:80/backer/api/information/online',
        type: 'POST', //GET
        async: false,    //或false,是否异步
        headers:{"token": getCookie("token")},
        data: {
            informationId: obj
        },
        traditional: true,
        timeout: 50000,    //超时时间
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {
            //console.log(data)；

            if (data.code == 0) {
                layer.msg('资讯上线成功', {icon: 1, time: 1000});
                setTimeout(function () {
                    window.location.reload();
                }, 1500)
            } else {
                layer.msg('资讯上线失败', {icon: 2, time: 1000});
            }


        },
        error: function () {
            alert("服务器异常，请稍后再试！")
        }
    })

}

function offlineInformation(obj) {
    $.ajax({
        url: 'http://106.52.215.30:80/backer/api/information/offline',
        type: 'POST', //GET
        async: false,    //或false,是否异步
        headers:{"token": getCookie("token")},
        data: {
            informationId: obj
        },
        traditional: true,
        timeout: 50000,    //超时时间
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {
            //console.log(data)；

            if (data.code == 0) {
                layer.msg('资讯下线成功', {icon: 1, time: 1000});
                setTimeout(function () {
                    window.location.reload();
                }, 1500)
            } else {
                layer.msg('资讯下线失败', {icon: 2, time: 1000});
            }


        },
        error: function () {
            alert("服务器异常，请稍后再试！")
        }
    })
}