package parkinglot

import java.util.*
import kotlin.NoSuchElementException

class Driver(var name: String) {

    fun park(vehicle: Vehicle, parkingLot: ParkingLot): Receipt {
        if (isFull(parkingLot)) {
            if (parkingLot.vehicles.contains(vehicle)) {
                throw IllegalArgumentException("Such vehicle has existed.")
            }
            parkingLot.vehicles.add(vehicle)
        } else {
            throw Exception("Parking lot is full now.")
        }

        return Receipt(vehicle.licenseNo, this.name)
    }

    fun take(receipt: Receipt, parkingLot: ParkingLot): Vehicle? {
        val vehicle = parkingLot.vehicles.firstOrNull { v ->
            v.licenseNo.equals(receipt.licenseNo) && this.name.equals(receipt.driverName)
        }
        if (Objects.isNull(vehicle)) {
            throw NoSuchElementException("No such vehicle in parkingLot.")
        }
        return vehicle
    }

    private fun isFull(parkingLot: ParkingLot) = parkingLot.vehicles.size < ParkingLot.constant.OVERSIZE
}