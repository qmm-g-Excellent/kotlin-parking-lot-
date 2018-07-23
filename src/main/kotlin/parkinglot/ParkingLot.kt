package parkinglot

import java.util.*

class ParkingLot {

    private companion object Constant {
        const val MAX_SIZE = 10
    }

    private val vehicles = ArrayList<Vehicle>(Constant.MAX_SIZE)

    fun park(vehicle: Vehicle, driverName: String): Receipt {
        if (vehicles.size == MAX_SIZE) {
            throw Exception("Parking lot is full now.")
        } else {
            if (vehicles.contains(vehicle)) {
                throw IllegalArgumentException("Such vehicle has existed.")
            }
            vehicles.add(vehicle)
        }
        return Receipt(vehicle.licenseNo, driverName)
    }

    fun take(receipt: Receipt, driverName: String): Vehicle? {
        val vehicle = vehicles.firstOrNull { v ->
            receipt.match(v.licenseNo, driverName)
        }
        if (Objects.isNull(vehicle)) {
            throw NoSuchElementException("No such vehicle in parkingLot.")
        }
        vehicles.remove(vehicle)
        return vehicle
    }

}
