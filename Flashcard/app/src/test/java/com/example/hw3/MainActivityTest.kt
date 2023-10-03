package com.example.hw3

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class MainActivityTest{

    @Test
    fun testAddition() {
        val mathOperations = MathOperation()
        val result = mathOperations.add(5, 7)
        Assert.assertEquals(12, result)
    }

    @Test
    fun testSubtraction() {
        val mathOperations = MathOperation()
        val result = mathOperations.subtract(10, 3)
        Assert.assertEquals(7, result)
    }
}