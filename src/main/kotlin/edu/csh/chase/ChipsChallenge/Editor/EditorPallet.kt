package ChipsChallenge.Editor

import ChipsChallenge.Editor.PalletPanes.*
import edu.csh.chase.ChipsChallenge.Engine.ObjectBase
import edu.csh.chase.ChipsChallenge.Engine.Point
import edu.csh.chase.ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Map.Tile
import ChipsChallenge.Map.Tiles.Floor
import ChipsChallenge.Map.Tiles.Wall
import javax.swing.JFrame
import javax.swing.JTabbedPane
import javax.swing.WindowConstants

class EditorPallet : JFrame() {

    val gridSize = 6

    val inspectPanel = InspectionPanel(this)

    init {
        pack()
        setSize(insets.left + (32 * 6) + insets.right + 32, insets.top + (32 * 6) + insets.bottom + 32)
        defaultCloseOperation = WindowConstants.DO_NOTHING_ON_CLOSE
        setLocation(400, 200)
        val tabs = JTabbedPane()
        tabs.border = null
        tabs.setLocation(insets.left, insets.top)
        tabs.add("Tiles", TilePanel(this))
        tabs.add("Objects", ObjectPanel(this))
        tabs.add("Player", PlayerLocationPanel(this))
        tabs.add("Enemies", UnitPanel(this))
        tabs.add("Inspect", inspectPanel)
        tabs.addChangeListener() {
            if (tabs.selectedIndex == 0) {
                palletStatus = PalletStatus.TILE
            } else if (tabs.selectedIndex == 1) {
                palletStatus = PalletStatus.OBJECT
            } else if (tabs.selectedIndex == 2) {
                palletStatus = PalletStatus.PLAYER
            } else if (tabs.selectedIndex == 3) {
                palletStatus = PalletStatus.UNIT
            }
        }
        add(tabs)
    }

    var palletStatus = PalletStatus.TILE

    var currentTile: Tile = Wall(Point(0, 0))
    var currentObject: ObjectBase? = null
    var currentUnit: UnitBase? = null//Not changing!

    val deleteTile: Tile = Floor(Point(0, 0))

    var buttonObject: ObjectBase? = null

}

enum class PalletStatus {
    TILE,
    OBJECT,
    PLAYER,
    TRIGGER,
    UNIT
}