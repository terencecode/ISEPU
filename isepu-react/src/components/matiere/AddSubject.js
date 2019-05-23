import React, { Component } from 'react'
import {Link} from "react-router-dom";
import PropTypes from"prop-types";
import {connect} from "react-redux";
import {addSubject} from "../../actions/SubjectAction";


 class AddSubject extends Component {
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
         this.props.addSubject(newSubject,this.props.history);
        }
    render() {
        return (
            <div className="addProjectTask">
        <div className="container">
            <div className="row">
                <div className="col-md-8 m-auto">
                    <Link to="/" className="btn btn-light">
                        Back to Board
                    </Link>
                    <h4 className="display-4 text-center">Ajouter une matière</h4>
                    <form onSubmit={this.onSubmit}>
                        <div className="form-group">
                            <input type="text" className="form-control form-control-lg" name="name" value={this.state.name} placeholder="" onChange={this.onChange} />
                        </div>
                        {/* <div className="form-group">
                            <textarea className="form-control form-control-lg" placeholder="Acceptance Criteria" value={this.state.description} name="description" onChange={this.onChange}></textarea>
                        </div> */}
                        {/* <div className="form-group">
                            <select className="form-control form-control-lg" name="status" value={this.state.status} onChange={this.onChange}>
                                <option value="">Select Status</option>
                                <option value="TO_DO">TO DO</option>
                                <option value="IN_PROGRESS">IN PROGRESS</option>
                                <option value="DONE">DONE</option>
                            </select>
                        </div> */}
                        <input type="submit" className="btn btn-primary btn-block mt-4" />
                    </form>
                </div>
            </div>
        </div>
    </div>
        )
    }
}

addSubject.prototype= {
    addSubject: PropTypes.func.isRequired,
    error:PropTypes.object.isRequired
}

const mapStateToProps =state => ({
    errors: state.errors
});
export default connect(null, {addSubject}) (AddSubject);