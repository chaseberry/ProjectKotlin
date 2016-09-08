package ChipsChallenge.Object

import ChipsChallenge.Engine.*
import ChipsChallenge.JSON.JSONObject
import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.kjson.JsonObject
import java.awt.image.BufferedImage

abstract class Button(typeId: Int, location: Point, image: BufferedImage,
                      var target: Id?, uniqueId: Id) : ObjectBase(typeId, location, image, uniqueId) {

    var triggered = false

    override fun getSaveObject(): JsonObject {
        val obj = super.getSaveObject()
        if (target != null) {
            obj.put("targetId", target!!.getJson())
        }
        return obj
    }

    open fun trigger(engine: Engine) {
        if (!triggered && target != null) {
            val tgt = engine.getEngineObjectBase(target!!)
            if (tgt != null && tgt is Triggerable) {
                triggered = true
                tgt.onTrigger()
            }
        }
    }

    open fun offTrigger(engine: Engine) {
        if (triggered && target != null) {
            val tgt = engine.getEngineObjectBase(target!!)
            if (tgt != null && tgt is Triggerable) {
                triggered = false
                tgt.offTrigger()
            }
        }
    }

}