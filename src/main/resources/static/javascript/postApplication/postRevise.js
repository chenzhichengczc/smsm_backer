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
        dataType:'json',    //返回的数据格式：json/xml/val/script/jsonp/text
        success:function(data){
            //console.log(data)；
            if(data.code == 0){
                var data = data.data;
                $("#postName").val(data.postName);
                $("#postCode").val(data.postCode);
                $("#applicationDeadline").val(data.applicationDeadline);
                $("#hireDepartment").val(data.hireDepartment);
                $("#postDuty").val(data.postDuty);
                $("#hireAmount").val(data.hireAmount);
                $("#major").val(data.major);
                $("#educationRequirement").val(data.educationRequirement);
                $("#ageRange").val(data.ageRange);
                $("#recruitment").val(data.recruitment);
                $("#applicationQualifications").val(data.applicationQualifications);
                $("#otherRequirement").val(data.otherRequirement);
            }else{
                layer.msg('获取岗位信息失败', {icon: 2, time: 1000});
            }
        },
        error:function () {
            alert("服务器异常，请稍后再试！")
        }
    })

})

function updateForm() {

    var id = getParameter("id");

    if (id == null || id == "" || id == undefined) {
        layer.alert("id获取失败，请稍后再试！")
        return;
    }
    var postCode = $("#postCode").val()
    var postName = $("#postName").val()
    var hireDepartment = $("#hireDepartment").val()
    var applicationDeadline = $("#applicationDeadline").val().replace(/-/g, "/");
    var postDuty = $("#postDuty").val()
    var hireAmount = $("#hireAmount").val()
    var major = $("#major").val()
    var educationRequirement = $("#educationRequirement").val();
    var ageRange = $("#ageRange").val()
    var recruitment = $("#recruitment").val()
    var applicationQualifications = $("#applicationQualifications").val()
    var otherRequirement = $("#otherRequirement").val()


    if (isEmpty123(postCode)) {
        layer.msg('岗位编号出错', {icon: 2, time: 1000});
        return
    }
    if (isEmpty123(postName)) {
        layer.msg('岗位名字为空', {icon: 2, time: 1000});
        return
    }
    if (isEmpty123(hireDepartment)) {
        layer.msg('招聘单位为空', {icon: 2, time: 1000});
        return
    }
    if (isEmpty123(applicationDeadline)) {
        layer.msg('截止时间为空', {icon: 2, time: 1000});
        return
    }
    if (isEmpty123(postDuty)) {
        layer.msg('岗位职责为空', {icon: 2, time: 1000});
        return
    }
    if (isEmpty123(hireAmount)) {
        layer.msg('招聘人数为空', {icon: 2, time: 1000});
        return
    } else if (/[^\d]/g.test(hireAmount)) {
        layer.msg('招聘人数请输入数字', {icon: 2, time: 1000});
        return
    }
    if (isEmpty123(major)) {
        layer.msg('专业要求为空', {icon: 2, time: 1000});
        return
    }
    if (isEmpty123(educationRequirement)) {
        layer.msg('学历要求为空', {icon: 2, time: 1000});
        return
    }
    if (isEmpty123(ageRange)) {
        layer.msg('年龄范围为空', {icon: 2, time: 1000});
        return
    }
    if (isEmpty123(recruitment)) {
        layer.msg('招聘对象为空', {icon: 2, time: 1000});
        return
    }
    if (isEmpty123(applicationQualifications)) {
        layer.msg('招聘条件为空', {icon: 2, time: 1000});
        return
    }
    if (isEmpty123(otherRequirement)) {
        otherRequirement = "";
    }

    $.ajax({
        url: 'http://123.207.230.97:8090/backer/api/postApplication/updatePost',
        type: 'POST', //GET
        async: true,    //或false,是否异步
        headers: {},
        data: {
            postCode: postCode,
            postName: postName,
            hireDepartment: hireDepartment,
            applicationDeadline: applicationDeadline,
            postDuty: postDuty,
            hireAmount: hireAmount,
            major: major,
            educationRequirement: educationRequirement,
            ageRange: ageRange,
            recruitment: recruitment,
            applicationQualifications: applicationQualifications,
            otherRequirement: otherRequirement,
            id : id,
        },
        timeout: 50000,    //超时时间
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {
            //console.log(data)；
            if (data.code == 0) {
                layer.msg('修改岗位成功', {icon: 1, time: 1000});
                setTimeout(function () {
                    window.parent.location.reload();
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                }, 1500)

            } else {
                layer.msg('修改岗位失败', {icon: 2, time: 1000});
            }

        },
        error: function () {
            alert("服务器异常，请稍后再试！")
        }
    })
}

//从路径获取参数
function getParameter(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}