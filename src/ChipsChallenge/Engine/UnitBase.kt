package ChipsChallenge.Engine

import java.awt.image.BufferedImage
import ChipsChallenge.Map.Tile
import java.util.HashMap
import ChipsChallenge.Unit.PinkBall
import ChipsChallenge.JSON.JSONObject

/**
 * Created by chase on 2/25/15.
 */

fun unitFromId(id: Int, location: Point, direction: Direction): UnitBase? {
    return when (id) {
        0 -> PinkBall(direction, location)
        else -> null
    }
}

abstract class UnitBase(val id: Int, var location: Point) : EngineObjectBase {

    protected val imageSet: HashMap<String, BufferedImage> = HashMap()

    var image: BufferedImage? = null

    abstract fun canMoveToTile(tile: Tile): Boolean

    override fun getSaveObject(): JSONObject {
        val obj = JSONObject()
        obj.put("location", location.saveObject)
        obj.put("id", id)
        return obj
    }

}