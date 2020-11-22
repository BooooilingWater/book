//一般直接写在一个js文件中
layui.use(['layer', 'form', 'jquery'], function () {
    var layer = layui.layer
        , form = layui.form
        , $ = layui.jquery;
    $('#to-register').click(function () {
        //弹出注册框
        layer.open({
            type: 1,
            title: "注册",
            area: ['420px', '280px'], //宽高
            content: $('#register'),
            offset: '50px'
        });
    });
    // 表单验证
    form.verify({
        username: function (value, item) { //value：表单的值、item：表单的DOM对象
            if (value.length < 1 || value.length > 6) {
                return '用户名长度为1-6位'
            }
            if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                return '用户名不能有特殊字符';
            }
            if (/(^\_)|(\__)|(\_+$)/.test(value)) {
                return '用户名首尾不能出现下划线\'_\'';
            }
            if (/^\d+\d+\d$/.test(value)) {
                return '用户名不能全为数字';
            }

        }

        //我们既支持上述函数式的方式，也支持下述数组的形式
        //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
        , pass: [
            /^[\S]{6,12}$/
            , '密码必须6到12位，且不能出现空格'
        ]
    });

    // 表单提交
    form.on('submit(*)', function (data) {
        $.ajax({
            url: "./user/login",
            type: "POST",
            data: data.field,
            success: function (data) {
                if (data.status === 200) {
                    layer.msg("登录成功");
                    setInterval(function () {
                        window.location.href = "./user/home"
                    }, 1000);
                } else if (data.status === 201) {
                    layer.msg(data.data)
                }
            },
            error: function (xhr, status, error) {
                layer.msg("网络请求失败", {icon:2})
            }
        });
        return false; //阻止表单跳转。
    });

    form.on('submit(register)', function (data) {
        if (data.field.password !== data.field.rePassword) {
            layer.msg('两次密码不一致', {icon: 2})
            return false
        }
        $.ajax({
            url: "/user/register",
            type: "POST",
            data: data.field,
            success: function (data) {
                if (data.status === 200) {
                    layer.msg("注册成功")
                } else if (data.status === 201) {
                    layer.msg(data.data)
                }
            },
            error: function (xhr, status, error) {
                if (xhr.status === 400) {
                    layer.msg('格式存在错误')
                } else {
                    layer.error("网络请求失败")
                }
            }
        });
        return false; //阻止表单跳转。
    });
});