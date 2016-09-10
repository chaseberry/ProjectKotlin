package edu.csh.chase.ChipsChallenge.Editor.PalletPanes

import javax.swing.JPanel
import javax.swing.JButton
import javax.swing.ImageIcon
import edu.csh.chase.ChipsChallenge.Editor.EditorPallet
import edu.csh.chase.ChipsChallenge.Editor.PalletStatus
import edu.csh.chase.ChipsChallenge.Engine.loadImage

/**
 * Created by chase on 2/27/15.
 */
class PlayerLocationPanel(val editorPallet: EditorPallet) : JPanel() {

    init {
        layout = null
        setSize(32, 32)
        add(createButton())
        isVisible = true
    }

    fun createButton(): JButton {
        val button = JButton()
        button.setLocation(0, 0)
        button.setSize(32, 32)
        button.isContentAreaFilled = false
        button.border = null
        button.icon = ImageIcon(loadImage("chip-south.gif"))
        button.addActionListener {
            editorPallet.palletStatus = PalletStatus.PLAYER
        }
        return button
    }
}