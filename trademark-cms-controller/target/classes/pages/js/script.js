/*$('#password').focusin(function () {
    $('form').addClass('up')
})
$('#password').focusout(function () {
    $('form').removeClass('up')
})*/

// 眼睛移动
$(document).on('mousemove', function (event) {
    var dw = $(document).width() / 15
    var dh = $(document).height() / 15
    var x = event.pageX / dw
    var y = event.pageY / dh
    $('.eye-ball').css({
        width: x,
        height: y
    })
})

// 表单验证
$('.btn').click(function () {
    /* $('form').addClass('wrong-entry');
     setTimeout(function () {
         $('form').removeClass('wrong-entry')
     }, 3000)*/
    let adminAccount = $("#account").val();
    let adminPassword = $("#password").val();
    $.ajax({
        url: ctxPath + "/admin/doLogin",
        type: "POST",
        data:JSON.stringify({"adminAccount": adminAccount, "adminPassword": adminPassword}) ,
        contentType: "application/json;charset=utf-8",
        dataType:"JSON",
        async:true,
        success: function (res) {
            alert(res.message);
        },
        error: function (res) {
            alert(res.message);
        }
    });
});

// 刷新验证码
$('#codeImage').click(function () {
     document.getElementById("codeImage").src = ctxPath + "/captcha/kaptcha.jpg?"+Math.random();
});