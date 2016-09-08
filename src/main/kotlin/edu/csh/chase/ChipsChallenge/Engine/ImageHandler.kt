package edu.csh.chase.ChipsChallenge.Engine

import ChipsChallenge.Engine.BaseObject
import java.awt.image.BufferedImage
import java.net.URL
import javax.imageio.ImageIO
import kotlin.properties.Delegates

/**
 * Created by chase on 2/28/15.
 */

val fileUrl: String by lazy {
    var fileName = ""
    for (filePart in (BaseObject() as Object).`class`.getResource("").toString().split("/")) {
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

val floorImage: BufferedImage by lazy {
    loadImage("floor.gif") as BufferedImage
}

val wallImage: BufferedImage by lazy {
    loadImage("wall.gif") as BufferedImage
}

val waterImage: BufferedImage by lazy {
    loadImage("water.gif") as BufferedImage
}

val finishImage: BufferedImage by lazy {
    loadImage("finish.gif") as BufferedImage
}

val helpImage: BufferedImage by lazy {
    loadImage("hint.gif") as BufferedImage
}

val iceImage: BufferedImage by lazy {
    loadImage("ice.gif") as BufferedImage
}

val iceLeftUpImage: BufferedImage by lazy {
    loadImage("curve-4-ice.gif") as BufferedImage
}

val iceRightUpImage: BufferedImage by lazy {
    loadImage("curve-3-ice.gif") as BufferedImage
}

val iceLeftDownImage: BufferedImage by lazy {
    loadImage("curve-1-ice.gif") as BufferedImage
}

val iceRightDownImage: BufferedImage by lazy {
    loadImage("curve-2-ice.gif") as BufferedImage
}

val teleportImage: BufferedImage by lazy {
    loadImage("teleporter.gif") as BufferedImage
}

val gravelImage: BufferedImage by lazy {
    loadImage("gravel.gif") as BufferedImage
}

val forceFloorEast: BufferedImage by lazy {
    loadImage("forcefloor-east.gif") as BufferedImage
}

val forceFloorNorth: BufferedImage by lazy {
    loadImage("forcefloor-north.gif") as BufferedImage
}
val forceFloorSouth: BufferedImage by lazy {
    loadImage("forcefloor-south.gif") as BufferedImage
}
val forceFloorWest: BufferedImage by lazy {
    loadImage("forcefloor-west.gif") as BufferedImage
}

val forceFloorRandom: BufferedImage by lazy {
    loadImage("forcefloor-random.gif") as BufferedImage
}

val toggleWallOpenImage: BufferedImage by lazy {
    loadImage("toggle-off.gif") as BufferedImage
}

val toggleWallClosedImage: BufferedImage by lazy {
    loadImage("toggle-on.gif") as BufferedImage
}

val blueKeyImage: BufferedImage by lazy {
    loadImage("blue_key.gif") as BufferedImage
}

val redKeyImage: BufferedImage by lazy {
    loadImage("red_key.gif") as BufferedImage
}

val yellowKeyImage: BufferedImage by lazy {
    loadImage("yellow_key.gif") as BufferedImage
}

val greenKeyImage: BufferedImage by lazy {
    loadImage("green_key.gif") as BufferedImage
}

val blueKLockImage: BufferedImage by lazy {
    loadImage("blue_lock.gif") as BufferedImage
}

val redLockImage: BufferedImage by lazy {
    loadImage("red_lock.gif") as BufferedImage
}

val yellowLockImage: BufferedImage by lazy {
    loadImage("yellow_lock.gif") as BufferedImage
}

val greenLockImage: BufferedImage by lazy {
    loadImage("green_lock.gif") as BufferedImage
}

val chipImage: BufferedImage by lazy {
    loadImage("chip.gif") as BufferedImage
}

val dirtImage: BufferedImage by lazy {
    loadImage("dirt.gif") as BufferedImage
}

val socketImage: BufferedImage by lazy {
    loadImage("socket.gif") as BufferedImage
}

val blockImage: BufferedImage by lazy {
    loadImage("block.gif") as BufferedImage
}

val greenButtonImage: BufferedImage by lazy {
    loadImage("green_button.gif") as BufferedImage
}

val brownButtonImage: BufferedImage by lazy {
    loadImage("brown_button.gif") as BufferedImage
}

val bearTrapImage: BufferedImage by lazy {
    loadImage("beartrap.gif") as BufferedImage
}

val pinkBallImage: BufferedImage by lazy {
    loadImage("ball.gif") as BufferedImage
}

val upArrowImage: BufferedImage by lazy {
    loadImage("up_arrow.gif") as BufferedImage
}

val leftArrowImage: BufferedImage by lazy {
    loadImage("left_arrow.gif") as BufferedImage
}

val downArrowImage: BufferedImage by lazy {
    loadImage("down_arrow.gif") as BufferedImage
}

val rightArrowImage: BufferedImage by lazy {
    loadImage("right_arrow.gif") as BufferedImage
}

val bugUpImage: BufferedImage by lazy {
    loadImage("bug-up.gif") as BufferedImage
}

val bugDownImage: BufferedImage by lazy {
    loadImage("bug-down.gif") as BufferedImage
}

val bugLeftImage: BufferedImage by lazy {
    loadImage("bug-left.gif") as BufferedImage
}

val bugRightImage: BufferedImage by lazy {
    loadImage("bug-right.gif") as BufferedImage
}

val tankUpImage: BufferedImage by lazy {
    loadImage("tank-north.gif") as BufferedImage
}

val tankLeftImage: BufferedImage by lazy {
    loadImage("tank-west.gif") as BufferedImage
}

val tankRightImage: BufferedImage by lazy {
    loadImage("tank-east.gif") as BufferedImage
}

val tankDownImage: BufferedImage by lazy {
    loadImage("tank-south.gif") as BufferedImage
}

val fireImage: BufferedImage by lazy {
    loadImage("fire.gif") as BufferedImage
}

val suctionBootImage: BufferedImage by lazy {
    loadImage("suction.gif") as BufferedImage
}

val iceSkatesImage: BufferedImage by lazy {
    loadImage("skate.gif") as BufferedImage
}

val flippersImage: BufferedImage by lazy {
    loadImage("flipper.gif") as BufferedImage
}

val fireBootImage: BufferedImage by lazy {
    loadImage("fireboots.gif") as BufferedImage
}

val blueButtonImage: BufferedImage by lazy {
    loadImage("blue_button.gif") as BufferedImage
}

val bombImage: BufferedImage by lazy {
    loadImage("bomb.gif") as BufferedImage
}

val gliderUpImage: BufferedImage by lazy {
    loadImage("gliderUp.gif") as BufferedImage
}

val gliderDownImage: BufferedImage by lazy {
    loadImage("gliderDown.gif") as BufferedImage
}
val gliderLeftImage: BufferedImage by lazy {
    loadImage("gliderLeft.gif") as BufferedImage
}
val gliderRightImage: BufferedImage by lazy {
    loadImage("gliderRight.gif") as BufferedImage
}

val fireballImage: BufferedImage by lazy {
    loadImage("fireball.gif") as BufferedImage

}

val clonerImage: BufferedImage by lazy {
    loadImage("cloner.gif") as BufferedImage
}

val redButtonImage: BufferedImage by lazy {
    loadImage("red_button.gif") as BufferedImage
}

val wallBlueImage: BufferedImage by lazy {
    loadImage("wall-blue.gif") as BufferedImage
}

val thiefImage: BufferedImage by lazy {
    loadImage("thief.gif") as BufferedImage
}

val recessedWallImage: BufferedImage by lazy {
    loadImage("wall-recessed.gif") as BufferedImage
}

val walkerUpImage: BufferedImage by lazy {
    loadImage("walkerUp.gif") as BufferedImage
}

val walkerLeftImage: BufferedImage by lazy {
    loadImage("walkerLeft.gif") as BufferedImage
}

val parameciumUpImage: BufferedImage by lazy {
    loadImage("parameciumUp.gif") as BufferedImage
}

val parameciumLeftImage: BufferedImage by lazy {
    loadImage("parameciumLeft.gif") as BufferedImage
}

val blobImage: BufferedImage by lazy {
    loadImage("blob.gif") as BufferedImage
}