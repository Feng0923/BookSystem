var elements=['张三','李四','小明','小红'];
    selects("div",elements);
//动态生成select多选下拉框
function selects(type,element) {
    var select = $("#" + type);
    select = $('<select>', {
        'class': 'selectpicker show-tick form-control validate[required]',
        'id': type + "id",
        'name': type + "name",
        'data-live-search': "false"//查询输入框
    });
    for (var o in element) {
        select.append($('<option>', {
            'value': element[o].value
        }).append(element[o].text));
    }
    $("#" + type).html(select);

    // 缺一不可
    //要以编程方式更新JavaScript的选择，首先操作选择，然后使用refresh方法更新UI以匹配新状态。 在删除或添加选项时，或通过JavaScript禁用/启用选择时，这是必需的。
    $('#' + type + 'id').selectpicker('refresh');
    //render方法强制重新渲染引导程序 - 选择ui,如果当您编程时更改任何相关值而影响元素布局，这将非常有用。
    $('#' + type + 'id').selectpicker('render');
}