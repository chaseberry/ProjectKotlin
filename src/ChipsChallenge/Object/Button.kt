package ChipsChallenge.Object

import ChipsChallenge.Engine.ObjectBase
import java.awt.image.BufferedImage
import ChipsChallenge.Map.Point

/**
 * Created by chase on 3/2/15.
 */
abstract class Button(id: Int, location: Point, image: BufferedImage) : ObjectBase(id, location, image) {}
