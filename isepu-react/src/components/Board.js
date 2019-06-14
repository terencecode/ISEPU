import React, { Component } from 'react'
import {Link} from "react-router-dom";
import CourseItem from './cours/CourseItem';
import {connect} from "react-redux";
import PropTypes from 'prop-types';
import {getHomework} from '../actions/HomeworkAction';

class Board extends Component {

    componentDidMount() {
        this.props.getHomework();
    }

    render() {
       /* const {sessions}=this.props.Sessions;
        let BoardContent;
        let todoItems=[];
        let inProgressItems =[];
        let doneItems=[];
        const BoardAlgorith = sessions => {
            if (sessions.length<1){
                return <div className="alert alert-info text-center" role="alert">
                    No Sessions on this board
                </div>
            }else{
                const homework=sessions.map( session =>{
                   // <CourseItem key={session.id} session={session}/>
                });
                for(let i=0;i<sessions.length;i++){
                    console.log(sessions[i]);
                }

            }
        }*/
        return (
            <div className="container">
        <Link to="/addCourse" className="btn btn-primary mb-3">
            <i className="fas fa-plus-circle"> Ajouter un devoir</i>
        </Link>
        <br />
        <hr />
        <div className="container">
            <div className="row">
                <div className="col-md-4">
                    <div className="card text-center mb-2">
                        <div className="card-header bg-secondary text-white">
                            <h3>TO DO</h3>
                        </div>
                    </div>

                    {//<!-- SAMPLE PROJECT TASK STARTS HERE -->
                    }
                    <CourseItem />
                    {
                        //<!-- SAMPLE PROJECT TASK ENDS HERE -->
                    }
                </div>
                <div className="col-md-4">
                    <div className="card text-center mb-2">
                        <div className="card-header bg-primary text-white">
                            <h3>In Progress</h3>
                        </div>
                    </div>
                    {
                        //<!-- SAMPLE PROJECT TASK STARTS HERE -->
                        
                    //<!-- SAMPLE PROJECT TASK ENDS HERE -->
                    }

                    <CourseItem/>
                </div>
                <div className="col-md-4">
                    <div className="card text-center mb-2">
                        <div className="card-header bg-success text-white">
                            <h3>Done</h3>
                        </div>
                    </div>
                    {
                        //<!-- SAMPLE PROJECT TASK STARTS HERE -->

                    //<!-- SAMPLE PROJECT TASK ENDS HERE -->
                    }
                </div>
            </div>
        </div>

    </div>
        );    
    }
}
Board.propTypes={
    getHomework:PropTypes.func.isRequired,
    Sessions: PropTypes.object.isRequired
};
const mapStateToProps =state => ({
    Sessions:state.Homework
});
export default connect(null,{getHomework}) (Board);
