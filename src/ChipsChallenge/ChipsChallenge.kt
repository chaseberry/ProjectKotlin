import ChipsChallenge.Engine.engineFromFile
import ChipsChallenge.Engine.loadLevel
import java.net.URL

/**
 * Created by chase on 2/25/15.
 */


fun main(args: Array<String>) {
    val engine = engineFromFile((loadLevel("level1")))
    if (engine != null) {
        print("notnull")
        engine.start()
    }
    println("null")
}