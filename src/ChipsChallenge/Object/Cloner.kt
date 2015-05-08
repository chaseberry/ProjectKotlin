package ChipsChallenge.Object

import ChipsChallenge.Engine.*
import ChipsChallenge.JSON.JSONObject
import java.awt.image.BufferedImage

/**
 * Created by chase on 3/2/15.
 */
class Cloner(location: Point, uniqueId: Id, var template: EngineObjectBase?, var direction: Direction?) :
        ObjectBase(CLONER_TYPE_ID, location, clonerImage, uniqueId), Triggerable {

    override var image: BufferedImage? = null
        get():BufferedImage? {
            val img = BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB)
            val g = img.getGraphics()
            g.drawImage(clonerImage, 0, 0, null)
            if (template != null) {
                g.drawImage(template!!.image, 0, 0, null)
            }
            return img
        }

    constructor(location: Point, template: EngineObjectBase?, direction: Direction?) :
    this(location, Id(IdType.OBJECT), template, direction) {
    }

    override fun onTrigger() {

    }

    override fun offTrigger() {
    }

    override fun getSaveObject(): JSONObject {
        val obj = super<ObjectBase>.getSaveObject()
        if (template == null) {
            obj.put("template", "null")
        } else {
            obj.put("template", template!!.getSaveObject())
        }
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