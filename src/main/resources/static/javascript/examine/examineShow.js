$(function () {

    var id = getParameter("id");

    if (id == null || id == "" || id == undefined) {
        layer.alert("id获取失败，请稍后再试！")
        return;
    }

    $.ajax({
        url: 'http://106.52.215.30:80/backer/api/userApplication/getUserApplication',
        type: 'get', //GET
        async: true,    //或false,是否异步
        headers: {"token": getCookie("token")},
        data: {
            id: id
        },
        timeout: 50000,    //超时时间
        dataType: 'json',    //返回的数据格式：json/xml/html/script/jsonp/text
        success: function (data) {
            //console.log(data)；
            if (data.code == 0) {
                var data = data.data[0];

                sessionStorage.setItem("postApplicationId", data.id)

                if (data.paymentStatus != 0 && data.checkResult == 0) {
                    $("#btnGroup").css("display", "inline")
                }

                var paymentStatus = data.paymentStatus;
                switch (paymentStatus) {
                    case 0 :
                        paymentStatus = "<span style='font-weight:bold;color: firebrick'>未支付</span>";
                        break;
                    case 1 :
                        paymentStatus = "<span style='font-weight:bold;color: green'>已支付</span>";
                        break;
                    default:
                        paymentStatus = "信息错误"
                }

                var checkResult = data.checkResult;
                switch (checkResult) {
                    case 0 :
                        checkResult = "<span style='font-weight:bold;color: sandybrown'>审核中</span>";
                        break;
                    case 1 :
                        checkResult = "<span style='font-weight:bold;color: green'>已通过</span>";
                        break;
                    case 2 :
                        checkResult = "<span style='font-weight:bold;color: red'>不通过</span>";
                        break;
                    default:
                        checkResult = "信息错误"
                }

                var applicantPoliticalStatus = data.applicantPoliticalStatus;
                switch (applicantPoliticalStatus) {
                    case 0 :
                        applicantPoliticalStatus = "中共党员";
                        break;
                    case 1 :
                        applicantPoliticalStatus = "中共预备党员";
                        break;
                    case 2 :
                        applicantPoliticalStatus = "共青团员";
                        break;
                    case 3 :
                        applicantPoliticalStatus = "民革党员";
                        break;
                    case 4 :
                        applicantPoliticalStatus = "民盟盟员";
                        break;
                    case 5 :
                        applicantPoliticalStatus = "民建会员";
                        break;
                    case 6 :
                        applicantPoliticalStatus = "民进会员";
                        break;
                    case 7 :
                        applicantPoliticalStatus = "农工党党员";
                        break;
                    case 8 :
                        applicantPoliticalStatus = "致公党党员";
                        break;
                    case 9 :
                        applicantPoliticalStatus = "九三公社社员";
                        break;
                    case 10 :
                        applicantPoliticalStatus = "台盟盟员";
                        break;
                    case 11 :
                        applicantPoliticalStatus = "无党派人士";
                        break;
                    case 12 :
                        applicantPoliticalStatus = "群众";
                        break;
                    default:
                        applicantPoliticalStatus = "信息错误"
                }

                var applicantMarriageStatus = data.applicantMarriageStatus;
                switch (applicantMarriageStatus) {
                    case 0 :
                        applicantMarriageStatus = "未婚";
                        break;
                    case 1 :
                        applicantMarriageStatus = "已婚";
                        break;
                    case 2 :
                        applicantMarriageStatus = "丧偶";
                        break;
                    case 2 :
                        applicantMarriageStatus = "离婚";
                        break;
                    default:
                        applicantMarriageStatus = "信息错误"
                }

                var applicantEducationalBackground = data.applicantEducationalBackground;
                switch (applicantEducationalBackground) {
                    case 0 :
                        applicantEducationalBackground = "小学";
                        break;
                    case 1 :
                        applicantEducationalBackground = "初中";
                        break;
                    case 2 :
                        applicantEducationalBackground = "高中";
                        break;
                    case 3 :
                        applicantEducationalBackground = "大专";
                        break;
                    case 4 :
                        applicantEducationalBackground = "本科";
                        break;
                    case 5 :
                        applicantEducationalBackground = "专业学士";
                        break;
                    case 6 :
                        applicantEducationalBackground = "硕士研究生";
                        break;
                    case 7 :
                        applicantEducationalBackground = "博士研究生";
                        break;
                    default:
                        applicantEducationalBackground = "信息错误"
                }


                $("#userName").html(data.userName)
                $("#userPhone").html(data.userPhone)
                $("#paymentStatus").html(paymentStatus)
                $("#checkResult").html(checkResult)
                $("#checkReport").html(data.checkReport)
                $("#applicantApplicationPost").html(data.applicantApplicationPost)
                $("#applicantName").html(data.applicantName)
                $("#applicantGender").html(data.applicantGender == 0 ? "男" : data.applicantGender == 1 ? "女" : "信息错误")
                $("#applicantBirth").html(data.applicantBirth)
                $("#applicantPoliticalStatus").html(applicantPoliticalStatus)
                $("#applicantHouseholdRegister").html(data.applicantHouseholdRegister)
                $("#applicantMarriageStatus").html(applicantMarriageStatus)
                $("#applicantIdentityCard").html(data.applicantIdentityCard)
                $("#applicantGraduatedCollege").html(data.applicantGraduatedCollege)
                $("#applicantGraduatedTime").html(data.applicantGraduatedTime)
                $("#applicantEducationalBackground").html(applicantEducationalBackground)
                $("#applicantOccupationalQualification").html(data.applicantOccupationalQualification)
                $("#applicantMajor").html(data.applicantMajor)
                $("#applicantEnglishLevel").html(data.applicantEnglishLevel)
                $("#applicantComputerLevel").html(data.applicantComputerLevel)
                $("#applicantContactAddress").html(data.applicantContactAddress)
                $("#applicantContactPhone").html(data.applicantContactPhone)
                $("#applicantWorkExprience").html(data.applicantWorkExprience.replace(/s/g, "，"))
                $("#applicantErgentContact").html(data.applicantErgentContact)
                $("#applicantErgentPhone").html(data.applicantErgentPhone)
                $("#applicantFamilyRelationship").html(data.applicantFamilyRelationship)
                $("#applicantSignName").html(data.applicantSignName)
                $("#applicantSignTime").html(data.applicantSignTime)
                $("#applicantPhotoSrc").attr("src", data.applicantPhotoSrc)
                $("#applicantIdentityCardPhoneSrc").attr("src", data.applicantIdentityCardPhoneSrc)
                $("#applicantIdentityCardPhoneReverseSrc").attr("src", data.applicantIdentityCardPhoneReverseSrc)
                $("#applicantDiplomaSrc").attr("src", data.applicantDiplomaSrc)
                $("#ticketNumber").html(data.ticketNumber)
                $("#ticketExamTime").html(data.ticketExamTime)
                $("#ticketExamAddressOne").html(data.ticketExamAddressOne)
                $("#ticketExamAddressTwo").html(data.ticketExamAddressTwo)
                $("#ticketRemark").html(data.ticketRemark)

            } else {
                layer.msg('获取简历信息失败', {icon: 2, time: 1000});
            }
        },
        error: function () {
            alert("服务器异常，请稍后再试！")
        }
    })

})

function pass() {
    var id = sessionStorage.getItem("postApplicationId");
    layer.confirm('是否提交通过？', function (index) {
        $.ajax({
            type: 'POST',
            url: 'http://106.52.215.30:80/backer/api/userApplication/changeStatus',
            dataType: 'json',
            data: {
                id: id,
                checkResult: 1
            },
            headers:{"token": getCookie("token")},
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
    });
}
function fail() {
    var id = sessionStorage.getItem("postApplicationId");
    layer.open({
        type: 1,
        title: '审核不通过',
        skin: 'layui-layer-rim',
        area: ['500px', 'auto', 'overfolow-'],
        content: '<div class="row cl">' +
            '<label class="form-label col-xs-4 col-sm-3" style="text-align: center">不通过原因：</label>' +
            ' <div class="formControls col-xs-8 col-sm-9">' +
            '<textarea style="width: 300px" name="" cols="" rows="" class="textarea" placeholder="说点什么...100个字符以内" dragonfly="true"\n' +
            '                          onKeyUp="$.Huitextarealength(this,100)" id="checkReport1"></textarea>' +
            ' <p class="textarea-numberbar"><em class="textarea-length">0</em>/100</p>' +
            ' </div>' +
            ' </div>'
        ,
        btn: ['提交', '取消'],
        btn1: function (index, layero) {
            $.ajax({
                type: 'POST',
                url: 'http://106.52.215.30:80/backer/api/userApplication/changeStatus',
                dataType: 'json',
                data: {
                    id: id,
                    checkResult: 2,
                    checkReport: $("#checkReport1").val()
                },
                headers:{"token": getCookie("token")},
                success: function (data) {
                    if (data.code == 0) {
                        layer.msg('已审批不通过!', {icon: 1, time: 1000});
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
        },
        btn2: function (index, layero) {
            layer.close(index);
        }
    })
}