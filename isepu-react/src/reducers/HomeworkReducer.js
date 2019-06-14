import {GET_HOMEWORK} from "../actions/types";

const initialState={
    Sessions:[]
};

export default function (state=initialState,action) {
    switch (action.type) {

        case GET_HOMEWORK:
            return {
                ...state,
                Sessions:action.payload
            };
        default:
            return state;


    }

}