package ChipsChallenge.Editor

import ChipsChallenge.Map.Tiles.Floor
import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Map.Tiles.Wall

/**
 * Created by chase on 2/27/15.
 */
class EditorPallet {

    var palletStatus = PalletStatus.TILE

    val currentTile = Wall()
    val currentObject: ObjectBase? = null

    val deleteTile = Floor()
    val deleteObject: ObjectBase? = null

}

public enum class PalletStatus {
    TILE; OBJECT; PLAYER;
}