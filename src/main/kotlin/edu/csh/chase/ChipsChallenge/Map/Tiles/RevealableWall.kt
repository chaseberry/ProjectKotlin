package edu.csh.chase.ChipsChallenge.Map.Tiles

import edu.csh.chase.ChipsChallenge.Map.Tiles.Revealable
import edu.csh.chase.ChipsChallenge.Map.REVEALABLE_WALL_TYPE_ID
import edu.csh.chase.ChipsChallenge.Engine.*
import java.awt.Color
import java.awt.image.BufferedImage


class RevealableWall(location: Point, uniqueId: Id) :
        Revealable(floorImage, wallImage, REVEALABLE_WALL_TYPE_ID, location, uniqueId) {

    override fun getEditorImage(): BufferedImage {
        val img = BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB)
        val g = img.graphics
        g.drawImage(imageBase, 0, 0, null)
        g.color = Color.BLACK
        g.drawString("W", 16, 16)
        return img
    }

    constructor(location: Point, open: Boolean = false) : this(location, Id(IdType.TILE))

    override fun onTick(engine: Engine) {
    }

}