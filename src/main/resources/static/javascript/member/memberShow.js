$(function () {

    var id = getParameter("id");
    if (id == null || id == "" || id == undefined) {
        layer.alert("id获取失败，请稍后再试！")
        return;
    }
    $.ajax({
        url: 'http://localhost:8080/backer/api/userApplication/getUserApplication',
        type: 'get', //GET
        async: true,    //或false,是否异步
        headers: {},
        data: {
            id: id
        },
        timeout: 50000,    //超时时间
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {
            //console.log(data)；

        },
        error: function () {
            alert("服务器异常，请稍后再试！")
        }
    })

})

//从路径获取参数
function getParameter(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}