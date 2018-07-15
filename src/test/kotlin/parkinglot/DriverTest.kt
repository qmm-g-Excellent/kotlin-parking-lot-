package parkinglot

import org.junit.Assert.assertEquals
import org.junit.Test
import kotlin.test.assertFailsWith

class DriverTest {

    @Test
    fun `should parking a vehicle to a parking lot and take it out`() {
        val vehicle = Vehicle("001")
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

        val actualFirstVehicle = firstDriver.take(firstReceipt, parkingLot)
        val actualSecondVehicle = secondDriver.take(secondReceipt, parkingLot)

        assertEquals(firstVehicle, actualFirstVehicle)
        assertEquals(actualSecondVehicle, actualSecondVehicle)
    }

    @Test
    fun `should throw error when park a existed vehicle`() {
        val driver = Driver("XiaoZhang")
        val vehicle = Vehicle("001")
        val parkingLot = ParkingLot()

        assertFailsWith(
                exceptionClass = IllegalArgumentException::class,
                message = "Such vehicle has existed.",
                block = { driver.park(vehicle, parkingLot) }
        )
    }

    @Test
    fun `should throw error when take a not existed vehicle`() {
        val driver = Driver("XiaoZhang")
        val parkingLot = ParkingLot()

        assertFailsWith(
                exceptionClass = NoSuchElementException::class,
                message = "No such vehicle in parkingLot.",
                block = {
                    driver.take(Receipt("Not parked vehicle", "xiaoming"),
                            parkingLot)
                }
        )
    }

    @Test
    fun `should throw error when park a vehicle while parking lot is full`() {
        val driver = Driver("XiaoZhang")
        val parkingLot = ParkingLot()

        for (i in 1..10) {
            driver.park(Vehicle(i.toString()), parkingLot)
        }

        assertFailsWith(
                exceptionClass = Exception::class,
                message = "Parking lot is full now.",
                block = { driver.park(Vehicle("11"), parkingLot) }
        )
    }
}
