import React, { Component } from 'react'
import {Link} from "react-router-dom";
import PropTypes from"prop-types";
import {connect} from "react-redux";
import {updateSubject} from "../../actions/SubjectUptade";


class UpdateSubject extends Component {
    constructor(props){
        super(props);
        this.state={
            "item":{
                "name":''
            }

        };
        this.onChange=this.onChange.bind(this);
        this.onSubmit=this.onSubmit.bind(this);

    }

    async componentDidMount() {
        if (this.props.match.params.id !== 'new') {
            const name = await (await fetch(`http://localhost:8080/subject/get/${this.props.match.params.id}`)).json();
            this.setState({item: name});
        }
    }
    onChange(e){
        const target = e.target;
        const value = target.value;
        const name = target.name;
        let item = {...this.state.item};
        item[name] = value;
        this.setState({item});
    }


    async onSubmit(e){
        e.preventDefault();
        const {item} = this.state;

        await fetch(`http://localhost:8080/subject/${this.props.match.params.id}`, {
            method:  'PUT' ,
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(item),
        });
        this.props.history.push('/Subject');
    }
    render() {
        return (
            <div className="addProjectTask">
                <div className="container">
                    <div className="row">
                        <div className="col-md-8 m-auto">
                            <Link to="/" className="btn btn-light">
                                Acceuil
                            </Link>
                            <h4 className="display-4 text-center">Modifier une matière</h4>
                            <form onSubmit={this.onSubmit}>
                                <div className="form-group">
                                    <input type="text" className="form-control form-control-lg" name="name" value={this.state.name} placeholder="" onChange={this.onChange} />
                                </div>

                                <input type="submit" className="btn btn-primary btn-block mt-4" />
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        )
    }
}

/*UpdateSubject.prototype= {
    addSubject: PropTypes.func.isRequired,
    error:PropTypes.object.isRequired
};*/

const mapStateToProps =state => ({
    errors: state.errors
});
export default connect(null, {updateSubject}) (UpdateSubject);