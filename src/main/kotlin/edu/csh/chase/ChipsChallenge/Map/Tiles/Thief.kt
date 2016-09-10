package edu.csh.chase.ChipsChallenge.Map.Tiles

import edu.csh.chase.ChipsChallenge.Map.THIEF_TYPE_ID
import edu.csh.chase.ChipsChallenge.Map.Tile
import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Unit.Player

class Thief(location: Point, uniqueId: Id) : Tile(thiefImage, THIEF_TYPE_ID, location, uniqueId) {

    override fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine) {
        if (interactor is Player) {
            interactor.inventory.removeBoots()
        }
    }

    override fun onExit(interactor: UnitBase, direction: Direction, engine: Engine) {
    }

    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onTick(engine: Engine) {

    }

}