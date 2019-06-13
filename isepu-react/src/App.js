import React, { Component } from 'react';
import {
  Route,
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
import Profile from './components/user/profile';
import LoadingIndicator from './common/LoadingIndicator';
import { getCurrentUser } from './utils/APIUtils';
import { ACCESS_TOKEN } from './constants';
import ListOfSession from './components/Session/ListSession';
import { Layout, notification } from 'antd';
import UptadeSubject from "./components/matiere/UptadeSubject";
import ListOfCourse from "./components/cours/ListOfCourse";
import AddSession from "./components/Session/AddSession";
import AddHomework from "./components/devoirs/AddHomework";
import AddStudent from "./components/cours/AddStudent";
const { Content } = Layout;
class App extends Component{

constructor(props) {
  super(props);
  this.state = {
    currentUser: null,
    isAuthenticated: false,
    isStudent:false,
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
    if(this.state.currentUser.promo !== undefined){
      this.setState({
        isStudent:true
      })
    }
  }).catch(error => {
    this.setState({
      isLoading: false
    });  
  });
}

componentWillMount() {
  this.loadCurrentUser();
  if(this.props.isAuthenticated===false){
    console.log('redirect');
    this.props.history.push('/login');
  }

}

handleLogout(redirectTo="/login", notificationType="success", description="You're successfully logged out.") {
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

                <Route path="/login"
    render={(props) => <Login onLogin={this.handleLogin} {...props} />}/>
                <Route path="/signup" component={Signup}></Route>
                <Route path="/Profile"
                       render={(props) => <Profile isAuthenticated={this.state.isAuthenticated} onLogout={this.handleLogout} currentUser={this.state.currentUser} {...props}  />}>
                </Route>
                <Provider store={store}>
                <Router>
                  <Route path="/Course" />
                  <Route exact path="/"
                         render={(props) => <Board isAuthenticated={this.state.isAuthenticated}
                                                   currentUser={this.state.currentUser} handleLogout={this.handleLogout} {...props} />}>
                  </Route>

                  <Route  path="/addSubject" component={AddSubject}/>
                  <Route  path="/Subject" component={ListOfSubject}/>
                  <Route  path="/Update/:id" component={UptadeSubject}/>
                  <Route path="/Course" component={ListOfCourse}/>
                <Route path="/addCourse" component={AddCourse}/>
                <Route path="/addSession/:courseName" component={AddSession}/>
                <Route path="/listSession" component={ListOfSession}/>
                <Route path="/AddHomework/:courseName/:sessionId" component={AddHomework}/>
                <Route path="/AddStudent/:courseName" component={AddStudent}/>
                </Router>
              </Provider>
              </Switch>

            </div>
          </Content>
        </Layout>
    
      </div>
      
    
  );
  }
}

export default App;
