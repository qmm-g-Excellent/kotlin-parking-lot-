package parkinglot

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class AttendantTest {

    @Test
    fun `attendant parking a vehicle to a parking lot in order and take it out by drive`() {
        val parkingLot = ParkingLot(ArrayList(2))

        val attendant = Attendant("Xiao Li")
        val drive = Driver("Xiao Zhang")
        val vehicle = Vehicle("100")
        val parkingLots = arrayListOf(parkingLot)

        val receipt = attendant.park(vehicle, parkingLots)
        val actualVehicle = drive.take(receipt, parkingLots[0])

        assertEquals(vehicle, actualVehicle)
    }

    @Test
    fun `attendant parking three vehicles to two parking lots in order and take third vehicle out from second parking lot by drive`() {
        val firstParkingLot = ParkingLot(ArrayList(2))
        val secondParkingLot = ParkingLot(ArrayList(2))

        val drive = Driver("Xiao Zhang")
        val attendant = Attendant("Xiao Hu")

        val firstVehicle = Vehicle("100")
        val secondVehicle = Vehicle("101")
        val thirdVehicle = Vehicle("102")

        val parkingLots = arrayListOf(firstParkingLot, secondParkingLot)

        val firstReceipt = attendant.park(firstVehicle, parkingLots)
                          attendant.park(secondVehicle, parkingLots)
        val thirdReceipt = attendant.park(thirdVehicle, parkingLots)

        val actualFirstVehicle = drive.take(firstReceipt, parkingLots[0])
        val actualThirdVehicle = drive.take(thirdReceipt, parkingLots[1])

        assertEquals(firstVehicle, actualFirstVehicle)
        assertEquals(thirdVehicle, actualThirdVehicle)
    }

    @Test
    fun `attendant parking two vehicles to two parking lots in order and take second vehicle out from second parking lot by drive`() {
        val firstParkingLot = ParkingLot(ArrayList(2))
        val secondParkingLot = ParkingLot(ArrayList(2))

        val drive = Driver("Xiao Zhang")
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

        assertEquals(firstVehicle, drive.take(firstReceipt, parkingLots[0]))
        assertEquals(lastVehicle, drive.take(lastReceipt, parkingLots[1]))
    }

    @Test
    fun `drive parking a vehicle to a parking lot in order and take it out by attendant`() {
        val parkingLot = ParkingLot(ArrayList(2))

        val attendant = Attendant("Xiao Li")
        val drive = Driver("Xiao Zhang")
        val vehicle = Vehicle("100")
        val parkingLots = arrayListOf(parkingLot)

        val receipt = drive.park(vehicle, parkingLots)
        val actualVehicle = attendant.take(receipt, parkingLots[0])


        assertEquals(vehicle, actualVehicle)
    }

}
