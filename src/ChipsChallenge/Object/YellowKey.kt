package ChipsChallenge.Object

import ChipsChallenge.Engine.*
import ChipsChallenge.Unit.Player

/**
 * Created by chase on 2/27/15.
 */
class YellowKey(location: Point, uniqueId: Id) : ObjectBase(YELLOW_KEY_TYPE_ID, location, yellowKeyImage, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT)) {
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        if (interactor !is Player) {
            return ObjectResolution.NOTHING
        }
        engine.player.inventory.yellowKeys++
        return ObjectResolution.REMOVE
    }

    override fun onTick(engine: Engine) {
    }

}