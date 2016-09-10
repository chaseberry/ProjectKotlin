package edu.csh.chase.ChipsChallenge.Engine

import edu.csh.chase.ChipsChallenge.Object.Button
import edu.csh.chase.ChipsChallenge.Map.Map
import edu.csh.chase.ChipsChallenge.Object.Block
import edu.csh.chase.ChipsChallenge.Object.Cloner
import edu.csh.chase.ChipsChallenge.Unit.DirectionalUnit
import edu.csh.chase.kjson.JsonArray
import edu.csh.chase.kjson.JsonObject
import java.util.*

class Level(val map: Map, val objects: ArrayList<ObjectBase>, val units: ArrayList<UnitBase>,
            var chipCount: Int, var requiredChips: Int, val playerStart: Point, var title: String, var hintText: String,
            var timeLimit: Int) {

    fun getSaveObject(): JsonObject {
        val obj = JsonObject()

        val unitArray = JsonArray()
        units.forEach { unitArray.put(it.getSaveObject()) }

        val objectArray = JsonArray()
        objects.forEach { objectArray.put(it.getSaveObject()) }

        obj["title"] = title
        obj["map"] = map.getSaveObject()
        obj["hintText"] = hintText
        obj["timeLimit"] = timeLimit
        obj["chipCount"] = chipCount
        obj["requiredChips"] = requiredChips
        obj["playerStart"] = playerStart.saveObject
        obj["units"] = unitArray
        obj["objects"] = objectArray
        return obj
    }

    fun clone(): Level {
        val newMap = map.copy()
        val newUnits = ArrayList<UnitBase>(units.size)
        units.forEach {
            newUnits.add(unitFromId(it.typeId, it.location.copy(), if (it is DirectionalUnit) {
                it.direction
            } else {
                Direction.UP
            }
            ))
        }
        val newObjects = ArrayList<ObjectBase>(objects.size)
        objects.forEach {
            var objUnder: ObjectBase? = null
            var template: EngineObjectBase? = null
            var direction = Direction.UP
            var targetId: Id? = null
            if (it is Block && it.objectUnder != null) {
                if (it.objectUnder!! is Button) {
                    targetId = (it.objectUnder!! as Button).target
                }
                objUnder = objectFromTypeIdWithId(it.objectUnder!!.typeId, it.objectUnder!!.location.copy(),
                        it.objectUnder!!.uniqueId, target = targetId)
            }
            if (it is Button) {
                targetId = it.target
            }
            if (it is Cloner) {
                template = it.template
                direction = it.direction
            }
            newObjects.add(objectFromTypeIdWithId(it.typeId, it.location.copy(), it.uniqueId.copy(), target = targetId,
                    template = template, direction = direction, objUnder = objUnder)!!)
        }
        return Level(newMap, newObjects, newUnits, chipCount, requiredChips, playerStart.copy(), title, hintText, timeLimit)
    }

}