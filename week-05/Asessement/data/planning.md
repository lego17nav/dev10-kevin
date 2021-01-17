# Don't Wreck My House Planning

### High Level Requirements

<Strong>The administrator may view existing reservations for a host</Strong>
  * <em>Will need to create a stream method in domain that filters using a host id and
pass it through to View to be printed using the controller.</em><br>

<strong>The administrator may create reservation for a guest with a host</strong>
  * <em>Create a method within reservationrepo class that will access
reservation files as well as host and guest to determine if it is valid</em>.

<strong>The administrator may edit existing reservation</strong>
  * <em>Create a method within repo to access the reservation based on guest id given
. Given a date write a method/validation that will stream through current reservation to
    ensure no overlaps.</em>

<strong>Administrator may cancel a future reservation</strong>
  * <em>Create a method within reservation domain/repo that would stream through current reservation
and match using given variable (Maybe Id or name).</em>


### Components (Hours)

<strong>Guest (1 hour)</strong> -
  * Fields - String guestId, String firstname, String Lastname, String email, String Phone, Enum State.
  * Methods
    * Getters/Setters
<strong>Host & Location (1 hour)</strong> -
  * Fields - String id, String last_name, String email, String phone,String address,String city,Enum state,int
    postal_code, Bigdecimal standard_rate, Bigdecimal weekend_rate.
  * Methods
    * Getters/Setters
<strong>Reservation (1 hour)</strong> -
  * Fields - String id, localdate start_date,localdate end_date, string guest_id, Bigdecimal total


### Rough Draft of Methods needed. Task (Hour to completion)
> Additional Methods will be applied when needed.

* View all Reservation given a host ID. Will Access the corresponding file.(4 - 5)
  * Will need repo method that will access file by matching the hostid to the reservation filename. (1 hour)
  * Will need domain method to filter out past dates.(1 hour)
  * UI to Prompt user (1 hour)
  * UI to display results.(1 hour)
* Making a Reservation
  * Method to ask for guest email.(< 1 hour)
  * Method to ask for Host email/ID.(< 1 hour)
  * Method to grab appropriate reservation file (1 - 2 hour)
  * Stream to display reservations for specified host.(1 hour)
  * Method to Prompt for StartDate and EndDate and Check for Overlapping Dates.
    Another Method to calculate total $.(2 hour)
* Editing Reservation
  * Method to ask for guest email.(1 hour)
  * Method to ask for Host email/ID.(1 hour)
  * Method to grab appropriate reservation file (1 hour)
  * Method to parse through Returned reservation file and match the host email.(2 hours)
  * Prompt to enter new dates recalculate and date Logic. (2 hours)
* Cancel Reservation
  * Method to ask for guest email.(1 hour)
  * Method to ask for Host email/ID.(1 hour)
  * Method to grab appropriate reservation file (1 hour)
  * Parse through given file and grab matching guest email/ID.(1 hour)
  * Once confirmed rewrite file.(1 hour)

### Three Layer Spring DI Configuration will be used (1 - 2 hours)

### Testing

* Domain - (2 - 4 hours)
* Repository - (1 hour)

### Notes
  * Make Sure dates don't overlap by leveraging the domain.
  * Entered delimiters must be stripped or handled with exceptions.
  * Will need to read through Stream and LocalDate functions.
  * Host and Location are one Entity (One Class).
Housing a host field inside location class or Vice-versa.

