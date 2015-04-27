package ChipsChallenge.Engine


public enum class IdType {
    UNIT
    OBJECT
    TILE
}

val counter: Int by CounterDelegate();

data class Id(val type: IdType, val id: Int = counter) {

    override fun equals(other: Any?): Boolean {
        if (other is Id) {
            return equals(other);
        }
        return false;
    }

    fun equals(id: Id): Boolean {
        return this.id == id.id && this.type == id.type
    }

}