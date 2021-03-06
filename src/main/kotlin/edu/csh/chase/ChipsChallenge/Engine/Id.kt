package edu.csh.chase.ChipsChallenge.Engine

import edu.csh.chase.kjson.Json
import edu.csh.chase.kjson.JsonObject

enum class IdType(val typeInt: Int) {
    UNIT(0),
    OBJECT(1),
    TILE(2);

    companion object {
        fun fromInt(i: Int): IdType = when (i) {
            0 -> IdType.UNIT
            1 -> IdType.OBJECT
            else -> IdType.TILE;
        }
    }

}

val uCounter: Int by CounterDelegate();
val oCounter: Int by CounterDelegate();
val tCounter: Int by CounterDelegate();

data class Id(val type: IdType, val id: Int = if (type == IdType.UNIT) uCounter else if (type == IdType.OBJECT) oCounter else tCounter) {

    override fun equals(other: Any?): Boolean {
        return other is Id && other.id == id && other.type == type
    }

    fun getJson(): JsonObject {
        return Json("type" to type.typeInt, "id" to id)
    }


}