$(function () {

    var i = 1;

    var table = $('.table-sort').dataTable({

        "ordering": false,
        bLengthChange: false,

        "ajax": {
            "url": "http://localhost:8080/backer/api/userApplication/getUserApplication",
            "dataSrc": function (data) {
                $("#totalData").html(data.data.length)
                return data.data
            }
        },
        // "aaSorting": [[ 1, "desc" ]],//默认第几个排序
        // "bStateSave": true,//状态保存
        // "aoColumnDefs": [
        //     //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
        //     {"orderable":false,"aTargets":[0,8,9]}// 制定列不参与排序
        // ],
        columns: [{"data": null}, //各列对应的数据列
            {"data": null},
            {"data": "userName"},
            {"data": null},
            {"data": "userPhone"},
            {"data": "userEmail"},
            {"data": "birthTime"},
            {"data": "graduatedSchool"},
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
                targets: 8,
                render: function (data, type, row) {

                    var str = "";
                    if (data.paymentStatus == 0) {
                        str = "<span style='color: red'>未支付</span>"
                        return str;
                    } else if (data.paymentStatus == 1 && data.checkResult == 0) {
                        str = "<span style='color: darkorange'>审核中</span>"
                    } else if (data.paymentStatus == 1 && data.checkResult == 1) {
                        str = "<span style='color: green'>通过</span>"
                    } else if (data.paymentStatus == 1 && data.checkResult == 2) {
                        str = "<span style='color: firebrick'>不通过</span>"
                    }
                    return str;
                },
            }, {
                targets: 9,
                render: function (data, type, row) {
                    return "<a title='查看详情' href='javascript:void(0)' onclick='checkApplication(" + data.id + ")'>查看详情</a>"
                }
            }],
        "fnDrawCallback": function( oSettings ) {

        }
    });


});

function checkApplication(id) {
    layer_show("查看详情","member-show.html?id=" + id,1000,600)
}