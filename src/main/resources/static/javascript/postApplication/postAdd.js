$(function () {

    //初始化自增岗位编号
    var code = getCode()
    $("#postCode").val(code);

    laydate.render({
        elem: '#applicationDeadline',
        type: 'datetime'
    });


});

function sumbitForm() {
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
        url: 'http://106.52.215.30:80/backer/api/postApplication/insertPost',
        type: 'POST', //GET
        async:false,    //或false,是否异步
        headers:{"token": getCookie("token")},
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

        },
        timeout: 50000,    //超时时间
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {
            //console.log(data)；
            if (data.code == 0) {
                layer.msg('添加岗位成功', {icon: 1, time: 1000});
                setTimeout(function () {
                    window.parent.location.reload();
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);

                }, 1500)

            } else {
                layer.msg('添加岗位失败', {icon: 2, time: 1000});
            }

        },
        error: function () {
            alert("服务器异常，请稍后再试！")
        }
    })


}

//获取6位自增
function getCode() {

    var code = "";

    $.ajax({
        url: 'http://106.52.215.30:80/backer/api/postApplication/getCode',
        type: 'get', //GET
        async: false,    //或false,是否异步
        headers:{"token": getCookie("token")},
        data: {},
        timeout: 50000,    //超时时间
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {
            if (data.code == 0) {
                code = data.msg;
            }
        },
        error: function () {
            alert("服务器异常，请稍后再试！")
        }
    })
    return code;

}