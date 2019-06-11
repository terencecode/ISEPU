import axios from "axios";
import {GET_ERRORS} from "./types";
import {ACCESS_TOKEN} from "../constants";




export const addSession =(session,history)=> async dispatch => {
    try {
        await axios.post("http://localhost:8080/session", session,{headers:{Authorization:"Bearer "+ localStorage.getItem(ACCESS_TOKEN)}});
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
}