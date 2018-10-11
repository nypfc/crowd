<!DOCTYPE html>
<html lang="en">

<head>
<@common.header/>
</head>

<body>
<table id="point"></table>

<script type="text/javascript">
    $(function () {
        $('#point').datagrid({
            url: '/admin/point/list',
            method: 'get',
            striped: true,
            fitColumns: true,
            pagination: true,
            pageSize: 30,
            columns: [[
                {field: 'id', title: 'ID'}
            ]]
        });
    })
</script>
</body>

</html>