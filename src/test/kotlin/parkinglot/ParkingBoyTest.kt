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
        val parkingLot = ParkingLot(1)
        val parkingBoy = ParkingBoy("Xiao Ming", parkingLot)
        val vehicle = Vehicle("100")
        val receipt = parkingBoy.parkByInOrder(vehicle)!!
        val actualVehicle = parkingLot.take(receipt)
        assertEquals(vehicle, actualVehicle)
    }

    @Test
    fun `parking boy parking three vehicles to two parking lots in order and take third vehicle out from second parking lot by parking boy`() {
        val firstParkingLot = ParkingLot(2)
        val secondParkingLot = ParkingLot(2)

        val parkingBoy = ParkingBoy("Xiao Hu", firstParkingLot, secondParkingLot)

        val firstVehicle = Vehicle("100")
        val secondVehicle = Vehicle("101")
        val thirdVehicle = Vehicle("102")

        val firstReceipt = parkingBoy.parkByInOrder(firstVehicle)!!
        parkingBoy.parkByInOrder(secondVehicle)
        val thirdReceipt = parkingBoy.parkByInOrder(thirdVehicle)!!

        val actualFirstVehicle = firstParkingLot.take(firstReceipt)
        val actualThirdVehicle = secondParkingLot.take(thirdReceipt)

        assertEquals(firstVehicle, actualFirstVehicle)
        assertEquals(thirdVehicle, actualThirdVehicle)
    }

    @Test
    fun `should throw error when park a vehicle by parking boy while all parking lots are full`() {
        val firstParkingLot = ParkingLot(2)
        val secondParkingLot = ParkingLot(2)

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
        val parkingLot = ParkingLot(2)
        val parkingBoy = ParkingBoy("Xiao Li", parkingLot)
        val driver = Driver("Xiao Zhang")
        val vehicle = Vehicle("100")

        val receipt = parkingBoy.parkByInOrder(vehicle)

        assertEquals(vehicle, driver.take(receipt!!, parkingLot))
    }

    @Test
    fun `parking boy parking two vehicles to some parking lots with the max empty spaces and take it out by parling boy`() {
        val firstParkingLot = ParkingLot(2)
        val secondParkingLot = ParkingLot(2)
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
    fun `parking boy parking three vehicles some parking lots with the max empty spaces and take it out by parling boy`() {
        val firstParkingLot = ParkingLot(2)
        val secondParkingLot = ParkingLot(2)
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

    @Test
    fun `should throw error when park a vehicle to parking lots with the max empty spaces while all parking lots are full`() {
        val firstParkingLot = ParkingLot(2)
        val secondParkingLot = ParkingLot(2)
        val parkingBoy = ParkingBoy("Xiao Li", firstParkingLot, secondParkingLot)

        for (i in 1..4) {
            parkingBoy.parkByMaxEmptySpace(Vehicle(i.toString()))
        }

        assertFailsWith(
                exceptionClass = Exception::class,
                message = "Parking lots are full now.",
                block = { parkingBoy.parkByMaxEmptySpace(Vehicle("1005")) }
        )
    }

    @Test
    fun `parking boy parking two vehicles to some parking lots with the max empty space rate and take it out by parking boy`() {
        val firstParkingLot = ParkingLot(2)
        val secondParkingLot = ParkingLot(2)
        val parkingBoy = ParkingBoy("Xiao Li", firstParkingLot, secondParkingLot)
        val firstVehicle = Vehicle("100")
        val secondVehicle = Vehicle("101")
        val firstReceipt = parkingBoy.parkByMaxEmptySpaceRate(firstVehicle)
        val secondReceipt = parkingBoy.parkByMaxEmptySpaceRate(secondVehicle)

        val (firstParkingLotNum, actualFirstVehicle) = parkingBoy.take(firstReceipt!!)
        val (secondParkingLotNum, actualSecondVehicle) = parkingBoy.take(secondReceipt!!)
        assertEquals(firstVehicle, actualFirstVehicle)
        assertEquals(0, firstParkingLotNum)
        assertEquals(secondVehicle, actualSecondVehicle)
        assertEquals(1, secondParkingLotNum)
    }

}
