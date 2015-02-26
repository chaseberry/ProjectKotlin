package ChipsChallenge.Unit

import ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Engine.Engine

class Player(x: Int, y: Int) : UnitBase(x, y, "chip-south.gif") {

    val playerMoveSpeed = 5
    var currentMove = 0

    override fun onTick(engine: Engine) {
        if (currentMove > 0) {
            currentMove -= 1
        }
        if (currentMove == 0) {
            when (true) {
                engine.keyBindings.up -> {
                    
                    return;
                }
                engine.keyBindings.down -> {

                    return
                }
                engine.keyBindings.left -> {

                    return
                }
                engine.keyBindings.right -> {

                }
            }
        }
    }

}