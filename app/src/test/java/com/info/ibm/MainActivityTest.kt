package com.info.ibm

import com.info.ibm.view.MainActivity
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import org.junit.Assert
import org.junit.Test

class MainActivityTest {
    @Test
    fun inputRange_validation_ReturnsTrue() {
        assertTrue(MainActivity().validation())
    }

    @Test
    fun input_range_isCorrect() {
        Assert.assertEquals(100, 101)
    }

}