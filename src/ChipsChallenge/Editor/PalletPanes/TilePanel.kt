package ChipsChallenge.Editor.PalletPanes

import javax.swing.JPanel
import ChipsChallenge.Map.tileIdToTile
import javax.swing.JButton
import ChipsChallenge.Map.Tile
import javax.swing.ImageIcon
import ChipsChallenge.Editor.EditorPallet

/**
 * Created by chase on 2/27/15.
 */
class TilePanel(val editorPallet: EditorPallet) : JPanel() {

    val buttons = Array(2) { tileIdToTile(it) };
    val gridSize = 5

    {
        setLayout(null)
        setSize(buttons.size() % gridSize * 32, buttons.size() / gridSize * 32)
        for (z in 0..buttons.size() - 1) {
            add(createButton(buttons[z], z))
        }
        setVisible(true)
    }

    fun createButton(tile: Tile, x: Int): JButton {
        val button = JButton()
        button.setLocation((x % gridSize) * 32, (x / gridSize) * 32)
        button.setSize(32, 32)
        button.setContentAreaFilled(false)
        button.setBorder(null)
        button.setIcon(ImageIcon(tile.image))
        button.addActionListener { editorPallet.currentTile = tile }
        return button
    }
}