package ChipsChallenge.Engine

import ChipsChallenge.JSON.JSONObject
import edu.csh.chase.ChipsChallenge.Engine.Point
import edu.csh.chase.ChipsChallenge.Engine.Tickable
import edu.csh.chase.kjson.JsonObject
import java.awt.image.BufferedImage
import java.util.HashMap

abstract class EngineObjectBase(var location: Point, open var image: BufferedImage?) : Tickable {

    abstract fun getSaveObject(): JsonObject

}