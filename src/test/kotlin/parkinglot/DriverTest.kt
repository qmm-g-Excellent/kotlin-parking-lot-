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
        val parkingLot = ParkingLot()

        val xiaoMing = Driver("XiaoMing")
        val xiaoMingReceipt = xiaoMing.park(firstVehicle, parkingLot)
        val xiaoQing = Driver("XiaoQing")
        val xiaoQingReceipt = xiaoQing.park(secondVehicle, parkingLot)
        val actualFirstVehicle = xiaoMing.take(xiaoMingReceipt, parkingLot)
        val actualSecondVehicle = xiaoQing.take(xiaoQingReceipt, parkingLot)

        assertEquals(firstVehicle, actualFirstVehicle)
        assertEquals(secondVehicle, actualSecondVehicle)
    }

    @Test
    fun `should throw error when park a existed vehicle`() {
        val driver = Driver("XiaoZhang")
        val vehicle = Vehicle("001")
        val parkingLot = ParkingLot()
        driver.park(vehicle, parkingLot)

        assertFailsWith(IllegalArgumentException::class, "Such vehicle has existed.") {
            driver.park(vehicle, parkingLot)
        }
    }

    @Test
    fun `should throw error when take a not existed vehicle`() {
        val driver = Driver("XiaoZhang")
        val parkingLot = ParkingLot()

        assertFailsWith(NoSuchElementException::class,"No such vehicle in parkingLot.") {
            driver.take(Receipt("Not parked vehicle", "xiaoming"), parkingLot)
        }
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

    @Test
    fun `should not take the same vehicle`() {
        val parkingLot = ParkingLot()
        val driver = Driver("XiaoMing")
        val vehicle = Vehicle("001")

        val receipt = driver.park(vehicle, parkingLot)
        driver.take(receipt, parkingLot)

        assertFailsWith(NoSuchElementException::class, "No such vehicle in parkingLot.") {
            driver.take(receipt, parkingLot)
        }
    }

}
