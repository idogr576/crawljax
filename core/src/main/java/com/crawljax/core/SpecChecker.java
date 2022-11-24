package com.crawljax.core;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;
import javax.xml.xpath.XPathExpressionException;

import com.crawljax.core.state.StateVertex;

@Singleton
public class SpecChecker {

    private static SpecChecker instance = null;
    private List<StateConstraints> stateConstraintsList;
    private int index;

    /**
     * @param instance             - the only instance of the classs
     * @param stateConstraintsList - a list of StateConstraints
     * @param index                - the index in the ArrayList of the current
     *                             DOM state constraints
     */

    // empty private constructor
    private SpecChecker() {

    }

    public static SpecChecker getInstance() {
        if (instance == null) {
            instance = new SpecChecker();
            instance.index = 0; // the head of the list
            instance.stateConstraintsList = new ArrayList<StateConstraints>();
        }
        return instance;
    }

    public void setStateConstraintsList(List<StateConstraints> stateConstraintsList) {
        this.stateConstraintsList = stateConstraintsList;
    }

    /**
     * @param state - the current DOM state
     * @throws IOException              - failed or interrupted I/O operations
     * @throws XPathExpressionException - error in an XPath expression
     */
    public boolean isSatisfied(StateVertex state) throws IOException, XPathExpressionException {
        if (stateConstraintsList.get(index).isSatisfied(state.getDocument())) {
            index++; // move the pointer to the next stateConstraints
            return true;
        }
        return false;
    }
}