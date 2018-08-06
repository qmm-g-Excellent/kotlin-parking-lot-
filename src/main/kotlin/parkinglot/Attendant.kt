package parkinglot

class Attendant (var name: String){

    fun park(vehicle: Vehicle, parkingLots: ParkingLots) = parkingLots.park(vehicle,this.name)

    fun take(receipt: Receipt, parkingLot: ParkingLot) = parkingLot.take(receipt)
}