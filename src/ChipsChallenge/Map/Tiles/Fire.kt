package ChipsChallenge.Map.Tiles

import ChipsChallenge.Map.Tile
import ChipsChallenge.Engine.fireImage
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Point

/**
 * Created by chase on 3/6/15.
 */
class Fire(location:Point) : Tile(fireImage, 5, location) {

    override fun onTick(engine: Engine) {
    }

}