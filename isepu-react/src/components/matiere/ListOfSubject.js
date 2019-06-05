import React, { Component } from 'react'
import {Link} from "react-router-dom";
import {ListOfSubjects} from '../../utils/APIUtils';
import { Button, ButtonGroup, Container, Table } from 'reactstrap';

 class ListOfSubject extends Component {
    constructor(props){
        super(props);
        this.state={
            data:[]
        };
        this.headers=[
            {key:'id',label:'Id'},
            {key:'name',label:'Name'}
        ];
        this.remove = this.remove.bind(this);
    }

    componentDidMount(){
        ListOfSubjects()
        .then(response=>

            this.setState({
                data:response
            })
        );
    }
    async remove(id) {
        await fetch(`http://localhost:8080/subject/${id}`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).then(() => {
            let updatedGroups = [...this.state.groups].filter(i => i.id !== id);
            this.setState({data: updatedGroups});
        });
    }

    render() {
        return (
            <div className="container">
			<div className="col-md-8 m-auto">
				<Link to="/addSubject" className="btn btn-light">Ajouter une matière</Link>
				<br />
        <hr />

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
							this.state.data.map(function(item, key) {
							return (
								<tr key = {key}>
								  <td>{item.id}</td>
								  <td>{item.name}</td>
								  
								  <td>
                                      <ButtonGroup>
										<Link className="btn btn-primary" to={`/update/${item.id}`}>Edit</Link>

                                        <Button className="btn btn-danger" >Delete</Button>

										&nbsp;
                                      </ButtonGroup>
								  </td>
								</tr>
											)
							})
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

export default ListOfSubject;