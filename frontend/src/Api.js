import axios from "axios";
//var config = {
//    headers: { "Content-Type":"application/json",
//"Access-Control-Allow-Origin":"*",
//  };
const API_BASE_URL = 'http://13.233.117.57:8080'

export function fetchEmployees() {
    return axios.get(`${API_BASE_URL}/api/employees`);
}

export function createEmployee(employee) {
    return axios.post(`${API_BASE_URL}/api/employees`, employee);
}

export function deleteEmployee(id) {
    return axios.delete(`${API_BASE_URL}/api/employees/${id}`);
}

export function fetchEmployeeById(id) {
    return axios.get(`${API_BASE_URL}/api/employees/${id}`);
}

export function updateEmployee(id,employee) {
    return axios.delete(`${API_BASE_URL}/api/employees/${id}`,employee);
}
export function searchByName(name) {
    return axios.get(`${API_BASE_URL}/api/employees/search?query=${name}`);
}
