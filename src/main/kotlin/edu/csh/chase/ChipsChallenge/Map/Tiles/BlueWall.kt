package edu.csh.chase.ChipsChallenge.Map.Tiles

import edu.csh.chase.ChipsChallenge.Map.Tiles.Revealable
import edu.csh.chase.ChipsChallenge.Map.BLUE_WALL_TYPE_ID
import edu.csh.chase.ChipsChallenge.Engine.*
import java.awt.Color
import java.awt.image.BufferedImage


class BlueWall(location: Point, uniqueId: Id) :
        Revealable(wallBlueImage, wallImage, BLUE_WALL_TYPE_ID, location, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.TILE))

    override fun getEditorImage(): BufferedImage {
        val img = BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB)
        val g = img.graphics
        g.drawImage(imageBase, 0, 0, null)
        g.color = Color.BLACK
        g.drawString("W", 16, 16)
        return img
    }

    override fun onTick(engine: Engine) {
    }


}