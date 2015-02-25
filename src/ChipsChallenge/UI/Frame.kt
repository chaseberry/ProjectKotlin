package ChipsChallenge.UI

import javax.swing.JFrame
import java.awt.Graphics
import java.awt.image.BufferedImage
import ChipsChallenge.Engine.Engine

/**
 * Created by chase on 2/25/15.
 */
class Frame(val engine: Engine) : JFrame() {

    val keyListener = KeyListener(this);

    {
        setSize(getInsets().left + (32 * 9) + getInsets().right, getInsets().top + (32 * 9) + getInsets().bottom)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        addKeyListener(keyListener)
    }

    public var view: Viewport = Viewport(0, 0)

    public var image: BufferedImage? = null
        set(newImage) {
            //TODO calc viewX and viewY for max sizes and stuff
            /*
            val raster = newImage?.getData(Rectangle(view.x * 32, view.y * 32, 32 * 9, 32 * 9))
            val colorModel = ColorModel.getRGBdefault()
            $image = BufferedImage(colorModel, raster?.createCompatibleWritableRaster(), false, null)
            */
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
        if (image != null) {
            graphics.drawImage(image, getInsets().left, getInsets().top, null)
        }

    }


}