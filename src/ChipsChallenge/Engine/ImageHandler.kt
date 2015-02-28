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
            fileName += "$filePart/Images/"
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
    return ImageIO.read(URL("$fileUrl$imageSource"));
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