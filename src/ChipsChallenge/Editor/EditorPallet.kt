package ChipsChallenge.Editor

import ChipsChallenge.Map.Tiles.Floor
import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Map.Tiles.Wall
import javax.swing.JFrame
import javax.swing.WindowConstants
import ChipsChallenge.Editor.PalletPanes.TilePanel
import ChipsChallenge.Map.Tile

/**
 * Created by chase on 2/27/15.
 */
class EditorPallet : JFrame() {

    val tilePanel = TilePanel(this);

    {
        pack()
        setSize(getInsets().left + (32 * 5) + getInsets().right, getInsets().top + (32 * 5) + getInsets().bottom)
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE)
        tilePanel.setLocation(getInsets().left, getInsets().top)
        add(tilePanel)
    }

    var palletStatus = PalletStatus.TILE

    var currentTile: Tile = Wall()
    var currentObject: ObjectBase? = null

    val deleteTile: Tile = Floor()
    val deleteObject: ObjectBase? = null

}

public enum class PalletStatus {
    TILE; OBJECT; PLAYER;
}