package parkinglot

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class AttendantTest {

    @Test
    fun `attendant parking a vehicle to a parking lot in order and take it out by drive`() {
        val parkingLot = ParkingLot(ArrayList(2))

        val attendant = Attendant("Xiao Li")
        val driver = Driver("Xiao Zhang")
        val vehicle = Vehicle("100")
        val parkingLots = arrayListOf(parkingLot)

        val receipt = attendant.park(vehicle, parkingLots)
        val actualVehicle = driver.take(receipt, parkingLots[0])

        assertEquals(vehicle, actualVehicle)
    }

    @Test
    fun `attendant parking three vehicles to two parking lots in order and take third vehicle out from second parking lot by drive`() {
        val firstParkingLot = ParkingLot(ArrayList(2))
        val secondParkingLot = ParkingLot(ArrayList(2))

        val driver = Driver("Xiao Zhang")
        val attendant = Attendant("Xiao Hu")

        val firstVehicle = Vehicle("100")
        val secondVehicle = Vehicle("101")
        val thirdVehicle = Vehicle("102")

        val parkingLots = arrayListOf(firstParkingLot, secondParkingLot)

        val firstReceipt = attendant.park(firstVehicle, parkingLots)
                          attendant.park(secondVehicle, parkingLots)
        val thirdReceipt = attendant.park(thirdVehicle, parkingLots)

        val actualFirstVehicle = driver.take(firstReceipt, parkingLots[0])
        val actualThirdVehicle = driver.take(thirdReceipt, parkingLots[1])

        assertEquals(firstVehicle, actualFirstVehicle)
        assertEquals(thirdVehicle, actualThirdVehicle)
    }

    @Test
    fun `should throw error when park a vehicle by attendant while all parking lots are full`() {
        val firstParkingLot = ParkingLot(ArrayList(2))
        val secondParkingLot = ParkingLot(ArrayList(2))

        val driver = Driver("Xiao Zhang")
        val attendant = Attendant("Xiao Hu")

        val firstVehicle = Vehicle("100")
        val lastVehicle = Vehicle("101")

        val parkingLots = arrayListOf(firstParkingLot, secondParkingLot)

        val firstReceipt = attendant.park(firstVehicle, parkingLots)

        for (i in 2..3) {
            attendant.park(Vehicle(i.toString()), parkingLots)
        }

        val lastReceipt = attendant.park(lastVehicle, parkingLots)

        assertFailsWith(
                exceptionClass = Exception::class,
                message = "Parking lots are full now.",
                block = {attendant.park(Vehicle("1005"), parkingLots)}
        )

        assertEquals(firstVehicle, driver.take(firstReceipt, parkingLots[0]))
        assertEquals(lastVehicle, driver.take(lastReceipt, parkingLots[1]))
    }

    @Test
    fun `drive parking a vehicle to a parking lot in order and take it out by attendant`() {
        val parkingLot = ParkingLot(ArrayList(2))

        val attendant = Attendant("Xiao Li")
        val driver = Driver("Xiao Zhang")
        val vehicle = Vehicle("100")
        val parkingLots = arrayListOf(parkingLot)

        val receipt = driver.park(vehicle, parkingLots)
        val actualVehicle = attendant.take(receipt, parkingLots[0])


        assertEquals(vehicle, actualVehicle)
    }

    @Test
    fun `drive parking three vehicles to two parking lots in order and take third vehicle out from second parking lot by attendant`() {
        val firstParkingLot = ParkingLot(ArrayList(2))
        val secondParkingLot = ParkingLot(ArrayList(2))

        val driver = Driver("Xiao Zhang")
        val attendant = Attendant("Xiao Hu")

        val firstVehicle = Vehicle("100")
        val secondVehicle = Vehicle("101")
        val thirdVehicle = Vehicle("102")

        val parkingLots = arrayListOf(firstParkingLot, secondParkingLot)

        val firstReceipt = driver.park(firstVehicle, parkingLots)
        driver.park(secondVehicle, parkingLots)
        val thirdReceipt = driver.park(thirdVehicle, parkingLots)

        val actualFirstVehicle = attendant.take(firstReceipt, parkingLots[0])
        val actualThirdVehicle = attendant.take(thirdReceipt, parkingLots[1])

        assertEquals(firstVehicle, actualFirstVehicle)
        assertEquals(thirdVehicle, actualThirdVehicle)
    }

    @Test
    fun `should throw error when park a vehicle by drive while all parking lots are full`() {
        val firstParkingLot = ParkingLot(ArrayList(2))
        val secondParkingLot = ParkingLot(ArrayList(2))

        val driver = Driver("Xiao Zhang")
        val attendant = Attendant("Xiao Hu")

        val firstVehicle = Vehicle("100")
        val lastVehicle = Vehicle("101")

        val parkingLots = arrayListOf(firstParkingLot, secondParkingLot)

        val firstReceipt = attendant.park(firstVehicle, parkingLots)

        for (i in 2..3) {
            attendant.park(Vehicle(i.toString()), parkingLots)
        }

        val lastReceipt = attendant.park(lastVehicle, parkingLots)

        assertFailsWith(
                exceptionClass = Exception::class,
                message = "Parking lots are full now.",
                block = {attendant.park(Vehicle("1005"), parkingLots)}
        )

        assertEquals(firstVehicle, driver.take(firstReceipt, parkingLots[0]))
        assertEquals(lastVehicle, driver.take(lastReceipt, parkingLots[1]))
    }
}
