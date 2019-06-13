import React, { Component } from 'react';
import { signup, checklastNameAvailability, checkEmailAvailability,signupEleve } from '../../utils/APIUtils';
import './Signup.css';
import { Link } from 'react-router-dom';
import { 
    FIRSTNAME_MIN_LENGTH, FIRSTNAME_MAX_LENGTH, 
    LASTNAME_MIN_LENGTH, LASTNAME_MAX_LENGTH,
    EMAIL_MAX_LENGTH,
    PASSWORD_MIN_LENGTH, PASSWORD_MAX_LENGTH
} from '../../constants';
import Tab from 'react-bootstrap/Tab';
import Tabs from 'react-bootstrap/Tabs';

import {Form, Input, Button, notification, Select} from 'antd';
import "antd/dist/antd.css";
import loginSVG from "./img/login.svg";
const FormItem = Form.Item;
const Option=Select.Option;

class Signup extends Component {

    constructor(props) {
        super(props);
        this.state = {


            firstName: {
                value: ''
            },
            lastName: {
                value: ''
            },
            email: {
                value: ''
            },
            password: {
                value: ''
            },

            promo: ''

        };
        this.handleInputChange = this.handleInputChange.bind(this);
        this.handleSelectChange=this.handleSelectChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
        this.handleSubmitEleve=this.handleSubmitEleve.bind(this);
        this.validatelastNameAvailability = this.validatelastNameAvailability.bind(this);
        this.validateEmailAvailability = this.validateEmailAvailability.bind(this);
        this.isFormInvalid = this.isFormInvalid.bind(this);
    }



    handleInputChange(event, validationFun) {
        const target = event.target;
        const inputName = target.name;        
        const inputValue = target.value;


        this.setState({
            [inputName] : {
                value: inputValue,
                ...validationFun(inputValue)
            }
        });


    }
    handleSelectChange(value) {
        this.setState({
            promo:`${value}`
        });
    }
       handleSubmit(event) {
        event.preventDefault();

        const signupRequest = {
            firstName: this.state.firstName.value,
            email: this.state.email.value,
            lastName: this.state.lastName.value,
            password: this.state.password.value
        };
        signup(signupRequest)
            .then(response => {
                notification.success({
                    message: 'Polling App',
                    description: "Thank you! You're successfully registered. Please Login to continue!",
                });
                this.props.history.push("/login");
            }).catch(error => {
            notification.error({
                message: 'Polling App',
                description: error.message || 'Sorry! Something went wrong. Please try again!'
            });
        });
    }
    handleSubmitEleve(event) {
        event.preventDefault();
        console.log(this.state);
        const signupRequestEleve = {
            firstName: this.state.firstName.value,
            email: this.state.email.value,
            lastName: this.state.lastName.value,
            password: this.state.password.value,
            promo:this.state.promo
        };
        signupEleve(signupRequestEleve)
            .then(response => {
                notification.success({
                    message: 'Polling App',
                    description: "Thank you! You're successfully registered. Please Login to continue!",
                });
                this.props.history.push("/login");
            }).catch(error => {
            notification.error({
                message: 'Polling App',
                description: error.message || 'Sorry! Something went wrong. Please try again!'
            });
        });
    }


    isFormInvalid() {
        return !(this.state.firstName.validateStatus === 'success' &&
            this.state.lastName.validateStatus === 'success' &&
            this.state.email.validateStatus === 'success' &&
            this.state.password.validateStatus === 'success'
        );
    }

    render() {

        return <div className="signup-container">
            <img src={loginSVG} alt="login" className='loginSVG'/>
            <h1 className="page-title">S'enregistrer</h1>
            <Tabs id="Signup-tab"
                  activeKey={this.state.key}
                  onSelect={key => this.setState({key})}>

                <Tab eventKey="professor" title="Professeur">
                    <div className="signup-content">
                        <Form onSubmit={this.handleSubmit} className="signup-form">
                            <FormItem
                                label="prenom"
                                validateStatus={this.state.firstName.validateStatus}
                                help={this.state.firstName.errorMsg}>
                                <Input
                                    size="large"
                                    name="firstName"
                                    autoComplete="off"
                                    placeholder="Votre Prenom"
                                    className="form-control form-control-lg"
                                    value={this.state.firstName.value}
                                    onChange={(event) => this.handleInputChange(event, this.validateName)}/>
                            </FormItem>
                            <FormItem label="nom"
                                      hasFeedback
                                      validateStatus={this.state.lastName.validateStatus}
                                      help={this.state.lastName.errorMsg}>
                                <Input
                                    size="large"
                                    name="lastName"
                                    autoComplete="off"
                                    placeholder="Votre Nom de Famille"
                                    className="form-control form-control-lg"
                                    value={this.state.lastName.value}
                                    onBlur={this.validatelastNameAvailability}
                                    onChange={(event) => this.handleInputChange(event, this.validatelastName)}/>
                            </FormItem>
                            <FormItem
                                label="Email"
                                hasFeedback
                                validateStatus={this.state.email.validateStatus}
                                help={this.state.email.errorMsg}>
                                <Input
                                    size="large"
                                    name="email"
                                    type="email"
                                    autoComplete="off"
                                    placeholder="Votre Email"
                                    className="form-control form-control-lg"
                                    value={this.state.email.value}
                                    onBlur={this.validateEmailAvailability}
                                    onChange={(event) => this.handleInputChange(event, this.validateEmail)}/>
                            </FormItem>
                            <FormItem
                                label="Mot de passe"
                                validateStatus={this.state.password.validateStatus}
                                help={this.state.password.errorMsg}>
                                <Input
                                    size="large"
                                    name="password"
                                    type="password"
                                    autoComplete="off"
                                    placeholder="un mot de passe entre 6 et 20 characteres"
                                    className="form-control form-control-lg"
                                    value={this.state.password.value}
                                    onChange={(event) => this.handleInputChange(event, this.validatePassword)}/>
                            </FormItem>
                            <FormItem>
                                <Button type="primary"
                                        htmlType="submit"
                                        size="large"
                                        className="signup-form-button btn btn-primary"
                                        disabled={this.isFormInvalid()}>Valider</Button>
                                déjà enregistré? <Link to="/login">Se connecter!</Link>
                            </FormItem>
                        </Form>
                    </div>
                </Tab>

                <Tab eventKey="eleve" title="Eleve">
                    <div className="signup-content">
                        <Form onSubmit={this.handleSubmitEleve} className="signup-form">
                            <FormItem
                                label="Prenom"
                                validateStatus={this.state.firstName.validateStatus}
                                help={this.state.firstName.errorMsg}>
                                <Input
                                    size="large"
                                    name="firstName"
                                    autoComplete="off"
                                    placeholder="votre prenom"
                                    className="form-control form-control-lg"
                                    value={this.state.firstName.value}
                                    onChange={(event) => this.handleInputChange(event, this.validateName)}/>
                            </FormItem>
                            <FormItem label="Nom"
                                      hasFeedback
                                      validateStatus={this.state.lastName.validateStatus}
                                      help={this.state.lastName.errorMsg}>
                                <Input
                                    size="large"
                                    name="lastName"
                                    autoComplete="off"
                                    placeholder="votre nom de famille"
                                    className="form-control form-control-lg"
                                    value={this.state.lastName.value}
                                    onBlur={this.validatelastNameAvailability}
                                    onChange={(event) => this.handleInputChange(event, this.validatelastName)}/>
                            </FormItem>
                            <FormItem
                                label="Email"
                                hasFeedback
                                validateStatus={this.state.email.validateStatus}
                                help={this.state.email.errorMsg}>
                                <Input
                                    size="large"
                                    name="email"
                                    type="email"
                                    autoComplete="off"
                                    placeholder="Votre email"
                                    className="form-control form-control-lg"
                                    value={this.state.email.value}
                                    onBlur={this.validateEmailAvailability}
                                    onChange={(event) => this.handleInputChange(event, this.validateEmail)}/>
                            </FormItem>

                            <FormItem
                                label="promo"
                                help={this.state.promo.errorMsg}>
                                <Select

                                    name="promo"
                                    value={this.state.promo}
                                    ref="promo"
                                    onChange={this.handleSelectChange}
                                >
                                    <Option value="P1">P1</Option>
                                    <Option value="P2">P2</Option>
                                    <Option value="I1">I1</Option>
                                    <Option value="I2">I2</Option>
                                    <Option selected={"selected"} value="A1">A1</Option>
                                    <Option value="A2">A2</Option>
                                    <Option value="A3">A3</Option>

                                </Select>
                            </FormItem>

                            <FormItem
                                label="Mot de passe"
                                validateStatus={this.state.password.validateStatus}
                                help={this.state.password.errorMsg}>
                                <Input
                                    size="large"
                                    name="password"
                                    type="password"
                                    autoComplete="off"
                                    placeholder="un mot de passe entre 6 et 20 characteres"
                                    className="form-control form-control-lg"
                                    value={this.state.password.value}
                                    onChange={(event) => this.handleInputChange(event, this.validatePassword)}/>
                            </FormItem>

                            <FormItem>
                                <Button type="primary"
                                        htmlType="submit"
                                        size="large"
                                        className="signup-form-button btn btn-primary"
                                        disabled={this.isFormInvalid()}>Valider</Button>
                                Déjà enregistré? <Link to="/login">Se connecter!</Link>
                            </FormItem>
                        </Form>
                    </div>
                </Tab>
            </Tabs>
        </div>;
    }

    // Validation Functions

    validateName = (name) => {
        if(name.length < FIRSTNAME_MIN_LENGTH) {
            return {
                validateStatus: 'error',
                errorMsg: `Name is too short (Minimum ${FIRSTNAME_MIN_LENGTH} characters needed.)`
            }
        } else if (name.length > FIRSTNAME_MAX_LENGTH) {
            return {
                validationStatus: 'error',
                errorMsg: `Name is too long (Maximum ${FIRSTNAME_MAX_LENGTH} characters allowed.)`
            }
        } else {
            return {
                validateStatus: 'success',
                errorMsg: null,
              };            
        }
    }

    validateEmail = (email) => {
        if(!email) {
            return {
                validateStatus: 'error',
                errorMsg: 'Email may not be empty'                
            }
        }

        const EMAIL_REGEX = RegExp('[^@ ]+@[^@ ]+\\.[^@ ]+');
        if(!EMAIL_REGEX.test(email)) {
            return {
                validateStatus: 'error',
                errorMsg: 'Email not valid'
            }
        }

        if(email.length > EMAIL_MAX_LENGTH) {
            return {
                validateStatus: 'error',
                errorMsg: `Email is too long (Maximum ${EMAIL_MAX_LENGTH} characters allowed)`
            }
        }

        return {
            validateStatus: null,
            errorMsg: null
        }
    }

    validatelastName = (lastName) => {
        if(lastName.length < LASTNAME_MIN_LENGTH) {
            return {
                validateStatus: 'error',
                errorMsg: `lastName is too short (Minimum ${LASTNAME_MIN_LENGTH} characters needed.)`
            }
        } else if (lastName.length > LASTNAME_MAX_LENGTH) {
            return {
                validationStatus: 'error',
                errorMsg: `lastName is too long (Maximum ${LASTNAME_MAX_LENGTH} characters allowed.)`
            }
        } else {
            return {
                validateStatus: null,
                errorMsg: null
            }
        }
    }

    validatelastNameAvailability() {
        // First check for client side errors in lastName
        const lastNameValue = this.state.lastName.value;
        const lastNameValidation = this.validatelastName(lastNameValue);

        if(lastNameValidation.validateStatus === 'error') {
            this.setState({
                lastName: {
                    value: lastNameValue,
                    ...lastNameValidation
                }
            });
            return;
        }

        this.setState({
            lastName: {
                value: lastNameValue,
                validateStatus: 'validating',
                errorMsg: null
            }
        });

        checklastNameAvailability(lastNameValue)
        .then(response => {
            if(response.available) {
                this.setState({
                    lastName: {
                        value: lastNameValue,
                        validateStatus: 'success',
                        errorMsg: null
                    }
                });
            } else {
                this.setState({
                    lastName: {
                        value: lastNameValue,
                        validateStatus: 'error',
                        errorMsg: 'This lastName is already taken'
                    }
                });
            }
        }).catch(error => {
            // Marking validateStatus as success, Form will be recchecked at server
            this.setState({
                lastName: {
                    value: lastNameValue,
                    validateStatus: 'success',
                    errorMsg: null
                }
            });
        });
    }

    validateEmailAvailability() {
        // First check for client side errors in email
        const emailValue = this.state.email.value;
        const emailValidation = this.validateEmail(emailValue);

        if(emailValidation.validateStatus === 'error') {
            this.setState({
                email: {
                    value: emailValue,
                    ...emailValidation
                }
            });    
            return;
        }

        this.setState({
            email: {
                value: emailValue,
                validateStatus: 'validating',
                errorMsg: null
            }
        });

        checkEmailAvailability(emailValue)
        .then(response => {
            if(response.available) {
                this.setState({
                    email: {
                        value: emailValue,
                        validateStatus: 'success',
                        errorMsg: null
                    }
                });
            } else {
                this.setState({
                    email: {
                        value: emailValue,
                        validateStatus: 'error',
                        errorMsg: 'This Email is already registered'
                    }
                });
            }
        }).catch(error => {
            // Marking validateStatus as success, Form will be recchecked at server
            this.setState({
                email: {
                    value: emailValue,
                    validateStatus: 'success',
                    errorMsg: null
                }
            });
        });
    }

    validatePassword = (password) => {
        if(password.length < PASSWORD_MIN_LENGTH) {
            return {
                validateStatus: 'error',
                errorMsg: `Password is too short (Minimum ${PASSWORD_MIN_LENGTH} characters needed.)`
            }
        } else if (password.length > PASSWORD_MAX_LENGTH) {
            return {
                validationStatus: 'error',
                errorMsg: `Password is too long (Maximum ${PASSWORD_MAX_LENGTH} characters allowed.)`
            }
        } else {
            return {
                validateStatus: 'success',
                errorMsg: null,
            };            
        }
    }


}

export default Signup;