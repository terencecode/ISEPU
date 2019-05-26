import React, { Component } from 'react'
import {Link} from "react-router-dom"
class CourseItem extends Component {
    render() {
        return (
            <div className="card mb-1 bg-light">

                        <div className="card-header text-primary">
                            Devoirs GL
                        </div>
                        <div className="card-body bg-light">
                            <h5 className="card-title">Livrable 1</h5>
                            <p className="card-text text-truncate ">
                                rendre le livrable 1 avec les <br></br>
                                personas pour le 6 mai 
                            </p>
                            <Link   className="btn btn-primary">
                                View / Update
                            </Link>

                            <button className="btn btn-danger ml-4">
                                Delete
                            </button>
                        </div>
                    </div>

        )
    }
}
export default CourseItem;