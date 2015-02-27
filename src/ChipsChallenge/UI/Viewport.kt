package ChipsChallenge.UI

import ChipsChallenge.Map.Point
import ChipsChallenge.Map.Map

/**
 * Created by chase on 2/25/15.
 */

fun getViewport(playerLocation: Point, map: Map): Viewport {
    return Viewport(
            if (playerLocation.x <= 3) (0) else (if (playerLocation.x >= (map.x - 4)) (map.x - 9) else (playerLocation.x - 4))
            , if (playerLocation.y <= 3) (0) else (if (playerLocation.y >= (map.y - 4)) (map.y - 9) else (playerLocation.y - 4))
    )
}

data class Viewport internal (val xStart: Int, val yStart: Int) {

    val xEnd: Int
        get() {
            return xStart + 8
        }

    val yEnd: Int
        get() {
            return yStart + 8
        }

}