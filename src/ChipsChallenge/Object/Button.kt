package ChipsChallenge.Object

import ChipsChallenge.Engine.*
import java.awt.image.BufferedImage

/**
 * Created by chase on 3/2/15.
 */
abstract class Button(typeId: Int, location: Point, image: BufferedImage,
                      var target: Id?, uniqueId: Id) : ObjectBase(typeId, location, image, uniqueId) {

    var triggered = false

    fun trigger(engine: Engine) {
        if (!triggered && target != null) {
            val tgt = engine.getEngineObjectBase(target!!)
            if (tgt != null && tgt is Triggerable) {
                triggered = true
                tgt.onTrigger()
            }
        }
    }

    fun offTrigger(engine: Engine) {
        if (triggered && target != null) {
            val tgt = engine.getEngineObjectBase(target!!)
            if (tgt != null && tgt is Triggerable) {
                triggered = false
                tgt.offTrigger()
            }
        }
    }

}