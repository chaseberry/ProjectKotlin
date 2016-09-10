package edu.csh.chase.ChipsChallenge.Editor.PalletPanes

import edu.csh.chase.ChipsChallenge.Editor.Editor
import edu.csh.chase.ChipsChallenge.Editor.EditorPallet
import edu.csh.chase.ChipsChallenge.Engine.Point
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel


class InspectionPanel(val editorPallet: EditorPallet) : JPanel() {

    val locationLabel = JLabel()
    val nameLabel = JLabel()
    val descriptionLabel = JLabel()

    var tileName: String
        set(name) {
            nameLabel.text = name
        }
        get() {
            return nameLabel.text
        }

    var tileDescription: String
        set(desc) {
            descriptionLabel.text = desc
        }
        get() {
            return descriptionLabel.text
        }

    init {
        layout = null
        setSize(6 * 32, 6 * 32)
        isVisible = true
        initComponent(locationLabel, 0, 0, 32 * 3, 16)
        initComponent(nameLabel, 0, 15, 32 * 6, 16)
        initComponent(descriptionLabel, 0, 30, 32 * 6, 16)
    }

    fun initComponent(comp: JComponent = JLabel(), x: Int, y: Int, width: Int, height: Int) {
        comp.setLocation(x, y)
        comp.setSize(width, height)
        comp.isVisible = true
        add(comp)
    }

    fun inspect(location: Point, editor: Editor) {
        val tile = editor.map.getTile(location)
        val obj = editor.objects.objects[location]
        val unit = editor.unitManager.unitOnPoint(location)
        if (tile == null) {
            return
        }

        locationLabel.text = "Location: (${location.x}, ${location.y})"
        /* val inspection = tile.getInspectionData()
         tileName = inspection[0].second
         tileDescription = inspection[1].second*/
    }
}