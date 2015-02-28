package ChipsChallenge.Map

/**
 * Created by chase on 2/26/15.
 */
data class Point(var x: Int, var y: Int) {

    override fun equals(other: Any?): Boolean {
        if (other != null && other is Point) {
            return x == other.x && y == other.y
        }
        return false
    }

    fun minus(point: Point): Point {
        return Point(x - point.x, y - point.y)
    }

    fun plus(point: Point): Point {
        return Point(x + point.x, y + point.y)
    }

}