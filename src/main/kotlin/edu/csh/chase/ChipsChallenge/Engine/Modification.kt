package edu.csh.chase.ChipsChallenge.Engine

data class Modification<T>(val type: Type, val item: T) {



    enum class Type {
        add,
        remove
    }

}