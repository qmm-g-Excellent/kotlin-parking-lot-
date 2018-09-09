package parkinglot

import java.util.*
import kotlin.collections.ArrayList

class ParkingLot(private val capacity: Int) {

    private val vehicles = ArrayList<Vehicle>(capacity)

    fun park(vehicle: Vehicle, driverName: String): Receipt {
        if (isFull()) {
            throw Exception("Parking lot is full now.")
        } else {
            if (vehicles.contains(vehicle)) {
                throw IllegalArgumentException("Such vehicle has existed.")
            }
            vehicles.add(vehicle)
            return Receipt(vehicle.licenseNo, driverName)
        }
    }

    fun take(receipt: Receipt): Vehicle? {
        val vehicle = vehicles.firstOrNull { v ->
            receipt.match(v.licenseNo)
        }
        if (Objects.nonNull(vehicle)) {
            vehicles.remove(vehicle)
        }
        return vehicle
    }

    fun isFull() = vehicles.size == capacity

    fun countEmptySpace() = capacity - vehicles.size

    fun countEmptySpaceRate() = countEmptySpace().toDouble() / capacity
}
