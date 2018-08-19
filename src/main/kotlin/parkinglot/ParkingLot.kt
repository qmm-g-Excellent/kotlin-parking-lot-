package parkinglot

import java.util.*

class ParkingLot(vehicles: MutableList<Vehicle>) {

    companion object Constant {
        const val MAX_SIZE = 2
    }

    private val vehicles = vehicles

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
//        if (Objects.isNull(vehicle)) {
//            throw NoSuchElementException("No such vehicle in parkingLot.")
//        }
        if (Objects.nonNull(vehicle)) {
            vehicles.remove(vehicle)
        }
        return vehicle
    }

    fun isFull() = vehicles.size == MAX_SIZE

    fun countEmptySpace() = MAX_SIZE - vehicles.size

    fun isEmpty() = vehicles.size == 0

}
