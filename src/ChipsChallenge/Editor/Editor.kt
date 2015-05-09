package ChipsChallenge.Editor

import ChipsChallenge.Engine.*
import ChipsChallenge.JSON.JSONArray
import ChipsChallenge.Map.Tiles.*
import ChipsChallenge.Map.blankMap
import ChipsChallenge.Map.mapFromIds
import ChipsChallenge.Map.tileIdToTile
import ChipsChallenge.Object.*
import ChipsChallenge.UI.getViewport
import ChipsChallenge.Unit.DirectionalUnit
import java.awt.image.BufferedImage
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.util.ArrayList
import javax.swing.JFileChooser
import javax.swing.filechooser.FileFilter

/**
 * Created by chase on 2/27/15.
 */
class Editor(x: Int, y: Int) {

    val map = blankMap(x, y)

    val frame = EditorFrame(this)

    val objects = ObjectManager(null)

    val mouseBindings = MouseBindings()

    val currentCenter = Point(0, 0)

    val pallet = EditorPallet()

    val playerImage = loadImage("chip-south.gif")

    val keyBindings = KeyBindings()

    val unitManager = UnitManager(null)

    var rotateMode = false

    fun start() {
        frame.image = buildFrameImage()
        frame.setVisible(true)
        pallet.setVisible(true)
    }

    fun buildFrameImage(): BufferedImage {
        val image = BufferedImage(9 * 32, 9 * 32, BufferedImage.TYPE_INT_ARGB)
        val viewport = getViewport(currentCenter, map)
        val mapImage = map.getEditorImage(viewport)
        val g = image.getGraphics()

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

        g.drawImage(playerImage, (map.defaultPlayerLocation.x - viewport.xStart) * 32,
                (map.defaultPlayerLocation.y - viewport.yStart) * 32, null)

        return image
    }

    fun triggerUpdate() {
        val viewport = getViewport(currentCenter, map)
        val tileLocation = mouseBindings.mouseLocation + Point(viewport.xStart, viewport.yStart)
        when (pallet.palletStatus) {
            PalletStatus.TILE -> updateTile(tileLocation)
            PalletStatus.OBJECT -> updateObject(tileLocation)
            PalletStatus.PLAYER -> addPlayer(tileLocation)
            PalletStatus.TRIGGER -> applyTrigger(tileLocation)
            PalletStatus.UNIT -> updateUnit(tileLocation)
        }
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
        val obj = objects.objects.get(tileLocation)
        if (obj != null && obj is Cloner) {
            val tempUnit = unitFromId(pallet.currentUnit!!.typeId, tileLocation, Direction.UP)!!
            if (obj.template != null && tempUnit == obj.template) {
                if (obj.template is DirectionalUnit) {
                    (obj.template!! as DirectionalUnit).rotateDirection()
                    obj.direction = when (obj.direction) {
                        Direction.UP -> Direction.RIGHT
                        Direction.RIGHT -> Direction.DOWN
                        Direction.DOWN -> Direction.LEFT
                        Direction.LEFT -> Direction.UP
                    }
                }
            } else {
                obj.template = tempUnit
            }
            return
        }

        val unit = unitManager.unitOnPoint(tileLocation)

        if (unit == null) {
            unitManager.add(unitFromId(pallet.currentUnit!!.typeId, tileLocation, Direction.UP))
            return
        }
        if (unit is DirectionalUnit) {
            unit.rotateDirection()
        }
    }

    fun removeUnit(tileLocation: Point) {
        val index = unitManager.unitOnPointIndex(tileLocation)
        if (index != null) {
            unitManager.remove(index)
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
        val tile = map.getTile(tileLocation)
        if (tile == null) {
            return
        }

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
            map.setTile(tileLocation, pallet.currentTile)
        }
    }

    fun removeTile(tileLocation: Point) {
        val tile = map.getTile(tileLocation)
        if (tile == null) {
            return
        }
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
        if (pallet.currentObject == null) {
            return
        }
        if (objects.objects.containsKey(tileLocation)) {
            val obj = objects.objects.get(tileLocation)
            if (obj is Block && pallet.currentObject !is Block) {
                obj.cover(objectFromTypeId(pallet.currentObject!!.typeId, tileLocation)!!, null)
            }
            if (obj is Cloner && pallet.currentObject is Block) {
                if (obj.template != null) {
                    obj.direction = when (obj.direction) {
                        Direction.UP -> Direction.RIGHT
                        Direction.RIGHT -> Direction.DOWN
                        Direction.DOWN -> Direction.LEFT
                        Direction.LEFT -> Direction.UP
                    }
                    return
                }
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
        var triggeredObject = objects.objects.get(tileLocation)
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
            map.defaultPlayerLocation = tileLocation
        }
    }

    fun save() {
        val saveData = generateSave()
        val fileChooser = JFileChooser()
        fileChooser.setFileFilter(object : FileFilter() {
            override fun accept(f: File?): Boolean {
                return f != null && f.extension == ".ccl"
            }

            override fun getDescription(): String? {
                return "Chips Challenge Level"
            }

        })
        fileChooser.showSaveDialog(null)
        var file = fileChooser.getSelectedFile()
        if (file == null) {
            //ERROR
        }
        if (file.extension != "ccl") {
            file = File("${file.getPath()}.ccl")
        }
        if (!file.exists()) {
            file.createNewFile()
        }
        val writer = BufferedWriter(FileWriter(file))
        writer.write(saveData)
        writer.close()
    }

    fun generateSave(): String {
        val saveObj = map.getSaveObject()

        val objArray = JSONArray()
        for (obj in objects.objects.values()) {
            objArray.put(obj.getSaveObject())
        }

        saveObj.put("units", unitManager.getSaveObject())
        saveObj.put("objects", objArray)
        saveObj.put("playerStartLocation", JSONArray().put(map.defaultPlayerLocation.x).put(map.defaultPlayerLocation.y))
        try {
            saveObj.put("chipCount", Integer.parseInt(frame.chipCountTextField.getText()))
        } catch(e: NumberFormatException) {
            saveObj.put("chipCount", 0)
        }
        return saveObj.toString()
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
        try {
            map.chipTotal = Integer.parseInt(frame.chipCountTextField.getText())
        } catch(e: Exception) {

        }
        val objs = objects.clone()
        Engine(mapFromIds(Array(map.x) { x -> Array(map.y) { y -> map.map[x][y].tileId } }, map.defaultPlayerLocation, map.chipTotal),
                ArrayList(objs.objects.values()), unitManager.clone()).start()
    }

}