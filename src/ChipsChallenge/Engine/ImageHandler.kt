package ChipsChallenge.Engine

import java.awt.image.BufferedImage
import java.net.URL
import javax.imageio.ImageIO
import kotlin.properties.Delegates

/**
 * Created by chase on 2/28/15.
 */

val fileUrl: String by Delegates.lazy {
    var fileName = ""
    for (filePart in (BaseObject() as java.lang.Object).getClass().getResource("").toString().split("/")) {
        if (filePart == "ChipsChallenge") {
            fileName += "$filePart/"
            break;
        }
        fileName += "$filePart/"
    }
    fileName
}


fun loadImage(imageSource: String?): BufferedImage? {
    if (imageSource == null) {
        return null
    }
    return ImageIO.read(URL("${fileUrl}Images/$imageSource"));
}

val floorImage: BufferedImage by Delegates.lazy {
    loadImage("floor.gif") as BufferedImage
}

val wallImage: BufferedImage by Delegates.lazy {
    loadImage("wall.gif") as BufferedImage
}

val waterImage: BufferedImage by Delegates.lazy {
    loadImage("water.gif") as BufferedImage
}

val finishImage: BufferedImage by Delegates.lazy {
    loadImage("finish.gif") as BufferedImage
}

val helpImage: BufferedImage by Delegates.lazy {
    loadImage("hint.gif") as BufferedImage
}

val iceImage: BufferedImage by Delegates.lazy {
    loadImage("ice.gif") as BufferedImage
}

val iceLeftUpImage: BufferedImage by Delegates.lazy {
    loadImage("curve-4-ice.gif") as BufferedImage
}

val iceRightUpImage: BufferedImage by Delegates.lazy {
    loadImage("curve-3-ice.gif") as BufferedImage
}

val iceLeftDownImage: BufferedImage by Delegates.lazy {
    loadImage("curve-1-ice.gif") as BufferedImage
}

val iceRightDownImage: BufferedImage by Delegates.lazy {
    loadImage("curve-2-ice.gif") as BufferedImage
}

val teleportImage: BufferedImage by Delegates.lazy {
    loadImage("teleporter.gif") as BufferedImage
}

val gravelImage: BufferedImage by Delegates.lazy {
    loadImage("gravel.gif") as BufferedImage
}

val forceFloorEast: BufferedImage by Delegates.lazy {
    loadImage("forcefloor-east.gif") as BufferedImage
}

val forceFloorNorth: BufferedImage by Delegates.lazy {
    loadImage("forcefloor-north.gif") as BufferedImage
}
val forceFloorSouth: BufferedImage by Delegates.lazy {
    loadImage("forcefloor-south.gif") as BufferedImage
}
val forceFloorWest: BufferedImage by Delegates.lazy {
    loadImage("forcefloor-west.gif") as BufferedImage
}

val forceFloorRandom: BufferedImage by Delegates.lazy {
    loadImage("forcefloor-random.gif") as BufferedImage
}

val toggleWallOpenImage: BufferedImage by Delegates.lazy {
    loadImage("toggle-off.gif") as BufferedImage
}

val toggleWallClosedImage: BufferedImage by Delegates.lazy {
    loadImage("toggle-on.gif") as BufferedImage
}

val blueKeyImage: BufferedImage by Delegates.lazy {
    loadImage("blue_key.gif") as BufferedImage
}

val redKeyImage: BufferedImage by Delegates.lazy {
    loadImage("red_key.gif") as BufferedImage
}

val yellowKeyImage: BufferedImage by Delegates.lazy {
    loadImage("yellow_key.gif") as BufferedImage
}

val greenKeyImage: BufferedImage by Delegates.lazy {
    loadImage("green_key.gif") as BufferedImage
}

val blueKLockImage: BufferedImage by Delegates.lazy {
    loadImage("blue_lock.gif") as BufferedImage
}

val redLockImage: BufferedImage by Delegates.lazy {
    loadImage("red_lock.gif") as BufferedImage
}

val yellowLockImage: BufferedImage by Delegates.lazy {
    loadImage("yellow_lock.gif") as BufferedImage
}

val greenLockImage: BufferedImage by Delegates.lazy {
    loadImage("green_lock.gif") as BufferedImage
}

val chipImage: BufferedImage by Delegates.lazy {
    loadImage("chip.gif") as BufferedImage
}

val dirtImage: BufferedImage by Delegates.lazy {
    loadImage("dirt.gif") as BufferedImage
}

val socketImage: BufferedImage by Delegates.lazy {
    loadImage("socket.gif") as BufferedImage
}

val blockImage: BufferedImage by Delegates.lazy {
    loadImage("block.gif") as BufferedImage
}

val greenButtonImage: BufferedImage by Delegates.lazy {
    loadImage("green_button.gif") as BufferedImage
}

val brownButtonImage: BufferedImage by Delegates.lazy {
    loadImage("brown_button.gif") as BufferedImage
}

val bearTrapImage: BufferedImage by Delegates.lazy {
    loadImage("beartrap.gif") as BufferedImage
}

val pinkBallImage: BufferedImage by Delegates.lazy {
    loadImage("ball.gif") as BufferedImage
}

val upArrowImage: BufferedImage by Delegates.lazy {
    loadImage("up_arrow.gif") as BufferedImage
}

val leftArrowImage: BufferedImage by Delegates.lazy {
    loadImage("left_arrow.gif") as BufferedImage
}

val downArrowImage: BufferedImage by Delegates.lazy {
    loadImage("down_arrow.gif") as BufferedImage
}

val rightArrowImage: BufferedImage by Delegates.lazy {
    loadImage("right_arrow.gif") as BufferedImage
}

val bugUpImage: BufferedImage by Delegates.lazy {
    loadImage("bug-up.gif") as BufferedImage
}

val bugDownImage: BufferedImage by Delegates.lazy {
    loadImage("bug-down.gif") as BufferedImage
}

val bugLeftImage: BufferedImage by Delegates.lazy {
    loadImage("bug-left.gif") as BufferedImage
}

val bugRightImage: BufferedImage by Delegates.lazy {
    loadImage("bug-right.gif") as BufferedImage
}

val tankUpImage: BufferedImage by Delegates.lazy {
    loadImage("tank-north.gif") as BufferedImage
}

val tankLeftImage: BufferedImage by Delegates.lazy {
    loadImage("tank-west.gif") as BufferedImage
}

val tankRightImage: BufferedImage by Delegates.lazy {
    loadImage("tank-east.gif") as BufferedImage
}

val tankDownImage: BufferedImage by Delegates.lazy {
    loadImage("tank-south.gif") as BufferedImage
}

val fireImage: BufferedImage by Delegates.lazy {
    loadImage("fire.gif") as BufferedImage
}

val suctionBootImage: BufferedImage by Delegates.lazy {
    loadImage("suction.gif") as BufferedImage
}

val iceSkatesImage: BufferedImage by Delegates.lazy {
    loadImage("skate.gif") as BufferedImage
}

val flippersImage: BufferedImage by Delegates.lazy {
    loadImage("flipper.gif") as BufferedImage
}

val fireBootImage: BufferedImage by Delegates.lazy {
    loadImage("fireboots.gif") as BufferedImage
}

val blueButtonImage: BufferedImage by Delegates.lazy {
    loadImage("blue_button.gif") as BufferedImage
}