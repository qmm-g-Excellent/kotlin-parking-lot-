package parkinglot

data class Receipt(private val licenseNo: String, private val driverName: String) {
    fun match(licenseNo: String) = licenseNo == this.licenseNo
}