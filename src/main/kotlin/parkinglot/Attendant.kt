package parkinglot

class Attendant (var name: String){

    fun park(vehicle: Vehicle, parkingLots: List<ParkingLot>): Receipt {
      return  parkingLots[0].park(vehicle, this.name)
    }

    fun take(receipt: Receipt, parkingLot: ParkingLot) {
        return
    }

}