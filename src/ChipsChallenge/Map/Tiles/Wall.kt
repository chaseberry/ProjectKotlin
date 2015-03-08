package ChipsChallenge.Map.Tiles

import ChipsChallenge.Map.Tile
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.wallImage
import ChipsChallenge.Engine.Point

/**
 * Created by chase on 2/25/15.
 */
class Wall(location:Point) : Tile(wallImage, 1, location) {
    override fun onTick(engine: Engine) {
    }
}