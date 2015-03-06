package ChipsChallenge.Editor

import ChipsChallenge.Map.blankMap
import java.awt.image.BufferedImage
import ChipsChallenge.UI.getViewport
import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.ObjectManager
import ChipsChallenge.Engine.objectFromId
import ChipsChallenge.JSON.JSONObject
import ChipsChallenge.JSON.JSONArray
import ChipsChallenge.Engine.loadImage
import ChipsChallenge.Engine.KeyBindings
import javax.swing.JFileChooser
import javax.swing.filechooser.FileFilter
import java.io.File
import java.io.BufferedWriter
import java.io.FileWriter
import ChipsChallenge.Engine.Engine
import ChipsChallenge.Map.mapFromIds
import ChipsChallenge.Object.Block
import ChipsChallenge.Object.BrownButton
import ChipsChallenge.Object.Button
import ChipsChallenge.Engine.Triggerable
import java.util.ArrayList
import ChipsChallenge.Engine.UnitManager
import ChipsChallenge.Engine.unitFromId
import ChipsChallenge.Engine.Direction
import ChipsChallenge.Unit.DirectionalUnit
import ChipsChallenge.Engine.upArrowImage
import ChipsChallenge.Engine.downArrowImage
import ChipsChallenge.Engine.leftArrowImage
import ChipsChallenge.Engine.rightArrowImage

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

    fun start() {
        frame.image = buildFrameImage()
        frame.setVisible(true)
        pallet.setVisible(true)
    }

    fun buildFrameImage(): BufferedImage {
        val image = BufferedImage(9 * 32, 9 * 32, BufferedImage.TYPE_INT_ARGB)
        val viewport = getViewport(currentCenter, map)
        val mapImage = map.getImage(viewport)
        val g = image.getGraphics()

        //Draw the map
        g.drawImage(mapImage, 0, 0, null)

        //Draw objects
        for (obj in objects.objectsInViewport(viewport)) {
            g.drawImage(obj.image, (obj.location.x - viewport.xStart) * 32, (obj.location.y - viewport.yStart) * 32, null)
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
        val unit = unitManager.unitOnPoint(tileLocation)
        if (unit == null) {
            unitManager.add(unitFromId(pallet.currentUnit!!.id, tileLocation, Direction.UP))
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
        if (tile != pallet.currentTile) {
            map.setTile(tileLocation, pallet.currentTile)
        }
    }

    fun removeTile(tileLocation: Point) {
        val tile = map.getTile(tileLocation)
        if (tile != pallet.deleteTile) {
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
        if (objects.objects.containsKey(tileLocation) || pallet.currentObject == null) {
            val obj = objects.objects.get(tileLocation)
            if (obj is Block) {
                obj.cover(objectFromId(pallet.currentObject!!.id, tileLocation)!!)
            }
            return
        }
        val obj = (objectFromId((pallet.currentObject!!).id, tileLocation)!!)
        objects.add(obj, tileLocation)
        if (pallet.currentObject is BrownButton) {
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
        (pallet.buttonObject as Button).target = (triggeredObject as Triggerable)
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
        val mapObj = JSONObject()
        val mapArray = JSONArray()
        for (x in map.map) {
            val mapSection = JSONArray()
            for (tile in x) {
                mapSection.put(tile.tileId)
            }
            mapArray.put(mapSection)
        }

        val objArray = JSONArray()
        for (obj in objects.objects.values()) {
            objArray.put(JSONObject().put("id", obj.id).put("location", JSONArray().put(obj.location.x).put(obj.location.y)))
        }

        mapObj.put("tileMap", mapArray)
        mapObj.put("objects", objArray)
        mapObj.put("playerStartLocation", JSONArray().put(map.defaultPlayerLocation.x).put(map.defaultPlayerLocation.y))
        try {
            mapObj.put("chipCount", Integer.parseInt(frame.chipCountTextField.getText()))
        } catch(e: NumberFormatException) {
            mapObj.put("chipCount", 0)
        }
        return mapObj.toString()
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