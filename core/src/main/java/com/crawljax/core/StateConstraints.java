package com.crawljax.core;

import com.crawljax.core.state.Identification;
import com.crawljax.core.state.Identification.How;
import java.util.List;
import java.util.ArrayList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Node;

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
        public ElementConstraints(List<Identification> identifications, boolean isClickable) {
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
        public List<Identification> getIdentifications() {
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

        /**
         * @param how - the identification method
         * @return true if the element was removed and false if the element was not
         *         found
         */
        public boolean removeIdentification(How how) {
            Identification id = getIdentificationMethod(how);
            if (!id.equals(null)) {
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

    /**
     * @param elementConstraintsList -  a list of ElementConstraints objects which captures all the constraints on a
    // single DOM state.
     */
    private List<ElementConstraints> elementConstraintsList;
    // the semantics - AND/OR
    private Semantics semantics;

    // constructor
    public StateConstraints(List<ElementConstraints> elementConstraintsList, Semantics semantics) {
        this.elementConstraintsList = new ArrayList<ElementConstraints>(elementConstraintsList);
        this.semantics = semantics;
    }

    public List<ElementConstraints> getElementConstraintsList() {
        return elementConstraintsList;
    }

    /**
     * @return true if the current DOM state is clickable or not, according to the
     *         semantics:
     *         AND - everything, OR - at least one
     */
    public boolean isClickable() {
        // iterate over the ElementConstraints list
        for (ElementConstraints ec : elementConstraintsList) {
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

    /**
     * @param doc - the document
     * @return - whether or not the constraints were satisfied. in our case,
     *          it is the presence of elements in the DOM state
     * @throws XPathExpressionException
     */
    public boolean isSatisfied(Document doc) throws XPathExpressionException
    {
        for(ElementConstraints ec : elementConstraintsList)
        {
            for(Identification id : ec.identifications){
                // generate a new XPath varibale
                XPath xpath = XPathFactory.newInstance().newXPath();
                String expr = "//*[@" + id.getHow().toString() + "='" + id.getValue().toString() + "']"; // example: //*[@name='example_name']
                Node node = (Node)xpath.evaluate(expr , doc, XPathConstants.NODE);
                if(semantics.equals(Semantics.AND) && node.equals(null)){
                   return false; 
                }
                if(semantics.equals(Semantics.OR) && !node.equals(null)){
                    return true;
                }
            }
        }
        return semantics.equals(Semantics.AND) ? true : false;
    }
}
