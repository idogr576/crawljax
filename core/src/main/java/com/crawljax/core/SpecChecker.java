package com.crawljax.core;

import com.crawljax.core.state.StateVertex;
import java.util.List;
import java.util.ArrayList;

// needs to be created using a singleton: only one instance required.
public class SpecChecker {

    private List<StateConstraints> list; // the name will be changed
    private int stateConstraintsPtr;

    /**
     * @param list             - a list of StateConstraints
     * @param stateConstraintsPtr - the index in the ArrayList of the current
     *                         DOM state constraints
     */

    // constructor
    public SpecChecker(List<StateConstraints> list) {
        this.list = new ArrayList<StateConstraints>(list);
        this.stateConstraintsPtr = 0; // the head of the list
    }

    public boolean isSatisfied() {
        if (list.get(stateConstraintsPtr).isSatisfied()) {
            stateConstraintsPtr++; // move the pointer to the next stateConstraints
            return true;
        }
        return false;
    }
}