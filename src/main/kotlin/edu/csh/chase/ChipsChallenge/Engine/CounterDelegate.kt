package edu.csh.chase.ChipsChallenge.Engine

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class CounterDelegate : ReadOnlyProperty<Any?, Int> {

    var counter = 0

    override fun getValue(thisRef: Any?, property: KProperty<*>): Int {
        counter++
        return counter - 1
    }

}