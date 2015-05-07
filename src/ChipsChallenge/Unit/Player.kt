package ChipsChallenge.Unit

import ChipsChallenge.Engine.*
import ChipsChallenge.Map.Tiles.ForceFloor

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
        //Force an override when on force floor
        if (engine.map.getTile(location) is ForceFloor && currentMove == 1) {
            if (engine.keyBindings.isKeyPressed) {
                val tile = engine.map.getTile(location) as ForceFloor
                when (true) {
                    engine.keyBindings.up && (Direction.UP in tile.getAllowedOverrideDirections()) -> {
                        moveUp(engine)
                        return
                    }
                    engine.keyBindings.down && (Direction.DOWN in tile.getAllowedOverrideDirections()) -> {
                        moveDown(engine)
                        return
                    }
                    engine.keyBindings.left && (Direction.LEFT in tile.getAllowedOverrideDirections()) -> {
                        moveLeft(engine)
                        return
                    }
                    engine.keyBindings.right && (Direction.RIGHT in tile.getAllowedOverrideDirections()) -> {
                        moveRight(engine)
                        return
                    }
                }
            }
        }
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

    override fun canSurviveInWater(): Boolean {
        return inventory.hasFlippers
    }

    override fun canSurviveInFire(): Boolean {
        return inventory.hasFireBoots
    }

}