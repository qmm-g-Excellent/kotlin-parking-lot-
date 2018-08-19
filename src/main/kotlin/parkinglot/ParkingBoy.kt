package parkinglot

import java.util.*
import kotlin.collections.HashMap

class ParkingBoy(
        private val name: String,
        private vararg val parkingLots: ParkingLot
) {
    fun parkByInOrder(vehicle: Vehicle): Receipt? {
        val receipt = parkingLots.firstOrNull { !it.isFull() }?.park(vehicle, name)
        if (Objects.isNull(receipt)) {
            throw Exception("All parking lots are full now.")
        }
        return receipt
    }

    fun parkByMaxEmptySpace(vehicle: Vehicle): Receipt? {
        var emptySpacesMap = HashMap<ParkingLot, Int>()
        parkingLots.forEach {
            if (!it.isFull()) {
                emptySpacesMap[it] = it.countEmptySpace()
            }
        }

        if (emptySpacesMap.isEmpty()) {
            throw Exception("All parking lots are full now.")
        }

        return emptySpacesMap.toList()
                .sortedBy { (key, value) -> -value }
                .first().first.park(vehicle, name)
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