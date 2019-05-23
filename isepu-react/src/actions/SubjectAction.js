import axios from "axios";
import {GET_ERRORS} from "./types";




export const addSubject =(Subject,history)=> async dispatch => {
    try {
        await axios.post("http://localhost:8080/Subject",Subject);
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
}