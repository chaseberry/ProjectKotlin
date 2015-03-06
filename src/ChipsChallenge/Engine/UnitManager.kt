package ChipsChallenge.Engine

import java.util.ArrayList
import ChipsChallenge.Engine.Point
import ChipsChallenge.UI.pointInViewport
import ChipsChallenge.UI.Viewport
import ChipsChallenge.Unit.DirectionalUnit

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

    fun unitOnPoint(location: Point): UnitBase? {
        forEach { if (it.location == location) return it }
        return null
    }

    override fun clone(): UnitManager {
        val units = UnitManager(engine)

        forEach {
            units.add(unitFromId(it.id, it.location.copy(), if (it is DirectionalUnit) {
                it.direction
            } else {
                Direction.UP
            }
            ))
        }

        return units
    }

}