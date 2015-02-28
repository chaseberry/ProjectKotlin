package ChipsChallenge.Map.Tiles

import ChipsChallenge.Map.Tile
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.wallImage

/**
 * Created by chase on 2/25/15.
 */
class Wall() : Tile(wallImage, 1) {
    override fun onTick(engine: Engine) {
    }
}