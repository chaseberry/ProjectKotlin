package ChipsChallenge.Engine

import java.util.ArrayList
import ChipsChallenge.Map.Point
import ChipsChallenge.UI.pointInViewport
import ChipsChallenge.UI.Viewport

/**
 * Created by chase on 3/5/15.
 */
class UnitManager(val engine: Engine?) : ArrayList<UnitBase>(), EngineObjectBase {

    override fun onTick(engine: Engine) {
        forEach { it.onTick(engine) }
    }

    fun isUnitOnPoint(location: Point): Boolean {
        forEach { if (it != null && it.location == location) return true }
        return false
    }

    fun unitOnPointIndex(location: Point): Int? {
        forEachIndexed {(i, unitBase) -> if (unitBase != null && unitBase.location == location) return i }
        return null;
    }

    fun unitsInViewPort(view: Viewport): ArrayList<UnitBase> {
        val units = ArrayList<UnitBase>()
        forEach { if (pointInViewport(it.location, view)) units.add(it) }
        return units
    }

}