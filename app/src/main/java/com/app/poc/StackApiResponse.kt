package com.app.poc

class StackApiResponse {

    class Owner {
        var reputation: Int = 0
        var user_id: Long = 0
         var user_type: String = ""
         var profile_image: String= ""
         var display_name: String= ""
         var link: String= ""
    }

    class Items {
        lateinit var owner: Owner
        var score: Int = 0
        var last_activity_date: Long = 0
        var creation_date: Long = 0
        var question_id: Long = 0
        var is_accepted: Boolean = false
        var answer_id: Long = 0
    }

    lateinit var items: ArrayList<Items>
    var has_more: Boolean = false
     var quota_max: String= ""
     var quota_remaining: String= ""

}
