package ChipsChallenge.Map.Tiles

import ChipsChallenge.Map.Tile
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.finishImage

/**
 * Created by chase on 2/28/15.
 */
class Finish : Tile(finishImage, 2) {

    override fun onTick(engine: Engine) {
    }

}