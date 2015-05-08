package ChipsChallenge.Object

import ChipsChallenge.Engine.*
import ChipsChallenge.JSON.JSONObject

/**
 * Created by chase on 3/2/15.
 */
class Cloner(location: Point, uniqueId: Id, val template: EngineObjectBase, val direction: Direction) :
        ObjectBase(CLONER_TYPE_ID, location, clonerImage, uniqueId), Triggerable {

    constructor(location: Point, template: EngineObjectBase, direction: Direction) :
    this(location, Id(IdType.OBJECT), template, direction) {
    }

    override fun onTrigger() {

    }

    override fun offTrigger() {
    }

    override fun getSaveObject(): JSONObject {
        val obj = super<ObjectBase>.getSaveObject()
        obj.put("template", template.getSaveObject())
        return obj
    }

    override fun canInteractorMove(engine: Engine, interactor: UnitBase): Boolean {
        return false // if the trap is active nothing can move
    }

    override fun clone(): Triggerable {
        return objectFromTypeId(typeId, location) as Triggerable
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        return ObjectResolution.NOTHING
    }

    override fun onTick(engine: Engine) {
    }

}