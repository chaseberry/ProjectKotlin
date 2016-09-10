package edu.csh.chase.ChipsChallenge.Editor.PalletPanes

import ChipsChallenge.Editor.EditorPallet
import ChipsChallenge.Editor.PalletStatus
import edu.csh.chase.ChipsChallenge.Engine.Direction
import edu.csh.chase.ChipsChallenge.Engine.Point
import edu.csh.chase.ChipsChallenge.Engine.UnitBase
import edu.csh.chase.ChipsChallenge.Engine.unitFromId
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JPanel

/**
 * Created by chase on 2/27/15.
 */
class UnitPanel(val editorPallet: EditorPallet) : JPanel() {

    val buttons = Array(8) { unitFromId(it, Point(0, 0), Direction.UP) };

    init {
        layout = null
        setSize(buttons.size % editorPallet.gridSize * 32, buttons.size / editorPallet.gridSize * 32)
        for (z in 0..buttons.size - 1) {
            add(createButton(buttons[z]!!, z))
        }
        isVisible = true
    }

    fun createButton(unit: UnitBase, x: Int): JButton {
        val button = JButton()
        button.setLocation((x % editorPallet.gridSize) * 32, (x / editorPallet.gridSize) * 32)
        button.setSize(32, 32)
        button.isContentAreaFilled = false
        button.border = null
        button.icon = ImageIcon(unit.image)
        button.addActionListener {
            editorPallet.currentUnit = unit
            editorPallet.palletStatus = PalletStatus.UNIT
        }
        return button
    }
}