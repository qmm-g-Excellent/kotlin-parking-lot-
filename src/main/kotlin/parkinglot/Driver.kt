package parkinglot

import java.util.*
import kotlin.NoSuchElementException

class Driver(var name: String) {

    fun park(vehicle: Vehicle, parkingLot: ParkingLot): String {
        if (parkingLot.vehicles.size < 10) {
            if (parkingLot.vehicles.contains(vehicle)) {
                throw IllegalArgumentException("No such vehicle in parkingLot.")
            }
            parkingLot.vehicles.add(vehicle)
        } else {
            throw Exception("Parking lot is full now.")
        }

        return vehicle.licenseNo
    }

    fun take(licenseNo: String, parkingLot: ParkingLot): Vehicle? {
        val vehicle = parkingLot.vehicles.firstOrNull { v -> v.licenseNo == licenseNo }
        if (Objects.isNull(vehicle)) {
            throw NoSuchElementException("No such vehicle in parkingLot.")
        }
        return vehicle
    }

}