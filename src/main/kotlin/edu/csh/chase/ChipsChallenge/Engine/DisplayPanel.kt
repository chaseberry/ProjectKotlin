package edu.csh.chase.ChipsChallenge.Engine

import java.awt.Graphics
import java.awt.image.BufferedImage
import javax.swing.JFrame
import javax.swing.JPanel

class DisplayPanel : JPanel() {

    init {
        setLocation(0, 0)
        setSize(9 * 32, 9 * 32)
        isVisible = true
    }

    var image: BufferedImage? = null
        set(newImage) {
            field = newImage
        }

    override fun paintComponent(g: Graphics) {
        super.paintComponent(g)
        g.clearRect(0, 0, 9 * 32, 9 * 32)
        if (image != null) {
            g.drawImage(image, insets.left, insets.top, null)
        }
    }

}