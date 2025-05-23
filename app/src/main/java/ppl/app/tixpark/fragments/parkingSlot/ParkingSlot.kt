package ppl.app.tixpark.fragments.parkingSlot

data class ParkingSlot(
    val id: String,
    val name: String,
    var status: SlotStatus = SlotStatus.AVAILABLE
)

enum class SlotStatus {
    AVAILABLE,
    UNAVAILABLE,
    SELECTED
}