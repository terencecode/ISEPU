import React, { Component } from 'react'
import {Link} from "react-router-dom";
import PropTypes from"prop-types";
import {connect} from "react-redux";
import {updateSubject} from "../../actions/SubjectUptade";


class UpdateSubject extends Component {
    constructor(){
        super();
        this.state={
            "name":""

        };
        this.onChange=this.onChange.bind(this);
        this.onSubmit=this.onSubmit.bind(this);

    }
    onChange(e){
        const state=this.state;
        this.setState({[e.target.name]:e.target.value})
    }

    onSubmit(e){
        e.preventDefault();
        const newSubject={
            "name":this.state.name
        };
        console.log(newSubject);
        this.props.updateSubject(newSubject,this.props.history);
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
                            <h4 className="display-4 text-center">Modifier une matière</h4>
                            <form onSubmit={this.onSubmit}>
                                <div className="form-group">
                                    <input type="text" className="form-control form-control-lg" name="name" value={this.state.name} placeholder="" onChange={this.onChange} />
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

/*UpdateSubject.prototype= {
    addSubject: PropTypes.func.isRequired,
    error:PropTypes.object.isRequired
};*/

const mapStateToProps =state => ({
    errors: state.errors
});
export default connect(null, {updateSubject}) (UpdateSubject);