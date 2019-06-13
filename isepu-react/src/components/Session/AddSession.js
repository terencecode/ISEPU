import React, { Component } from 'react'
import {Link} from "react-router-dom";
import PropTypes from"prop-types";
import {connect} from "react-redux";
import {addSession} from "../../actions/SessionAction";
import {DatePicker} from "antd";
import Moment from 'react-moment';
import moment from 'moment';

const {RangePicker} =DatePicker;

class AddSession extends Component {

    constructor(){
        super();
        this.state={
            courseName:'',
            startingTime:'',
            finishingTime:''



        };
        this.onChange=this.onChange.bind(this);
        this.onSubmit=this.onSubmit.bind(this);

    }
    onChange(date){
        this.setState({
            startingTime:new Date(date[0]._d).getTime(),
            finishingTime:new Date(date[1]._d).getTime()
        });
        console.log('test',new Date(date[0]._d).toISOString());
    }

    onSubmit(e){
        e.preventDefault();
        const newSession={
            courseName:this.props.match.params.courseName,
            startingTime:this.state.startingTime,
            finishingTime:this.state.finishingTime
        };
        console.log(newSession);
        this.props.addSession(newSession,this.props.history);
    }
    render() {
        return (
            <div className="addProjectTask">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <Link to="/" className="btn btn-light">
                                Acceuil
                            </Link>
                            <h4 className="display-4 text-center">Ajouter une session pour le cours de {this.props.match.params.courseName}</h4>
                            <form onSubmit={this.onSubmit}>
                                <div className="form-group">
                                    <RangePicker className="mt-4" onChange={this.onChange} />
                                </div>

                                <input type="submit" className="btn btn-primary btn-block mt-4" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

addSession.prototype= {
    addSession: PropTypes.func.isRequired,
    error:PropTypes.object.isRequired
}

const mapStateToProps =state => ({
    errors: state.errors
});
export default connect(null, {addSession}) (AddSession);