package ChipsChallenge.Editor

import ChipsChallenge.Map.blankMap

/**
 * Created by chase on 2/27/15.
 */
class Editor(x: Int, y: Int) {

    val map = blankMap(x, y)

    val frame = EditorFrame(this)

    val mouseBindings = MouseBindings()

    fun start() {
        frame.setVisible(true)
    }



}