package ChipsChallenge.Map.Tiles

import ChipsChallenge.Map.Tile
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.helpImage
import ChipsChallenge.Engine.Point

/**
 * Created by chase on 2/28/15.
 */
class Help(location:Point) : Tile(helpImage, 3, location) {
    override fun onTick(engine: Engine) {
    }

}