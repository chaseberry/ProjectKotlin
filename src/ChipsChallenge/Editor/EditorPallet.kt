package ChipsChallenge.Editor

import ChipsChallenge.Map.Tile
import ChipsChallenge.Map.Tiles.Floor
import ChipsChallenge.Engine.ObjectBase

/**
 * Created by chase on 2/27/15.
 */
class EditorPallet {

    var palletStatus = PalletStatus.TILE

    val currentTile: Tile = Floor()
    val currentObject: ObjectBase? = null


}

public enum class PalletStatus {
    TILE; OBJECT; PLAYER;
}