package ChipsChallenge.UI

import javax.swing.JFrame
import java.awt.Graphics
import java.awt.image.BufferedImage

/**
 * Created by chase on 2/25/15.
 */
class Frame : JFrame() {

    public var image: BufferedImage? = null;

    {
        setSize(getInsets().left + (32 * 9) + getInsets().right, getInsets().top + (32 * 9) + getInsets().bottom)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

    }

    override fun paint(graphics: Graphics) {
        super.paint(graphics)
        if (image != null) {
            graphics.drawImage(image, 0, 0, null)
        }

    }

}