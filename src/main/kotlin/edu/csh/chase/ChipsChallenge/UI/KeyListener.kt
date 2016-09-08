package edu.csh.chase.ChipsChallenge.UI

import edu.csh.chase.ChipsChallenge.UI.Frame
import java.awt.event.KeyEvent
import java.awt.event.KeyListener

/**
 * Created by chase on 2/25/15.
 */
class KeyListener(val frame: Frame) : KeyListener {


    override fun keyPressed(e: KeyEvent) {
        frame.keyPressed(e.keyCode)
    }

    override fun keyReleased(e: KeyEvent) {
        frame.keyReleased(e.keyCode)
    }

    override fun keyTyped(e: KeyEvent) {
    }

}