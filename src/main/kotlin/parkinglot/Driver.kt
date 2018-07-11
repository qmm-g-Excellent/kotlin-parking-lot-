package parkinglot

class Driver {

    lateinit var name: String

    fun park(vehicle: Vehicle, parkingLot: ParkingLot) {
        parkingLot.vehicle = vehicle

    }

    fun take(parkingLot: ParkingLot): Vehicle? {
        return parkingLot.vehicle
    }

}