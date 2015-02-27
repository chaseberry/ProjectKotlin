package ChipsChallenge.Editor.PalletPanes

import javax.swing.JPanel
import javax.swing.JButton
import javax.swing.ImageIcon
import ChipsChallenge.Editor.EditorPallet
import ChipsChallenge.Engine.objectFromId
import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Editor.PalletStatus

/**
 * Created by chase on 2/27/15.
 */
class ObjectPanel(val editorPallet: EditorPallet) : JPanel() {

    val buttons = Array(10) { objectFromId(it, Point(0, 0)) };
    val gridSize = 5

    {
        setLayout(null)
        setSize(buttons.size() % gridSize * 32, buttons.size() / gridSize * 32)
        for (z in 0..buttons.size() - 1) {
            if (buttons[z] != null) {
                add(createButton(buttons[z] as ObjectBase, z))
            }
        }
        setVisible(true)
    }

    fun createButton(obj: ObjectBase, x: Int): JButton {
        val button = JButton()
        button.setLocation((x % gridSize) * 32, (x / gridSize) * 32)
        button.setSize(32, 32)
        button.setContentAreaFilled(false)
        button.setBorder(null)
        button.setIcon(ImageIcon(obj.image))
        button.addActionListener {
            editorPallet.currentObject = obj
            editorPallet.palletStatus = PalletStatus.OBJECT
        }
        return button
    }
}