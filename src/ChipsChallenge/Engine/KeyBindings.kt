package ChipsChallenge.Engine

data class KeyBindings(val KEY_CODE_UP: Int = 38,
                       val KEY_CODE_DOWN: Int = 40,
                       val KEY_CODE_LEFT: Int = 37,
                       val KEY_CODE_RIGHT: Int = 39) {

    var up = false
    var down = false
    var left = false
    var right = false

    val isKeyPressed: Boolean
        get() {
            return up || down || left || right
        }

    public fun keyPressed(keyCode: Int) {
        when (keyCode) {
            KEY_CODE_UP -> up = true
            KEY_CODE_DOWN -> down = true
            KEY_CODE_LEFT -> left = true
            KEY_CODE_RIGHT -> right = true
        }
    }

    public fun keyReleased(keyCode: Int) {
        when (keyCode) {
            KEY_CODE_UP -> up = false
            KEY_CODE_DOWN -> down = false
            KEY_CODE_LEFT -> left = false
            KEY_CODE_RIGHT -> right = false
        }
    }


}