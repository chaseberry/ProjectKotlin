package ChipsChallenge.Engine

import ChipsChallenge.Map.Tiles.IceBase
import ChipsChallenge.Object.Block
import ChipsChallenge.Object.Button
import ChipsChallenge.UI.Viewport
import ChipsChallenge.UI.pointInViewport
import java.util.ArrayList
import java.util.HashMap

/**
 * Created by chase on 2/27/15.
 */


class ObjectManager(val engine: Engine?) : Tickable {

    override fun onTick(engine: Engine) {
        val array = ArrayList<EngineObjectBase>(objects.values())
        array.forEach { it.onTick(engine) }

    }

    val objects = HashMap<Point, ObjectBase>()

    fun add(obj: ObjectBase, location: Point) {
        objects[location] = obj
    }

    fun remove(newLocation: Point): ObjectBase? {
        if (objects.containsKey(newLocation)) {
            return objects.remove(newLocation)
        }
        return null
    }

    fun getById(id: Id): ObjectBase? {
        objects.forEach { if (it.value.uniqueId == id) return it.value }
        return null
    }

    public fun isObjectAt(location: Point): Boolean {
        return objects.containsKey(location)
    }

    public fun objectsInViewport(viewport: Viewport): ArrayList<ObjectBase> {
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
        if (resolution == ObjectResolution.REMOVE) {
            remove(obj.location)
        }
        //For blocks add 1 to block space, if block goes onto ice begin ice calc stuff?
        if (resolution == ObjectResolution.MOVE && obj is Block) {
            if (obj.objectUnder != null) {
                if (!resolveObject(obj.objectUnder!!, direction, interactor)) {
                    return false
                } else {
                    add(obj.unCover(engine)!!, obj.location)
                }
            } else {
                remove(obj.location)
            }
            val newObjLocation = when (direction) {
                Direction.UP -> obj.location.copy(y = obj.location.y - 1)
                Direction.DOWN -> obj.location.copy(y = obj.location.y + 1)
                Direction.LEFT -> obj.location.copy(x = obj.location.x - 1)
                Direction.RIGHT -> obj.location.copy(x = obj.location.x + 1)
            }
            obj.location = newObjLocation
            if (objects.get(newObjLocation) != null) {
                obj.cover(objects[newObjLocation], engine)
            }
            add(obj, newObjLocation)
            if (engine.map.getTile(newObjLocation) is IceBase) {
                obj.forcedDirection = direction
            }

        }

        return true
    }

    public fun resolve(newLocation: Point, direction: Direction, interactor: UnitBase): Boolean {
        if (engine == null) {
            return false
        }
        if (!objects.containsKey(newLocation)) {
            return true
        }
        return resolveObject(objects.get(newLocation), direction, interactor)

    }

    fun clone(): ObjectManager {
        val objManager = ObjectManager(engine)
        for ((key, value) in objects) {
            val newObj = objectFromTypeIdWithId(value.typeId, key, value.uniqueId.copy())!!
            if (newObj is Block && (value as Block).objectUnder != null) {
                val objUnder = (value as Block).objectUnder!!
                newObj.cover(objectFromTypeIdWithId(objUnder.typeId, key, objUnder.uniqueId.copy())!!, engine)
            }
            if (value is Button) {
                (newObj as Button).target = value.target
            }
            objManager.add(newObj, key)
        }

        return objManager
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