
import {GET_ERRORS} from "./types";
import {ACCESS_TOKEN} from "../constants";




export const logoutUser =(history)=> async dispatch => {
    try {
        localStorage.removeItem(ACCESS_TOKEN);
        history.push("/login");
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