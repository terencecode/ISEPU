import React, { Component } from 'react';
import {
  Route,
  withRouter,
  Switch
} from 'react-router-dom';

import './App.css';
import "bootstrap/dist/css/bootstrap.min.css";
import Navbar from './components/Navbar';
import Board from './components/Board';
import {BrowserRouter as Router} from "react-router-dom";
import AddCourse from './components/cours/AddCourse';
import {Provider}from "react-redux"
import store from "./store"
import AddSubject from './components/matiere/AddSubject';
import ListOfSubject from './components/matiere/ListOfSubject';
import Login from './components/user/login';
import Signup from './components/user/signup';
import LoadingIndicator from './common/LoadingIndicator';

import { getCurrentUser } from './utils/APIUtils';
import { ACCESS_TOKEN } from './constants';

import { Layout, notification } from 'antd';
const { Content } = Layout;
class App extends Component{
constructor(props) {
  super(props);
  this.state = {
    currentUser: null,
    isAuthenticated: false,
    isLoading: false
  }
  this.handleLogout = this.handleLogout.bind(this);
  this.loadCurrentUser = this.loadCurrentUser.bind(this);
  this.handleLogin = this.handleLogin.bind(this);

  notification.config({
    placement: 'topRight',
    top: 70,
    duration: 3,
  });    
}

loadCurrentUser() {
  this.setState({
    isLoading: true
  });
  getCurrentUser()
  .then(response => {
    this.setState({
      currentUser: response,
      isAuthenticated: true,
      isLoading: false
    });
  }).catch(error => {
    this.setState({
      isLoading: false
    });  
  });
}

componentDidMount() {
  this.loadCurrentUser();
}

handleLogout(redirectTo="/", notificationType="success", description="You're successfully logged out.") {
  localStorage.removeItem(ACCESS_TOKEN);

  this.setState({
    currentUser: null,
    isAuthenticated: false
  });

  this.props.history.push(redirectTo);
  
  notification[notificationType]({
    message: 'Polling App',
    description: description,
  });
}

handleLogin() {
  notification.success({
    message: 'Polling App',
    description: "You're successfully logged in.",
  });
  this.loadCurrentUser();
  this.props.history.push("/");
}

 render(){
  if(this.state.isLoading) {
    return <LoadingIndicator />
  }
  return (

<div className="App">
    <Layout className="app-container">
          <Navbar isAuthenticated={this.state.isAuthenticated} 
            currentUser={this.state.currentUser} 
            onLogout={this.handleLogout} />

          <Content className="app-content">
            <div className="container">
              <Switch>      
                <Route exact path="/" 
                  render={(props) => <Board isAuthenticated={this.state.isAuthenticated} 
                      currentUser={this.state.currentUser} handleLogout={this.handleLogout} {...props} />}>
                </Route>
                <Route path="/login" 
                  render={(props) => <Login onLogin={this.handleLogin} {...props} />}></Route>
                <Route path="/signup" component={Signup}></Route>
                
              </Switch>
              <Provider store={store}>
    <Router>
    <Route  path="/addCourse" component={AddCourse}/>
      <Route  path="/addSubject" component={AddSubject}/>
      <Route  path="/Subject" component={ListOfSubject}/>
    
              
    </Router>
    </Provider>
            </div>
          </Content>
        </Layout>
    
      </div>
      
    
  );
  }
}

export default App;
