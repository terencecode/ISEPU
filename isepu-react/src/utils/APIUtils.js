import { API_BASE_URL, ACCESS_TOKEN } from '../constants';

const request = (options) => {
    const headers = new Headers({
        'Content-Type': 'application/json',
    });
    
    if(localStorage.getItem(ACCESS_TOKEN)) {
        headers.append('Authorization', 'Bearer ' + localStorage.getItem(ACCESS_TOKEN))
    }

    const defaults = {headers: headers};
    options = Object.assign({}, defaults, options);

    return fetch(options.url, options)
    .then(response => 
        response.json().then(json => {
            if(!response.ok) {
                return Promise.reject(json);
            }
            return json;
        })
    );
};



export function login(loginRequest) {
    return request({
        url: API_BASE_URL + "/auth",
        method: 'POST',
        body: JSON.stringify(loginRequest)
    });
}
export function ListOfSubjects(){
    return request({
        url:API_BASE_URL+ "/subject/all",
        method:'GET'

    })
}
export function RemoveItemSubject(id) {
    return request(
        {
            url:API_BASE_URL+"/subject/"+{id},
            method:"DELETE"

        }
    )
}
export function signup(signupRequest) {
    return request({
        url: API_BASE_URL + "/auth/professor",
        method: 'PUT',
        body: JSON.stringify(signupRequest)
    });
}
export function signupEleve(signupRequest) {
    return request({
        url: API_BASE_URL + "/auth/student",
        method: 'PUT',
        body: JSON.stringify(signupRequest)
    });
}
export function checklastNameAvailability(lastname) {
    return request({
        url: API_BASE_URL + "/user/checklastnameAvailability?lastname=" + lastname,
        method: 'GET'
    });
}

export function checkEmailAvailability(email) {
    return request({
        url: API_BASE_URL + "/auth/" + email,
        method: 'GET'
    });
}


export function getCurrentUser() {
    if(!localStorage.getItem(ACCESS_TOKEN)) {
        return Promise.reject("No access token set.");
    }

    return request({
        url: API_BASE_URL + "/user",
        method: 'GET'
    });
}
export function getCourseOfUser(email) {
    return request({
        url:API_BASE_URL+"/course/all/email/"+email,
        method:'GET'
    })
}
export function getAllStudent(){
    return request({
        url:API_BASE_URL+"/student/all",
        method:'GET'
    })
}
export function getUserProfile() {
    return request({
        url: API_BASE_URL + "/user",
        method: 'GET'
    });
}
export function getSession(){
    return request({
        url:API_BASE_URL+"/session/all",
        method:'GET'
    })
}

