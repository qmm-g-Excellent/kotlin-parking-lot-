package parkinglot

import org.junit.Assert.assertEquals
import org.junit.Test

class DriverTest {

    @Test
    fun `should parking a vehicle to a parking lot and take it out`() {
        val vehicle = Vehicle()
        val driver = Driver()
        val parkingLot = ParkingLot()

        driver.park(vehicle, parkingLot)
        val actualVehicle = driver.take(parkingLot)
        assertEquals(vehicle, actualVehicle)
    }
}
