$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("/Users/rasulm/eclipse-workspace/MSGEventEngine_Cucumber/eventengine-cucumber-java-framework/src/test/resources/EEFeatureFiles/EE_AdminUIEventPage.feature");
formatter.feature({
  "line": 2,
  "name": "Event Page",
  "description": "As an Admin UI User \nI Can land on Event Page\nSo that I can see Upcoming events. Search event. Paginate Event Data \u0026 Filter Event Display List",
  "id": "event-page",
  "keyword": "Feature",
  "tags": [
    {
      "line": 1,
      "name": "@eventpage"
    }
  ]
});
formatter.before({
  "duration": 2209887,
  "status": "passed"
});
formatter.before({
  "duration": 133790,
  "status": "passed"
});
formatter.background({
  "line": 7,
  "name": "User is Logged In",
  "description": "",
  "type": "background",
  "keyword": "Background"
});
formatter.step({
  "line": 8,
  "name": "I login to \"EE-Login-Page\" as \"SuperAdmin\"",
  "keyword": "Given "
});
formatter.match({
  "arguments": [
    {
      "val": "EE-Login-Page",
      "offset": 12
    },
    {
      "val": "SuperAdmin",
      "offset": 31
    }
  ],
  "location": "EE_Background_Steps.I_login_to(String,String)"
});
formatter.result({
  "duration": 328242830,
  "status": "passed"
});
formatter.scenario({
  "line": 11,
  "name": "Landing on Event Page \u0026 Validate Basic Elments",
  "description": "",
  "id": "event-page;landing-on-event-page-\u0026-validate-basic-elments",
  "type": "scenario",
  "keyword": "Scenario",
  "tags": [
    {
      "line": 10,
      "name": "@smoke"
    }
  ]
});
formatter.after({
  "duration": 33857,
  "status": "passed"
});
});