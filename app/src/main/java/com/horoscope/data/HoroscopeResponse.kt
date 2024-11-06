package com.horoscope.data

class HoroscopeResponse(data:DataResponse,  status:Int, success:Boolean) {
    var data = data
    var status = status
    var success = success
}

class DataResponse (date:String, horoscope_data:String) {
    var date = date
    var horoscope_data = horoscope_data
}