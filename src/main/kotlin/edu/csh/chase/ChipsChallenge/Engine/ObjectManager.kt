package edu.csh.chase.ChipsChallenge.Engine

import edu.csh.chase.ChipsChallenge.Map.Tiles.IceBase
import edu.csh.chase.ChipsChallenge.Object.Block
import edu.csh.chase.ChipsChallenge.Object.Bomb
import edu.csh.chase.ChipsChallenge.UI.Viewport
import edu.csh.chase.ChipsChallenge.UI.pointInViewport
import edu.csh.chase.ChipsChallenge.Unit.Player
import edu.csh.chase.kjson.JsonArray
import java.util.*

class ObjectManager(val engine: Engine?) : Tickable {

    val objects = HashMap<Point, ObjectBase>()

    val modList = ArrayList<Modification<ObjectBase>>()

    override fun onTick(engine: Engine) {
        val array = ArrayList<EngineObjectBase>(objects.values)
        array.forEach { it.onTick(engine) }
    }

    fun applyChanges() {
        modList.removeAll {
            if (it.type == Modification.Type.add) {
                objects[it.item.location] = it.item
            } else {
                objects.remove(it.item.location)
            }
            true
        }

    }

    fun add(obj: ObjectBase, location: Point) {
        modList.add(Modification(Modification.Type.add, obj))
    }

    fun remove(newLocation: Point): ObjectBase? {
        return objects.remove(newLocation)

    }

    fun getById(id: Id): ObjectBase? {
        objects.forEach { if (it.value.uniqueId == id) return it.value }
        return null
    }

    fun isObjectAt(location: Point): Boolean {
        return objects.containsKey(location)
    }

    fun objectsInViewport(viewport: Viewport): ArrayList<ObjectBase> {
        val objs = ArrayList<ObjectBase>()
        for ((key, value) in objects) {
            if (pointInViewport(key, viewport)) {
                objs.add(value)
            }
        }
        return objs
    }

    fun resolveObject(obj: ObjectBase, direction: Direction, interactor: UnitBase): Boolean {
        if (engine == null) {
            return false
        }
        val resolution = obj.interact(engine, direction, interactor)
        if (resolution == ObjectResolution.NOTHING) {
            return false
        }

        if (resolution == ObjectResolution.KILL) {
            if (interactor is Player) {
                engine.lose(interactor)
            } else {
                engine.unitManager.kill(interactor)
                remove(obj.location)
            }
            return true
        }

        if (resolution == ObjectResolution.REMOVE) {
            remove(obj.location)
            return true
        }
        //For blocks add 1 to block space, if block goes onto ice begin ice calc stuff?
        if (resolution == ObjectResolution.MOVE && obj is Block) {

            if (obj.objectUnder != null) {
                val objUnder = obj.objectUnder!!
                if (!resolveObject(objUnder, direction, interactor)) {
                    return false
                }
                obj.unCover(engine)?.let { add(it, it.location) }
            } else {
                remove(obj.location)
            }

            val newObjLocation = when (direction) {
                Direction.UP -> obj.location.copy(y = obj.location.y - 1)
                Direction.DOWN -> obj.location.copy(y = obj.location.y + 1)
                Direction.LEFT -> obj.location.copy(x = obj.location.x - 1)
                Direction.RIGHT -> obj.location.copy(x = obj.location.x + 1)
            }

            if (objects[newObjLocation] is Bomb) {
                remove(newObjLocation)
                remove(obj.location)
                return true
            }

            obj.location = newObjLocation
            if (objects[newObjLocation] != null) {
                obj.cover(objects[newObjLocation]!!, engine)
            }

            add(obj, newObjLocation)
            if (engine.map.getTile(newObjLocation) is IceBase) {
                obj.forcedDirection = direction
            }

        }

        return true
    }

    fun resolve(newLocation: Point, direction: Direction, interactor: UnitBase): Boolean {
        if (engine == null) {
            return false
        }

        val obj = objects[newLocation] ?: return true

        return resolveObject(obj, direction, interactor)

    }

    fun getSaveObject(): JsonArray {
        val objArray = JsonArray()
        for (obj in objects.values) {
            objArray.put(obj.getSaveObject())
        }
        return objArray
    }

    fun forceMoveUp(obj: ObjectBase) {
        forceMove(obj, obj.location, obj.location.copy(y = obj.location.y - 1))
    }

    fun forceMoveDown(obj: ObjectBase) {
        forceMove(obj, obj.location, obj.location.copy(y = obj.location.y + 1))
    }

    fun forceMoveLeft(obj: ObjectBase) {
        forceMove(obj, obj.location, obj.location.copy(x = obj.location.x - 1))
    }

    fun forceMoveRight(obj: ObjectBase) {
        forceMove(obj, obj.location, obj.location.copy(x = obj.location.x + 1))
    }

    fun forceMove(obj: ObjectBase, oldLocation: Point, location: Point) {
        obj.location = location
        objects.remove(oldLocation)
        objects.put(location, obj)
    }

}