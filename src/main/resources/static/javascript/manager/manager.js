$(function () {
    
})

function loginManager() {

    var username = $("#loginAccount").val();
    var password = $("#loginPassword").val();


    $.ajax({
        url: 'http://localhost:80/backer/manager/login',
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
            debugger
           if(data.code == 401){
               layer.msg(data.msg, {icon: 2, time: 1000});
           }else if(data.code == 0){
               layer.msg("登录成功，欢迎您", {icon: 1, time: 1000});
               setCookie("token", data.data.token, 24*60*60*1000*30)
               setTimeout(function () {
                   window.location.href = "/index.html";
               },1500)

           }
        },
        error: function () {
            alert("服务器异常，请稍后再试！")
        }
    })

}


/*
	参数解释：
	title	标题
	url		请求的url
	id		需要操作的数据id
	w		弹出层宽度（缺省调默认值）
	h		弹出层高度（缺省调默认值）
*/
/*管理员-增加*/
function admin_add(title,url,w,h){
    layer_show(title,url,w,h);
}
/*管理员-删除*/
function admin_del(obj,id){
    layer.confirm('确认要删除吗？',function(index){
        $.ajax({
            type: 'POST',
            url: '',
            dataType: 'json',
            success: function(data){
                $(obj).parents("tr").remove();
                layer.msg('已删除!',{icon:1,time:1000});
            },
            error:function(data) {
                console.log(data.msg);
            },
        });
    });
}

/*管理员-编辑*/
function admin_edit(title,url,id,w,h){
    layer_show(title,url,w,h);
}
/*管理员-停用*/
function admin_stop(obj,id){
    layer.confirm('确认要停用吗？',function(index){
        //此处请求后台程序，下方是成功后的前台处理……

        $(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_start(this,id)" href="javascript:;" title="启用" style="text-decoration:none"><i class="Hui-iconfont">&#xe615;</i></a>');
        $(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">已禁用</span>');
        $(obj).remove();
        layer.msg('已停用!',{icon: 5,time:1000});
    });
}

/*管理员-启用*/
function admin_start(obj,id){
    layer.confirm('确认要启用吗？',function(index){
        //此处请求后台程序，下方是成功后的前台处理……


        $(obj).parents("tr").find(".td-manage").prepend('<a onClick="admin_stop(this,id)" href="javascript:;" title="停用" style="text-decoration:none"><i class="Hui-iconfont">&#xe631;</i></a>');
        $(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已启用</span>');
        $(obj).remove();
        layer.msg('已启用!', {icon: 6,time:1000});
    });
}