function getState(state) {
    var states  = ["等待发货","已发货","订单完成","退款中","退款成功"];
    return states[state];

}