package ChipsChallenge.UI

import ChipsChallenge.Map.Point
import ChipsChallenge.Map.Map

/**
 * Created by chase on 2/25/15.
 */

fun getViewport(playerLocation: Point, map: Map): Viewport {
    return Viewport(
            if (playerLocation.x <= 4) (0) else (if (playerLocation.x > (map.x - 5)) (map.x - 5) else (playerLocation.x - 4))
            , if (playerLocation.y <= 4) (0) else (if (playerLocation.y > (map.y - 5)) (map.y - 5) else (playerLocation.y - 4))
    )
}

data class Viewport internal (val xStart: Int, val yStart: Int) {

    val xEnd: Int
        get() {
            return xStart + 9
        }

    val yEnd: Int
        get() {
            return yStart + 9
        }

}