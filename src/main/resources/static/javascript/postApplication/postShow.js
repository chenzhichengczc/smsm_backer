$(function () {

    var id = getParameter("id");

    if (id == null || id == "" || id == undefined) {
        layer.alert("id获取失败，请稍后再试！")
        return;
    }

    $.ajax({
        url:'http://123.207.230.97:8090/backer/api/postApplication/selectOne',
        type:'get', //GET
        async:false,    //或false,是否异步
        headers:{

        },
        data:{
            id : id
        },
        timeout:50000,    //超时时间
        dataType:'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success:function(data){
            //console.log(data)；
            if(data.code == 0){
                var data = data.data;
                $("#postName").html("岗位名称：" + data.postName);
                $("#postCode").html("代号："+data.postCode);
                $("#applicationDeadline").html("截止时间：" + data.applicationDeadline);
                $("#hireDepartment").html(data.hireDepartment);
                $("#postDuty").html(data.postDuty);
                $("#hireAmount").html(data.hireAmount);
                $("#major").html(data.major);
                $("#educationRequirement").html(data.educationRequirement);
                $("#ageRange").html(data.ageRange);
                $("#recruitment").html(data.recruitment);
                $("#applicationQualifications").html(data.applicationQualifications);
                $("#otherRequirement").html(data.otherRequirement);
                $("#createTime").html(data.createTime);
                $("#updateTime").html(data.updateTime);
            }else{
                layer.msg('获取岗位信息失败', {icon: 2, time: 1000});
            }
        },
        error:function () {
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