package edu.csh.chase.ChipsChallenge.Engine

/**
 * Created by chase on 4/27/15.
 */
class CounterDelegate {

    var counter = 0;

    fun get(ref: Any?, property: PropertyMetadata): Int {
        counter++;
        return counter - 1;
    }
}