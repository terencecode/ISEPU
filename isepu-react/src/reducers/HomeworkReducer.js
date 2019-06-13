import {GET_HOMEWORK} from "../actions/types";

const initialState={
    Homeworks:[]
};

export default function (state=initialState,action) {
    switch (action.type) {

        case GET_HOMEWORK:
            return {
                ...state,
                Homeworks:action.payload
            };
        default:
            return state;


    }

}