package ChipsChallenge.Editor

import ChipsChallenge.Map.Tiles.Floor
import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Map.Tiles.Wall
import javax.swing.JFrame
import javax.swing.WindowConstants
import ChipsChallenge.Map.Tile
import javax.swing.JTabbedPane
import ChipsChallenge.Editor.PalletPanes.TilePanel
import ChipsChallenge.Editor.PalletPanes.ObjectPanel
import ChipsChallenge.Editor.PalletPanes.PlayerLocationPanel
import ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Editor.PalletPanes.UnitPanel

/**
 * Created by chase on 2/27/15.
 */
class EditorPallet : JFrame() {

    val gridSize = 6

    {
        pack()
        setSize(getInsets().left + (32 * 6) + getInsets().right + 32, getInsets().top + (32 * 5) + getInsets().bottom + 32)
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE)
        setLocation(400, 200)
        val tabs = JTabbedPane()
        tabs.setBorder(null)
        tabs.setLocation(getInsets().left, getInsets().top)
        tabs.add("Tiles", TilePanel(this))
        tabs.add("Objects", ObjectPanel(this))
        tabs.add("Player", PlayerLocationPanel(this))
        tabs.add("Enemies", UnitPanel(this))
        tabs.addChangeListener() {
            if (tabs.getSelectedIndex() == 0) {
                palletStatus = PalletStatus.TILE
            } else if (tabs.getSelectedIndex() == 1) {
                palletStatus = PalletStatus.OBJECT
            } else if (tabs.getSelectedIndex() == 2) {
                palletStatus = PalletStatus.PLAYER
            } else if (tabs.getSelectedIndex() == 3) {
                palletStatus = PalletStatus.UNIT
            }
        }
        add(tabs)
    }

    var palletStatus = PalletStatus.TILE

    var currentTile: Tile = Wall()
    var currentObject: ObjectBase? = null
    var currentUnit: UnitBase? = null

    val deleteTile: Tile = Floor()
    val deleteObject: ObjectBase? = null
    val deleteUnit: UnitBase? = null

    var buttonObject: ObjectBase? = null

}

public enum class PalletStatus {
    TILE
    OBJECT
    PLAYER
    TRIGGER
    UNIT
}