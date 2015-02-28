package ChipsChallenge.Map.Tiles

import ChipsChallenge.Map.Tile
import ChipsChallenge.Engine.Engine

/**
 * Created by chase on 2/28/15.
 */
class Help:Tile("help.gif", 3){
    override fun onTick(engine: Engine) {
        throw UnsupportedOperationException()
    }

}