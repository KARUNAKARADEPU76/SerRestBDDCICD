package com.serenity.tests.features.steps;

import com.serenity.tests.features.endpoints.OrderEndpoint;
import com.serenity.tests.features.model.Order;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;


public class OrderEndpointStepDefinitions {

    private World world;
    private OrderEndpoint orderEndpoint = new OrderEndpoint();

    public OrderEndpointStepDefinitions(World world) {
        this.world = world;
    }
    public OrderEndpointStepDefinitions() {
        world = new World();
    }

    @When("^I search for an order with an id value of '(.*?)'$")
    public void iSearchForAnOrderWithAnIdValueOfId(String id) {
        world.setResponse(orderEndpoint.getOrderById(world.getRequest(),id));
    }

    @When("^I place an order for a pet with an order id of '(\\d+)'$")
    public void iMakeAPostRequest(Integer orderId) {
        world.setRequest(orderEndpoint.getRequestWithJSONHeaders());
        world.setResponse(orderEndpoint.placeOrder(world.getRequest(), new Order(orderId, 0, 1, "2019-02-05T14:11:44.922Z", "placed", false)));
    }

    @Then("^the order request response has a '(\\d+)' response code$")
    public void theResponseHasTheCorrectResponseCode(Integer rc) {
        orderEndpoint.verifyResponseStatusValue(world.getResponse(), rc.intValue());
    }

    @Then("^the order requests response contains the correct json data$")
    public void theJsonResponseContainsTheCorrectData() {
        orderEndpoint.verifyResponseKeyValues("id", "58", world.getResponse());
    }

    @Given("^an order exists for a pet$")
    public void anOrderExistsForAPet() {
        world.setRequest(orderEndpoint.getRequestWithJSONHeaders());
        world.setResponse(orderEndpoint.placeOrder(world.getRequest()));
        orderEndpoint.verifyResponseStatusValue(world.getResponse(), OrderEndpoint.SUCCESS_STATUS_CODE);
    }

    @When("^I search for the order by its id$")
    public void iSearchForTheOrderByItsId() {
        world.setResponse(orderEndpoint.getOrderById(world.getRequest(), orderEndpoint.getDefaultOrder().getId().toString()));
    }

    @Then("^the complete order is returned$")
    public void theCompleteOrderIsReturned() {
        orderEndpoint.verifyOrderValuesAreAsExpected(world.getResponse(), orderEndpoint.getDefaultOrder());
    }

    @Then("^I am able to place an order for a cat$")
    public void iAmAbleToPlaceAnOrderForACat() {
        world.setResponse(orderEndpoint.placeOrder(world.getRequest(), new Order(33, world.getPet().getId(), 1, "2020-12-23T14:11:44.922Z", "placed", false)));
        orderEndpoint.verifyResponseStatusValue(world.getResponse(), orderEndpoint.SUCCESS_STATUS_CODE);
    }

    @Then("^I am not able to place an order for a cat$")
    public void iAmNotAbleToPlaceAnOrderForACat() {
        world.setResponse(orderEndpoint.placeOrder(world.getRequest(), new Order(22, world.getPet().getId(), 1, "2020-12-23:11:44.922Z", "placed", false)));
        orderEndpoint.verifyResponseStatusValue(world.getResponse(), orderEndpoint.INVALID_ORDER_STATUS_CODE);
    }
}
