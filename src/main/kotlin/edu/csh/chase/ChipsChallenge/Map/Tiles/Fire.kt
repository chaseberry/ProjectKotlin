package edu.csh.chase.ChipsChallenge.Map.Tiles

import edu.csh.chase.ChipsChallenge.Map.FIRE_TYPE_ID
import edu.csh.chase.ChipsChallenge.Map.Tile
import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Unit.Player

class Fire(location: Point, uniqueId: Id) : Tile(fireImage, FIRE_TYPE_ID, location, uniqueId) {
    override fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine) {
        if (!interactor.canSurviveInFire()) {
            if (interactor is Player) {
                engine.lose()
            } else {
                engine.unitManager.kill(interactor)
            }
        }
    }

    override fun onExit(interactor: UnitBase, direction: Direction, engine: Engine) {
    }

    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onTick(engine: Engine) {
    }

}