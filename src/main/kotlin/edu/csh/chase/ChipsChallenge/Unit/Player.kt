package edu.csh.chase.ChipsChallenge.Unit

import edu.csh.chase.ChipsChallenge.Map.Tiles.ForceFloor
import edu.csh.chase.ChipsChallenge.Unit.PlayerInventory
import edu.csh.chase.ChipsChallenge.Engine.*

class Player(location: Point, val keyBindings: KeyBindings) : UnitBase(-1, location) {

    init {
        imageSet.put("up", loadImage("chip-north.gif"))
        imageSet.put("down", loadImage("chip-south.gif"))
        imageSet.put("left", loadImage("chip-west.gif"))
        imageSet.put("right", loadImage("chip-east.gif"))
        image = imageSet["down"]
    }

    val inventory = PlayerInventory()

    var dead = false

    override fun onTick(engine: Engine) {
        if (dead) {
            return
        }
        //Force an override when on force floor
        if (engine.map.getTile(location) is ForceFloor && currentMove == 1) {
            if (keyBindings.isKeyPressed) {
                val tile = engine.map.getTile(location) as ForceFloor
                when (true) {
                    keyBindings.up && (Direction.UP in tile.getAllowedOverrideDirections()) -> {
                        moveUp(engine)
                        return
                    }
                    keyBindings.down && (Direction.DOWN in tile.getAllowedOverrideDirections()) -> {
                        moveDown(engine)
                        return
                    }
                    keyBindings.left && (Direction.LEFT in tile.getAllowedOverrideDirections()) -> {
                        moveLeft(engine)
                        return
                    }
                    keyBindings.right && (Direction.RIGHT in tile.getAllowedOverrideDirections()) -> {
                        moveRight(engine)
                        return
                    }
                }
            }
        }
        super.onTick(engine)
        if (currentMove == 0) {
            when (true) {
                keyBindings.up -> {
                    moveUp(engine)
                    return
                }
                keyBindings.down -> {
                    moveDown(engine)
                    return
                }
                keyBindings.left -> {
                    moveLeft(engine)
                    return
                }
                keyBindings.right -> {
                    moveRight(engine)
                    return
                }
            }
        }
    }

    override fun moveUp(engine: Engine) {
        image = imageSet["up"]
        super.moveUp(engine)
    }

    override fun moveDown(engine: Engine) {
        image = imageSet["down"]
        super.moveDown(engine)
    }

    override fun moveLeft(engine: Engine) {
        image = imageSet["left"]
        super.moveLeft(engine)
    }

    override fun moveRight(engine: Engine) {
        image = imageSet["right"]
        super.moveRight(engine)
    }

    override fun canSurviveInWater(): Boolean {
        return inventory.hasFlippers
    }

    override fun canSurviveInFire(): Boolean {
        return inventory.hasFireBoots
    }

}