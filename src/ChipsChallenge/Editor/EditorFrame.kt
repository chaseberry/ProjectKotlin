package ChipsChallenge.Editor

import javax.swing.JFrame
import java.awt.image.BufferedImage
import java.awt.Graphics
import java.awt.event.MouseListener
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionListener
import java.awt.event.KeyListener
import java.awt.event.KeyEvent

/**
 * Created by chase on 2/27/15.
 */
class EditorFrame(val editor: Editor) : JFrame() {


    val mouseListener = object : MouseListener {
        override fun mousePressed(e: MouseEvent) {
            editor.mouseBindings.mousePressed(e.getButton())
            editor.triggerUpdate()
        }

        override fun mouseReleased(e: MouseEvent) {
            editor.mouseBindings.mouseReleased(e.getButton())
        }

        override fun mouseEntered(e: MouseEvent) {
        }

        override fun mouseExited(e: MouseEvent) {
        }

        override fun mouseClicked(e: MouseEvent) {

        }

    }

    val mouseMotionListener = object : MouseMotionListener {
        override fun mouseMoved(e: MouseEvent) {
            editor.mouseBindings.mouseLocation.x = (e.getX() - getInsets().left) / 32
            editor.mouseBindings.mouseLocation.y = (e.getY() - getInsets().top) / 32
        }

        override fun mouseDragged(e: MouseEvent) {
            editor.mouseBindings.mouseLocation.x = (e.getX() - getInsets().left) / 32
            editor.mouseBindings.mouseLocation.y = (e.getY() - getInsets().top) / 32
            if (editor.mouseBindings.mouseButtonIsPressed) {
                editor.triggerUpdate()
            }
        }

    }

    val keyListener = object : KeyListener {
        override fun keyPressed(e: KeyEvent) {
            editor.keyBindings.keyPressed(e.getKeyCode())
            editor.triggerScreenMove()
        }

        override fun keyReleased(e: KeyEvent) {
            editor.keyBindings.keyReleased(e.getKeyCode())
        }

        override fun keyTyped(e: KeyEvent) {

        }

    }

    {
        pack()
        setSize(getInsets().left + (32 * 9) + getInsets().right, getInsets().top + (32 * 9) + getInsets().bottom)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
        addMouseListener(mouseListener)
        addMouseMotionListener(mouseMotionListener)
        addKeyListener(keyListener)
    }


    public var image: BufferedImage? = null
        set(newImage) {
            $image = newImage
            repaint()
        }

    override fun paint(graphics: Graphics) {
        super.paint(graphics)
        graphics.clearRect(0, 0, getWidth(), getHeight())
        if (image != null) {
            graphics.drawImage(image, getInsets().left, getInsets().top, null)
        }

    }


}