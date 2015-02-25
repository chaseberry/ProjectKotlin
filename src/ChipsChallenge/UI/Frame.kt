package ChipsChallenge.UI

import javax.swing.JFrame
import java.awt.Graphics

/**
 * Created by chase on 2/25/15.
 */
class Frame : JFrame() {

    {
        setVisible(true)
        setSize(50, 50)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    }

    override fun paint(graphics: Graphics) {
        super.paint(graphics)

    }

}