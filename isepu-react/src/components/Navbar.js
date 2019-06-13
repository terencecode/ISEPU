import React, {Component} from 'react'
import {Link, withRouter} from "react-router-dom"
import { Dropdown, DropdownItem, DropdownToggle, DropdownMenu } from 'reactstrap';
import {getUserProfile} from "../utils/APIUtils";
class Navbar extends Component {
    constructor(props){
        super(props);
        this.state={
            user:'',
            dropdownOpen:false,
            prof:false
        };
        this.loadUserProfile=this.loadUserProfile.bind(this);
        this.toggle=this.toggle.bind(this);
    }

    loadUserProfile() {
        this.setState({
            isLoading: true
        });

        getUserProfile()
            .then(response => {
                this.setState({
                    user: response,
                    isLoading: false
                });
                if (this.state.user.promo!== undefined) {
                    this.setState({
                        prof:true
                    });
                    console.log(this.state);
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
    toggle() {
        this.setState({
            dropdownOpen: !this.state.dropdownOpen
        });
    }


    componentWillMount()
    {
        this.loadUserProfile();
        if(!this.props.isAuthenticated){
            this.props.history.push('/login');
        }
    }
    render() {
        if (this.props.isAuthenticated) {
            return (
                <nav className="navbar navbar-expand-sm navbar-dark bg-primary mb-4">
                    <div className="container">
                        <Link className="navbar-brand" to="/">
                            ISEPU
                        </Link>
                        <button className="navbar-toggler" type="button" data-toggle="collapse"
                                data-target="#mobile-nav">
                            <span className="navbar-toggler-icon"/>
                        </button>
                        <div className="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul className="navbar-nav mr-auto">
                                <li className="nav-item active">
                                    <Link className="nav-link" to="/">Board <span
                                        className="sr-only">(current)</span></Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/Subject">Matière</Link>
                                </li>

                                    <Dropdown className="" nav isOpen={this.state.dropdownOpen} toggle={this.toggle}>
                                        <DropdownToggle className="nav-link" nav caret>
                                        Cours
                                        </DropdownToggle>
                                    <DropdownMenu>
                                        <Link to='/Course'> <DropdownItem className="dropdown-item">Cours</DropdownItem></Link>
                                        <Link className="dropdown-item" to="/listSession">Liste des sessions</Link>

                                        <Link className="dropdown-item" to="#">Liste des devoirs</Link>
                                    </DropdownMenu>
                                    </Dropdown>

                                <li className="nav-item">
                                    <Link className="nav-link " to="/Profile">Profile</Link>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
            )
        } else {
            return null;
        }
    }
}

export default withRouter(Navbar);