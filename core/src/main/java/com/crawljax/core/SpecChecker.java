package com.crawljax.core;

import com.crawljax.core.state.StateVertex;
import java.util.List;
import java.util.ArrayList;


public class SpecChecker {
    /*
    * input:
    * a DOM state and a specification list, each list entry containing:
    * (1) one or more identification means for a UI element
    * (2) whether the element is a clickable
    * output:
    * return for each element in the specification list, whether it is present in the given DOM state
    */

    // a list of constraints, a node for each ui element
    private List<StateConstraints> list;
    StateVertex state;

    /**
	 * @param state - the current DOM
     * @param list - a list of identifications means for each ui element
	 */
    public SpecChecker(StateVertex state, List<StateConstraints> list) {
        // work in progress
    }
}