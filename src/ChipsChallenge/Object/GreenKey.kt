package ChipsChallenge.Object

import ChipsChallenge.Engine.*
import ChipsChallenge.Unit.Player

/**
 * Created by chase on 2/27/15.
 */
class GreenKey(location: Point, uniqueId: Id) : ObjectBase(GREEN_KEY_TYPE_ID, location, greenKeyImage, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT)) {
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor !is Player) {
            return ObjectResolution.PASSOVER
        }
        engine.player.inventory.hasGreenKey = true
        return ObjectResolution.REMOVE
    }

    override fun onTick(engine: Engine) {

    }

}