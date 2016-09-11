package edu.csh.chase.ChipsChallenge.Editor

import edu.csh.chase.ChipsChallenge.Engine.*
import edu.csh.chase.ChipsChallenge.Map.Tiles.*
import edu.csh.chase.ChipsChallenge.Map.blankMap
import edu.csh.chase.ChipsChallenge.Map.tileFromId
import edu.csh.chase.ChipsChallenge.Map.tileIdToTile
import edu.csh.chase.ChipsChallenge.Object.*
import edu.csh.chase.ChipsChallenge.UI.getViewport
import edu.csh.chase.ChipsChallenge.Unit.DirectionalUnit
import java.awt.Color
import java.awt.image.BufferedImage
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.*
import javax.swing.JFileChooser
import javax.swing.filechooser.FileFilter

enum class Mode {
    DRAW,
    ROTATE,
    INSPECT
}

class Editor(x: Int, y: Int) {

    var mode = Mode.DRAW

    val level = Level(blankMap(x, y), ArrayList<ObjectBase>(), ArrayList<UnitBase>(), 0, 0, Point(0, 0), "", "", 0)

    val map = level.map

    val frame = EditorFrame(this)

    val objects = ObjectManager(null)

    val mouseBindings = MouseBindings()

    val currentCenter = Point(0, 0)

    val pallet = EditorPallet()

    val playerImage = loadImage("chip-south.gif")

    val keyBindings = KeyBindings()

    val unitManager = UnitManager(null)

    val drawMode: Boolean
        get():Boolean {
            return mode == Mode.DRAW
        }

    val rotateMode: Boolean
        get(): Boolean {
            return mode == Mode.ROTATE
        }

    val inspectMode: Boolean
        get(): Boolean {
            return mode == Mode.INSPECT
        }

    val playerLocation = level.playerStart

    fun start() {
        frame.image = buildFrameImage()
        frame.isVisible = true
        pallet.isVisible = true
    }

    fun buildFrameImage(): BufferedImage {
        val image = BufferedImage(9 * 32, 9 * 32, BufferedImage.TYPE_INT_ARGB)
        val viewport = getViewport(currentCenter, map)
        val mapImage = map.getEditorImage(viewport)
        val g = image.graphics

        //Draw the map
        g.drawImage(mapImage, 0, 0, null)

        //Draw objects
        for (obj in objects.objectsInViewport(viewport)) {
            g.drawImage(obj.image, (obj.location.x - viewport.xStart) * 32, (obj.location.y - viewport.yStart) * 32, null)
            if (obj is Cloner) {
                g.drawImage(
                        when (obj.direction) {
                            Direction.UP -> upArrowImage
                            Direction.DOWN -> downArrowImage
                            Direction.LEFT -> leftArrowImage
                            Direction.RIGHT -> rightArrowImage
                        }, (obj.location.x - viewport.xStart) * 32, (obj.location.y - viewport.yStart) * 32, null
                )
            }
        }

        for (unit in unitManager.unitsInViewPort(viewport)) {
            g.drawImage(unit.image, (unit.location.x - viewport.xStart) * 32, (unit.location.y - viewport.yStart) * 32, null)
            if (unit is DirectionalUnit) {
                g.drawImage(
                        when (unit.direction) {
                            Direction.UP -> upArrowImage
                            Direction.DOWN -> downArrowImage
                            Direction.LEFT -> leftArrowImage
                            Direction.RIGHT -> rightArrowImage
                        }, (unit.location.x - viewport.xStart) * 32, (unit.location.y - viewport.yStart) * 32, null
                )

            }
        }

        g.drawImage(playerImage, (playerLocation.x - viewport.xStart) * 32,
                (playerLocation.y - viewport.yStart) * 32, null)
        g.color = Color.BLACK
        g.drawString(when (mode) {
            Mode.DRAW -> "D"
            Mode.INSPECT -> "I"
            Mode.ROTATE -> "R"
        }, 0, 15)
        return image
    }

    fun triggerUpdate() {
        val viewport = getViewport(currentCenter, map)
        val tileLocation = mouseBindings.mouseLocation + Point(viewport.xStart, viewport.yStart)

        if (inspectMode) {
            pallet.inspectPanel.inspect(tileLocation, this)
            return
        }

        when (pallet.palletStatus) {
            PalletStatus.TILE -> updateTile(tileLocation)
            PalletStatus.OBJECT -> updateObject(tileLocation)
            PalletStatus.PLAYER -> addPlayer(tileLocation)
            PalletStatus.TRIGGER -> applyTrigger(tileLocation)
            PalletStatus.UNIT -> updateUnit(tileLocation)
        }

        objects.applyChanges()

        frame.image = buildFrameImage()
    }

    fun updateUnit(tileLocation: Point) {
        if (mouseBindings.mouseOne) {
            addUnit(tileLocation)
        } else if (mouseBindings.mouseTwo) {
            removeUnit(tileLocation)
        }
    }

    fun addUnit(tileLocation: Point) {
        val unit = unitManager.unitOnPoint(tileLocation)

        if (rotateMode ) {
            if (unit is DirectionalUnit) {
                unit.rotateDirection()
            }
            return
        }
        if (pallet.currentUnit == null) {
            return
        }
        val obj = objects.objects[tileLocation]
        if (obj != null && obj is Cloner) {
            val tempUnit = unitFromId(pallet.currentUnit!!.typeId, tileLocation, Direction.UP)
            if (obj.template != null && tempUnit == obj.template) {
                if (obj.template is DirectionalUnit) {
                    (obj.template!! as DirectionalUnit).rotateDirection()
                    obj.direction = obj.direction.rotateRight()
                }
            } else {
                obj.template = tempUnit
            }
            return
        }

        if (unit == null) {
            unitManager.add(unitFromId(pallet.currentUnit!!.typeId, tileLocation, Direction.UP))
            return
        }
    }

    fun removeUnit(tileLocation: Point) {
        val index = unitManager.unitOnPointIndex(tileLocation)
        if (index != null) {
            unitManager.removeAt(index)
        }
    }

    fun updateTile(tileLocation: Point) {
        if (mouseBindings.mouseOne) {
            addTile(tileLocation)
        } else if (mouseBindings.mouseTwo) {
            removeTile(tileLocation)
        }
    }

    fun addTile(tileLocation: Point) {
        val tile = map.getTile(tileLocation) ?: return

        if (rotateMode && tile is IceCorner && pallet.currentTile is IceCorner) {
            map.setTile(tileLocation, tileIdToTile(getNextIceCorner(tile.tileId), tileLocation))
            return
        }
        if (rotateMode && tile is ForceFloor && pallet.currentTile is ForceFloor) {
            map.setTile(tileLocation, tileIdToTile(getNextForceFloor(tile.tileId), tileLocation))
            return
        }

        if (rotateMode && tile is ToggleWall && pallet.currentTile is ToggleWall) {
            tile.onTrigger()
        }

        if (!tile.typeEquals(pallet.currentTile)) {
            map.setTile(tileLocation, tileFromId(pallet.currentTile.tileId, tileLocation))
        }
    }

    fun removeTile(tileLocation: Point) {
        val tile = map.getTile(tileLocation) ?: return
        if (!tile.typeEquals(pallet.deleteTile)) {
            map.setTile(tileLocation, pallet.deleteTile)
        }
    }

    fun updateObject(tileLocation: Point) {
        if (mouseBindings.mouseOne) {
            addObject(tileLocation)
        } else if (mouseBindings.mouseTwo) {
            removeObject(tileLocation)
        }
    }

    fun addObject(tileLocation: Point) {
        if (mode == Mode.ROTATE ) {
            val obj = objects.objects[tileLocation] ?: return
            if (obj is Cloner) {
                obj.direction = obj.direction.rotateRight()
            }
            return
        }
        if (pallet.currentObject == null) {
            return
        }
        if (objects.objects.containsKey(tileLocation)) {
            val obj = objects.objects[tileLocation]
            if (obj is Block && pallet.currentObject !is Block && pallet.currentObject !is Cloner) {
                obj.cover(objectFromTypeId(pallet.currentObject!!.typeId, tileLocation)!!, null)
            }
            if (obj is Cloner) {
                obj.template = objectFromTypeId(pallet.currentObject!!.typeId, tileLocation)!!
            }
            return
        }
        val obj = (objectFromTypeId((pallet.currentObject!!).typeId, tileLocation)!!)
        objects.add(obj, tileLocation)
        if (pallet.currentObject is BrownButton || pallet.currentObject is RedButton) {
            pallet.palletStatus = PalletStatus.TRIGGER
            pallet.buttonObject = obj
        }
    }

    fun removeObject(tileLocation: Point) {
        objects.objects.remove(tileLocation)
    }

    fun applyTrigger(tileLocation: Point) {
        var triggeredObject = objects.objects[tileLocation]
        pallet.palletStatus = PalletStatus.OBJECT
        if (triggeredObject == null || triggeredObject !is Triggerable) {
            return
        }
        if (pallet.buttonObject is RedButton && triggeredObject is Cloner) {
            (pallet.buttonObject as Button).target = triggeredObject.uniqueId

        } else if (pallet.buttonObject is BrownButton && triggeredObject is BearTrap) {
            (pallet.buttonObject as Button).target = triggeredObject.uniqueId
        }
        println((pallet.buttonObject as Button).target)
    }

    fun addPlayer(tileLocation: Point) {
        if (mouseBindings.mouseOne) {
            playerLocation.x = tileLocation.x
            playerLocation.y = tileLocation.y
        }
    }

    fun save() {
        syncLevel()
        val saveData = level.getSaveObject().toString()
        val fileChooser = JFileChooser()
        fileChooser.fileFilter = object : FileFilter() {
            override fun accept(f: File?): Boolean {
                return f != null && f.extension == ".ccl"
            }

            override fun getDescription(): String? {
                return "Chips Challenge Level"
            }

        }
        fileChooser.showSaveDialog(null)
        var file = fileChooser.selectedFile
        if (file == null) {
            //ERROR
        }
        if (file.extension != "ccl") {
            file = File("${file.path}.ccl")
        }
        if (!file.exists()) {
            file.createNewFile()
        }
        val writer = BufferedWriter(FileWriter(file))
        writer.write(saveData)
        writer.close()
    }

    fun triggerScreenMove() {
        when {
            keyBindings.up -> if (currentCenter.y != 0 ) currentCenter.y--
            keyBindings.down -> if (currentCenter.y != (map.y - 1) ) currentCenter.y++
            keyBindings.left -> if (currentCenter.x != 0) currentCenter.x--
            keyBindings.right -> if (currentCenter.x != (map.x - 1)) currentCenter.x++
        }
        frame.image = buildFrameImage()
    }

    fun testMap() {
        syncLevel()
        try {
            level.requiredChips = Integer.parseInt(frame.chipCountTextField.text)
        } catch(e: Exception) {

        }

        Engine(level.clone()).start()
    }

    fun syncLevel() {
        level.units.clear()
        level.objects.clear()
        level.units.addAll(unitManager)
        level.objects.addAll(objects.objects.values)
    }

    fun cycleMode() {
        mode = when (mode) {
            Mode.DRAW -> Mode.ROTATE
            Mode.ROTATE -> Mode.INSPECT
            else -> Mode.DRAW
        }
        frame.image = buildFrameImage()

    }

}