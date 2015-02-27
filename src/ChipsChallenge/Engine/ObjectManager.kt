package ChipsChallenge.Engine

import java.util.HashMap
import ChipsChallenge.Map.Point

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

    public fun resolve(newLocation: Point, direction: Direction): Boolean {
        if (!objects.containsKey(newLocation)) {
            return true
        }

        return objects.get(newLocation).interact(engine, direction)

    }

}