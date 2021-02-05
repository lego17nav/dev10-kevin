# React Field Agent Planning


### High-Level Requirements

* Create Agent Component with the following props [1 hour]
  - addAgent
  - deleteAgent
  - updateAgent
  - getAgents
  - agents = []  
  <strong>Set useState for each Agent Attribute</strong>
    * First Name
    * Middle Name
    * Last Name
    * DOB
    * Height
  
* Display all agent - [2 hours]
  * Fetch data from Local Host/"Agent"
  * Promise statement if response status isn't success   
    reject promise.
    If success -> return response JSON (Data)
    set empty List equals to the output.
  * Only Display Id First Name and Last Name.  

* Create AddAgent Component [3 hours]
  * Set up our agent Object with Const newAgent = {firstname
    , MiddleName, LastName, DOB, Height}
  * Create Use state for said agent list.
  * Create onChangeHandler
    * Grab target name and value
    * Create a copy of the list created from useState
    * setAgent with the value on form.
    * If there's agent add it to the List of agent.
      * Else setErrors with Data
    * Set original List to updated one.
  
* Create HandleDeleteFunction [1 hour]
  * fetch(localhost using Method: Delete)
  * If promise is success Filter Agents with the matching
  AgentId set the List without the filtered out todo.
   * I fetch fails console.log(error)  
  
  
* Create handleUpdate Function [3 hours]
  * Grab Data to be "Put" from the form field
  * Call fetch with Put method and include our data as an argument.
  * The if not success return response
  * Pass trough data if it exists copy agent lists;
  * Find agent to be updated and update with data obtained from the form.
  * Set the oldList with the new updated list.
  

### App Body [1 hour]
  * Set up Form and assign an event handler for submit,
    * Set up value to {firstname, MiddleName, LastName, DOB, Height}
    * Create button with onClick Handler calling addAgent
  * Set up second form with event handler for Update.

  * Set up Table to store all Agent Data including edit and delete handlers
    (Will use Bootstrap to customize table)
    
  * Table will consist six columns to match each Agent Field
    * Map agent list to each row.
  
  * Class Name for Bootstrap undetermined

### UI Lay-Out
  * Will include a header spanning the top of the browser.
 ------------------------------------------------------------------
 Agent |ID|First Name|Middle Name| Last Name | DOB | Height  
 Input Fields | | | | | |  

-------------------------------------------------------------------
List of Agents ......
....... Id, First Name, Last Name
.......
.......

         
