package edu.csh.chase.ChipsChallenge.Engine

import ChipsChallenge.JSON.JSONObject
import edu.csh.chase.ChipsChallenge.Engine.CounterDelegate
import edu.csh.chase.kjson.Json
import edu.csh.chase.kjson.JsonObject

fun idTypeFromInt(i: Int): IdType {
    return when (i) {
        0 -> IdType.UNIT
        1 -> IdType.OBJECT
        else -> IdType.TILE;
    }
}

enum class IdType(val typeInt: Int) {
    UNIT(0),
    OBJECT(1),
    TILE(2)
}

fun idFromJson(obj: JsonObject): Id {
    return Id(idTypeFromInt(obj.getInt("type")), obj.getInt("id"))
}

val uCounter: Int by CounterDelegate();
val oCounter: Int by CounterDelegate();
val tCounter: Int by CounterDelegate();

data class Id(val type: IdType, val id: Int = if (type == IdType.UNIT) uCounter else if (type == IdType.OBJECT) oCounter else tCounter) {

    override fun equals(other: Any?): Boolean {
        if (other is Id) {
            return equals(other);
        }
        return false;
    }

    fun getJson(): JsonObject {
        return Json("type" to type.typeInt, "id" to id)
    }

    fun equals(id: Id): Boolean {
        return this.id == id.id && this.type == id.type
    }

}