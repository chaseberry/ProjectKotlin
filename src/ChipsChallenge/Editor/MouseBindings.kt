package ChipsChallenge.Editor

import ChipsChallenge.Map.Point

/**
 * Created by chase on 2/27/15.
 */
class MouseBindings() {

    val MOUSE_ONE = 1
    val MOUSE_TWO = 3

    var mouseOne = false
    var mouseTwo = false

    val mouseLocation = Point(0, 0)

    val mouseButtonIsPressed: Boolean
        get() {
            return mouseOne || mouseTwo
        }

    fun mousePressed(keyCode: Int) {
        when (keyCode) {
            MOUSE_ONE -> mouseOne = true
            MOUSE_TWO -> mouseTwo = true
        }
    }

    fun mouseReleased(keyCode: Int) {
        when (keyCode) {
            MOUSE_ONE -> mouseOne = false
            MOUSE_TWO -> mouseTwo = false
        }
    }

}