package ChipsChallenge.Object

import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.ObjectResolution
import ChipsChallenge.Engine.loadImage
import java.awt.image.BufferedImage

/**
 * Created by chase on 2/27/15.
 */
class BlueKey(location: Point) : ObjectBase(2, location, loadImage("blue_key.gif") as BufferedImage) {

    override fun interact(engine: Engine, direction: Direction): ObjectResolution {
        engine.player.inventory.blueKeys++
        return ObjectResolution.REMOVE
    }

    override fun onTick(engine: Engine) {

    }

}