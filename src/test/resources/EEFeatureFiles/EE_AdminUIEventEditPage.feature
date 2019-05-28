@eventpage
Feature: Event Edit Page
  As an Admin UI User 
  I Can land on Event Page
  So that I can see Upcoming events. Search event. Paginate Event Data & Filter Event Display List

  Background: User is Logged In
    Given I login to "EE-Login-Page" as "SuperAdmin"

  @smoke
  Scenario: Add-Remove Category & Verify API Response
    Given I Navigate to EventListPage
    And I Search "Billy Joel - In Concert" Event & Click The First Event Returns from EventTable
    And I Add Below Categories To This Event
      | Family |
      | Sports |
