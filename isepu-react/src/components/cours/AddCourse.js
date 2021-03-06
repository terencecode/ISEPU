import React, { Component } from 'react'
import {Link} from "react-router-dom";
import PropTypes from"prop-types";
import {connect} from "react-redux";
import {addCourse} from "../../actions/CourseAction";
import NotFound from '../../common/NotFound';
import ServerError from '../../common/ServerError';

 class AddCourse extends Component {
     constructor(){
         super();
         this.state={
            "name":"",
            "description":"",



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
             "name":this.state.name,
             "description":this.state.description,


         };
         //console.log(newCourse);
         this.props.addCourse(newCourse,this.props.history);
         if(this.props.addCourse.errors===404){
             return <NotFound/>;
         }
         if (!(this.props.addCourse.errors === 500 || this.props.addCourse.errors === 401)) {
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
                    <h4 className="display-4 text-center">Ajouter un cours</h4>
                    <form onSubmit={this.onSubmit}>
                        <div className="form-group">
                            <input type="text" className="form-control form-control-lg" name="name" value={this.state.name} placeholder="Nom du cours" onChange={this.onChange} />
                        </div>
                        <div className="form-group">
                            <textarea className="form-control form-control-lg" placeholder="Description" value={this.state.description} name="description" onChange={this.onChange}></textarea>
                        </div>
                        <div className="form-group">
                            <textarea className="form-control form-control-lg" placeholder="matière" value={this.state.subjectName} name="subjectName" onChange={this.onChange}></textarea>
                        </div>
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

addCourse.prototype= {
    addCourse: PropTypes.func.isRequired,
    error:PropTypes.object.isRequired
};

const mapStateToProps =state => ({
    errors: state.errors
});
export default connect(null, {addCourse}) (AddCourse);