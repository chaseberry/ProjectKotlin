package ChipsChallenge.Engine

import java.util.ArrayList
import java.awt.image.BufferedImage
import ChipsChallenge.JSON.JSONObject
import ChipsChallenge.JSON.JSONArray

/**
 * Created by chase on 3/1/15.
 */
class ObjectLocationList(obj: ObjectBase) : ArrayList<ObjectBase>(1) {

    {
        add(obj)
    }

    val headObject: ObjectBase
        get() {
            return get(0)
        }

    val image: BufferedImage
        get() {
            return headObject.image
        }

    val tailObjects: ArrayList<ObjectBase>?
        get() {
            if (size() == 1) {
                return null
            }
            val list = ArrayList(this)
            list.remove(0)
            return list
        }

    val saveObject: JSONObject
        get() {
            return JSONObject()
                    .put("head", headObject.saveObject)
                    .put("tail", JSONArray(tailObjects))
        }

}