package edu.csh.chase.ChipsChallenge.Editor

import java.awt.Graphics
import java.awt.image.BufferedImage
import javax.swing.JPanel

class EditorPanel : JPanel() {

    var image: BufferedImage? = null
        set(newImage) {
            field = newImage
            //repaint()
        }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.clearRect(0, 0, 9 * 32, 9 * 32)
        if (image != null) {
            g.drawImage(image, insets.left, insets.top, null)
        }
    }

}