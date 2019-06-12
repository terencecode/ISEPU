import React, {Component} from 'react'
import {Link, withRouter} from "react-router-dom"
import {getUserProfile, ListOfSubjects} from "../utils/APIUtils";
class Navbar extends Component {
    constructor(props){
        super(props);
        this.state={
            user:''
        };
        this.loadUserProfile=this.loadUserProfile.bind(this);
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


    componentWillMount()
    {
        this.loadUserProfile();
        if(this.props.isAuthenticated===false){
            this.props.history.push('/login');
        }
        console.log('props',this.props)
    }
    render(){
    return (
        <nav className="navbar navbar-expand-sm navbar-dark bg-primary mb-4">
        <div className="container">
            <Link className="navbar-brand" to="/">
                ISEPU
            </Link>
            <button className="navbar-toggler" type="button" data-toggle="collapse" data-target="#mobile-nav">
                <span className="navbar-toggler-icon" />
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <Link className="nav-link" to="/">Board <span class="sr-only">(current)</span></Link>
      </li>
      <li className="nav-item">
        <Link className="nav-link" to="/Subject">Matière</Link>
      </li>
      <li className="nav-item dropdown">
        <Link className="nav-link dropdown-toggle" to='#' id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Dropdown
        </Link>
        <div className="dropdown-menu" aria-labelledby="navbarDropdown">
          <Link className="dropdown-item" to="#">Action</Link>
          <Link className="dropdown-item" to="#">Another action</Link>
          <div className="dropdown-divider"></div>
          <Link className="dropdown-item" to="#">Something else here</Link>
        </div>
      </li>
      <li className="nav-item">
        <Link className="nav-link " to="/Profile">Profile</Link>
      </li>
    </ul>
    </div>
        </div>
    </nav>
    )
}
}

export default withRouter(Navbar);