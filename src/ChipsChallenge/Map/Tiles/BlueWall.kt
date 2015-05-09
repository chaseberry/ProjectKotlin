package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.BLUE_WALL_TYPE_ID
import ChipsChallenge.Map.Tile
import java.awt.image.BufferedImage


class BlueWall(location: Point, uniqueId: Id, var revealed: Boolean = false) :
        Tile(floorImage, BLUE_WALL_TYPE_ID, location, uniqueId) {

    constructor(location: Point, open: Boolean = false) : this(location, Id(IdType.TILE), open) {

    }

    fun reveal() {
        revealed = true
    }

    override var image: BufferedImage? = null
        get():BufferedImage? {
            if (revealed) {
                return wallImage
            } else {
                return wallBlueImage
            }
        }

    override fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine) {

    }

    override fun onExit(interactor: UnitBase, direction: Direction, engine: Engine) {
    }

    override fun onTick(engine: Engine) {
    }


}