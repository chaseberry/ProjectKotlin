package ChipsChallenge.Engine

import ChipsChallenge.JSON.JSONObject

/**
 * Created by chase on 2/25/15.
 */
trait EngineObjectBase {

    public fun onTick(engine: Engine)

    fun getSaveObject(): JSONObject

}