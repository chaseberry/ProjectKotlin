package edu.csh.chase.ChipsChallenge.Engine

/**
 * Created by chase on 3/2/15.
 */
interface Triggerable {

    fun onTrigger()

    fun offTrigger()

    fun clone(): Triggerable

}