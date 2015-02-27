package ChipsChallenge.Editor

import ChipsChallenge.Map.Tiles.Floor
import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Map.Tiles.Wall
import javax.swing.JFrame
import javax.swing.WindowConstants

/**
 * Created by chase on 2/27/15.
 */
class EditorPallet : JFrame() {

    {
        pack()
        setSize(getInsets().left + (32 * 5) + getInsets().right, getInsets().top + (32 * 5) + getInsets().bottom)
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE)
    }

    var palletStatus = PalletStatus.TILE

    val currentTile = Wall()
    val currentObject: ObjectBase? = null

    val deleteTile = Floor()
    val deleteObject: ObjectBase? = null

}

public enum class PalletStatus {
    TILE; OBJECT; PLAYER;
}