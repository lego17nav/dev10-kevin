import React from 'react';
import Heading from './Heading';
import Numbers from './Numbers';



class App extends React.Component {
  render() {
    return (
      <div>
        <h1>Ramping Up on React</h1> 
        <Heading heading = "Hello World!"/>
        <Heading heading = "Hi!"/>
        <Heading heading = "Jack!"/>
        <Heading heading = "Rose!"/>
        <ul><Numbers low = {1} high = {13} /></ul>
      </div>
    );
  }
}

export default App;