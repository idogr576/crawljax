package com.crawljax.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import com.crawljax.core.state.StateVertex;

// needs to be created using a singleton: only one instance required.
public class SpecChecker {

    private List<StateConstraints> stateConstraintsList;
    private int index;

    /**
     * @param stateConstraintsList - a list of StateConstraints
     * @param index - the index in the ArrayList of the current
     *                         DOM state constraints
     */

    // constructor
    public SpecChecker(List<StateConstraints> stateConstraintsList) {
        this.stateConstraintsList = new ArrayList<StateConstraints>(stateConstraintsList);
        this.index = 0; // the head of the list
    }

    /**
     * @param state - the current DOM state
     * @throws IOException - failed or interrupted I/O operations
     * @throws XPathExpressionException - error in an XPath expression
     */
    public boolean isSatisfied(StateVertex state) throws IOException, XPathExpressionException
    {
        if(stateConstraintsList.get(index).isSatisfied(state.getDocument())){
            index++; // move the pointer to the next stateConstraints
            return true;
        }
        return false;
    }
}