package ChipsChallenge.Engine

import ChipsChallenge.JSON.JSONObject

/**
 * Created by chase on 2/25/15.
 */
abstract class EngineObjectBase(var location: Point) : Tickable {

    var forcedDirection: Direction? = null

    abstract fun getSaveObject(): JSONObject

}