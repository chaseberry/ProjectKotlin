import ChipsChallenge.Editor.Editor
import ChipsChallenge.Engine.engineFromFile
import ChipsChallenge.Engine.fileUrl
import java.io.File
import java.net.URI

fun main(args: Array<String>) {
    Editor(9, 9).start()
    //(engineFromFile(File(URI("${fileUrl}Levels/level4.ccl"))))!!.start()
}