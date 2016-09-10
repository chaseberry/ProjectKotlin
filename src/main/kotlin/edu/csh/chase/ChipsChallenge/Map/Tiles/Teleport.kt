package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Map.TELEPORT_TYPE_ID
import edu.csh.chase.ChipsChallenge.Map.Tile
import edu.csh.chase.ChipsChallenge.Engine.*

class Teleport(location: Point, uniqueId: Id) : Tile(teleportImage, TELEPORT_TYPE_ID, location, uniqueId) {

    override fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine) {
        engine.teleport(interactor, direction, this)
    }

    fun onTeleportEnter(interactor: UnitBase, direction: Direction, engine: Engine) {
        arriving = true
    }

    override fun onExit(interactor: UnitBase, direction: Direction, engine: Engine) {
        arriving = false
    }

    var arriving = false

    //TODO implement teleporting for objects(Blocks)
    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onTick(engine: Engine) {

    }

}