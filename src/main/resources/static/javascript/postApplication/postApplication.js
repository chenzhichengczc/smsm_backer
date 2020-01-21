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

                    var html =  '<a style="text-decoration:none" class="ml-5" onClick="" ' +
                        'href="javascript:;" title="查看"><i class="Hui-iconfont">&#xe683;</i></a>' +
                        '<a style="text-decoration:none" class="ml-5" onClick="" href="javascript:;" title="编辑">' +
                        '<i class="Hui-iconfont">&#xe6df;</i></a> ' +
                        '<a style="text-decoration:none" class="ml-5" onClick="" ' +
                        'href="javascript:;" title="删除"><i class="Hui-iconfont">&#xe6e2;</i></a>'

                    return html
                }
            }
        ]

    });
})

function createPost() {
    layer_show("增添岗位","product-brand-add.html","800","600")
}