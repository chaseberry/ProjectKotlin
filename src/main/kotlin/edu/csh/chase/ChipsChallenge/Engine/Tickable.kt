package edu.csh.chase.ChipsChallenge.Engine

import edu.csh.chase.ChipsChallenge.Engine.Engine

/**
 * Created by chase on 3/8/15.
 */
interface Tickable {
    fun onTick(engine: Engine)

}