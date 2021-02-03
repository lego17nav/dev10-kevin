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

export default App;
