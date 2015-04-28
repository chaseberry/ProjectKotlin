package ChipsChallenge.Engine

import ChipsChallenge.JSON.JSONArray
import ChipsChallenge.UI.Viewport
import ChipsChallenge.UI.pointInViewport
import ChipsChallenge.Unit.DirectionalUnit
import java.util.ArrayList

/**
 * Created by chase on 3/5/15.
 */
class UnitManager(val engine: Engine?) : ArrayList<UnitBase>(), Tickable {

    fun getSaveObject(): JSONArray {
        val unitArray = JSONArray()

        forEach { unit ->
            unitArray.put(unit.getSaveObject())
        }
        return unitArray
    }

    override fun onTick(engine: Engine) {
        forEach { it.onTick(engine) }
    }

    fun getById(id: Id): UnitBase? {
        forEach { if (it.uniqueId == id) return it }
        return null
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
            units.add(unitFromId(it.typeId, it.location.copy(), if (it is DirectionalUnit) {
                it.direction
            } else {
                Direction.UP
            }
            ))
        }

        return units
    }

}