import axios from "axios";
import {GET_ERRORS, GET_HOMEWORK} from "./types";
import {ACCESS_TOKEN} from "../constants";



export const addHomework =(homework,history)=> async dispatch => {
    try {
        await axios.post("http://localhost:8080/homework", homework,{headers:{Authorization:"Bearer "+ localStorage.getItem(ACCESS_TOKEN)}});
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

export const getHomework = () => async dispatch => {
    const res = await axios.get('http://localhost:8080/homework/all',{headers:{Authorization:"Bearer "+ localStorage.getItem(ACCESS_TOKEN)}});
    dispatch({
        type:GET_HOMEWORK,
        payload:res.data
    });
    console.log(res.data);
};