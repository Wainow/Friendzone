package com.wainow.friendzone.utils

class RegisteredDateMapper {
    companion object{
        fun map(registeredDate: String): String{
            val registeredDateArray = registeredDate.split("T")
            val timeNumbersArray = registeredDateArray[1].split(':')
            val dateNumbersArray = registeredDateArray[0].split('-')
            val date = "${dateNumbersArray[2]}.${dateNumbersArray[1]}.${dateNumbersArray[0]}"
            val time = "${timeNumbersArray[0]}:${timeNumbersArray[1]}"
            return "$time $date"
        }
    }
}