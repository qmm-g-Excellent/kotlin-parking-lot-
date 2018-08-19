package parkinglot

class Driver(var name: String) {

    fun park(vehicle: Vehicle, parkingLot: ParkingLot) = parkingLot.park(vehicle, this.name)

    fun take(receipt: Receipt, parkingLot: ParkingLot) = parkingLot.take(receipt)
}