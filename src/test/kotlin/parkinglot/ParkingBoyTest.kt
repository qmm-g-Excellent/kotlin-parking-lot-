package parkinglot

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class ParkingBoyTest {

    /**
     * parking vehicle into parking lot in order
     */

    @Test
    fun `parking boy parking a vehicle to a parking lot in oreder and take it out by parking boy`() {
        var parkingLot = ParkingLot(ArrayList(1))
        var parkingBoy = ParkingBoy("Xiao Ming", parkingLot)
        var vehicle = Vehicle("100")
        var receipt = parkingBoy.parkByInOrder(vehicle)
        val (parkingLotNum, actualVehicle) = parkingBoy.take(receipt!!)
        assertEquals(vehicle, actualVehicle)
        assertEquals(0, parkingLotNum)
    }

    @Test
    fun `parking boy parking three vehicles to two parking lots in order and take third vehicle out from second parking lot by parking boy`() {
        val firstParkingLot = ParkingLot(ArrayList(2))
        val secondParkingLot = ParkingLot(ArrayList(1))

        val parkingBoy = ParkingBoy("Xiao Hu", firstParkingLot, secondParkingLot)

        val firstVehicle = Vehicle("100")
        val secondVehicle = Vehicle("101")
        val thirdVehicle = Vehicle("102")

        val firstReceipt = parkingBoy.parkByInOrder(firstVehicle)
        parkingBoy.parkByInOrder(secondVehicle)
        val thirdReceipt = parkingBoy.parkByInOrder(thirdVehicle)

        val (firstParkingLotNum, actualFirstVehicle)   = parkingBoy.take(firstReceipt!!)
        val (secondParkingLotNum, actualThirdVehicle)   = parkingBoy.take(thirdReceipt!!)

        assertEquals(firstVehicle, actualFirstVehicle)
        assertEquals(0, firstParkingLotNum)
        assertEquals(thirdVehicle, actualThirdVehicle)
        assertEquals(1, secondParkingLotNum)
    }

    @Test
    fun `should throw error when park a vehicle by parking boy while all parking lots are full`() {
        val firstParkingLot = ParkingLot(ArrayList(2))
        val secondParkingLot = ParkingLot(ArrayList(2))

        val parkingBoy = ParkingBoy("Xiao Hu", firstParkingLot,secondParkingLot)

        val firstVehicle = Vehicle("100")
        val lastVehicle = Vehicle("101")

        val firstReceipt = parkingBoy.parkByInOrder(firstVehicle)

        for (i in 2..3) {
            parkingBoy.parkByInOrder(Vehicle(i.toString()))
        }

        val lastReceipt = parkingBoy.parkByInOrder(lastVehicle)

        assertFailsWith(
                exceptionClass = Exception::class,
                message = "Parking lots are full now.",
                block = { parkingBoy.parkByInOrder(Vehicle("1005")) }
        )

        val (firstParkingLotNum, actualFirstVehicle)   = parkingBoy.take(firstReceipt!!)
        val (lastParkingLotNum, actualLastVehicle)   = parkingBoy.take(lastReceipt!!)

        assertEquals(firstVehicle, actualFirstVehicle )
        assertEquals(0, firstParkingLotNum )
        assertEquals(lastVehicle, actualLastVehicle)
        assertEquals(1, lastParkingLotNum)
    }

    @Test
    fun `parking boy parking a vehicle to a parking lot in order and take it out by driver`() {
        val parkingLot = ParkingLot(ArrayList(2))
        val parkingBoy = ParkingBoy("Xiao Li", parkingLot)
        val driver = Driver("Xiao Zhang")
        val vehicle = Vehicle("100")

        val receipt = parkingBoy.parkByInOrder(vehicle)

        assertEquals(vehicle, driver.take(receipt!!, parkingLot))
    }

    @Test
    fun `parking boy parking two vehicles to some parking lots with the most empty spaces and take it out by parling boy`() {
        val firstParkingLot = ParkingLot(ArrayList(2))
        val secondParkingLot = ParkingLot(ArrayList(2))
        val parkingBoy = ParkingBoy("Xiao Li", firstParkingLot, secondParkingLot)
        val firstVehicle = Vehicle("100")
        val secondVehicle = Vehicle("101")
        val firstReceipt = parkingBoy.parkByMaxEmptySpace(firstVehicle)
        val secondReceipt = parkingBoy.parkByMaxEmptySpace(secondVehicle)

        val (firstParkingLotNum, actualFirstVehicle) = parkingBoy.take(firstReceipt!!)
        val (secondParkingLotNum, actualSecondVehicle) = parkingBoy.take(secondReceipt!!)
        assertEquals(firstVehicle, actualFirstVehicle)
        assertEquals(0, firstParkingLotNum)
        assertEquals(secondVehicle, actualSecondVehicle)
        assertEquals(1, secondParkingLotNum)
    }


    @Test
    fun `parking boy parking three vehicles some parking lots with the most empty spaces and take it out by parling boy`() {
        val firstParkingLot = ParkingLot(ArrayList(2))
        val secondParkingLot = ParkingLot(ArrayList(2))
        val parkingBoy = ParkingBoy("Xiao Li", firstParkingLot, secondParkingLot)
        val firstVehicle = Vehicle("100")
        val secondVehicle = Vehicle("101")
        val thirdVehicle = Vehicle("103")
        val firstReceipt = parkingBoy.parkByMaxEmptySpace(firstVehicle)
        val secondReceipt = parkingBoy.parkByMaxEmptySpace(secondVehicle)
        val thirdReceipt = parkingBoy.parkByMaxEmptySpace(thirdVehicle)

        val (firstParkingLotNum, actualFirstVehicle) = parkingBoy.take(firstReceipt!!)
        val (secondParkingLotNum, actualSecondVehicle) = parkingBoy.take(secondReceipt!!)
        val (thirdParkingLotNum, actualThirdVehicle) = parkingBoy.take(thirdReceipt!!)
        assertEquals(firstVehicle, actualFirstVehicle)
        assertEquals(0, firstParkingLotNum)
        assertEquals(secondVehicle, actualSecondVehicle)
        assertEquals(1, secondParkingLotNum)
        assertEquals(thirdVehicle, actualThirdVehicle)
        assertEquals(0, thirdParkingLotNum)
    }
}
