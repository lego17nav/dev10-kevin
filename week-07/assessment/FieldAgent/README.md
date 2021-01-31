# Field Agent HTTP Service

### High Level Requirements

* <strong>Create full HTTP CRUD for security clearance</strong>  
<strong>Create the following method under SecurityClearanceJdbcTemplateRepository</strong>  
  * addSecurityClearance(securityClearance) - returns SecurityClearance  [1/2 hour]
    > create sql statement "insert into securityclearance (securityClearanceId, name) object value"  
      assign key to object.  
    > return security clearance.
  * updateSecurityClearance(securityClearance) - returns boolean [1/2 hour]
    > create sql statement "update securityclearance set `name` to new name where id = argumentId"  
    > return if rowUpdated > 0; 
  * deleteSecurityClearance(securityClearance) - returns boolean [1/2 hour]
    > create sql statement "delete from securityclearance where securityid = argId"  
    > return if affected is > 0;
  * findAll() - returns all securityClearance [1/2 hour]
    > create swl statement "select name,id from securityclearance"  
    > return list </br>
    
  <strong>Create SecurityClearanceService and add the following method</strong>
    * validate() - [1/2 hour]
      > is name is blank add errormessage
    * findAll() - returns list<SecurityClearance> [1/2 hour]
      > call repositoryFindAll
    * update(SecurityClearance) - [1/2 hour]
      > validate() if success 
      > call repositoryUpdate 
      > >if fail add errorMessage "Id not found with not found status"
    * delete(securityClearanceId) [1/2 hour]
      > call repositoryDeleteId
    * add(securityClearance) [1/2 hour]
      > validate 
      > if success call repository.add

* <strong>Create full HTTP CRUD for alias</strong>  
  <strong>Create Alias Class under Models with the following attributes and methods</strong>
    * AliasId int  w/ setters and gc
  <strong>Create AliasJdbcRepository under Data with the following methods</strong>
    * addAlias(Alias) - returns Alias [1/2 hour]
    > create sql statement "insert into alias(name, persona, agent_id) object value"    
      grab key from table and assign it to newly created object.
    * updateAlias(Alias) return boolean [1/2 hour]
    > create sqlstatement "update alias set `name`,`persona` to objectvalue name and persona
  > where alias.getid = alias_id"  
    > return if updated
    * deleteAlias(aliasId) - returns boolean [1/2 hour]
    > delete from alias where aliasId = table.aliasId  
    > return if rows affected > 0

  <strong>Create AliasService with the following method</strong>
   * validate() [1/4 hour]
    > check if name is null  
  > check if personanull
  > check if agent_id is null
   * update(alias) - [1/2 hour]
    > validate if success  
  > > call repositoryupdate  
   * delete(aliasId) [1/2 hour]
    > repositoryDelete()
   * add(alias) [1/2 hour]
    > validate  
    if success call repo.add
  
  <strong>Create a method in AgentRepository</strong>
   * agentAlias(AgentId) [1 hour]
    > create sql statement "select agent.first_name, agent.last_name from agent  
  > inner join alias on agent.agent_id = alias.agent_id  
  > where agent.agent_id = agentid"  
  > return list
   
  <strong>create method in agentservice</strong>
  * getAgentAlias(AgentId) [1/2 hour]
  > return repo.agentAlias
  
### Testing

## Repository
### SecurityClearance [2 hours]
* Setup()
  > Call knowngoodstate
* shouldAdd() 
* shouldUpdate()
  > if matching id update assertfalse(if no match)
* shouldDelete()
  > assertTrue(delete(id))  
  > assertFalse(delete(sameid))
* findall()
  > assertEquals(expected.size, actual.size)  
### Alias [1 hour]
* Setup()
  > Call knowngoodstate
* shouldAdd()
* shouldUpdate()
  > if matching id update assertfalse(if no match)  
* shouldDelete()
  > assertTrue(delete(id))  
  > assertFalse(delete(sameid))
### Agent (1 new method)
* shouldFindAllalias()
  > assertEquals(expected.number.alias, actual.number.alias)  
## Domain [Will use Mocking]
### Security Clearance
* shouldAdd()
  > create call create Security Clearance  
  > assert not null  
* shouldnotAddwhenMissingName()
  > create SecurityClearance
  > set name to empty or null
  > assert result invalid
* shouldnotUpdateMissingName()
  > create securityclearance  
  > set name blank or null  
  > assert result invalid  
* shouldnotdeleteWhenNoMatch()
  > call delete(10000)  
  > assert Not Found  
* shouldNotUpdateNoMatch()
  > call delete(10000)  
  > assert Not Found  
### Alias
* createAlias()
  > Clean data
* shouldAdd()
  > call createAlias  
  > call add      
  > assert success  
* shouldNotAddMissingName()
  > call createAlias  
  > set name null  
  > call add  
  > assert INVALID  
* shouldnotAddMissingPersona()
  > call createAlias  
  > setPersonaNull  
  > call add  
  > assert INVALID  
* shouldnotUpdateMissingName()
  > call createAlias  
  > set Name null  
  > update  
  > assert Invalid
* shouldnotUpdateMissingPersona()
  > call createAlias    
  > set Persona null  
  > update  
  > assert Invalid  
* shouldnotUpdateNoneMatchingID()
  > call createAlias
  > setID to 1000
  > assert Not Found  
* shouldNotDeleteNoneMatchingID()
  > call Delete 1000  
  > assert Not Found

## REST API Controller
### Will set Url 
<strong>SecurityClearanceController</strong> (1 hour)
* AddSecurityClearance
  * Post Mapping(/add)
* updateSecurityClearance
  * Put Mapping(/update)
* deleteSecurityClearance
  * Delete Mapping(/Delete)
* findAll()
  * Get Mapping(/findall)
<strong>AliasController</strong> (1 hour)
* AddAlias
  * Post Mapping(/add)
* updateAlias
  * Put Mapping(/update)
* deleteAlias
  * Delete Mapping(/Delete)    
  

### Things to do and Research
* Still a little fuzzy on error handling will need to reread lesson.
* Determine if using join on agent is the best route to take to get aliases for an agent.
* Controller Testing is optional (Will attempt with time)
  