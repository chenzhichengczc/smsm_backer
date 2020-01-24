$(function () {
    
})

function loginManager() {

    var username = $("#loginAccount").val();
    var password = $("#loginPassword").val();


    $.ajax({
        url: 'http://localhost:8080/backer/api/manager/login',
        type: 'POST', //GET
        async: true,    //或false,是否异步
        header: {},
        data: {
            username: username,
            password: password
        },
        timeout: 50000,    //超时时间
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {
           if(data.code == 401){
               layer.msg(data.msg, {icon: 2, time: 1000});
           }else if(data.code == 0){
               layer.msg("登录成功，欢迎您", {icon: 1, time: 1000});
               setCookie("token", data.data.token)
               setTimeout(function () {
                   window.location.href = "http://localhost:8080";
               },1500)

           }
        },
        error: function () {
            alert("服务器异常，请稍后再试！")
        }
    })

}