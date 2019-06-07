import { API_BASE_URL, POLL_LIST_SIZE, ACCESS_TOKEN } from '../constants';

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

export function getAllPolls(page, size) {
    page = page || 0;
    size = size || POLL_LIST_SIZE;

    return request({
        url: API_BASE_URL + "/polls?page=" + page + "&size=" + size,
        method: 'GET'
    });
}

export function createPoll(pollData) {
    return request({
        url: API_BASE_URL + "/polls",
        method: 'POST',
        body: JSON.stringify(pollData)         
    });
}

export function castVote(voteData) {
    return request({
        url: API_BASE_URL + "/polls/" + voteData.pollId + "/votes",
        method: 'POST',
        body: JSON.stringify(voteData)
    });
}

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
        url: API_BASE_URL + "/user/checkEmailAvailability?email=" + email,
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
export function getCourseOfUser(id) {
    return request({
        url:API_BASE_URL+"/course/all/"+id,
        method:'GET'
    })
}
export function getUserProfile(id) {
    return request({
        url: API_BASE_URL + "/user",
        method: 'GET'
    });
}

export function getUserCreatedPolls(username, page, size) {
    page = page || 0;
    size = size || POLL_LIST_SIZE;

    return request({
        url: API_BASE_URL + "/users/" + username + "/polls?page=" + page + "&size=" + size,
        method: 'GET'
    });
}

export function getUserVotedPolls(username, page, size) {
    page = page || 0;
    size = size || POLL_LIST_SIZE;

    return request({
        url: API_BASE_URL + "/users/" + username + "/votes?page=" + page + "&size=" + size,
        method: 'GET'
    });
}