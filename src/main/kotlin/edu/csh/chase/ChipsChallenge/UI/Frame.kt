package edu.csh.chase.ChipsChallenge.UI

import edu.csh.chase.ChipsChallenge.Engine.DisplayPanel
import edu.csh.chase.ChipsChallenge.Engine.Engine
import java.awt.Graphics
import java.awt.event.WindowAdapter
import java.awt.event.WindowEvent
import java.awt.image.BufferedImage
import javax.swing.JFrame
import javax.swing.WindowConstants

class Frame(val engine: Engine) : JFrame() {

    val keyListener = KeyListener(this);

    val panel = DisplayPanel()

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
        })
        add(panel)
    }

    var image: BufferedImage? = null
        set(newImage) {
            panel.image = newImage
            repaint()
        }

    fun keyPressed(keyCode: Int) {
        engine.keyPressed(keyCode)
    }

    fun keyReleased(keyCode: Int) {
        engine.keyReleased(keyCode)
    }

}