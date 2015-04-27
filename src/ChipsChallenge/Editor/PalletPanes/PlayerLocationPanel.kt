package ChipsChallenge.Editor.PalletPanes

import javax.swing.JPanel
import javax.swing.JButton
import javax.swing.ImageIcon
import ChipsChallenge.Editor.EditorPallet
import ChipsChallenge.Editor.PalletStatus
import ChipsChallenge.Engine.loadImage

/**
 * Created by chase on 2/27/15.
 */
class PlayerLocationPanel(val editorPallet: EditorPallet) : JPanel() {

    init {
        setLayout(null)
        setSize(32, 32)
        add(createButton())
        setVisible(true)
    }

    fun createButton(): JButton {
        val button = JButton()
        button.setLocation(0, 0)
        button.setSize(32, 32)
        button.setContentAreaFilled(false)
        button.setBorder(null)
        button.setIcon(ImageIcon(loadImage("chip-south.gif")))
        button.addActionListener {
            editorPallet.palletStatus = PalletStatus.PLAYER
        }
        return button
    }
}