package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.BLUE_FLOOR_TYPE_ID
import java.awt.Color
import java.awt.image.BufferedImage
import java.util.HashMap


class BlueFloor(location: Point, uniqueId: Id) :
        Revealable(wallBlueImage, floorImage, BLUE_FLOOR_TYPE_ID, location, uniqueId) {

    override fun getInspectionData(): HashMap<String, String> {
        val map = HashMap<String, String>()
        map["Name"] = "Blue Floor"
        map["Description"]
        return map
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