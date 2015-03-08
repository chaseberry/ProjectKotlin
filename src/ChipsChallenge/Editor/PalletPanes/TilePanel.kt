package ChipsChallenge.Editor.PalletPanes

import javax.swing.JPanel
import ChipsChallenge.Map.tileIdToTile
import javax.swing.JButton
import ChipsChallenge.Map.Tile
import javax.swing.ImageIcon
import ChipsChallenge.Editor.EditorPallet
import ChipsChallenge.Editor.PalletStatus
import ChipsChallenge.Engine.Point

/**
 * Created by chase on 2/27/15.
 */
class TilePanel(val editorPallet: EditorPallet) : JPanel() {

    val buttons = Array(6) { tileIdToTile(it, Point(0, 0)) };

    {
        setLayout(null)
        setSize(buttons.size() % editorPallet.gridSize * 32, buttons.size() / editorPallet.gridSize * 32)
        for (z in 0..buttons.size() - 1) {
            add(createButton(buttons[z], z))
        }
        setVisible(true)
    }

    fun createButton(tile: Tile, x: Int): JButton {
        val button = JButton()
        button.setLocation((x % editorPallet.gridSize) * 32, (x / editorPallet.gridSize) * 32)
        button.setSize(32, 32)
        button.setContentAreaFilled(false)
        button.setBorder(null)
        button.setIcon(ImageIcon(tile.image))
        button.addActionListener {
            editorPallet.currentTile = tile
            editorPallet.palletStatus = PalletStatus.TILE
        }
        return button
    }
}