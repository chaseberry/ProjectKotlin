package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.JSON.JSONObject
import ChipsChallenge.Map.TOGGLE_WALL_TYPE_ID
import ChipsChallenge.Map.Tile
import edu.csh.chase.ChipsChallenge.Engine.*
import java.awt.image.BufferedImage


class ToggleWall(location: Point, uniqueId: Id, var open: Boolean = false) :
        Tile(toggleWallClosedImage, TOGGLE_WALL_TYPE_ID, location, uniqueId), Triggerable {

    constructor(location: Point, open: Boolean = false) : this(location, Id(IdType.TILE), open) {

    }


    //button is pressed
    override fun onTrigger() {
        open = !open

    }

    //button is released
    override fun offTrigger() {
    }

    override fun clone(): Triggerable {
        return ToggleWall(location, uniqueId, open)
    }


    override var image: BufferedImage? = null
        get():BufferedImage? {
            return if (open) {
                toggleWallOpenImage
            } else {
                toggleWallClosedImage
            }
        }

    override fun getSaveObject(): JSONObject {
        val obj = super<Tile>.getSaveObject()
        obj.put("open", open)
        return obj
    }

    override fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine) {

    }

    override fun onExit(interactor: UnitBase, direction: Direction, engine: Engine) {
    }

    override fun onTick(engine: Engine) {
    }

}