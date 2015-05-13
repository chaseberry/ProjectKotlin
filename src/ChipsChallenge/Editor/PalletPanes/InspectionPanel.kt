package ChipsChallenge.Editor.PalletPanes

import ChipsChallenge.Editor.Editor
import ChipsChallenge.Editor.EditorPallet
import ChipsChallenge.Engine.Point
import javax.swing.JPanel


class InspectionPanel(val editorPallet: EditorPallet) : JPanel() {

    init {
        setLayout(null)
        setSize(6 * 32, 6 * 32)
        setVisible(true)
    }

    fun inspect(location: Point, editor: Editor) {
        val tile = editor.map.getTile(location)
        val obj = editor.objects.objects.get(location)
        val unit = editor.unitManager.unitOnPoint(location)
        if (tile == null) {
            return
        }

    }
}