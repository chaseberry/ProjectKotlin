package edu.csh.chase.ChipsChallenge.Engine

import java.awt.image.BufferedImage
import java.io.File
import java.net.URL
import javax.imageio.ImageIO

val fileUrl: String by lazy {
    //BaseObject::class.java.classLoader.getResource("").toString()
    "file:/Users/chase/Code/ProjectKotlin/build/resources/main/"
}


fun loadImage(imageSource: String): BufferedImage {
    return ImageIO.read(URL("${fileUrl}Images/$imageSource"));
}

val floorImage: BufferedImage by lazy {
    loadImage("floor.gif")
}

val wallImage: BufferedImage by lazy {
    loadImage("wall.gif")
}

val waterImage: BufferedImage by lazy {
    loadImage("water.gif")
}

val finishImage: BufferedImage by lazy {
    loadImage("finish.gif")
}

val helpImage: BufferedImage by lazy {
    loadImage("hint.gif")
}

val iceImage: BufferedImage by lazy {
    loadImage("ice.gif")
}

val iceLeftUpImage: BufferedImage by lazy {
    loadImage("curve-4-ice.gif")
}

val iceRightUpImage: BufferedImage by lazy {
    loadImage("curve-3-ice.gif")
}

val iceLeftDownImage: BufferedImage by lazy {
    loadImage("curve-1-ice.gif")
}

val iceRightDownImage: BufferedImage by lazy {
    loadImage("curve-2-ice.gif")
}

val teleportImage: BufferedImage by lazy {
    loadImage("teleporter.gif")
}

val gravelImage: BufferedImage by lazy {
    loadImage("gravel.gif")
}

val forceFloorEast: BufferedImage by lazy {
    loadImage("forcefloor-east.gif")
}

val forceFloorNorth: BufferedImage by lazy {
    loadImage("forcefloor-north.gif")
}
val forceFloorSouth: BufferedImage by lazy {
    loadImage("forcefloor-south.gif")
}
val forceFloorWest: BufferedImage by lazy {
    loadImage("forcefloor-west.gif")
}

val forceFloorRandom: BufferedImage by lazy {
    loadImage("forcefloor-random.gif")
}

val toggleWallOpenImage: BufferedImage by lazy {
    loadImage("toggle-off.gif")
}

val toggleWallClosedImage: BufferedImage by lazy {
    loadImage("toggle-on.gif")
}

val blueKeyImage: BufferedImage by lazy {
    loadImage("blue_key.gif")
}

val redKeyImage: BufferedImage by lazy {
    loadImage("red_key.gif")
}

val yellowKeyImage: BufferedImage by lazy {
    loadImage("yellow_key.gif")
}

val greenKeyImage: BufferedImage by lazy {
    loadImage("green_key.gif")
}

val blueKLockImage: BufferedImage by lazy {
    loadImage("blue_lock.gif")
}

val redLockImage: BufferedImage by lazy {
    loadImage("red_lock.gif")
}

val yellowLockImage: BufferedImage by lazy {
    loadImage("yellow_lock.gif")
}

val greenLockImage: BufferedImage by lazy {
    loadImage("green_lock.gif")
}

val chipImage: BufferedImage by lazy {
    loadImage("chip.gif")
}

val dirtImage: BufferedImage by lazy {
    loadImage("dirt.gif")
}

val socketImage: BufferedImage by lazy {
    loadImage("socket.gif")
}

val blockImage: BufferedImage by lazy {
    loadImage("block.gif")
}

val greenButtonImage: BufferedImage by lazy {
    loadImage("green_button.gif")
}

val brownButtonImage: BufferedImage by lazy {
    loadImage("brown_button.gif")
}

val bearTrapImage: BufferedImage by lazy {
    loadImage("beartrap.gif")
}

val pinkBallImage: BufferedImage by lazy {
    loadImage("ball.gif")
}

val upArrowImage: BufferedImage by lazy {
    loadImage("up_arrow.gif")
}

val leftArrowImage: BufferedImage by lazy {
    loadImage("left_arrow.gif")
}

val downArrowImage: BufferedImage by lazy {
    loadImage("down_arrow.gif")
}

val rightArrowImage: BufferedImage by lazy {
    loadImage("right_arrow.gif")
}

val bugUpImage: BufferedImage by lazy {
    loadImage("bug-up.gif")
}

val bugDownImage: BufferedImage by lazy {
    loadImage("bug-down.gif")
}

val bugLeftImage: BufferedImage by lazy {
    loadImage("bug-left.gif")
}

val bugRightImage: BufferedImage by lazy {
    loadImage("bug-right.gif")
}

val tankUpImage: BufferedImage by lazy {
    loadImage("tank-north.gif")
}

val tankLeftImage: BufferedImage by lazy {
    loadImage("tank-west.gif")
}

val tankRightImage: BufferedImage by lazy {
    loadImage("tank-east.gif")
}

val tankDownImage: BufferedImage by lazy {
    loadImage("tank-south.gif")
}

val fireImage: BufferedImage by lazy {
    loadImage("fire.gif")
}

val suctionBootImage: BufferedImage by lazy {
    loadImage("suction.gif")
}

val iceSkatesImage: BufferedImage by lazy {
    loadImage("skate.gif")
}

val flippersImage: BufferedImage by lazy {
    loadImage("flipper.gif")
}

val fireBootImage: BufferedImage by lazy {
    loadImage("fireboots.gif")
}

val blueButtonImage: BufferedImage by lazy {
    loadImage("blue_button.gif")
}

val bombImage: BufferedImage by lazy {
    loadImage("bomb.gif")
}

val gliderUpImage: BufferedImage by lazy {
    loadImage("gliderUp.gif")
}

val gliderDownImage: BufferedImage by lazy {
    loadImage("gliderDown.gif")
}
val gliderLeftImage: BufferedImage by lazy {
    loadImage("gliderLeft.gif")
}
val gliderRightImage: BufferedImage by lazy {
    loadImage("gliderRight.gif")
}

val fireballImage: BufferedImage by lazy {
    loadImage("fireball.gif")

}

val clonerImage: BufferedImage by lazy {
    loadImage("cloner.gif")
}

val redButtonImage: BufferedImage by lazy {
    loadImage("red_button.gif")
}

val wallBlueImage: BufferedImage by lazy {
    loadImage("wall-blue.gif")
}

val thiefImage: BufferedImage by lazy {
    loadImage("thief.gif")
}

val recessedWallImage: BufferedImage by lazy {
    loadImage("wall-recessed.gif")
}

val walkerUpImage: BufferedImage by lazy {
    loadImage("walkerUp.gif")
}

val walkerLeftImage: BufferedImage by lazy {
    loadImage("walkerLeft.gif")
}

val parameciumUpImage: BufferedImage by lazy {
    loadImage("parameciumUp.gif")
}

val parameciumLeftImage: BufferedImage by lazy {
    loadImage("parameciumLeft.gif")
}

val blobImage: BufferedImage by lazy {
    loadImage("blob.gif")
}