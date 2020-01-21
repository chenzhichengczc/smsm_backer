$(function () {
    $('.table-sort').dataTable({

        "ordering": false,
        bLengthChange: false,

        "ajax": {
            "url": "http://localhost:8080/backer/api/postApplication/getPostList",
            "dataSrc": function (data) {
                $("#totleSize").html(data.data.length)
                return data.data
            }
        },
        columns: [{"data": null}, //各列对应的数据列
            {"data": "postCode"},
            {"data": "postName"},
            {"data": "hireDepartment"},
            {"data": "applicationDeadline"},
            {"data": "postDuty"},
            {"data": null}],
        columnDefs: [
            {
                targets: 0,
                render: function (data, type, row) {
                    var html = "<input type='checkbox' data-id='" + data.id + "'>"
                    return html
                }
            },
            {
                targets: 6,
                render: function (data, type, row) {

                    var html = '<a style="text-decoration:none" class="ml-5" onclick="showOne(' + data.id + ')" ' +
                        'href="javascript:;" title="查看"><i class="Hui-iconfont">&#xe683;</i></a>' +
                        '<a style="text-decoration:none" class="ml-5" onclick="reviseOne(' + data.id + ')" href="javascript:;" title="编辑">' +
                        '<i class="Hui-iconfont">&#xe6df;</i></a> ' +
                        '<a style="text-decoration:none" class="ml-5" data-id="' + data.id + '" onclick="deleteOne(this)" ' +
                        'href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>'

                    return html
                }
            }
        ]

    });
})

function reviseOne(id) {
    layer_show("岗位信息修改", "product-brand-revise.html?id=" + id, 800, 600)
}

function showOne(id) {
    layer_show("岗位查询", "product-brand-show.html?id=" + id, 800, 600)
}

function deleteOne(obj) {

    var id = obj.dataset.id;

    var array = new Array();

    array.push(id)

    deleteMethod(array)

}

function datadel() {
    var checkList = $(":checked");

    if (checkList.length <= 0) {
        layer.msg('请勾选需删除行', {icon: 2, time: 1000});
        return
    }

    var array = new Array();

    for (var i = 0; i < checkList.length; i++) {
        array.push(checkList[i].dataset.id)
    }

    deleteMethod(array)
}

function deleteMethod(array) {
    $.ajax({
        url: 'http://localhost:8080/backer/api/postApplication/deletePostList',
        type: 'POST', //GET
        async:false,    //或false,是否异步
        headers: {},
        data: {
            ids: array
        },
        traditional: true,
        timeout: 50000,    //超时时间
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {
            //console.log(data)；

            if (data.code == 0) {
                layer.msg('删除岗位成功', {icon: 1, time: 1000});
                setTimeout(function () {
                    window.location.reload();
                }, 1500)
            } else {
                layer.msg('删除岗位失败', {icon: 2, time: 1000});
            }


        },
        error: function () {
            alert("服务器异常，请稍后再试！")
        }
    })

}

function createPost() {
    layer_show("增添岗位", "product-brand-add.html", "800", "600")
}