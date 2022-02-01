package com.virtusa.assignment

/**
1.

Write a program which takes two strings as input from the user (str1 and str2).
This program should print two strings as output (op1 and op2).

op1 should contain all the characters which are present in str1 but NOT present in str2.
op2 should contain all the characters which are present in str2 but NOT present in str1.
 */

fun main() {
    findUnique(arrayOf(1, 2, 5, 2, 4, 5))
}

fun findUnique(array: Array<Int>) {
    val map = HashMap<Int, Int>()

    for (i in array.indices) {
        print(array[i].toString() + " ")
        if (map.containsKey(array[i])) {
            val count = map[array[i]]
            if (count != null) {
                map[array[i]] = count + 1
            }
        } else {
            map[array[i]] = 1
        }
    }

    val filteredMap = map.filter {
        it.value == 1
    }

    print(filteredMap.keys.toString()+" ")
}

fun remove(str1: String, str2: String): String? {
    var strnew = ""
    var strUnmatch = str2
    val l1 = str1.length
    val l2 = str2.length
    for (j in 0 until l1) {
        val ch1 = str1[j]
        var isMatched = false
        for (p in 0 until l2) {
            val ch2 = str2[p]
            if (ch1 == ch2) {
                isMatched = true
                strUnmatch = strUnmatch.removePrefix(ch2.toString())
            }
        }
        if (!isMatched) {
            strnew += ch1
        }
    }
    return "op1 : $strnew  &  otp2 : $strUnmatch"
}