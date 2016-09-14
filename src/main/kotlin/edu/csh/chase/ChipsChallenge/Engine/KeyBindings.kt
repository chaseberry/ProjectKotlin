package edu.csh.chase.ChipsChallenge.Engine

data class KeyBindings(val keyCodeUp: Int = 38,
                       val keyCodeDown: Int = 40,
                       val keyCodeLeft: Int = 37,
                       val keyCodeRight: Int = 39) {

    var up = false
    var down = false
    var left = false
    var right = false

    val isKeyPressed: Boolean
        get() {
            return up || down || left || right
        }

    fun keyPressed(keyCode: Int) {
        when (keyCode) {
            keyCodeUp -> up = true
            keyCodeDown -> down = true
            keyCodeLeft -> left = true
            keyCodeRight -> right = true
        }
    }

    fun keyReleased(keyCode: Int) {
        when (keyCode) {
            keyCodeUp -> up = false
            keyCodeDown -> down = false
            keyCodeLeft -> left = false
            keyCodeRight -> right = false
        }
    }


}