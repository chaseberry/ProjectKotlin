package ChipsChallenge.Engine

import ChipsChallenge.Map.Point
import ChipsChallenge.UI.Viewport
import java.util.ArrayList
import ChipsChallenge.UI.pointInViewport
import ChipsChallenge.Map.Tiles.Water
import ChipsChallenge.Map.Tiles.Floor
import ChipsChallenge.Object.Dirt
import java.util.HashMap

/**
 * Created by chase on 2/27/15.
 */

class ObjectManager(val engine: Engine?) {

    val objects = HashMap<Point, ObjectBase>()

    fun add(obj: ObjectBase, location: Point) {
        objects.put(location, obj)
    }


    fun remove(obj: ObjectBase, newLocation: Point) {
        if (objects.containsKey(newLocation)) {
            objects.remove(newLocation)
        }
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

    public fun resolve(newLocation: Point, direction: Direction, interactor: UnitBase): Boolean {
        if (engine == null) {
            return false
        }
        if (!objects.containsKey(newLocation)) {
            return true
        }

        val obj = objects.get(newLocation)
        val resolution = obj.interact(engine, direction, interactor)
        if (resolution == ObjectResolution.NOTHING) {
            return false
        }
        if (resolution == ObjectResolution.REMOVE) {
            remove(obj, newLocation)
        }
        //For blocks add 1 to block space, if block goes onto ice begin ice calc stuff?
        if (resolution == ObjectResolution.MOVE) {
            remove(obj, newLocation)
            val newObjLocation = when (direction) {
                Direction.UP -> newLocation.copy(y = newLocation.y - 1)
                Direction.DOWN -> newLocation.copy(y = newLocation.y + 1)
                Direction.LEFT -> newLocation.copy(x = newLocation.x - 1)
                Direction.RIGHT -> newLocation.copy(x = newLocation.x + 1)

            }
            if (engine.map.getTile(newObjLocation) is Water) {
                engine.map.setTile(newObjLocation, Floor())
                add(Dirt(newObjLocation), newObjLocation)
            } else {
                obj.location = newObjLocation
                add(obj, newObjLocation)
            }
        }

        return true
    }


}