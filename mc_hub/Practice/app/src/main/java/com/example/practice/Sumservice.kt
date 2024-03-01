package com.example.practice

class Sumservice {
    companion object {
        fun calculateSum(number: Int): Int {
            var sum = 0
            for (i in 1..number) {
                sum += i
            }
            return sum
        }
    }
}