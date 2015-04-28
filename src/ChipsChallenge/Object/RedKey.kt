package ChipsChallenge.Object

import ChipsChallenge.Engine.*

/**
 * Created by chase on 2/27/15.
 */
class RedKey(location: Point, uniqueId: Id) : ObjectBase(RED_KEY_TYPE_ID, location, redKeyImage, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT)) {
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        engine.player.inventory.redKeys++
        return ObjectResolution.REMOVE
    }

    override fun onTick(engine: Engine) {

    }

}