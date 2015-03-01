package ChipsChallenge.Object

import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.ObjectResolution
import ChipsChallenge.Engine.loadImage
import java.awt.image.BufferedImage
import ChipsChallenge.Engine.redKeyImage

/**
 * Created by chase on 2/27/15.
 */
class RedKey(location: Point) : ObjectBase(4, location, redKeyImage) {

    override fun interact(engine: Engine, direction: Direction): ObjectResolution {
        engine.player.inventory.redKeys++
        return ObjectResolution.REMOVE
    }

    override fun onTick(engine: Engine) {

    }

}