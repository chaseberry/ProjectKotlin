package ChipsChallenge.Map.Tiles

import ChipsChallenge.Map.Tile
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.finishImage
import ChipsChallenge.Engine.Point

/**
 * Created by chase on 2/28/15.
 */
class Finish(location:Point) : Tile(finishImage, 2, location) {

    override fun onTick(engine: Engine) {
    }

}