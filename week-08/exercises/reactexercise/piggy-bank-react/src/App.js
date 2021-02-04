// 1. Only `createElement` is required.
import { createElement } from 'react';
import CoinPanel from './CoinPanel';

function App() {
  // 2. createElement can be nested to represent children.
  return createElement(
    "div",
    { 
        className: "container" 
    },
    createElement(CoinPanel)); // 3.
}

<Trigonometry
    pi={3.14159}
    cos={Math.cos}
    triangleVertices={[[0, 0], [3, 0], [3, 5.7]]}
/>

export default App;
