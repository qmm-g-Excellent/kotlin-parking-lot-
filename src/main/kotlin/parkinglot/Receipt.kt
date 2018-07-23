package parkinglot

data class Receipt(private val licenseNo: String, private val driverName: String) {
    fun match(licenseNo: String, driverName: String) = licenseNo == this.licenseNo && driverName == this.driverName
}