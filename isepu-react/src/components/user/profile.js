import React, { Component } from 'react';

import { getUserProfile } from '../../utils/APIUtils';
import { Avatar, Tabs } from 'antd';
import { getAvatarColor } from '../../utils/Colors';
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
            <div className="profile">
                {
                    this.state.user ? (
                        <div className="user-profile">
                            <div className="user-details">
                                <div className="user-avatar">
                                    <Avatar className="user-avatar-circle" style={{ backgroundColor: getAvatarColor(this.state.user.name)}}>
                                        {this.state.user.name[0].toUpperCase()}
                                    </Avatar>
                                </div>
                                <div className="user-summary">
                                    <div className="full-name">{this.state.user.first_name}</div>
                                    <div className="username">@{this.state.user.last_name}</div>

                                </div>
                            </div>
                            <div className="user-poll-details">
                                <Tabs defaultActiveKey="1"
                                      animated={false}
                                      tabBarStyle={tabBarStyle}
                                      size="large"
                                      className="profile-tabs">
                                    <TabPane tab={`${this.state.user.pollCount} Polls`} key="1">
                                        {/*<PollList username={this.props.match.params.username} type="USER_CREATED_POLLS" />*/}
                                    </TabPane>
                                    <TabPane tab={`${this.state.user.voteCount} Votes`}  key="2">
                                        {/*<PollList username={this.props.match.params.username} type="USER_VOTED_POLLS" />*/}
                                    </TabPane>
                                </Tabs>
                            </div>
                        </div>
                    ): null
                }
            </div>
        );
    }
}

export default Profile;