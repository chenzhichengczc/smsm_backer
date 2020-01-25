$(function () {

})

function sumbitForm() {

    var loginAccount = $("#loginAccount").val()
    var loginPassword = $("#loginPassword").val()
    var managerPhone = $("#managerPhone").val()
    var managerEmail = $("#managerEmail").val()

    if (isEmpty123(loginAccount)) {
        layer.msg('管理员账号为空!', {icon: 2, time: 1000});
        return
    }

    if (isEmpty123(loginPassword)) {
        layer.msg('管理员密码为空!', {icon: 2, time: 1000});
        return
    }
    if (isEmpty123(managerPhone)) {
        layer.msg('管理员联系方式为空!', {icon: 2, time: 1000});
        return
    }
    if (isEmpty123(managerEmail)) {
        layer.msg('管理员邮箱为空!', {icon: 2, time: 1000});
        return
    }

    $.ajax({
        url: 'http://localhost:8080/backer/api/manager/insertManager',
        type: 'POST', //GET
        async: false,    //或false,是否异步
        headers: {
            "token": getCookie("token")
        },
        data: {
            loginAccount: loginAccount,
            loginPassword: loginPassword,
            managerPhone: managerPhone,
            managerEmail: managerEmail
        },
        timeout: 50000,    //超时时间
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {
            //console.log(data)；
            if(data.code == 0){
                layer.msg('新增管理员成功!', {icon: 1, time: 1000});
                setTimeout(function () {
                    window.parent.location.reload();
                    var index = parent.layer.getFrameIndex(window.name);
                    layer.close(index);
                }, 1500)
            }else{
                layer.msg(data.msg, {icon: 2, time: 1000});
            }
        },
        error: function () {
            alert("服务器异常，请稍后再试！")
        }
    })

}