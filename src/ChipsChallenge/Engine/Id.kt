package ChipsChallenge.Engine

import ChipsChallenge.JSON.JSONObject

fun idTypeFromInt(i: Int): IdType {
    return when (i) {
        0 -> IdType.UNIT
        1 -> IdType.OBJECT
        else -> IdType.TILE;
    }
}

public enum class IdType(val typeInt: Int) {
    UNIT : IdType(0)
    OBJECT : IdType(1)
    TILE : IdType(2)
}

fun idFromJson(obj: JSONObject): Id {
    return Id(idTypeFromInt(obj.getInt("type")), obj.getInt("id"))
}

val counter: Int by CounterDelegate();

data class Id(val type: IdType, val id: Int = counter) {

    override fun equals(other: Any?): Boolean {
        if (other is Id) {
            return equals(other);
        }
        return false;
    }

    fun getJson(): JSONObject {
        return JSONObject().put("type", type.typeInt).put("id", id)
    }

    fun equals(id: Id): Boolean {
        return this.id == id.id && this.type == id.type
    }

}