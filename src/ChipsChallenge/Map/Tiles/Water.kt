package ChipsChallenge.Map.Tiles

import ChipsChallenge.Map.Tile
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.waterImage
import ChipsChallenge.Engine.Point

/**
 * Created by chase on 2/28/15.
 */
class Water(location: Point) : Tile(waterImage, 4, location) {
    override fun onTick(engine: Engine) {

    }

}