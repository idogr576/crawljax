package com.crawljax.core;

import com.crawljax.core.state.Identification;
import com.crawljax.core.state.Identification.How;
import java.util.List;
import java.util.ArrayList;

public class StateConstraints {
    /*
     * this class gathers all of the DOM states constraints:
     * (1) a list of ElementConstaints - each entry containing a list of
     * identification means and isClickable flag (for a single ui-element)
     * (2) semantics on how to handle the list - AND, OR
     */

    public enum Semantics {
        AND, OR
    }
    /*
     * OR - the union of the constraints associated with the ui-elements
     * AND - the intersection of the constraints associated with the ui-elements
     */

    private class ElementConstraints {
        /*
         * inner class which implements a list of identifications means for a single
         * ui-element,
         * and a boolean flag to determine whether the element is clickable or not.
         */
        private List<Identification> identifications;
        private boolean isClickable;

        // constructor
        public StateConstraints(List<Identification> identifications, boolean isClickable) {
            this.identifications = new ArrayList<Identification>(identifications);
            this.isClickable = isClickable;
        }

        // return true if the ui element is clickable
        public boolean isClickable() {
            return isClickable;
        }

        // return the identification method in the list at the specified index
        public Identification getIdentificationAt(int index) {
            return identifications.get(index);
        }

        // return the entire list
        public List<Identification> getIdentifications(int index) {
            return identifications;
        }

        // set the ui elements to be clickable or not
        public void setIsClickable(boolean isClickable) {
            this.isClickable = isClickable;
        }

        // add a new identification means to the ui element
        public void addIdentification(Identification identification) {
            this.identifications.add(identification);
        }

        // search the list for a specefic identification method.
        // return the identification, or null if not found
        Identification getIdentificationMethod(How how) {
            for (Identification id : identifications) {
                if (id.getHow().equals(how)) {
                    return id;
                }
            }
            return null;
        }

        // remove a specefic identification method
        // return true if the element was removed and false if the element was not found
        public boolean removeIdentification(How how) {
            Identification id = getIdentificationMethod(how);
            if (id != null) {
                identifications.remove(id);
                return true;
            }
            return false;

        }

        // clear the identification list
        public void clearIdentifications() {
            identifications.clear();
        }
    }

    // a list of ElementConstraints objects which captures all the constraints on a
    // single DOM state.
    private List<ElementConstraints> stateConstraints;
    // the semantics - AND/OR
    private Semantics semantics;

    // constructor
    public StateConstraints(List<ElementConstraints> stateConstraints, Semantics semantics) {
        this.stateConstraints = new ArrayList<ElementConstraints>(stateConstraints);
        this.semantics = semantics;
    }

    // return true if the constraints were satisfied. otherwise, return false.
    // currently, the only constraint is the presence of elements in the DOM state
    public boolean isSatisfied() {
    
        // iterate over the ElementConstraints list
        for (ElementConstraints ec : stateConstraints) {
            if (semantics.equals(Semantics.OR) && ec.isClickable()) {
                // at least one ui-element satisfies the constraints, OR semantics
                return true;
            }
            if (semantics.equals(Semantics.AND) && !ec.isClickable()) {
                // at least one ui-element doesn't satisfy to constraints, AND semantics
                return false;
            }
        }
        return semantics.equals(Semantics.AND) ? true : false;
    }
}