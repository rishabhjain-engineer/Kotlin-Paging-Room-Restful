package com.app.poc

class ApiResponse {

    var has_more:Boolean = false
    lateinit var order:List<Order>

}

class Order{

    var id:Int = 0
    var nested_cust_name:String = ""
    lateinit var nested_order:List<NestedOrder>

}


class NestedOrder {

    var id:Int = 0
    var second_nested_cust_name:String = ""
}