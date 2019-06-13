import React, { Component } from 'react'
import {Link} from "react-router-dom";
import PropTypes from"prop-types";
import {connect} from "react-redux";
import {addHomework} from "../../actions/HomeworkAction";
import NotFound from '../../common/NotFound';
import ServerError from '../../common/ServerError';

class AddHomework extends Component {
    constructor(){
        super();
        this.state={
            courseName:"",
            description:"",
            sessionId:"",
            status:""

        };
        this.onChange=this.onChange.bind(this);
        this.onSubmit=this.onSubmit.bind(this);

    }
    onChange(e){
        this.setState({[e.target.name]:e.target.value})
    }

    onSubmit(e){
        e.preventDefault();
        const newCourse={
            courseName:this.props.match.params.courseName,
            sessionId:this.props.match.params.sessionId,
            description: this.state.description,
            status:this.state.status


        };

        this.props.addHomework(newCourse,this.props.history);
        if(this.props.addHomework.errors===404){
            return <NotFound/>;
        }
        if (!(this.props.addHomework.errors === 500 || this.props.addHomework.errors === 401)) {
            return <ServerError/>;
        }
    }
    render() {

        return (

            <div className="addProjectTask">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <Link to="/" className="btn btn-light">
                                Retour Acceuil
                            </Link>
                            <h4 className="display-4 text-center">Ajouter un devoir pour le cours de {this.props.match.params.courseName}</h4>
                            <form onSubmit={this.onSubmit}>

                                <div className="form-group">
                                    <textarea className="form-control form-control-lg" placeholder="Description" value={this.state.description} name="description" onChange={this.onChange}/>
                                </div>
                                 <div className="form-group">
                            <select className="form-control form-control-lg" name="status" value={this.state.status} onChange={this.onChange}>
                                <option value="">Select Status</option>
                                <option value={1}>TO DO</option>
                                <option value={2}>IN PROGRESS</option>
                                <option value={3}>DONE</option>
                            </select>
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

addHomework.prototype= {
    addHomework: PropTypes.func.isRequired,
    error:PropTypes.object.isRequired
};

const mapStateToProps =state => ({
    errors: state.errors
});
export default connect(null, {addHomework}) (AddHomework);