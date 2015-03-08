package ChipsChallenge.Map.Tiles

import ChipsChallenge.Map.Tile
import ChipsChallenge.Engine.iceImage
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Point

/**
 * Created by chase on 3/8/15.
 */
class Ice(location:Point) : Tile(iceImage, 6, location) {

    override fun onTick(engine: Engine) {

    }

}