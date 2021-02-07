import { useState, useEffect } from 'react';
import Errors from './Errors';


function Agent() {

    const [agents, setAgents] = useState([]);
    const [firstName, setFirstName] = useState('');
    const [middleName, setMiddleName] = useState('');
    const [lastName, setLastName] = useState('');
    const [dob, setDob] = useState('');
    const [height, setHeight] = useState(0);


    const [editAgentId, setAgentId] = useState(0);
    const [errors, setErrors] = useState([]);

    const DEFAULT_AGENT = {

    };

    useEffect(() => {

        fetch('http://localhost:8080/api/agent')
        .then(response => response.json())
        .then(data => setAgents(data))
        .catch(error => console.log(error));
    }, []);

    const handleChangeFirstName = (event) => {
        setFirstName(event.target.value);
    }
    const handleChangeMiddleName = (event) => {
        setMiddleName(event.target.value);
    }
    const handleChangeLastName = (event) => {
        setLastName(event.target.value);
    }
    const handleChangeDob = (event) => {
        setDob(event.target.value);
    }
    const handChangeheight = (event) => {
        setHeight(event.target.value);
    }
    

    const handleAddSubmit = (event) => {
        event.preventDefault();

        const newAgent = {
            firstName,
            middleName,
            lastName,
            dob,
            height,
        
        };

        const body = JSON.stringify(newAgent);

        fetch('http://localhost:8080/api/agent', {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body
        })
        .then(response => {
            if (response.status === 201 || response.status === 400) {
              return response.json();
            } else {
              Promise.reject('Shoot! Something unexpected went wrong :(');
            }
          })
          .then(data => {
              if (data.id) {
                setAgents([...agents, data]);
              
                setFirstName('');
                setLastName('');
                setMiddleName('');
                setDob('');
                setHeight(0);
                

                setErrors([]);

          } else {
              setErrors(data);
          }
          })
          .catch(error => console.log(error));

    };

    const handleDelete = (agentId) => {
    fetch(`http://localhost:8080/api/agent/${agentId}`, {
        method: "DELETE"      
    })
        .then(response => {
        if (response.status === 204) {
            const newAgent = agents.filter(agent => agent.agentId !== agentId);
            setAgents(newAgent);      
        } else if (response.status === 404) {
            Promise.reject(`Agent ID #${agentId} not found.`);
        } else {
            Promise.reject('Shoot! Something unexpected went wrong :(');
        }
        })
        .catch(error => console.log(error));
    };

    const handleEdit = (agentId) => {
        // get the todo that the user wants to edit
        const agentToEdit = agents.find(agent => agent.agentId === agentId);
    
        // update the state with that todo's information
        setAgentId(agentToEdit.agentId);
        setFirstName(agentToEdit.firstName);
        setMiddleName(agentToEdit.middleName);
        setLastName(agentToEdit.lastName);
        setDob(agentToEdit.dob);
        setHeight(agentToEdit.height);
        
      };
    

    const handleUpdateSubmit = (event) => {
        event.preventDefault();

        const updatedAgent = {
            id: editAgentId,
            firstName,
            middleName,
            lastName,
            dob,
            height,
            agencies: [],
            alias: []
        };

        const body = JSON.stringify(updatedAgent);

        fetch('http//localhost:8080/api/agent/#{editAgentId}', {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body
        })
        .then(response => {
            if (response.status === 204) {
              return null;
            } else if (response.status === 400) {
              return response.json();
            } else {
              Promise.reject('Shoot! Something unexpected went wrong :(');
            }
          })
        .then(data => {
            
            if (!data) {
              const newAgent = [...agents];

              const agentIndexToedit = agents.findIndex(agent => agent.agentId === editAgentId);

              newAgent[agentIndexToedit] = {
                id: editAgentId,
                firstName,
                middleName,
                lastName,
                dob,
                height,
                agencies: [],
                alias: []                
              };

              setAgents(newAgent);

              setFirstName('');
              setLastName('');
              setMiddleName('');
              setDob('');
              setHeight(0);
            

              setErrors([]);
    }       else {
            setErrors(data);
    }
})          
    .catch(error => console.log(error));
};

    const handleUpdateCancel = () => {
        // reset the form
        setFirstName('');
        setLastName('');
        setMiddleName('');
        setDob('');
        setHeight(0);
        setAgentId(0);
      };

    return (
      <>
        <Errors errors={errors}/>
        
        <form onSubmit ={handleAddSubmit} >
            <div className = "form-group">
                <label htmlFor="firstName">First Name : </label>
                <input onChange={handleChangeFirstName} id="firstName" name="firstName" type="text"
                value={firstName} className="form-control" placeholder="John"/>
            </div>
            <div className = "form-group">
                <label htmlFor="middleName">Middle Name : </label>
                <input onChange={handleChangeMiddleName} id="middleName" name="middleName" type="text" 
                value={middleName} className="form-control" placeholder="B."/>
            </div>
            <div className = "form-group">
                <label htmlFor="lastName">lastName : </label>
                <input onChange={handleChangeLastName} id="lastName" name="lastName" type="text" 
                value={lastName} className="form-control" placeholder="Doe"/>
            </div>

            <div className = "form-group">
                <label htmlFor="dob">Birth Date : </label>
                <input onChange={handleChangeDob} id="dob" name="dob" type="date" 
                value={dob} className="form-control"/>
            </div>

            <div className = "form-group">
                <label htmlFor="height">Height : </label>
                <input onChange = {handChangeheight} id="height" name="height" 
                value={height} type="number" className="form-control" placeholder="xx.xx" />
            </div> 

            <div className = "form-group" style={{marginTop:"10px"}}>
                <button className="btn btn-success ml-2" type="submit">Add Agent</button>
                {(firstName && lastName && middleName && dob && height)||(errors.length > 0) ? (
                <button className="btn btn-warning ml-2" type="button"
                onClick={handleUpdateCancel}>Cancel</button>
                ) : null}
            </div>
        </form>

        

      </>
    )


}

export default Agent;