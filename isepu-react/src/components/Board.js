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

        const devoirs = this.props.Homeworks;
        let BoardContent;
        let todoItems = [];
        let inProgressItems = [];
        let doneItems = [];


       function BoardAlgorith(){
            if (devoirs.length < 1) {
                return <div className="alert alert-info text-center" role="alert">
                    No Sessions on this board
                </div>
            } else {
                const task = devoirs.map(homeworks => {
                     //<CourseItem key={homeworks.id}/>
                });
                for (let i = 0; i < task.length; i++) {
                    if (task[i].props.Homework.status === "TO DO") {
                        todoItems.push(task[i]);
                    }
                    if (task[i].props.Homework.status === "IN PROGRESS") {
                        inProgressItems.push(task[i]);
                    }
                    if (task[i].props.Homework.status === "DONE") {
                        doneItems.push(task[i]);
                    }
                }

            }
        }

        return (
            <div className="container">
        <Link to="/addCourse" className="btn btn-light mb-3">
            Ajouter un devoir
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
                    {console.log(todoItems)}

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
    Homeworks: PropTypes.object.isRequired
};
const mapStateToProps =state => ({
    Homework:state.Homeworks
});
export default connect(null,{getHomework}) (Board);
