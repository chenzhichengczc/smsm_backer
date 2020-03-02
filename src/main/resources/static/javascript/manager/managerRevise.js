$(function () {

    var id = getParameter("id");
    if (isEmpty123(id)) {
        layer.msg('id获取失败，请稍后再试!', {icon: 2, time: 1000});
        return
    }

    sessionStorage.setItem("managerId", id)

    $.ajax({
        url: 'http://localhost:80/backer/api/manager/managerList',
        type: 'get', //GET
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
            if (data.code == 0) {

                var data = data.data[0]

                $("#loginAccount").val(data.loginAccount)
                $("#loginPassword").val(data.loginPassword)
                $("#managerEmail").val(data.managerEmail)
                $("#managerPhone").val(data.managerPhone)

            } else {
                layer.msg('加载管理员信息异常', {icon: 2, time: 1000});
            }
        },
        error: function () {
            alert("服务器异常，请稍后再试！")
        }
    })


})

function updateForm() {

    var loginAccount = $("#loginAccount").val()
    var loginPassword = $("#loginPassword").val()
    var managerEmail = $("#managerEmail").val()
    var managerPhone = $("#managerPhone").val()

    if (isEmpty123(managerEmail)) {
        layer.msg('邮箱不能为空!', {icon: 2, time: 1000});
        return
    }
    if (isEmpty123(managerPhone)) {
        layer.msg('联系方式不能为空!', {icon: 2, time: 1000});
        return
    }

    $.ajax({
        url: 'http://localhost:80/backer/api/manager/updateManager',
        type: 'POST', //GET
        async: true,    //或false,是否异步
        headers: {
            "token": getCookie("token")
        },
        data: {
            loginAccount: loginAccount,
            loginPassword: loginPassword,
            managerEmail: managerEmail,
            managerPhone: managerPhone,
            id: sessionStorage.getItem("managerId")
        },
        timeout: 50000,    //超时时间
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {
            //console.log(data)；
            if(data.code == 0){
                layer.msg('管理员信息更新成功!', {icon: 1, time: 1000});
                setTimeout(function () {
                    window.parent.location.reload();
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                }, 1500)
            }else{
                layer.msg('管理员信息更新失败', {icon: 2, time: 1000});
            }
        },
        error: function () {
            alert("服务器异常，请稍后再试！")
        }
    })


}
