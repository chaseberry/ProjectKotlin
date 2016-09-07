package edu.csh.chase.ChipsChallenge.Engine

import edu.csh.chase.kjson.Json
import edu.csh.chase.kjson.JsonObject

fun pointFromJson(obj: JsonObject): Point {
    return Point(obj.getInt("x"), obj.getInt("y"))
}

data class Point(var x: Int, var y: Int) {

    val saveObject: JsonObject
        get() {
            return Json("x" to x, "y" to y)
        }

    override fun equals(other: Any?): Boolean {
        return other != null && other is Point && other.x == x && other.y == y
    }

    operator fun minus(point: Point): Point {
        return Point(x - point.x, y - point.y)
    }

    operator fun plus(point: Point): Point {
        return Point(x + point.x, y + point.y)
    }

}