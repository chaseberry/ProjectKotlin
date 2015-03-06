package ChipsChallenge.Engine

import java.util.ArrayList

/**
 * Created by chase on 3/5/15.
 */
class UnitManager(val engine: Engine?) : ArrayList<UnitBase>(), EngineObjectBase {

    override fun onTick(engine: Engine) {
        forEach { it.onTick(engine) }
    }

}