package parkinglot

class Attendant (var name: String){

    fun park(vehicle: Vehicle, parkingLots: List<ParkingLot>): Receipt {
        for (parkingLot in parkingLots) {
            if (!parkingLot.isFull()) {
                return  parkingLot.park(vehicle, this.name)
            }
            if (parkingLots.indexOf(parkingLot) == parkingLots.size) {
                continue
            }
        }
        throw Exception("All parking lots are full now.")
    }

    fun take(receipt: Receipt, parkingLot: ParkingLot) = parkingLot.take(receipt, this.name)

}