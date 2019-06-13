import {GET_HOMEWORK} from "../actions/types";

const initialState={
    Homework:[]
};

export default function (state=initialState,action) {
    switch (action.type) {

        case GET_HOMEWORK:
            return {
                ...state,
                Homework:action.payload
            };
        default:
            return state;


    }

}