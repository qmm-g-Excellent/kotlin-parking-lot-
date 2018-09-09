package parkinglot

class ParkingBoy(
        private val name: String,
        private vararg val parkingLots: ParkingLot
) {
    fun parkByInOrder(vehicle: Vehicle) =
            parkingLots.firstOrNull { !it.isFull() }?.park(vehicle, name)
                    ?: throw Exception("All parking lots are full now.")

    fun parkByMaxEmptySpace(vehicle: Vehicle) =
            parkingLots.maxBy { it.countEmptySpace() }?.park(vehicle, name)
                    ?: throw Exception("No parking lot found.")

    fun parkByMaxEmptySpaceRate(vehicle: Vehicle) =
            parkingLots.maxBy { it.countEmptySpaceRate() }?.park(vehicle, name)
                    ?: throw Exception("No parking lot.")

    fun take(receipt: Receipt) =
            parkingLots.foldIndexed(Pair<Int, Vehicle?>(-1, null)) { i, pair, parkingLot ->
                val vehicle = parkingLot.take(receipt)
                if (vehicle != null) return Pair(i, vehicle) else return@foldIndexed pair
            }
}