import React, { Component } from 'react';

import { getUserProfile } from '../../utils/APIUtils';
import { Avatar, Tabs } from 'antd';

import { formatDate } from '../../utils/Helper';
import LoadingIndicator  from '../../common/LoadingIndicator';
import './Profile.css';
import NotFound from '../../common/NotFound';
import ServerError from '../../common/ServerError';

const TabPane = Tabs.TabPane;

class Profile extends Component {
    constructor(props) {
        super(props);
        this.state = {
            user: null,
            isLoading: false
        }
        this.loadUserProfile = this.loadUserProfile.bind(this);
    }

    loadUserProfile(id) {
        this.setState({
            isLoading: true
        });

        getUserProfile(id)
            .then(response => {
                this.setState({
                    user: response,
                    isLoading: false
                });
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

    componentDidMount() {
        const id = this.props.match.params.id;
        this.loadUserProfile(id);
    }

    componentDidUpdate(nextProps) {
        if(this.props.match.params.id !== nextProps.match.params.id) {
            this.loadUserProfile(nextProps.match.params.id);
        }
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

        const tabBarStyle = {
            textAlign: 'center'
        };

        return (
            <div className="row profile">
                <div className="col-md-3">

                {
                    this.state.user ? (
                        <div className="profile-sidebar">

                            <div className="profile-userpic">
                                <img
                                    src="./img/Student.svg"
                                    className="img-responsive" alt=""/>
                            </div>

                            <div className="profile-usertitle">
                                <div className="profile-usertitle-name">
                                    {this.state.user.firstName} {this.state.user.lastName}
                                </div>
                                <div className="profile-usertitle-job">
                                    Developer
                                </div>
                            </div>

                            <div className="profile-userbuttons">
                                <button type="button" className="btn btn-success btn-sm">Follow</button>
                                <button type="button" className="btn btn-danger btn-sm">Logout</button>
                            </div>
                            <div className="profile-usermenu">
                                <ul className="nav">
                                    <li className="active">
                                        <a href="#">
                                            <i className="glyphicon glyphicon-home"></i>
                                            Overview </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i className="glyphicon glyphicon-user"></i>
                                            Account Settings </a>
                                    </li>
                                    <li>
                                        <a href="#" target="_blank">
                                            <i className="glyphicon glyphicon-ok"></i>
                                            Tasks </a>
                                    </li>
                                    <li>
                                        <a href="#">
                                            <i className="glyphicon glyphicon-flag"></i>
                                            Help </a>
                                    </li>
                                </ul>
                            </div>

                        </div>



                    ): null
                }
            </div>
            </div>
        );
    }
}

export default Profile;