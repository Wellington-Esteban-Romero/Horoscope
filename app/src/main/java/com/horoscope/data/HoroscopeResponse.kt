package com.horoscope.data

class HoroscopeResponse(data:DataResponse) {
    var data = data
}

class DataResponse (date:String, horoscope_data:String, status:Int, success:Boolean) {
    var date = date
    var horoscope_data = horoscope_data
    var status = status
    var success = success
}