package edu.csh.chase.ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.BLUE_FLOOR_TYPE_ID
import ChipsChallenge.Map.Tiles.Revealable
import edu.csh.chase.ChipsChallenge.Engine.Direction
import edu.csh.chase.ChipsChallenge.Engine.Engine
import edu.csh.chase.ChipsChallenge.Engine.Point
import edu.csh.chase.ChipsChallenge.Engine.UnitBase
import java.awt.Color
import java.awt.image.BufferedImage


class BlueFloor(location: Point, uniqueId: Id) :
        Revealable(wallBlueImage, floorImage, BLUE_FLOOR_TYPE_ID, location, uniqueId) {

    override fun getEditorImage(): BufferedImage {
        val img = BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB)
        val g = img.graphics
        g.drawImage(imageBase, 0, 0, null)
        g.color = Color.BLACK
        g.drawString("F", 16, 16)
        return img
    }

    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine) {

    }

    override fun onExit(interactor: UnitBase, direction: Direction, engine: Engine) {
    }

    override fun onTick(engine: Engine) {
    }


}