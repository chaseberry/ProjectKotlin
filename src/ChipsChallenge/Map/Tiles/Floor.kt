package ChipsChallenge.Map.Tiles

import ChipsChallenge.Map.Tile
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.floorImage
import ChipsChallenge.Engine.Point

/**
 * Created by chase on 2/25/15.
 */

class Floor(location:Point) : Tile(floorImage, 0, location) {

    override fun onTick(engine: Engine) {

    }

}