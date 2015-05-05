package ChipsChallenge.Map.Tiles

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.FIRE_TYPE_ID
import ChipsChallenge.Map.Tile
import ChipsChallenge.Unit.Player

/**
 * Created by chase on 3/6/15.
 */
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