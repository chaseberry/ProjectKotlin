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

    val objects = HashMap<Point, ObjectLocationList>()

    fun add(obj: ObjectBase, location: Point) {
        if (objects.contains(location)) {
            objects.get(location).add(obj)
        } else {
            objects.put(location, ObjectLocationList(obj))
        }
    }


    fun remove(obj: ObjectBase, newLocation: Point) {
        if (objects.get(newLocation).size() == 1) {
            objects.remove(newLocation)
        } else {
            objects.get(newLocation).remove(obj)
        }
    }

    public fun isObjectAt(location: Point): Boolean {
        return objects.containsKey(location)
    }

    public fun objectsInViewport(viewport: Viewport): ArrayList<ObjectBase> {
        val objs = ArrayList<ObjectBase>()
        for ((key, value) in objects) {
            if (pointInViewport(key, viewport)) {
                objs.add(value.headObject)
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
        //Check all resolutions
        //if ANY resolution is NOTHING
        //DO NOTHING
        //else do all resolutions
        for (obj in objects.get(newLocation)) {
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
        }
        return true
    }


}