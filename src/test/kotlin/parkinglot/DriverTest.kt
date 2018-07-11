package parkinglot

import org.junit.Assert.assertEquals
import org.junit.Test

class DriverTest {

    @Test
    fun `should parking a vehicle to a parking lot and take it out`() {
        val vehicle = Vehicle("1")
        val driver = Driver("XiaoMing")
        val parkingLot = ParkingLot()

        val receipt = driver.park(vehicle, parkingLot)
        val actualVehicle = driver.take(receipt, parkingLot)
        assertEquals(vehicle, actualVehicle)
    }

    @Test
    fun `should parking multiple vehicles to a parking lot and take it out`() {
        val firstVehicle = Vehicle("001")
        val secondVehicle = Vehicle("002")

        val firstDriver = Driver("XiaoMing")
        val secondDriver = Driver("XiaoQiang")

        val parkingLot = ParkingLot()

        val firstReceipt = firstDriver.park(firstVehicle, parkingLot)
        val secondReceipt = secondDriver.park(secondVehicle, parkingLot)

        val actualFirstVehicle = firstDriver.take(firstReceipt,parkingLot)
        val actualSecondVehicle = secondDriver.take(secondReceipt,parkingLot)

        assertEquals(firstVehicle, actualFirstVehicle)
        assertEquals(secondReceipt,actualSecondVehicle )
    }
}
