package ChipsChallenge.Unit

import ChipsChallenge.Engine.Engine
import ChipsChallenge.Engine.Point
import ChipsChallenge.Engine.UnitBase
import ChipsChallenge.Engine.loadImage

class Player(location: Point) : UnitBase(-1, location) {

    init {
        imageSet.put("up", loadImage("chip-north.gif"))
        imageSet.put("down", loadImage("chip-south.gif"))
        imageSet.put("left", loadImage("chip-west.gif"))
        imageSet.put("right", loadImage("chip-east.gif"))
        image = imageSet.get("down")
    }

    val inventory = PlayerInventory()

    override fun onTick(engine: Engine) {
        super.onTick(engine)
        if (currentMove == 0) {
            when (true) {
                engine.keyBindings.up -> {
                    moveUp(engine)
                    return;
                }
                engine.keyBindings.down -> {
                    moveDown(engine)
                    return
                }
                engine.keyBindings.left -> {
                    moveLeft(engine)
                    return
                }
                engine.keyBindings.right -> {
                    moveRight(engine)
                    return
                }
            }
        }
    }

    override fun moveUp(engine: Engine) {
        image = imageSet.get("up")
        super.moveUp(engine)
    }

    override fun moveDown(engine: Engine) {
        image = imageSet.get("down")
        super.moveDown(engine)
    }

    override fun moveLeft(engine: Engine) {
        image = imageSet.get("left")
        super.moveLeft(engine)
    }

    override fun moveRight(engine: Engine) {
        image = imageSet.get("right")
        super.moveRight(engine)
    }

}