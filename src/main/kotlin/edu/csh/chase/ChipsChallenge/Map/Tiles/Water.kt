package edu.csh.chase.ChipsChallenge.Map.Tiles

import edu.csh.chase.ChipsChallenge.Map.Tile
import edu.csh.chase.ChipsChallenge.Map.WATER_TYPE_ID
import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Unit.Player

class Water(location: Point, uniqueId: Id) : Tile(waterImage, WATER_TYPE_ID, location, uniqueId) {

    override fun onEnter(interactor: UnitBase, direction: Direction, engine: Engine) {
        super.onEnter(interactor, direction, engine)
        if (!interactor.canSurviveInWater()) {
            if (interactor is Player) {
                engine.lose(interactor)
            } else {
                engine.unitManager.kill(interactor)
            }
        }
    }

    constructor(location: Point) : this(location, Id(IdType.TILE)) {

    }

    override fun onTick(engine: Engine) {

    }

}