package ChipsChallenge.Object

import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Engine.ObjectResolution
import ChipsChallenge.Engine.loadImage
import java.awt.image.BufferedImage
import ChipsChallenge.Engine.yellowKeyImage

/**
 * Created by chase on 2/27/15.
 */
class YellowKey(location: Point) : ObjectBase(6, location, yellowKeyImage) {
    override fun interact(engine: Engine, direction: Direction): ObjectResolution {
        engine.player.inventory.yellowKeys++
        return ObjectResolution.REMOVE
    }

    override fun onTick(engine: Engine) {
    }

}