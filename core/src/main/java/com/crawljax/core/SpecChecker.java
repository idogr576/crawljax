package com.crawljax.core;

import com.crawljax.core.state.Identification.How;
import com.crawljax.core.StateConstraints.ElementConstraints;
import com.crawljax.core.state.Identification;
import com.crawljax.core.state.StateVertex;

import java.util.List;
import java.util.ArrayList;
import java.lang.String;

// needs to be created using a singleton: only one instance required.
public class SpecChecker {

    private List<StateConstraints> list; // the name will be changed
    private int index;

    /**
     * @param list             - a list of StateConstraints
     * @param index - the index in the ArrayList of the current
     *                         DOM state constraints
     */

    // constructor
    public SpecChecker(List<StateConstraints> list) {
        this.list = new ArrayList<StateConstraints>(list);
        this.index = 0; // the head of the list
    }

    /**
     * @param state - the current DOM state
     */
    public boolean isSatisfied(StateVertex state)
    {
        if(list.get(index).isSatisfied(state.getDocument())){
            index++; // move the pointer to the next stateConstraints
            return true;
        }
        return false;
    }
}