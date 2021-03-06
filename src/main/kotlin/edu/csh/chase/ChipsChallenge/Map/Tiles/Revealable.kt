package edu.csh.chase.ChipsChallenge.Map.Tiles

import edu.csh.chase.ChipsChallenge.Engine.Id
import edu.csh.chase.ChipsChallenge.Engine.Point
import edu.csh.chase.ChipsChallenge.Map.Tile
import java.awt.image.BufferedImage

/**
 * Created by chase on 5/8/15.
 */
abstract class Revealable(val imageBase: BufferedImage, val imageRevealed: BufferedImage, typeId: Int, location: Point, uniqueId: Id) :
        Tile(imageBase, typeId, location, uniqueId) {

    override var image: BufferedImage? = null

        get():BufferedImage? {
            if (revealed) {
                return imageRevealed
            } else {
                return imageBase
            }
        }

    var revealed = false

    fun reveal() {
        revealed = true
    }

    abstract fun getEditorImage(): BufferedImage

}