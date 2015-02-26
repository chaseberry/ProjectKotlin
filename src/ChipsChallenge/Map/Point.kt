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

}