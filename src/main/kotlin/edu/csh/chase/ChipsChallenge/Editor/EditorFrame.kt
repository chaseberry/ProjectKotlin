package edu.csh.chase.ChipsChallenge.Editor

import edu.csh.chase.ChipsChallenge.Editor.Editor
import java.awt.Graphics
import java.awt.event.*
import java.awt.image.BufferedImage
import javax.swing.JButton
import javax.swing.JFrame
import javax.swing.JTextField
import javax.swing.WindowConstants

class EditorFrame(val editor: Editor) : JFrame() {

    val chipCountTextField = JTextField()

    val mouseListener = object : MouseListener {
        override fun mousePressed(e: MouseEvent) {
            requestFocus()
            editor.mouseBindings.mousePressed(e.button)
            editor.triggerUpdate()
        }

        override fun mouseReleased(e: MouseEvent) {
            editor.mouseBindings.mouseReleased(e.button)
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
            editor.mouseBindings.mouseLocation.x = (e.x - insets.left) / 32
            editor.mouseBindings.mouseLocation.y = (e.y - insets.top) / 32
        }

        override fun mouseDragged(e: MouseEvent) {
            editor.mouseBindings.mouseLocation.x = (e.x - insets.left) / 32
            editor.mouseBindings.mouseLocation.y = (e.y - insets.top) / 32
            if (editor.mouseBindings.mouseButtonIsPressed) {
                if (editor.drawMode) {
                    editor.triggerUpdate()
                }
            }
        }

    }

    val keyListener = object : KeyListener {
        override fun keyPressed(e: KeyEvent) {
            requestFocus()
            editor.keyBindings.keyPressed(e.keyCode)
            editor.triggerScreenMove()
            if (e.keyCode == KeyEvent.VK_M) {
                editor.cycleMode()
            }
        }

        override fun keyReleased(e: KeyEvent) {
            editor.keyBindings.keyReleased(e.keyCode)
        }

        override fun keyTyped(e: KeyEvent) {

        }

    }

    init {
        pack()
        setSize(insets.left + (32 * 9) + insets.right, insets.top + (32 * 10) + insets.bottom)
        defaultCloseOperation = WindowConstants.DISPOSE_ON_CLOSE
        addMouseListener(mouseListener)
        addMouseMotionListener(mouseMotionListener)
        addKeyListener(keyListener)
        layout = null
        isFocusable = true
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
        chipCountTextField.toolTipText = "Number of chips required to open the socket"

        add(chipCountTextField)
    }


    var image: BufferedImage? = null
        set(newImage) {
            field = newImage
            repaint()
        }

    override fun paint(graphics: Graphics) {
        super.paint(graphics)
        graphics.clearRect(0, 0, 9 * 32, 9 * 32)
        if (image != null) {
            graphics.drawImage(image, insets.left, insets.top, null)
        }
    }


}