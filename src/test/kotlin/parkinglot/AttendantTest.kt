package parkinglot

import org.junit.Test
import kotlin.test.assertEquals

class AttendantTest {

    @Test
    fun `attendant parking a vehicle to a parking lot in order and take it out by drive`() {

        val drive = Driver("Xiao Zhang")
        val vehicle = Vehicle("100")
        val vehicles = ArrayList<Vehicle>(2)
        val parkingLot = ParkingLot(vehicles)

        val parkingLots = arrayListOf(parkingLot)

        val attendant = Attendant("Xiao Li")
        val receipt = attendant.park(vehicle, parkingLots)
        val actualVehicle = drive.take(receipt, parkingLots[0])

        assertEquals(vehicle, actualVehicle)
    }

    @Test
    fun `attendant parking two vehicle to a parking lot in order and take it out by drive`() {

        val drive = Driver("Xiao Zhang")
        val firstVehicle = Vehicle("100")
        val secondVehicle = Vehicle("101")
        val vehicles = ArrayList<Vehicle>(2)
        val parkingLot = ParkingLot(vehicles)

        val parkingLots = arrayListOf(parkingLot)

        val attendant = Attendant("Xiao Hu")
        val firstReceipt = attendant.park(firstVehicle, parkingLots)
        val secondReceipt = attendant.park(secondVehicle, parkingLots)
        val actualFirstVehicle = drive.take(firstReceipt, parkingLots[0])
        val actualSecondVehicle = drive.take(secondReceipt, parkingLots[0])

        assertEquals(firstVehicle, actualFirstVehicle)
        assertEquals(secondVehicle, actualSecondVehicle)
    }
}