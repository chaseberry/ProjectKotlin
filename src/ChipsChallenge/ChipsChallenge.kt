import ChipsChallenge.Editor.Editor
import ChipsChallenge.Engine.engineFromFile
import ChipsChallenge.Engine.fileUrl
import java.io.File
import java.net.URI

/**
 * Created by chase on 2/25/15.
 */


fun main(args: Array<String>) {
    //Editor(9, 9).start()
    (engineFromFile(File(URI("${fileUrl}Levels/level4.ccl"))))!!.start()
}