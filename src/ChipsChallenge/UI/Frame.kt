package ChipsChallenge.UI

import javax.swing.JFrame
import java.awt.Graphics
import java.awt.image.BufferedImage
import java.awt.Rectangle
import java.awt.image.ColorModel

/**
 * Created by chase on 2/25/15.
 */
class Frame : JFrame() {

    {
        setSize(getInsets().left + (32 * 9) + getInsets().right, getInsets().top + (32 * 9) + getInsets().bottom)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    }

    public var image: BufferedImage? = null
        set(newImage) {
            //TODO calc viewX and viewY for max sizes and stuff
            val raster = newImage?.getData(Rectangle(view.x * 32, view.y * 32, 32 * 9, 32 * 9))
            val colorModel = ColorModel.getRGBdefault()
            image = BufferedImage(colorModel, raster?.createCompatibleWritableRaster(), false, null)
        }

    public var view: Viewport = Viewport(0, 0)

    override fun paint(graphics: Graphics) {
        super.paint(graphics)
        if (image != null) {
            graphics.drawImage(image, 0, 0, null)
        }

    }

}