package parkinglot

import java.util.*
import kotlin.NoSuchElementException

class Driver(var name: String) {

    fun park(vehicle: Vehicle, parkingLot: ParkingLot): String {
        parkingLot.vehicles.add(vehicle)
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