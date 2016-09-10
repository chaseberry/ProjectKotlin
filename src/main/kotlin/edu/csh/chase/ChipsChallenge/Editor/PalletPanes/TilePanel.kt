package edu.csh.chase.ChipsChallenge.Editor.PalletPanes

import ChipsChallenge.Editor.EditorPallet
import ChipsChallenge.Editor.PalletStatus
import edu.csh.chase.ChipsChallenge.Engine.Point
import edu.csh.chase.ChipsChallenge.Map.Tile
import ChipsChallenge.Map.Tiles.Revealable
import edu.csh.chase.ChipsChallenge.Map.tileIdToTile
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JPanel

class TilePanel(val editorPallet: EditorPallet) : JPanel() {

    val buttons = Array(25) { tileIdToTile(it, Point(0, 0)) };

    init {
        layout = null
        setSize(buttons.size % editorPallet.gridSize * 32, buttons.size / editorPallet.gridSize * 32)
        for (z in 0..buttons.size - 1) {
            add(createButton(buttons[z], z))
        }
        isVisible = true
    }

    fun createButton(tile: Tile, x: Int): JButton {
        val button = JButton()
        button.setLocation((x % editorPallet.gridSize) * 32, (x / editorPallet.gridSize) * 32)
        button.setSize(32, 32)
        button.isContentAreaFilled = false
        button.border = null
        if (tile is Revealable) {
            button.icon = ImageIcon(tile.getEditorImage())
        } else {
            button.icon = ImageIcon(tile.image!!)
        }
        button.addActionListener {
            editorPallet.currentTile = tile
            editorPallet.palletStatus = PalletStatus.TILE
        }
        return button
    }
}