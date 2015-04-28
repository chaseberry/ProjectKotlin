package ChipsChallenge.Object

import ChipsChallenge.Engine.*

/**
 * Created by chase on 2/27/15.
 */
class BlueKey(location: Point, uniqueId: Id) : ObjectBase(BLUE_KEY_TYPE_ID, location, blueKeyImage, uniqueId) {

    constructor(location: Point) : this(location, Id(IdType.OBJECT)) {
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        engine.player.inventory.blueKeys++
        return ObjectResolution.REMOVE
    }

    override fun onTick(engine: Engine) {

    }

}