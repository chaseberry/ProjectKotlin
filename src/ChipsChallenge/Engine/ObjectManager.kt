package ChipsChallenge.Engine

import java.util.HashMap
import ChipsChallenge.Map.Point
import ChipsChallenge.UI.Viewport
import java.util.ArrayList
import ChipsChallenge.UI.pointInViewport

/**
 * Created by chase on 2/27/15.
 */

class ObjectManager(val engine: Engine) {

    val objects = HashMap<Point, ObjectBase>()

    public fun add(obj: ObjectBase, location: Point) {
        objects.put(location, obj)
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

    public fun resolve(newLocation: Point, direction: Direction): Boolean {
        if (!objects.containsKey(newLocation)) {
            return true
        }

        val resolution = objects.get(newLocation).interact(engine, direction)
        if (resolution == ObjectResolution.NOTHING) {
            return false
        }
        if (resolution == ObjectResolution.REMOVE) {
            objects.remove(newLocation)
        }
        //For blocks add 1 to block space, if block goes onto ice begin ice calc stuff?
        if (resolution == ObjectResolution.MOVE) {

        }
        return true

    }

}