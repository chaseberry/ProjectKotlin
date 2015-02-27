package ChipsChallenge.Editor

import ChipsChallenge.Map.blankMap
import java.awt.image.BufferedImage
import ChipsChallenge.UI.getViewport
import ChipsChallenge.Map.Point
import ChipsChallenge.UI.Viewport
import ChipsChallenge.Engine.ObjectManager
import ChipsChallenge.Engine.objectFromId
import ChipsChallenge.Engine.ObjectBase
import ChipsChallenge.JSON.JSONObject
import ChipsChallenge.JSON.JSONArray

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

        return image
    }

    fun triggerUpdate() {
        val viewport = getViewport(currentCenter, map)
        when (pallet.palletStatus) {
            PalletStatus.TILE -> updateTile(viewport)
            PalletStatus.OBJECT -> updateObject(viewport)
        }
        frame.image = buildFrameImage()
    }

    fun updateTile(viewport: Viewport) {
        val tileLocation = mouseBindings.mouseLocation - Point(viewport.xStart, viewport.yStart)
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

    fun updateObject(viewport: Viewport) {
        val tileLocation = mouseBindings.mouseLocation - Point(viewport.xStart, viewport.yStart)
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
        mapObj.put("tileMap", mapArray)

        val objArray = JSONArray()
        for (obj in objects.objects.values()) {
            objArray.put(JSONObject().put("id", obj.id).put("location", JSONArray().put(obj.location.x).put(obj.location.y)))
        }
        mapObj.put("objects", objArray)

        return mapObj.toString()
    }

}