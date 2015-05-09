package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.INVISIBLE_WALL_ID
import java.awt.Color
import java.awt.image.BufferedImage


class InvisibleWall(location: Point, uniqueId: Id) :
        Revealable(floorImage, floorImage, INVISIBLE_WALL_ID, location, uniqueId) {

    override fun getEditorImage(): BufferedImage {
        val img = BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB)
        val g = img.getGraphics()
        g.drawImage(imageBase, 0, 0, null)
        g.setColor(Color.BLACK)
        g.drawString("X", 16, 16)
        return img
    }

    override fun onExit(interactor: UnitBase, direction: Direction, engine: Engine) {

    }

    override fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine) {
    }

    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onTick(engine: Engine) {
    }
}