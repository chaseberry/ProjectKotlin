package edu.csh.chase.ChipsChallenge.Engine

import ChipsChallenge.Engine.EngineObjectBase
import edu.csh.chase.ChipsChallenge.Engine.Id
import ChipsChallenge.JSON.JSONArray
import ChipsChallenge.JSON.JSONObject
import edu.csh.chase.ChipsChallenge.Map.mapFromJSON
import edu.csh.chase.ChipsChallenge.Object.Block
import ChipsChallenge.Object.Button
import edu.csh.chase.ChipsChallenge.Object.Cloner
import edu.csh.chase.ChipsChallenge.Unit.DirectionalUnit
import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Map.Map
import java.util.ArrayList

fun levelFromJson(obj: JSONObject): Level? {
    val title = obj["title"]
    val mapObj = obj["map"]
    val hintText = obj["hintText"]
    val timeLimit = obj["timeLimit"]
    val chipCount = obj["chipCount"]
    val requiredChips = obj["requiredChips"]
    val playerStartObj = obj["playerStart"]
    val unitObj = obj["units"]
    val objectObj = obj["objects"]

    if (anyNull(title, mapObj, hintText, timeLimit, chipCount, requiredChips, playerStartObj, unitObj, objectObj)) {
        return null
    }

    if (title !is String || hintText !is String || timeLimit !is Int || requiredChips !is Int || chipCount !is Int ||
            playerStartObj !is JSONObject || unitObj !is JSONArray || objectObj !is JSONArray || mapObj !is JSONArray) {
        return null
    }

    val map = mapFromJSON(mapObj) ?: return null

    val playerStart = pointFromJson(playerStartObj)

    val objects = ArrayList<ObjectBase>(unitObj.length())
    for (z in 0..unitObj.length() - 1) {
        val obj = objectFromJSON(unitObj.getJSONObject(z)) ?: continue
        objects.add(obj)
    }

    val units = ArrayList<UnitBase>(unitObj.length())
    for ( z in 0..unitObj.length() - 1) {
        val unit = unitFromJson(unitObj.getJSONObject(z)) ?: continue
        units.add(unit)
    }

    return Level(map, objects, units, chipCount, requiredChips, playerStart, title, hintText, timeLimit)
}

fun anyNull(vararg args: Any?): Boolean {
    args.forEach { if (it == null) return false }
    return true
}

class Level(val map: Map, val objects: ArrayList<ObjectBase>, val units: ArrayList<UnitBase>,
            var chipCount: Int, var requiredChips: Int, val playerStart: Point, var  title: String, var hintText: String,
            var timeLimit: Int) {

    fun getSaveObject(): JSONObject {
        val obj = JSONObject()

        val unitArray = JSONArray()
        units.forEach { unitArray.put(it.getSaveObject()) }

        val objectArray = JSONArray()
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
        val newUnits = ArrayList<UnitBase>(units.size())
        units.forEach {
            newUnits.add(unitFromId(it.typeId, it.location.copy(), if (it is DirectionalUnit) {
                it.direction
            } else {
                Direction.UP
            }
            ))
        }
        val newObjects = ArrayList<ObjectBase>(objects.size())
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