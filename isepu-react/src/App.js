import React from 'react';

import './App.css';
import "bootstrap/dist/css/bootstrap.min.css";
import Navbar from './components/Navbar';
import Board from './components/Board';
import {BrowserRouter as Router, Route} from "react-router-dom";
import AddCourse from './components/cours/AddCourse';
import {Provider}from "react-redux"
import store from "./store"
import AddSubject from './components/matiere/AddSubject';
import ListOfSubject from './components/matiere/ListOfSubject';



function App() {
  return (
    <Provider store={store}>
    <Router>
    <div className="App">
      <Navbar />
      <Route exact path="/" component={Board}/>
      <Route exact path="/addCourse" component={AddCourse}/>
      <Route exact path="/addSubject" component={AddSubject}/>
      <Route exact path="/Subject" component={ListOfSubject}/>
    </div>
    </Router>
    </Provider>
  );
}

export default App;
