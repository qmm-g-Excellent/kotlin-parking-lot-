package parkinglot

class Driver(var name: String) {

    fun park(vehicle: Vehicle, parkingLot: ParkingLot) = parkingLot.park(vehicle, this.name)

    fun take(receipt: Receipt, parkingLot: ParkingLot) = parkingLot.take(receipt, this.name)

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

}