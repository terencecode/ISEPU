import React, { Component } from 'react'
import {Link} from "react-router-dom";
import { Button, ButtonGroup} from 'reactstrap';
import {getUserProfile,getCourseOfUser} from "../../utils/APIUtils";
 import LoadingIndicator  from '../../common/LoadingIndicator';
import NotFound from '../../common/NotFound';
import ServerError from '../../common/ServerError';


class ListOfCourse extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [],
            user:null,
            isLoading:false
        };
        this.headers = [

            {key: 'name', label: 'Name'},
            {key: 'description', label: 'Description'}
        ];
        this.remove = this.remove.bind(this);
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
                    getCourseOfUser()
                        .then(result => {

                                this.setState({
                                    data: result
                                });
                                console.log(this.state.data);
;                            }
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
    async remove(id) {
        await fetch(`http://localhost:8080/course/${id}`, {
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
       console.log(this.state.user);

        if(this.state.isLoading) {
            return <LoadingIndicator />;
        }

        if(this.state.notFound) {
            return <NotFound />;
        }

        if(this.state.serverError) {
            return <ServerError />;
        }

        return (


            <div className="container">
                <div className="col-md-8 m-auto">

                        <Link to="/addCourse" className="btn btn-light">Ajouter un Cours</Link>
                        <br />
                        <hr />

                        <div className="tab-content swing-in-top-fwd" id="nav-tabContent">
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
                                                <td>{item.name}</td>
                                                <td>{item.description}</td>

                                                <td>
                                                    <ButtonGroup>
                                                        <Link className="btn btn-primary" to={`/addSession/${item.name}`}>Ajouter une session</Link>
                                                        <Link className="btn btn-success" to={`/addStudent/${item.name}`}> Ajouter un eleve</Link>


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
        );

    }
}

export default ListOfCourse;