package ChipsChallenge.Unit

/**
 * Created by chase on 2/27/15.
 */
data class PlayerInventory(var hasFireBoots: Boolean = false, var hasIceSkates: Boolean = false,
                           var hasFlippers: Boolean = false, var hasScutionBoots: Boolean = false,
                           var redKeys: Int = 0, var blueKeys: Int = 0, var yellowKeys: Int = 0,
                           var hasGreenKey: Boolean = false, var chipsCollected: Int = 0)