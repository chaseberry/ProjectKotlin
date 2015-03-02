package ChipsChallenge.Engine

import kotlin.properties.Delegates
import java.awt.image.BufferedImage
import javax.imageio.ImageIO
import java.net.URL

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