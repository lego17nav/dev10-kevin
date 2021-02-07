# React Field Agent Planning


### High-Level Requirements

* Create the following UseState
  * FirstName
  * MiddleName  
  * LastName
  * DOB
  * Height
  * Agent

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
  * Collection of Agent in a list Will be displayed   
    using map in the form.
  * Fetch data from Local Host/Agent (All Agent)
  * Promise statement if response status isn't success   
    reject promise.
    If success -> return response JSON (Data)
    set empty List equals to the output.
  * Only Display Id First Name and Last Name.  

* Create AddAgent Component [3 hours]
  * Set up our agent Object with Const newAgent = {firstname
    , MiddleName, LastName, DOB, Height}
    * Fetch "using Post" the created Agent with datas from form
    * If fetch is success grab data and if id exist then add it 
    to the running list of AGENT.
    * Else Reject and display message.  
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
   * I fetch fails reject promise with the following message "Unable to find Agent"
  
  
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

------------------------------------------------------------------
Verification Are if Action is Success or if Failure display
message.

-----------------------------------------------------------
Input Area
Agent|First Name|Middle Name|Last Name|DOB|Height|   
Input Fields | | | | | |  

-------------------------------------------------------------------
List of Agents ......  Each rows will be draggable to a delete box 
at the bottom of the browser.   
....... First Name | Last Name | Edit Button |   
.......    
.......

------------------------------------------------------------------

#### Initiation
* Would first work on The Agent Component
* Function to show agent and Mapping on Return Body
* Delete Agent
* Add Agent
* Update Agent

#### Refactoring
* Will try to refactor each required function to its own component


#### Functionality Misc.
* All agents will be displayed down at the bottom of the Browser 
where each agent would be editable.
* There Will be a Table at the top of the browser that would toggle
between updating or adding depending on user's choice.
* Fields are missing fields would turn red.
* add message board at the top to display whether an action is a  
success.  



         
