import React from 'react'
import {Link} from "react-router-dom"
export default function Navbar() {
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
        <Link className="nav-link" to="/">Home <span class="sr-only">(current)</span></Link>
      </li>
      <li className="nav-item">
        <Link className="nav-link" to="/Subject">Subject</Link>
      </li>
      <li className="nav-item dropdown">
        <Link className="nav-link dropdown-toggle" to="/Course" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
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
        <Link className="nav-link " to="/Profile/:id">Profile</Link>
      </li>
    </ul>
    </div>
        </div>
    </nav>
    )
}
