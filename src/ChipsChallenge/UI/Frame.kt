package ChipsChallenge.UI

import ChipsChallenge.Engine.Engine
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
        setSize(getInsets().left + (32 * 9) + getInsets().right, getInsets().top + (32 * 9) + getInsets().bottom)
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE)
        addKeyListener(keyListener)
        addWindowListener(object : WindowAdapter() {
            override fun windowClosing(e: WindowEvent) {
                println("Closed")
                engine.gameOver()
            }
        });
    }

    public var image: BufferedImage? = null
        set(newImage) {
            $image = newImage
            repaint()
        }

    public fun keyPressed(keyCode: Int) {
        engine.keyPressed(keyCode)
    }

    fun keyReleased(keyCode: Int) {
        engine.keyReleased(keyCode)
    }

    override fun paint(graphics: Graphics) {
        super.paint(graphics)
        graphics.clearRect(0, 0, getWidth(), getHeight())
        if (image != null) {
            graphics.drawImage(image, getInsets().left, getInsets().top, null)
        }

    }


}