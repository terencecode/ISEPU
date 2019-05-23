import React from 'react';

import './App.css';
import "bootstrap/dist/css/bootstrap.min.css";
import Navbar from './components/Navbar';
import Board from './components/Board';
import {BrowserRouter as Router, Route} from "react-router-dom";
import AddCourse from './components/cours/AddCourse';
import {Provider}from "react-redux"
import store from "./store"



function App() {
  return (
    <Provider store={store}>
    <Router>
    <div className="App">
      <Navbar />
      <Route exact path="/" component={Board}/>
      <Route exact path="/addCourse" component={AddCourse}/>
    </div>
    </Router>
    </Provider>
  );
}

export default App;
