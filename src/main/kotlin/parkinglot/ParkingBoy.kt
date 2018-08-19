package parkinglot

import java.util.*

class ParkingBoy(
        private val name: String,
        private vararg val parkingLots: ParkingLot
) {
    fun park(vehicle: Vehicle): Receipt? {
        val receipt = parkingLots.firstOrNull { !it.isFull() }?.park(vehicle, name)
        if (Objects.isNull(receipt)) {
            throw Exception("All parking lots are full now.")
        }
        return receipt
    }

    fun take(receipt: Receipt): Pair<Int, Vehicle?> {
        var actualVehicle = Vehicle("")
        var parkingLotNum = -1
        for (parkingLot in parkingLots) {
            val vehicle = parkingLot.take(receipt)
            if (Objects.nonNull(vehicle)) {
                 parkingLotNum = parkingLots.indexOf(parkingLot)
                actualVehicle = vehicle!!
            }
        }
        return Pair(parkingLotNum, actualVehicle)

    }
}