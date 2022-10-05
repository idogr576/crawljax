package com.crawljax.examples.petclinic;

import com.crawljax.core.configuration.Form;
import com.crawljax.core.configuration.InputSpecification;
import com.crawljax.core.state.Identification;
import com.crawljax.forms.FormInput;

public class PetclinicForms {
    static void addOwner(InputSpecification inputSpecification) {
        Form addOwnerForm = new Form();
        addOwnerForm.inputField(FormInput.InputType.TEXT, new Identification(Identification.How.name, "firstName")).inputValues("pipi");
        addOwnerForm.inputField(FormInput.InputType.TEXT, new Identification(Identification.How.name, "lastName")).inputValues("yu");
        addOwnerForm.inputField(FormInput.InputType.TEXT, new Identification(Identification.How.name, "address")).inputValues("2710 N");
        addOwnerForm.inputField(FormInput.InputType.TEXT, new Identification(Identification.How.name, "city")).inputValues("Austin");
        addOwnerForm.inputField(FormInput.InputType.TEXT, new Identification(Identification.How.name, "telephone")).inputValues("5122005208");
        inputSpecification.setValuesInForm(addOwnerForm).beforeClickElement("button").withAttribute("value", "Add Owner");
    }

    static void addPet(InputSpecification inputSpecification) {
        Form addPetForm = new Form();
        addPetForm.inputField(FormInput.InputType.TEXT, new Identification(Identification.How.name, "name")).inputValues("water");
        addPetForm.inputField(FormInput.InputType.TEXT, new Identification(Identification.How.name, "birthDate")).inputValues("2020/06/30");
        addPetForm.inputField(FormInput.InputType.SELECT, new Identification(Identification.How.name, "type")).inputValues("cat");
        inputSpecification.setValuesInForm(addPetForm).beforeClickElement("button").withAttribute("value", "Save Pet");
    }

    static void editOwner(InputSpecification inputSpecification) {
        Form editOwnerForm = new Form();
        editOwnerForm.inputField(FormInput.InputType.TEXT, new Identification(Identification.How.name, "address")).inputValues("2710");
        inputSpecification.setValuesInForm(editOwnerForm).beforeClickElement("button").withAttribute("value", "Update Owner");
    }

    static void addVisit(InputSpecification inputSpecification) {
        Form addVisitForm = new Form();
        addVisitForm.inputField(FormInput.InputType.TEXT, new Identification(Identification.How.name, "description")).inputValues("sick");
        inputSpecification.setValuesInForm(addVisitForm).beforeClickElement("button").withAttribute("value", "Add Visit");
    }

    static void addVet(InputSpecification inputSpecification) {
        Form addVetForm = new Form();
        addVetForm.inputField(FormInput.InputType.TEXT, new Identification(Identification.How.name, "firstName")).inputValues("pipi");
        addVetForm.inputField(FormInput.InputType.TEXT, new Identification(Identification.How.name, "lastName")).inputValues("yu");
        addVetForm.inputField(FormInput.InputType.SELECT, new Identification(Identification.How.name, "specialties")).inputValues("surgery");
        inputSpecification.setValuesInForm(addVetForm).beforeClickElement("button").withAttribute("Set Vet", "Save Vet");
    }

    static void addType(InputSpecification inputSpecification){
        Form addTypeForm = new Form();
        addTypeForm.inputField(FormInput.InputType.TEXT, new Identification(Identification.How.name, "name")).inputValues("pig");
        inputSpecification.setValuesInForm(addTypeForm).beforeClickElement("button").withAttribute("value", "Save");
    }

}
