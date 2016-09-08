package edu.csh.chase.ChipsChallenge.Engine

import java.util.ArrayList

data class Inspection(name: String, description: String, map: ArrayList<Pair<String, String>>? = null) :
        Iterable<Pair<String, String>> {

    private val inspectionList: ArrayList<Pair<String, String>> = ArrayList(2)

    val indices: IntRange
        get() {
            return inspectionList.indices
        }

    override fun iterator(): MutableIterator<Pair<String, String>> {
        return inspectionList.iterator()
    }

    init {
        inspectionList.add("name" to name)
        inspectionList.add("description" to description)
        if (map != null) inspectionList.addAll(map)
    }

    fun get(index: Int): Pair<String, String> {
        return inspectionList[index]
    }

}