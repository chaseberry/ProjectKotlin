package ChipsChallenge.Object

import ChipsChallenge.Engine.ObjectBase
import java.awt.image.BufferedImage
import ChipsChallenge.Map.Point
import java.util.ArrayList
import ChipsChallenge.Engine.Triggerable

/**
 * Created by chase on 3/2/15.
 */
abstract class Button(id: Int, location: Point, image: BufferedImage,
                      var targets: ArrayList<Triggerable>) : ObjectBase(id, location, image) {

    fun trigger() {
        targets.forEach { it.onTrigger() }
    }

    fun offTrigger() {
        targets.forEach { it.offTrigger() }
    }

}