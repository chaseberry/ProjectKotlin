package ChipsChallenge.Editor.PalletPanes

import ChipsChallenge.Editor.EditorPallet
import ChipsChallenge.Editor.PalletStatus
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.Point
import ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Engine.unitFromId
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JPanel

/**
 * Created by chase on 2/27/15.
 */
class UnitPanel(val editorPallet: EditorPallet) : JPanel() {

    val buttons = Array(7) { unitFromId(it, Point(0, 0), Direction.UP) };

    init {
        setLayout(null)
        setSize(buttons.size() % editorPallet.gridSize * 32, buttons.size() / editorPallet.gridSize * 32)
        for (z in 0..buttons.size() - 1) {
            add(createButton(buttons[z]!!, z))
        }
        setVisible(true)
    }

    fun createButton(unit: UnitBase, x: Int): JButton {
        val button = JButton()
        button.setLocation((x % editorPallet.gridSize) * 32, (x / editorPallet.gridSize) * 32)
        button.setSize(32, 32)
        button.setContentAreaFilled(false)
        button.setBorder(null)
        button.setIcon(ImageIcon(unit.image))
        button.addActionListener {
            editorPallet.currentUnit = unit
            editorPallet.palletStatus = PalletStatus.UNIT
        }
        return button
    }
}