package edu.csh.chase.ChipsChallenge.UI

import edu.csh.chase.ChipsChallenge.UI.KeyListener
import edu.csh.chase.ChipsChallenge.Engine.Engine
import java.awt.Graphics
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.awt.image.BufferedImage
import javax.swing.JFrame
import javax.swing.WindowConstants

class Frame(val engine: Engine) : JFrame() {

    val keyListener = KeyListener(this);

    init {
        pack()
        setSize(insets.left + (32 * 9) + insets.right, insets.top + (32 * 9) + insets.bottom)
        defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        addKeyListener(keyListener)
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent) {
                println("Closed")
                engine.gameOver()
            }
        });
    }

    var image: BufferedImage? = null
        set(newImage) {
            field = newImage
            repaint()
        }

    fun keyPressed(keyCode: Int) {
        engine.keyPressed(keyCode)
    }

    fun keyReleased(keyCode: Int) {
        engine.keyReleased(keyCode)
    }

    override fun paint(graphics: Graphics) {
        super.paint(graphics)
        graphics.clearRect(0, 0, width, height)
        if (image != null) {
            graphics.drawImage(image, insets.left, insets.top, null)
        }

    }


}