<!DOCTYPE html>
<html lang="en">

<header>
<@common.header/>
</header>

<body class="easyui-layout">

<!-- 顶部区域 -->
<div data-options="region:'north'" style="height: 10%;">
    <h2>Crowd Admin</h2>
</div>
<!-- 顶部区域结束 -->

<!-- 左边栏区域 -->
<div data-options="region:'west'" title="导航菜单" style="width: 10%;">
    <ul style="list-style-type:none; text-align:center; margin:0; padding:0;">
        <li><a onclick="addTab(this);" href="#" style="display:block; margin-top:20px; text-decoration:none;">积分明细</a></li>
        <li><a onclick="addTab(this);" href="#" style="display:block; margin-top:20px; text-decoration:none;">抽奖明细</a></li>
        <li><a onclick="addTab(this);" href="#" style="display:block; margin-top:20px; text-decoration:none;">查看奖品</a></li>
        <li><a onclick="addTab(this);" href="#" style="display:block; margin-top:20px; text-decoration:none;">邀请明细</a></li>
        <li><a onclick="addTab(this);" href="#" style="display:block; margin-top:20px; text-decoration:none;">查看用户</a></li>
    </ul>
</div>
<!-- 左边栏区域结束 -->

<!-- 主区域 -->
<div data-options="region:'center'">
    <div id="mainTab" class="easyui-tabs" data-options="fit:true, border:false"
         style="width: 100%; height: auto;"></div>
</div>
<!-- 主区域结束 -->

<script type="text/javascript">
    function addTab(obj) {
        let text = $(obj).html();
        let url;
        if (text === '积分明细') {
            url = '/admin/pointPage';
        } else if (text === '抽奖明细') {
            url = '/admin/rafflePage';
        } else if (text === '查看奖品') {
            url = '/admin/awardPage';
        } else if (text === '邀请明细') {
            url = '/admin/invitedPage';
        } else if (text === '查看用户') {
            url = '/admin/userPage';
        } else {
            return;
        }
        $('#mainTab').tabs('add', {
            title: text,
            closable: true,
            href: url
        });
    }
</script>

</body>
</html>