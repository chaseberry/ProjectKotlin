package ChipsChallenge.Editor

import ChipsChallenge.Map.blankMap
import java.awt.image.BufferedImage
import ChipsChallenge.UI.getViewport
import java.util.ArrayList
import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Map.Point

/**
 * Created by chase on 2/27/15.
 */
class Editor(x: Int, y: Int) {

    val map = blankMap(x, y)

    val frame = EditorFrame(this)

    val objects = ArrayList<ObjectBase>()

    val mouseBindings = MouseBindings()

    val currentCenter = Point(0, 0)

    fun start() {
        frame.setVisible(true)
    }

    fun buildFrameImage(): BufferedImage {
        val image = BufferedImage(9 * 32, 9 * 32, BufferedImage.TYPE_INT_ARGB)
        val viewport = getViewport(currentCenter, map)
        val mapImage = map.getImage(viewport)
        val g = image.getGraphics()

        //Draw the map
        g.drawImage(mapImage, 0, 0, null)

        //Draw objects
        for (obj in objects) {
            g.drawImage(obj.image, (obj.location.x - viewport.xStart) * 32, (obj.location.y - viewport.yStart) * 32, null)
        }

        return image
    }

    fun triggerUpdate() {
        val tile = map.getTile(mouseBindings.mouseLocation - currentCenter)
    }

}