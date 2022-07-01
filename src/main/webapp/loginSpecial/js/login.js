$('body').particleground({
    dotColor: '#E8DFE8',
    lineColor: '#1b3273'
});

$('#loginBtn').click(function (){
        if ($('#username').val().length==0){
            layer.msg("用户名不能为空", {offset:'t'});
            return;
        }


        if ($('#password').val().length==0){
            layer.msg("密码不能为空", {offset:'t'});
            return;
        }


        if ($('#code').val().length==0){
            layer.msg("验证码不能为空", {offset:'t'});
            return;
        }
    });
