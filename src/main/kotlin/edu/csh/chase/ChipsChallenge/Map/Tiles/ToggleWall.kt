package edu.csh.chase.ChipsChallenge.Map.Tiles

import edu.csh.chase.ChipsChallenge.Map.TOGGLE_WALL_TYPE_ID
import edu.csh.chase.ChipsChallenge.Map.Tile
import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.kjson.JsonObject
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

    override fun getSaveObject(): JsonObject {
        val obj = super.getSaveObject()
        obj.put("open", open)
        return obj
    }

    override fun onTick(engine: Engine) {
    }

}