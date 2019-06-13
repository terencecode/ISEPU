import axios from "axios";
import {GET_ERRORS} from "./types";
import {ACCESS_TOKEN} from "../constants";




export const addCourse =(course,history)=> async dispatch => {
    try {
        await axios.post("http://localhost:8080/course", course,{headers:{Authorization:"Bearer "+ localStorage.getItem(ACCESS_TOKEN)}});
    history.push("/");
    dispatch({
        type:GET_ERRORS,
        payload:{}
    })
    } catch (error) {
        dispatch({
            type:GET_ERRORS,
            payload:error.response.data
        })
    }
};
export const addStudent =(course,history)=> async dispatch => {
    try {
        await axios.post("http://localhost:8080/course/register", course,{headers:{Authorization:"Bearer "+ localStorage.getItem(ACCESS_TOKEN)}});
        history.push("/Course");
        dispatch({
            type:GET_ERRORS,
            payload:{}
        })
    } catch (error) {
        dispatch({
            type:GET_ERRORS,
            payload:error.response.data
        })
    }
};