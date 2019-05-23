import React from 'react';

import './App.css';
import "bootstrap/dist/css/bootstrap.min.css";
import Navbar from './components/Navbar';
import Board from './components/Board';
function App() {
  return (
    <div className="App">
      <Navbar />
      <Board />
    </div>
  );
}

export default App;
