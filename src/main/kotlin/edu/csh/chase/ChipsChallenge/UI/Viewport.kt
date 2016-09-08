package edu.csh.chase.ChipsChallenge.UI

import edu.csh.chase.ChipsChallenge.Engine.Point
import edu.csh.chase.ChipsChallenge.Map.Map

/**
 * Created by chase on 2/25/15.
 */

fun getViewport(playerLocation: Point, map: Map): Viewport {
    return Viewport(
            Math.max(if (playerLocation.x <= 3) (0) else
                (if (playerLocation.x >= (map.x - 4)) (map.x - 9) else (playerLocation.x - 4)), 0)
            , Math.max(if (playerLocation.y <= 3) (0) else
        (if (playerLocation.y >= (map.y - 4)) (map.y - 9) else (playerLocation.y - 4)), 0),
            Math.min(8, (map.x - 1)),
            Math.min(8, (map.y - 1))
    )
}


fun pointInViewport(point: Point, viewport: Viewport): Boolean {
    return point.x in viewport.xStart..viewport.xEnd && point.y in viewport.yStart..viewport.yEnd
}

data class Viewport internal constructor(val xStart: Int, val yStart: Int, val xMod: Int, val yMod: Int) {

    val xEnd: Int
        get() {
            return xStart + xMod
        }

    val yEnd: Int
        get() {
            return yStart + yMod
        }

}