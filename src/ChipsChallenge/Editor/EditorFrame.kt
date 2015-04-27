package ChipsChallenge.Editor

import javax.swing.JFrame
import java.awt.image.BufferedImage
import java.awt.Graphics
import java.awt.event.MouseListener
import java.awt.event.MouseEvent
import java.awt.event.MouseMotionListener
import java.awt.event.KeyListener
import java.awt.event.KeyEvent
import javax.swing.JButton
import javax.swing.WindowConstants
import javax.swing.JTextField

/**
 * Created by chase on 2/27/15.
 */
class EditorFrame(val editor: Editor) : JFrame() {

    val chipCountTextField = JTextField()

    val mouseListener = object : MouseListener {
        override fun mousePressed(e: MouseEvent) {
            requestFocus()
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
            requestFocus()
            editor.keyBindings.keyPressed(e.getKeyCode())
            editor.triggerScreenMove()
        }

        override fun keyReleased(e: KeyEvent) {
            editor.keyBindings.keyReleased(e.getKeyCode())
        }

        override fun keyTyped(e: KeyEvent) {

        }

    }

    init {
        pack()
        setSize(getInsets().left + (32 * 9) + getInsets().right, getInsets().top + (32 * 10) + getInsets().bottom)
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE)
        addMouseListener(mouseListener)
        addMouseMotionListener(mouseMotionListener)
        addKeyListener(keyListener)
        setLayout(null)
        setFocusable(true)
        requestFocus()

        val saveButton = JButton("Save!")
        saveButton.setLocation(15, 32 * 9)
        saveButton.setSize(64, 32)
        saveButton.addActionListener { editor.save() }
        add(saveButton)

        val testButton = JButton("Test!")
        testButton.setLocation(175, 32 * 9)
        testButton.setSize(64, 32)
        testButton.addActionListener { editor.testMap() }
        add(testButton)

        chipCountTextField.setSize(64, 32)
        chipCountTextField.setLocation(100, 32 * 9)
        chipCountTextField.setToolTipText("Number of chips required to open the socket")

        add(chipCountTextField)
    }


    public var image: BufferedImage? = null
        set(newImage) {
            $image = newImage
            repaint()
        }

    override fun paint(graphics: Graphics) {
        super.paint(graphics)
        graphics.clearRect(0, 0, 9 * 32, 9 * 32)
        if (image != null) {
            graphics.drawImage(image, getInsets().left, getInsets().top, null)
        }
    }


}