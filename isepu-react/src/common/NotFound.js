import React, { Component } from 'react';
import './NotFound.css';
import { Link } from 'react-router-dom';
import { Button } from 'antd';
import NotFoundSVG from'./404.svg';

class NotFound extends Component {

    render() {
        return (
            <div className="page-not-found">
                <img src={NotFoundSVG} alt='404' className='notfoundSVG'/>

                <div className="desc">
                    The Page you're looking for was not found.
                </div>
                <Link to="/"><Button className="go-back-btn" type="primary" size="large">Go Back</Button></Link>
            </div>
        );
    }
}
export default NotFound;