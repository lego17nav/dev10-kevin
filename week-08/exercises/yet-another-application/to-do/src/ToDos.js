import { useState } from 'react';

const TODOS_DATA = [
    {
    id: 1,
    description: "Buy Pickles"
    },
    {
    id: 2,
    description: "Mow the lawn"    
    }
];

function ToDos() {
    const [toDos, seToDos] = useState(TODOS_DATA);
    const[description, setDescription] = useState('');

    const handleChange = (event) => {
        setDescription(event.target.value);
        
    };

    const handleSubmit = (event) => {
        event.preventDefault();
        console.log("Form submitted....")    

        const newToDos = [...toDos];

        newToDos.push({
            id: Date.now,
            description
        });

        seToDos(newToDos);
        setDescription("");
    };

    const handleDelete = (toDoId) => {

        const newToDos = toDos.filter(toDo => toDo.id !== toDoId)
        seToDos(newToDos);
    };

    return (
    <>    
        <h3>List ToDos</h3>
        <form onSubmit={handleSubmit}>
            <div>
                <label htmlFor="description">Description</label>
                <input id="description" name="description" type="text"
                onChange={handleChange}
                value={description}/>
            </div>
            <button type="submit" disabled={description === ''}>Add ToDo</button>
        </form>
        {toDos.map(todo => (
            <div key={todo.id}>
                {todo.description}
                <button onClick={() => handleDelete(todo.id)}>Delete</button>
            </div>
        ))}
    </>    
    );

}

export default ToDos;