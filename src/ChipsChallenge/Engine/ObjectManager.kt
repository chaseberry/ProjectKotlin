package ChipsChallenge.Engine

import java.util.HashMap
import ChipsChallenge.Map.Point
import ChipsChallenge.Object.RedLock

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

    public fun resolve(curLocation: Point, newLocation: Point): Boolean {
        if (!objects.containsKey(newLocation)) {
            return true
        }

        when (objects.get(newLocation)) {
            is RedLock -> {

            }
        }

    }

}