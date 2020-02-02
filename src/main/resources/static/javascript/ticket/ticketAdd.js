$(function () {

    var id = getParameter("id");
    var applicantName = decodeURIComponent((location.search.split("?")[1].split("&")[1].split("=")[1]));
    var applicantGender = getParameter("applicantGender");
    var applicantIdentityCard = window.atob(getParameter("applicantIdentityCard"));

    if (isEmpty123(id)) {
        layer.msg('信息异常!', {icon: 2, time: 1000});
        return;
    }

    if (isEmpty123(applicantName)) {
        layer.msg('信息异常!', {icon: 2, time: 1000});
        return;
    }

    if (isEmpty123(applicantGender)) {
        layer.msg('信息异常!', {icon: 2, time: 1000});
        return;
    }

    if (isEmpty123(applicantIdentityCard)) {
        layer.msg('信息异常!', {icon: 2, time: 1000});
        return;
    }

    laydate.render({
        elem: '#ticketExamDate'
        , format: 'yyyy/MM/dd'
    });
    laydate.render({
        elem: '#ticketExamTimer'
        , type: 'time'
    });


    $("#applicantName").val(applicantName)
    $("#applicantGender").val(applicantGender == 0 ? "男" : applicantGender == 1 ? '女' : '信息异常')
    $("#applicantIdentityCard").val(applicantIdentityCard)

})

function sumbitForm() {
    var id = getParameter("id");
    var ticketNumber = $("#ticketNumber").val()
    var ticketExamTime = $("#ticketExamDate").val() + " " + $("#ticketExamTimer").val()
    var ticketExamAddressOne = $("#ticketExamAddressOne").val()
    var ticketExamAddressTwo = $("#ticketExamAddressTwo").val()
    var ticketRemark = $("#ticketRemark").val()

    if (isEmpty123(ticketNumber)) {
        layer.msg('准考证为空!', {icon: 2, time: 1000});
        return;
    }

    if (isEmpty123($("#ticketExamDate").val())) {
        layer.msg('考试日期为空!', {icon: 2, time: 1000});
        return;
    }

    if (isEmpty123($("#ticketExamTimer").val())) {
        layer.msg('考试时间为空!', {icon: 2, time: 1000});
        return;
    }

    if (isEmpty123(ticketExamAddressOne)) {
        layer.msg('考试地址为空!', {icon: 2, time: 1000});
        return;
    }

    $.ajax({
        type: 'POST',
        url: 'http://106.52.215.30:80/backer/api/userApplication/updateStatusAndTicket',
        dataType: 'json',
        data: {
            id: id,
            checkResult: 1,
            ticketNumber:ticketNumber,
            ticketExamTime:ticketExamTime,
            ticketExamAddressOne:ticketExamAddressOne,
            ticketExamAddressTwo:ticketExamAddressTwo,
            ticketRemark:ticketRemark
        },
        "header": {
            "token": getCookie("token")
        },
        success: function (data) {
            if (data.code == 0) {
                layer.msg('已审批通过!', {icon: 1, time: 1000});
                setTimeout(function () {
                    window.parent.location.reload();
                    var index = parent.layer.getFrameIndex(window.name);
                    parent.layer.close(index);
                }, 1500)
            } else {
                layer.msg('服务器异常!', {icon: 2, time: 1000});
            }

        },
        error: function (data) {
            layer.msg('服务器异常!', {icon: 2, time: 1000});
        },
    });
}