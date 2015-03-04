package ChipsChallenge.Object

import ChipsChallenge.Engine.ObjectBase
import java.awt.image.BufferedImage
import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.Triggerable

/**
 * Created by chase on 3/2/15.
 */
abstract class Button(id: Int, location: Point, image: BufferedImage,
                      var target: Triggerable?) : ObjectBase(id, location, image) {

    fun trigger() {
        if (target != null) {
            target!!.onTrigger()
        }
    }

    fun offTrigger() {
        if (target != null) {
            target!!.offTrigger()
        }
    }

}