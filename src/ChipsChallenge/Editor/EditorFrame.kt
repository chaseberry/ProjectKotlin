package ChipsChallenge.Editor

import javax.swing.JFrame
import java.awt.image.BufferedImage
import java.awt.Graphics

/**
 * Created by chase on 2/27/15.
 */
class EditorFrame : JFrame() {
    {
        pack()
        setSize(getInsets().left + (32 * 9) + getInsets().right, getInsets().top + (32 * 9) + getInsets().bottom)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    }

    public var image: BufferedImage? = null
        set(newImage) {
            $image = newImage
            repaint()
        }

    override fun paint(graphics: Graphics) {
        super.paint(graphics)
        graphics.clearRect(0, 0, getWidth(), getHeight())
        if (image != null) {
            graphics.drawImage(image, getInsets().left, getInsets().top, null)
        }

    }
}