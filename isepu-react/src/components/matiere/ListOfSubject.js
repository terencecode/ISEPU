import React, { Component } from 'react'
import {Link} from "react-router-dom";
import {ListOfSubjects, RemoveItemSubject} from '../../utils/APIUtils';
import { Button, ButtonGroup } from 'reactstrap';

 class ListOfSubject extends Component {
    constructor(props){
        super(props);
        this.state={
            data:[]
        };
        this.headers=[

            {key:'name',label:'Name'}
        ];
this.deleteSubject=this.deleteSubject.bind(this);
    }

    componentDidMount(){
        ListOfSubjects()
        .then(response=>

            this.setState({
                data:response
            })
        );
    }
    deleteSubject(name){
         RemoveItemSubject(name);
     }


     render() {
        return (
            <div className="container">
			<div className="col-md-8 m-auto">
				<Link to="/addSubject" className="btn btn-light">Ajouter une matière</Link>
				<br />
        <hr />

                <div className="tab-content swing-in-top-fwd" id="nav-tabContent">
                <div className="tab-pane fade show active" id="nav-home" role="tabpanel" aria-labelledby="nav-home-tab">
				<table className="table" cellSpacing="0">
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

								  <td>{item.name}</td>
								  
								  <td>
                                      <ButtonGroup>
										<Link className="btn btn-primary" to={`/update/${item.name}`}>Edit</Link>

                                        <Button className="btn btn-danger">Delete</Button>

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