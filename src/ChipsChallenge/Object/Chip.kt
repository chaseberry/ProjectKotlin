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
class Chip(location: Point) : ObjectBase(0, location, loadImage("chip.gif") as BufferedImage) {

    override fun interact(engine: Engine, direction: Direction): ObjectResolution {
        engine.player.inventory.chipsCollected++
        return ObjectResolution.REMOVE
    }

    override fun onTick(engine: Engine) {
    }

}