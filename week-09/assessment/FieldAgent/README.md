# Thyme Leaf Field Agent


# Set up
* Add Thyme Leaf Dependency
* Create new Controller Folder to house Thyme Controllers  
* Create Home HTML file
  * Include Division header
  * Create UL List with 4 choices
    * View All
    * Add Agent
    * Update Agent
    * Delete Agent
* Create Get Mapping Method that returns home.html
* Create viewAll html file


# Assessment
### High-Level Requirements

* Display all agents  
  [Controller] 
  * Create a get mapping method taking in model and returns String
    * Underneath the medthod call service findall
    * Add findall result to model attribute
    * return viewall HTML file.  
  [Template]
  * Build general headers in template
  * Create Table with the following header firstName, middleName, lastName, DOB, height
  