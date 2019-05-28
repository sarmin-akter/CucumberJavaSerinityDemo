@eventpage
Feature: Event Page
  As an Admin UI User 
  I Can land on Event Page
  So that I can see Upcoming events. Search event. Paginate Event Data & Filter Event List

  @smoke
  Scenario: Landing on Event Page & Validate Basic Elments
    Given I login to "EE-Login-Page" as "SuperAdmin"
    Given I Navigate to EventListPage
    #Then I Validate EventListPage Has Below Web Elements
     # | EventCardHeaderIcon |
      #| EventHeadingLabel   |
      #| SearchButton        |
      #| EventListTable      |

  #@regression
  #Scenario: Landing on Event Page & Search for a Event
    #Given I login to "EE-Login-Page" as "SuperAdmin"
    #Given I Navigate to EventListPage
    #And I Search an Event with Event Name "Billy Joel - In Concert"
