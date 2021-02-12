# Thyme Leaf Field Agent


# Set up [1 hour]
* Add Thyme Leaf Dependency
* Create new Controller Folder to house Thyme Controllers  
* Create Home HTML file
  * Include Division headers
  
* Create Get Mapping Method that returns home.html
* Create viewAll html file
* Create conditional rendering to show update agent if id exist or create if it's 0.


# Create Validation on Model [30 mins]
* If first name is null
* If Middle name is null
* If Last name is null
* If DOB is null
* If Height is below minimum and above maximum
* If DOB is not a date
* if DOB is after today.

# Assessment
### High-Level Requirements

* #### Display all agents  
  [Controller 1/2 hour] 
  * Create a get mapping method taking in model and returns String
    * Underneath the method call service findall
    * Add findall result to model attribute
    * return viewall HTML file.  
  [Template 1 hour]
  * Add div tag with add agent  and reference to create html file.    
  * Build general headers in template
  * Create Table with the following header firstName, middleName, lastName, DOB, height
  * For body add the table data
    * with th:each and reference our agent model. Do this for each viewable attribute.
    * add reference link for edit button with th reference to agent update form while anchoring the agent id.
    * add reference link with th reference to the delete form while anchoring the agent id.

*  #### Create Agent
  [Controller 1/4 hour]
  * Create add agent html form.
  * Create a getMapping method that takes in Agent
    * set min height and max height
  * add division to display binding result with th:errors.
  * Create PostMapping Method
  * Add in if statement to handle errors and return current page if there is
  * redirect to home html.

  [Template 1- 2 hour]
  * Create form with method as post and reference the agent model
    * Inside the form add the following input field while binding them to their model attribute
      * First name text
      * Middle name text
      * Last name text
      * DOB date
      * height text number only
    * create button with submit type.
      
* #### Update Agent
 [Controller 1/2 hour]
   * Create get Mapping Method with the id and Model as argument
   * Call service find by id if not success add error message to result.
   

 [Template N/A]
   * Create Not found html page.
   * Template used is same as the create template form not much changes required outside of conditional   
button to toggle between submit or save if agent id is present.
     
* #### Delete Agent
  [Controller 1/2 hour]
   * Create a getmapping method that takes in Path variable id and Models.
   * call service find by id.
   * if return null return not found form
   * else det model attribute
   * and return delete confirmation page.
   * Create postmapping taking pathvariable id as argument.
   * if null return the not found page.
   * Else call service delete and redirect to home page.
  

  [Template 1/2 hour]
  * Create list with agents FIrst name middle and last name to confirm with th:text.
  * Create form tag with buttons as children and have a delete and Cancel .With Delete having a submit type and Cancel 
    referencing the home page.


#### CSS Framework 
* Undecided. Interested in using NES.CSS Framework but documentation seems vague will look further into it
* Else Will use Picnic (Interested in 8 Bit style)
* If time permits will also try to implement drag and drop with vanilla javascript.