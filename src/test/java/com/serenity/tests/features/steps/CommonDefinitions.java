package com.serenity.tests.features.steps;

import com.serenity.tests.features.endpoints.BaseEndpoints;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class CommonDefinitions {

    private World world;
    private final BaseEndpoints be = new BaseEndpoints();

    public CommonDefinitions(World world) {
        this.world = world;
    }
    public CommonDefinitions() {
        world = new World();
    }

    @Given("^the Swagger Petstore API is available$")
    public void theSwaggerPetstoreApiIsAvailable() {
        System.out.println("Hi");
        String url = be.getBaseUrl() + "swagger.json";
        be.sendRequest(null, BaseEndpoints.GET_REQUEST, url, null).then().statusCode(200);
    }

    @Then("^the requests response will contain the value '(.*?)' for the '(.*?)' field$")
    public void iWillBeAbleToRunConnectedStepDefinitions(String val, String key) {
        be.verifyResponseKeyValues(key, val, world.getResponse());
    }

}