package edu.csh.chase.ChipsChallenge.Engine

import edu.csh.chase.ChipsChallenge.Engine.Id
import edu.csh.chase.ChipsChallenge.Engine.Tickable
import ChipsChallenge.JSON.JSONArray
import edu.csh.chase.ChipsChallenge.UI.Viewport
import edu.csh.chase.ChipsChallenge.UI.pointInViewport
import edu.csh.chase.ChipsChallenge.Unit.DirectionalUnit
import edu.csh.chase.ChipsChallenge.Unit.Tank
import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.kjson.JsonArray
import java.util.ArrayList

/**
 * Created by chase on 3/5/15.
 */
class UnitManager(val engine: Engine?) : ArrayList<UnitBase>(), Tickable {

    fun getSaveObject(): JsonArray {
        val unitArray = JsonArray()

        forEach { unit ->
            unitArray.put(unit.getSaveObject())
        }
        return unitArray
    }

    override fun onTick(engine: Engine) {
        forEach { it.onTick(engine) }
    }

    fun kill(unit: UnitBase) {
        remove(unit)
    }

    fun getById(id: Id): UnitBase? {
        forEach { if (it.uniqueId == id) return it }
        return null
    }

    fun isUnitOnPoint(location: Point): Boolean {
        return unitOnPoint(location) != null
    }

    fun unitOnPointIndex(location: Point): Int? {
        forEachIndexed { i, unitBase -> if ( unitBase.location == location) return i }
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

    fun getAllTanks(): ArrayList<Tank> {
        val tanks = ArrayList<Tank>()
        forEach {
            if (it is Tank) {
                tanks.add(it)
            }
        }
        return tanks
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