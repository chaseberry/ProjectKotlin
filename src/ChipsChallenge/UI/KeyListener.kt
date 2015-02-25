package ChipsChallenge.UI

import java.awt.event.KeyEvent

/**
 * Created by chase on 2/25/15.
 */
class KeyListener(val frame: Frame) : java.awt.event.KeyListener {


    override fun keyPressed(e: KeyEvent) {
        frame.keyPressed(e.getKeyCode())
    }

    override fun keyReleased(e: KeyEvent) {
        frame.keyReleased(e.getKeyCode())
    }

    override fun keyTyped(e: KeyEvent) {
    }

}