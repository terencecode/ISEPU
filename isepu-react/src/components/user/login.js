import React, { Component } from 'react';
import { login } from '../../utils/APIUtils';
import './login.css';
import {Link, withRouter} from 'react-router-dom';
import { ACCESS_TOKEN } from '../../constants';
import loginSVG from './img/login.svg';

import { Form, Input, Button, notification } from 'antd';
const FormItem = Form.Item;


class Login extends Component {

    render() {
        const AntWrappedLoginForm = Form.create()(LoginForm);
        return (
            <div className="login-container">
                <img src={loginSVG} alt="login" className='loginSVG'/>
                <h1 className="page-title">Login</h1>
                <div className="login-content">

                    <AntWrappedLoginForm onLogin={this.props.onLogin} />
                </div>
            </div>
        );
    }
}

class LoginForm extends Component {
    constructor(props) {
        super(props);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event) {
        event.preventDefault();   
        this.props.form.validateFields((err, values) => {
            if (!err) {
                const loginRequest = Object.assign({}, values);
                login(loginRequest)
                .then(response => {
                    localStorage.setItem(ACCESS_TOKEN, response.accessToken);
                    this.props.onLogin();
                }).catch(error => {
                    if(error.status === 401) {
                        notification.error({
                            message: 'ISEPU',
                            description: 'Your Username or Password is incorrect. Please try again!'
                        });                    
                    } else {
                        notification.error({
                            message: 'ISEPU',
                            description: error.message || 'Sorry! Something went wrong. Please try again!'
                        });                                            
                    }
                });
            }
        });
    }

    onLogin() {
        //this.props.history.push('/');
    }

    render() {
        const { getFieldDecorator } = this.props.form;
        return (

            <Form onSubmit={this.handleSubmit} className="login-form">
                <FormItem>
                    {getFieldDecorator('email', {
                        rules: [{ required: true, message: 'Please input your username or email!' }],
                    })(
                    <Input 

                        size="large"
                        name="email" 
                        placeholder="email"
                        className="form-control form-control-lg" />    
                    )}
                </FormItem>
                <FormItem>
                {getFieldDecorator('password', {
                    rules: [{ required: true, message: 'Please input your Password!' }],
                })(
                    <Input 

                        size="large"
                        name="password" 
                        type="password" 
                        placeholder="Password"
                        className="form-control form-control-lg"  />                        
                )}
                </FormItem>
                <FormItem>
                    <Button type="primary" htmlType="submit" size="large" className="btn btn-primary login-form-button">Login</Button>
                    Or <Link to="/signup">register now!</Link>
                </FormItem>
            </Form>
        );
    }
}


export default withRouter(Login);