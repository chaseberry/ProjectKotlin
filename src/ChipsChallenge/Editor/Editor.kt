package ChipsChallenge.Editor

import ChipsChallenge.Map.blankMap
import java.awt.image.BufferedImage
import ChipsChallenge.UI.getViewport
import ChipsChallenge.Map.Point
import ChipsChallenge.Engine.ObjectManager
import ChipsChallenge.Engine.objectFromId
import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.JSON.JSONObject
import ChipsChallenge.JSON.JSONArray
import ChipsChallenge.Engine.loadImage
import ChipsChallenge.Engine.KeyBindings
import javax.swing.JFileChooser
import javax.swing.filechooser.FileFilter
import java.io.File
import java.io.BufferedWriter
import java.io.FileWriter

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
        }
        frame.image = buildFrameImage()
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
            return
        }
        objects.add((objectFromId((pallet.currentObject as ObjectBase).id, tileLocation) as ObjectBase), tileLocation)
    }

    fun removeObject(tileLocation: Point) {
        objects.objects.remove(tileLocation)
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

}