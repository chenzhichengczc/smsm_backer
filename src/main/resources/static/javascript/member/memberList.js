$(function () {

    var i = 1;

    var table = $('.table-sort').dataTable({

        "ordering": false,
        bLengthChange: false,

        "ajax": {
            "url": "http://123.207.230.97:8090/backer/api/user/getUser",
            "dataSrc": function (data) {
                $("#totalData").html(data.data.length)
                return data.data
            }
        },

        columns: [{"data": null}, //各列对应的数据列
            {"data": null},
            {"data": "userName"},
            {"data": null},
            {"data": "userPhone"},
            {"data": "userEmail"},
            {"data": null},
            {"data": "graduatedSchool"}],
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
                targets: 3,
                render: function (data, type, row) {
                    var str = "";
                    if (data.gender == 0) {
                        str = "<span style='color: royalblue'>男</span>"
                    } else if (data.gender == 1) {
                        str = "<span style='color: palevioletred'>女</span>"
                    }
                    return str;
                }
            }, {
                targets: 6,
                render: function (data, type, row) {
                   return data.birthTime.split(" ")[0]
                }
            }],
        "fnDrawCallback": function (oSettings) {

        }
    });


});

function checkApplication(id) {
    layer_show("查看详情", "member-show.html?id=" + id, 1000, 600)
}