import React, { Component } from 'react';
import './ServerError.css';
import { Link } from 'react-router-dom';
import { Button } from 'antd';
import ServerErrorSVG from './serverError.svg';
class ServerError extends Component {
    render() {
        return (
            <div className="server-error-page">
                <img src={ServerErrorSVG} alt='ServeurError' className='serverErrorSVG'/>
                <div className="server-error-desc">
                    Oops! Something went wrong at our Server. Why don't you go back?
                </div>
                <Link to="/"><Button className="server-error-go-back-btn" type="primary" size="large">Go Back</Button></Link>
            </div>
        );
    }
}
export default ServerError;