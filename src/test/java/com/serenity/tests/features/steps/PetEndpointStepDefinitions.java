package com.serenity.tests.features.steps;

import com.serenity.tests.features.endpoints.PetEndpoint;
import com.serenity.tests.features.model.Pet;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class PetEndpointStepDefinitions {

    private World world;
    private PetEndpoint petEndpoint = new PetEndpoint();

    public PetEndpointStepDefinitions(World world) {
        this.world = world;
    }
    public PetEndpointStepDefinitions() {
            world = new World();
    }

    @When("^I add a Pet to the system$")
    public void iAddAPetToTheSystem() {
        petEndpoint.addPet(world);
    }

    @Then("^the pet request response has a '(\\d+)' response code$")
    public void thePetRequestResponseHasTheCorrectResponseCode(Integer rc) {
        petEndpoint.verifyResponseStatusValue(world.getResponse(), rc.intValue());
    }

    @Then("^the pet requests response contains the correct json data$")
    public void thePetRequestsResponseContainsTheCorrectJsonData() {
        petEndpoint.verifyPetValuesAreAsExpected(world.getResponse(), petEndpoint.getDefaultPet());
    }

    @Given("^a pet exists$")
    public void aPetExists() {
        petEndpoint.addPet(world);
        petEndpoint.verifyResponseStatusValue(world.getResponse(), PetEndpoint.SUCCESS_STATUS_CODE);
    }

    @When("^I delete the pet$")
    public void iDeleteThePet() {
        world.setResponse(petEndpoint.deletePet(world.getRequest()));
    }

    @When("^then search for the pet by it's id$")
    public void thenSearchForThePetByItsId() {
        world.setResponse(petEndpoint.getPetById(world.getRequest()));
    }

    @Given("^a cat is '(.*?)'$")
    public void aCatIsAvailable(String availability) {
        petEndpoint.addPet(world,
                new Pet(16, "7:feline", "Pussy Cat", "image1:image2", "17:Furry", availability));
    }

    @Then("^I can add a pet that has multiple tags$")
    public void iCanAddAPetThatHasMultipleTags() {
        petEndpoint.addPet(world, new Pet(16, "45:rodent", "Rat", "image1", "17:Furry,29:cute,33:Small", "available"));
        petEndpoint.verifyResponseStatusValue(world.getResponse(), PetEndpoint.SUCCESS_STATUS_CODE);
    }

    @Then("^I can add a pet that has no tags$")
    public void iCanAddAPetThatHasNoTags() {
        petEndpoint.addPet(world, new Pet(16, "45:rodent", "Rat", "image1", "", "available"));
        petEndpoint.verifyResponseStatusValue(world.getResponse(), PetEndpoint.SUCCESS_STATUS_CODE);
    }

    @When("^I add a pet to the system without providing an id value$")
    public void iAddAPetToTheSystemWithoutProvidingAnIdValue() {
        petEndpoint.addPet(world,
                new Pet(null, "45:rodent", "Rat", "image1", "17:Furry,29:cute,33:Small", "available"));
    }

    @Then("^an id is automatically generated for the added pet$")
    public void anIdIsAutomaticallyGeneratedForTheAddedPet() {
        petEndpoint.verifyPetHasAnId(world.getResponse());
    }

    @When("^I add a pet and the json body is malformed and consists of only '(.*?)'$")
    public void iAddAPetAndTheJsonBodyIsMalformed(String body) {
        world.setRequest(petEndpoint.getRequestWithJSONHeaders());
        world.setResponse(petEndpoint.addPetWithBody(world.getRequest(), body));
    }

}
