import React, { Component } from 'react'
import {Link} from "react-router-dom";
import PropTypes from"prop-types";
import {connect} from "react-redux";
import {addStudent} from "../../actions/CourseAction";
import NotFound from '../../common/NotFound';
import ServerError from '../../common/ServerError';
import {getAllStudent,  getUserProfile} from "../../utils/APIUtils";

class AddStudent extends Component {
    constructor() {
        super();
        this.state = {
            courseName: "",
            studentEmails: "",
            data: [],
            user:null,
            isLoading:false


        };
        this.headers=[
            {key:"firstName",label:"Name"},
            {key:"lastName",label:"last name"}

        ];
        this.onChange = this.onChange.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
        this.loadUserProfile=this.loadUserProfile.bind(this);
    }

        loadUserProfile(){
            this.setState({
                isLoading: true
            });

            getUserProfile()
                .then(response => {
                    this.setState({
                        user: response,
                        isLoading: false
                    });
                    getAllStudent()
                        .then(result => {

                            this.setState({
                                data: result
                            });
                            console.log(this.state.data);
                        }
                        );

                }).catch(error => {
                if(error.status === 404) {
                    this.setState({
                        notFound: true,
                        isLoading: false
                    });
                } else {
                    this.setState({
                        serverError: true,
                        isLoading: false
                    });
                }

            });


        }






        componentDidMount(){


            this.loadUserProfile();
            console.log(this.state);




        }

    onChange(e){
        this.setState({[e.target.name]:e.target.value})
    }

    onSubmit(e){
        e.preventDefault();
        const newCourse={
            courseName:this.props.match.params.courseName,
            studentEmails: this.state.studentEmails


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

                                    <select className="form-control form-control-lg" name="status" value={this.state.studentEmails} onChange={this.onChange}>
                                        {this.state.data.map((key,item)=><option key={key} value={item.email}>{item.firstName}</option>)}
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

addStudent.prototype= {
    addStudent: PropTypes.func.isRequired,
    error:PropTypes.object.isRequired
};

const mapStateToProps =state => ({
    errors: state.errors
});
export default connect(null, {addStudent}) (AddStudent);