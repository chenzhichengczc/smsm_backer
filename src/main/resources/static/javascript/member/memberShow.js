$(function () {

    var id = getParameter("id");
    if (id == null || id == "" || id == undefined) {
        layer.alert("id获取失败，请稍后再试！")
        return;
    }
    $.ajax({
        url: 'http://localhost:8080/backer/api/user/getUser',
        type: 'get', //GET
        async: true,    //或false,是否异步
        headers:{"token": getCookie("token")},
        data: {
            id: id
        },
        timeout: 50000,    //超时时间
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {
            //console.log(data)；
            if (data.code == 0) {
                var data = data.data[0];

                $("#userName").html(data.userName)
                $("#userPhone").html(data.userPhone)
                $("#gender").html(data.gender == 0 ? '男' : '女')
                $("#userEmail").html(data.userEmail)
                $("#birthTime").html(data.birthTime.split(" ")[0])
                $("#identityCard").html(data.identityCard)
                $("#graduatedSchool").html(data.graduatedSchool)
                $("#createTime").html(data.createTime)
            } else {
                layer.msg('无法加载应聘者信息', {icon: 2, time: 1000});
            }
        },
        error: function () {
            alert("服务器异常，请稍后再试！")
        }
    })

})
