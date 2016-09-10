package edu.csh.chase.ChipsChallenge.Engine

import edu.csh.chase.kjson.JsonObject
import java.awt.image.BufferedImage

abstract class EngineObjectBase(var location: Point, open var image: BufferedImage?) : Tickable {

    abstract fun getSaveObject(): JsonObject

}