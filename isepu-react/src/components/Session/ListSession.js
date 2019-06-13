import React, { Component } from 'react'
import {Link} from "react-router-dom";
import { Button, ButtonGroup } from 'reactstrap';
import LoadingIndicator  from '../../common/LoadingIndicator';
import NotFound from '../../common/NotFound';
import ServerError from '../../common/ServerError';
import {getUserProfile,getSession} from "../../utils/APIUtils";
import Moment from 'react-moment';

class ListOfSession extends Component {
    constructor(props) {
        super(props);
        this.state = {
            data: [],
            user:null,
            isLoading:false
        };
        this.headers = [

            {key: 'courseName', label: 'Nom du cours'},
            {key: 'startingTime', label: 'Debut de la session'},
            {key: 'finishingTime', label: 'Fin de la session'}
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
                getSession()
                    .then(result => {

                        this.setState({
                            data: result
                        });
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

                    <Link to="/addCourse" className="btn btn-light">Ajouter une session</Link>
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
                                                <td>{item.courseName}</td>
                                                <td><Moment format="DD/MM/YYYY" date={item.startingTime}/></td>
                                                <td><Moment format="DD/MM/YYYY" date={item.finishingTime}/></td>

                                                <td>
                                                    <ButtonGroup>
                                                        <Link className="btn btn-primary" to={`/addHomework/${item.courseName}/${item.id}`}>Ajouter un devoir</Link>

                                                        <Button className="btn btn-danger" onClick={() => this.remove(item.id)}>Supprimer</Button>

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


export default  ListOfSession;