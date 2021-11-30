package world.cepi.kepi.item

sealed class CreationalItemResult {

    object MainHandItemResult : CreationalItemResult()

    class InInventoryItemResult(val slot: Int) : CreationalItemResult()

    object CouldNotPut : CreationalItemResult()

}