package com.crawljax.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.xpath.XPathExpressionException;

import com.crawljax.core.state.StateVertex;

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
     * @throws IOException
     * @throws XPathExpressionException
     */
    public boolean isSatisfied(StateVertex state) throws IOException, XPathExpressionException
    {
        if(list.get(index).isSatisfied(state.getDocument())){
            index++; // move the pointer to the next stateConstraints
            return true;
        }
        return false;
    }
}