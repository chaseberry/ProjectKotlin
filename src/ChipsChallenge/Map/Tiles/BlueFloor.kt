package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.BLUE_FLOOR_TYPE_ID
import java.awt.Color
import java.awt.image.BufferedImage


class BlueFloor(location: Point, uniqueId: Id) :
        Revealable(wallBlueImage, floorImage, BLUE_FLOOR_TYPE_ID, location, uniqueId) {

    override fun getInspectionData(): Inspection {
        return Inspection("Blue Floor", "A floor that appears as a Blue Wall, but turns into a floor on contact")
    }

    override fun getEditorImage(): BufferedImage {
        val img = BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB)
        val g = img.getGraphics()
        g.drawImage(imageBase, 0, 0, null)
        g.setColor(Color.BLACK)
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