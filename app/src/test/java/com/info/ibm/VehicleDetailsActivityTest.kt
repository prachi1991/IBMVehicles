package com.info.ibm

import com.info.ibm.view.MainActivity
import com.info.ibm.view.VehicleDetailsActivity
import junit.framework.Assert
import org.junit.Test

class VehicleDetailsActivityTest {

    @Test
    fun conversion_ReturnsTrue() {
        Assert.assertTrue(VehicleDetailsActivity().checkCarbonEmission())
    }
}