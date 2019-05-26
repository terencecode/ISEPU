import React, { Component } from 'react'
import {Link} from "react-router-dom";

export default class ListOfSubject extends Component {
    constructor(props){
        super(props);
        this.state={
            name:[]
        }
        this.headers=[
            {key:'id',label:'Id'},
            {key:'name',label:'Name'}
        ];
    }

    componentDidMount(){
        fetch('http://localhost:8080/Subject')
        .then(response=>{
            return response.json();
        }).then(result=>{
            console.log(result);
            this.setState({
                name:result
            });
        });
    }
    render() {
        return (
            <div className="container">
			<div className="col-md-8 m-auto">
				<Link to="/addSubject" className="btn btn-light">Add Subject</Link>


                <div className="tab-content" id="nav-tabContent">
                <div className="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
				<table className="table" cellspacing="0">
					<thead>
						<tr>
						{
							this.headers.map(function(h) {
								return (
									<th key = {h.key}>{h.label}</th>
								)
							})
						}
						  <th>Actions</th>
						</tr>
					</thead>
					<tbody>
						{
							this.state.name.map(function(item, key) {
							return (
								<tr key = {key}>
								  <td>{item.id}</td>
								  <td>{item.name}</td>
								  
								  <td>
										<Link className="btn btn-primary" to={`/update/${item.id}`}>Edit</Link>
										&nbsp;
										
								  </td>
								</tr>
											)
							}.bind(this))
						}
					</tbody>
				</table>
			</div>
            </div>
            </div>
			</div>
        )
    }
}
