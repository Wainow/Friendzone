package com.wainow.friendzone.utils

import kotlin.math.pow

class IdToColorMapper {
    companion object{
        fun map(id: Int): String {
            var id = id
            var str = id.toString()
            return if (str.length < 7) {
                when (str.length) {
                    1 -> {
                        "#${str}${str}${str}000"
                    }
                    2 -> {
                        "#9${str}${str}0"
                    }
                    3, 6 -> {
                        "#$str"
                    }
                    4 -> {
                        "#" + str + "44"
                    }
                    5 -> {
                        "#" + str + "6"
                    }
                    else -> {
                        "#080634"
                    }
                }
            } else {
                val pow = str.length - 6
                id = (id / 10.0.pow(pow.toDouble())).toInt()
                str = id.toString()
                "#$str"
            }
        }
    }
}