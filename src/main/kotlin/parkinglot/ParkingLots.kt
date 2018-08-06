package parkinglot

class ParkingLots(parkingLots: ArrayList<ParkingLot>) {

    private val parkingLots = parkingLots

    fun park(vehicle :Vehicle, driverName: String): Receipt {
        for (parkingLot in parkingLots) {
            if (!parkingLot.isFull()) {
                return  parkingLot.park(vehicle, driverName)
            }
            if (parkingLots.indexOf(parkingLot) == parkingLots.size) {
                continue
            }
        }
        throw Exception("All parking lots are full now.")
    }

}