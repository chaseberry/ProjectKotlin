package ChipsChallenge.Object

import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.ObjectResolution
import ChipsChallenge.Engine.loadImage
import java.awt.image.BufferedImage

/**
 * Created by chase on 2/27/15.
 */
class GreenLock(location: Point) : ObjectBase(9, location, loadImage("green_lock.gif") as BufferedImage) {

    override fun interact(engine: Engine, direction: Direction): ObjectResolution {
        if (engine.player.inventory.hasGreenKey) {
            return ObjectResolution.REMOVE
        }
        return ObjectResolution.NOTHING
    }

    override fun onTick(engine: Engine) {

    }

}