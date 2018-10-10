<!DOCTYPE html>
<html lang="en">

<@common.header/>

<body>
    用户页面
    <table class="easyui-datagrid" data-options="url:'datagrid_data1.json',method:'get',border:false,singleSelect:true,fit:true,fitColumns:true">
        <thead>
        <tr>
            <th data-options="field:'itemid'" width="80">Item ID</th>
            <th data-options="field:'productid'" width="100">Product ID</th>
            <th data-options="field:'listprice',align:'right'" width="80">List Price</th>
            <th data-options="field:'unitcost',align:'right'" width="80">Unit Cost</th>
            <th data-options="field:'attr1'" width="150">Attribute</th>
            <th data-options="field:'status',align:'center'" width="60">Status</th>
        </tr>
        </thead>
    </table>
</body>
</html>