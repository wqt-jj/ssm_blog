//离子背景
$('body').particleground({
    dotColor: '#E8DFE8',
    lineColor: '#1b3273'
});

//先发送请求
code();
//向后台发送验证码的请求
function code(){
    //因为base里面的controller包里面的验证码是@GetMapping("/code")
    $.get("code",function(data){
        showCheck(data);
    },'json')
}

//canvas绘制图片验证码
function showCheck(code) {
    var c = document.getElementById("myCanvas");
    var ctx = c.getContext("2d");
    ctx.clearRect(0, 0, 1000, 1000);
    ctx.font = "80px 'Hiragino Sans GB'";
    ctx.fillStyle = "#E8DFE8";
    ctx.fillText(code, 0, 100);
}


$('#loginBtn').click(function (){
    if ($('#username').val().length==0){
        //layer.msg("用户名不能为空", {offset:'t'});
        errorAlert("用户名不能为空");
        return;
    }


    if ($('#password').val().length==0){
        //layer.msg("密码不能为空", {offset:'t'});
        errorAlert("密码不能为空");
        return;
    }


    if ($('#code').val().length==0){
        //layer.msg("验证码不能为空", {offset:'t'});
        errorAlert("验证码不能为空");
        return;
    }

    $('.login').addClass('test'); //倾斜特效
    setTimeout(function () {
        $('.login').addClass('testtwo'); //平移特效
    }, 300);
    setTimeout(function () {
        $('.authent').show().animate({right: -320}, {
            easing: 'easeOutQuint',
            duration: 600,
            queue: false
        });
        $('.authent').animate({opacity: 1}, {
            duration: 200,
            queue: false
        }).addClass('visible');
    }, 500);

    //以上校验ok发送后台异步登录请求
    $.post("/login",{
        'username':$('#username').val(),
        'password':$('#password').val(),
        'code':$('#code').val()
    },function (data) {
        setTimeout(function () {
            $('.authent').show().animate({right: 90}, {
                easing: 'easeOutQuint',
                duration: 600,
                queue: false
            });
            $('.authent').animate({opacity: 0}, {
                duration: 200,
                queue: false
            }).addClass('visible');
            $('.login').removeClass('testtwo'); //平移特效
            $('.authent').hide();
        }, 2000);

        if(!data.ok){
            //清空原先表单
            $('#username').val("");
            $('#password').val("");
            $('#code').val("");
            setTimeout(function () {
                $('login').removeClass('test');
                errorAlert(data.mess);

                //登录失败，再次发送验证码
                $.get("/code",function (data) {
                    showCheck(data);
                },'json');
            }, 2400);
        }else{
            setTimeout(function () {
                $('.login').fadeOut(100);
                //登录校验成功，跳转到后台首页
                location.href = "/workbench/index";
            }, 2400);
        }
    },'json');
});
//专门抽取一个弹窗函数
function errorAlert(message){
    layer.msg(message, {offset:'t'});

    //alert(message);
}


