$(function () {
    
})

function loginManager() {

    var loginAccount = $("#loginAccount");
    var loginPassword = $("#loginPassword");



    $.ajax({
        url: 'http://localhost:8080/backer/api/manager/login',
        type: 'POST', //GET
        async: true,    //或false,是否异步
        headers: {},
        data: {
            loginAccount: loginAccount,
            loginPassword: loginPassword
        },
        timeout: 50000,    //超时时间
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {
           if(data.code == 0){

           }else{
               layer.msg(data.msg, {icon: 2, time: 1000});
           }
        },
        error: function () {
            alert("服务器异常，请稍后再试！")
        }
    })

}