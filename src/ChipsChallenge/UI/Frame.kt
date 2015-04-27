package ChipsChallenge.UI

import javax.swing.JFrame
import java.awt.Graphics
import java.awt.image.BufferedImage
import ChipsChallenge.Engine.Engine
import javax.swing.WindowConstants

/**
 * Created by chase on 2/25/15.
 */
class Frame(val engine: Engine) : JFrame() {

    val keyListener = KeyListener(this);

    init {
        pack()
        setSize(getInsets().left + (32 * 9) + getInsets().right, getInsets().top + (32 * 9) + getInsets().bottom)
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE)
        addKeyListener(keyListener)
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