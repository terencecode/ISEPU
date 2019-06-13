import React, { Component } from 'react';

import { getUserProfile } from '../../utils/APIUtils';
import { Avatar, Tabs } from 'antd';
import {Link} from "react-router-dom";
import { formatDate } from '../../utils/Helper';
import LoadingIndicator  from '../../common/LoadingIndicator';
import './Profile.css';
import NotFound from '../../common/NotFound';
import ServerError from '../../common/ServerError';
import {ACCESS_TOKEN} from "../../constants";
import ProfileNotReady from './img/profile_notReady.svg';

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
                if(this.state.user.promo===undefined){
                    this.setState({
                        Type:'professeur'
                    })
                }else{
                    this.setState({
                        Type:'Eleve'
                    })
                }
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
    handleLogout(){
        localStorage.removeItem(ACCESS_TOKEN);

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
                        <div className="profile-sidebar swing-in-top-fwd">

                            <div className="profile-userpic">
                                <Avatar size="large" style={{ backgroundColor: '#87d068' }} icon="user" />
                            </div>

                            <div className="profile-usertitle">
                                <div className="profile-usertitle-name">
                                    {this.state.user.firstName} {this.state.user.lastName} {this.state.user.email}
                                </div>
                                <div className="profile-usertitle-job">
                                    {this.state.Type}
                                </div>
                            </div>

                            <div className="profile-userbuttons">
                                <Link to='/login'> <button type="button" className="btn btn-danger btn-sm" onClick={this.handleLogout}>Logout</button></Link>
                            </div>


                        </div>



                    ): null
                }
            </div>
                <div className='col-md-9'>
                    <div className="profile-content swing-in-top-fwd">
                        <img src={ProfileNotReady} alt="ProfileNotReady" className="profileSVG"/>
                        <div className="desc">
                            We have to get homeWork and Courses and displays them here
                        </div>
                    </div>

                </div>
            </div>
        );
    }
}

export default Profile;