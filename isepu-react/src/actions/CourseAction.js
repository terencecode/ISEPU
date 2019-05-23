import axios from "axios";

export const addCourse =(course,history)=> async dispatch => {
    await axios.post("http://localhost:8080/Course", course);
    history.push("/");
}