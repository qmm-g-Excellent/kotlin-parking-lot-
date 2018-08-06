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

        val attendant = Attendant(drive.name)
        val receipt = attendant.park(vehicle, parkingLots)
        val actualVehicle = drive.take(receipt, parkingLots[0])

        assertEquals(vehicle, actualVehicle)
    }
}