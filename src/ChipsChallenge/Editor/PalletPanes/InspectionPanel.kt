package ChipsChallenge.Editor.PalletPanes

import ChipsChallenge.Editor.Editor
import ChipsChallenge.Editor.EditorPallet
import ChipsChallenge.Engine.Point
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel


class InspectionPanel(val editorPallet: EditorPallet) : JPanel() {

    val locationLabel = JLabel()

    init {
        setLayout(null)
        setSize(6 * 32, 6 * 32)
        setVisible(true)
        initComponent(locationLabel, 0, 0, 32 * 3, 16)
    }

    fun initComponent(comp: JComponent = JLabel(), x: Int, y: Int, width: Int, height: Int) {
        comp.setLocation(x, y)
        comp.setSize(width, height)
        comp.setVisible(true)
        add(comp)
    }

    fun inspect(location: Point, editor: Editor) {
        val tile = editor.map.getTile(location)
        val obj = editor.objects.objects.get(location)
        val unit = editor.unitManager.unitOnPoint(location)
        if (tile == null) {
            return
        }

        locationLabel.setText("Location: (${location.x}, ${location.y})")
        val inspection = tile.getInspectionData()
        for (z in inspection.indices) {
            initComponent(JLabel("${inspection[z].first}: ${inspection[z].second}"), 0, (z + 1) * 15, 32 * 3, 16)
        }
    }
}