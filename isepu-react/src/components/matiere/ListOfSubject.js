import React, { Component } from 'react'
import {Link} from "react-router-dom";

export default class ListOfSubject extends Component {
    constructor(props){
        super(props);
        this.state={
            name:""
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
            <div id="container">
				<Link to="/create">Add Website</Link>
				
				<table>
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
										<Link to={`/update/${item.id}`}>Edit</Link>
										&nbsp;
										
								  </td>
								</tr>
											)
							}.bind(this))
						}
					</tbody>
				</table>
			</div>
        )
    }
}
