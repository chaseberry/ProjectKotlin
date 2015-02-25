package ChipsChallenge.Engine

import ChipsChallenge.UI.Frame
import ChipsChallenge.Map.Map

/**
 * Created by chase on 2/25/15.
 */

class Engine {

    val frame = Frame()
    val map = Map()
    
    public fun start() {
        frame.setVisible(true)
    }


}