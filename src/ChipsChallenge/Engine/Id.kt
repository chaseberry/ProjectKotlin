package ChipsChallenge.Engine


public enum class IdType {
    UNIT
    OBJECT
    TILE
}

val counter: Int by CounterDelegate();

data class Id(val type: IdType, val id: Int = counter) {

}