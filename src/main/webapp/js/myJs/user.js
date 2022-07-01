//退出系统
function loginOut() {
    layer.alert('确定退出系统吗？', {
        time: 0 //不自动关闭
        ,btn: ['确定', '取消']
        ,yes: function(index){
            layer.close(index);
            location.href="/loginOut";
        }
    });
}

//异步更新用户信息
function updateUser() {
    //表单序列化 能够把表单的内容拼接成 key=值&key=值...,返回值是字符串
    var form = $('#updateUserForm').serialize();
    $.post("/updateUser", form, function (data) {
        if (data.ok) {
            layer.alert(data.mess, {icon: 6});

             setTimeout(function () {
                 //重新登录,跳转到登录页面
                 //setTimeout:隔多长时间执行指定代码
                 location.href = "/login.jsp";
             },1000);
        }
    }, 'json');
}
//异步校验旧密码输入是否正确
/*$('#oldPwd').blur(function (){
    $.post("/verifyooldPwd",{'oldPwd':$(this).val()},function (data){
        if (!data.ok){
            layer.msg(data.mess, {offset:'t'});
        }
    },'json')
});*/
