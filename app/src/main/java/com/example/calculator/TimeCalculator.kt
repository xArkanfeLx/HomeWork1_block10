package com.example.calculator

class TimeCalculator(val time1: String, val time2: String) {
    private var errorText:String = "Неверно введено одно из значений!"
    fun sum(): String {
        if(checkValuesTimes(time1) || checkValuesTimes(time2)) return errorText
        val num1 = stringTimeToIntSec(time1)
        val num2 = stringTimeToIntSec(time2)
        if(num1==null || num2==null) return errorText
        return intSecTimeToString(num1+num2)
    }

    fun dif(): String? {
        if(checkValuesTimes(time1) || checkValuesTimes(time2)) return errorText
        val num1 = stringTimeToIntSec(time1)
        val num2 = stringTimeToIntSec(time2)
        if(num1==null || num2==null) return errorText
        return intSecTimeToString(num1-num2)
    }

    fun stringTimeToIntSec(value: String): Int? {
        var result = value.toIntOrNull() // если ввели только цифры, считаем это за секунды сразу
        if (result != null) return result
        else {
            result = 0
            val times = timeStringToArray(value) // распределяем введённое время на массив значений
            for (i in times.indices) {
                var num = times[i]
                if ((i < 2 && num > 60) || i > 2) return null
                if(i<2) num *= Math.pow(60.0,(2-i).toDouble()).toInt()
                result += num
            }
        }
        return result
    }

    fun checkValuesTimes(str:String):Boolean {
        return str.replace("[smh]".toRegex(),"").trim().toIntOrNull() == null
    }

    fun timeStringToArray(value:String):Array<Int> {
        val times = arrayOf(0,0,0)
        var str = value
        val timesArg = arrayOf('h','m','s')
        for(s in 0..<timesArg.size) {
            val arr = str.split(timesArg[s])
            if(arr.size==1 && arr[0].trim().toIntOrNull()==null) times[s] = 0
            else {
                times[s] = arr[0].toInt()
                if(arr.size!=1) str = arr[1]
            }
        }
        return times
    }

    fun intSecTimeToString(value: Int): String {
        var result=""
        if(value<0) result="-"
        val num = Math.abs(value)
        val second = num%60
        val minites = num/60%60
        val hourses = num/60/60
        if(hourses>0) result+=hourses.toString()+"h"
        if(minites>0) result+=minites.toString()+"m"
        if(second>0) result+=second.toString()+"s"
        return result
    }
}