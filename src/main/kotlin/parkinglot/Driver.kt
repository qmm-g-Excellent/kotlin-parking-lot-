package parkinglot

class Driver(var name: String) {

    fun park(vehicle: Vehicle, parkingLot: ParkingLot): String {
        parkingLot.vehicles.add(vehicle)
        return vehicle.licenseNo
    }

    fun take(receipt: String, parkingLot: ParkingLot): Vehicle {
        return parkingLot.vehicles.first { v -> v.licenseNo == receipt }
    }

}