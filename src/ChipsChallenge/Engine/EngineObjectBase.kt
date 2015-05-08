package ChipsChallenge.Engine

import ChipsChallenge.JSON.JSONObject
import java.awt.image.BufferedImage

/**
 * Created by chase on 2/25/15.
 */
abstract class EngineObjectBase(var location: Point, open var image: BufferedImage?) : Tickable {

    abstract fun getSaveObject(): JSONObject

}