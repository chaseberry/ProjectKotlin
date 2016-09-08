package edu.csh.chase.ChipsChallenge.Object

import ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Object.Block
import edu.csh.chase.ChipsChallenge.Unit.DirectionalUnit
import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.kjson.JsonObject
import java.awt.image.BufferedImage

/**
 * Created by chase on 3/2/15.
 */
class Cloner(location: Point, uniqueId: Id, var template: EngineObjectBase?, var direction: Direction) :
        ObjectBase(CLONER_TYPE_ID, location, clonerImage, uniqueId), Triggerable {

    override var image: BufferedImage? = null
        get():BufferedImage? {
            val img = BufferedImage(32, 32, BufferedImage.TYPE_INT_ARGB)
            val g = img.graphics
            g.drawImage(clonerImage, 0, 0, null)
            if (template != null) {
                g.drawImage(template!!.image, 0, 0, null)
            }
            return img
        }

    constructor(location: Point, template: EngineObjectBase?, direction: Direction) :
    this(location, Id(IdType.OBJECT), template, direction) {
    }

    override fun onTrigger() {

    }

    override fun offTrigger() {
    }

    override fun getSaveObject(): JsonObject {
        val obj = super.getSaveObject()
        if (template == null) {
            obj.put("template", "null")
        } else {
            obj.put("template", template!!.getSaveObject())
        }
        obj.put("direction", direction)
        return obj
    }

    override fun canInteractorMove(engine: Engine, interactor: UnitBase): Boolean {
        return false // if the trap is active nothing can move
    }

    override fun clone(): Triggerable {
        return Cloner(location.copy(), uniqueId.copy(), template, direction)
    }

    override fun interact(engine: Engine, direction: Direction, interactor: UnitBase): ObjectResolution {
        return ObjectResolution.NOTHING
    }

    override fun onTick(engine: Engine) {
    }

    fun clone(engine: Engine) {
        if (template == null) {
            return
        }
        val targetTile = when (direction) {
            Direction.UP -> engine.map.getUp(location)
            Direction.LEFT -> engine.map.getLeft(location)
            Direction.RIGHT -> engine.map.getRight(location)
            Direction.DOWN -> engine.map.getDown(location)
        } ?: return
        if (template is Block) {
            val temp = Block(targetTile.location.copy())
            if (temp.canMoveToLocation(engine, targetTile.location)) {
                engine.objectManager.objects[targetTile.location] = temp
            }
        } else {
            val t = (template as UnitBase)
            val temp = unitFromId(t.typeId, targetTile.location, if (t is DirectionalUnit) t.direction else Direction.UP)!!
            if (temp.canMoveToTile(targetTile, direction, engine)) {
                engine.unitManager.add(temp)
            }
        }

    }

}
