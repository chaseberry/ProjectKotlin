package edu.csh.chase.ChipsChallenge.Editor.PalletPanes

import edu.csh.chase.ChipsChallenge.Editor.EditorPallet
import edu.csh.chase.ChipsChallenge.Editor.PalletStatus
import edu.csh.chase.ChipsChallenge.Engine.*
import javax.swing.ImageIcon
import javax.swing.JButton
import javax.swing.JPanel

/**
 * Created by chase on 2/27/15.
 */
class ObjectPanel(val editorPallet: EditorPallet) : JPanel() {

    val buttons = Array(23) { objectFromTypeIdWithId(it, Point(0, 0), Id(IdType.OBJECT, -1)) };

    init {
        layout = null
        setSize(buttons.size % editorPallet.gridSize * 32, buttons.size / editorPallet.gridSize * 32)
        for (z in 0..buttons.size - 1) {
            if (buttons[z] != null) {
                add(createButton(buttons[z] as ObjectBase, z))
            }
        }
        isVisible = true
    }

    fun createButton(obj: ObjectBase, x: Int): JButton {
        val button = JButton()
        button.setLocation((x % editorPallet.gridSize) * 32, (x / editorPallet.gridSize) * 32)
        button.setSize(32, 32)
        button.isContentAreaFilled = false
        button.border = null
        button.icon = ImageIcon(obj.image)
        button.addActionListener {
            editorPallet.currentObject = obj
            editorPallet.palletStatus = PalletStatus.OBJECT
        }
        return button
    }
}