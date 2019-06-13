import{combineReducers}from"redux";
import errorsReducer from "./errorsReducer";
import HomeworkReducer from "./HomeworkReducer";
export default combineReducers({
    //
    errors:errorsReducer,
    Homework:HomeworkReducer
});