package ChipsChallenge.Engine

import ChipsChallenge.JSON.JSONObject

fun pointFromJson(obj: JSONObject): Point {
    return Point(obj.getInt("x"), obj.getInt("y"))
}

data class Point(var x: Int, var y: Int) {

    val saveObject: JSONObject
        get() {
            return JSONObject().put("x", x).put("y", y)
        }

    override fun equals(other: Any?): Boolean {
        return other != null && other is Point && other.x == x && other.y == y
    }

    fun minus(point: Point): Point {
        return Point(x - point.x, y - point.y)
    }

    fun plus(point: Point): Point {
        return Point(x + point.x, y + point.y)
    }

}