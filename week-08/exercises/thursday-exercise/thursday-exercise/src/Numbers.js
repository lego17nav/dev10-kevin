function Numbers({low, high}) {

    const listOfNumbers = [];

    for (let i = low; low < high; i++) {
        listOfNumbers.push(<li>i</li>)
    }

        return ( 
           {listOfNumbers}
         );    
}

export default Numbers;