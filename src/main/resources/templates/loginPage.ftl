<!DOCTYPE html>
<html lang="en">

<@common.header/>

<body>
<div id="loginWindow" class="easyui-window" title="登录" style="width:320px;height:190px;">
    <form style="padding:10px 20px 10px 40px;">
        <p>用户名: <input type="text" id="username"></p>
        <p>密　码: <input type="password" id="password"></p>
        <div style="padding: 5px; text-align: center;">
            <a class="easyui-linkbutton" iconCls="icon-ok" onclick="submit();">Ok</a>
            <a class="easyui-linkbutton" iconCls="icon-cancel">Cancel</a>
        </div>
    </form>
</div>

<script src="/easyui/sha256.js"></script>
<script type="text/javascript">
    $(function () {
        $('#loginWindow').window({
            collapsible: false,
            minimizable: false,
            maximizable: false,
            closable: false
        });
    });

    function submit() {
        let username = $('#username').val();
        let password = SHA256($('#password').val());
        $.ajax({
            url: '/admin/login',
            type: 'post',
            data: JSON.stringify({
                username: username,
                password: password
            }),
            contentType: 'application/json',
            success: function(data) {
                if (data.success) {
                    location.href = "/admin/indexPage";
                } else {
                    console.info(data);
                }
            },
            error: function(data) {
                if (data.status === 401) {
                    $.messager.alert('错误', data.responseJSON.message, 'error');
                } else {
                    $.messager.alert('错误', '服务器异常', 'error');
                }
            }
        });
    }
</script>
</body>

</html>