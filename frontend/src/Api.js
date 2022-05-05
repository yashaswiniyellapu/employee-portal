import axios from "axios";
var config = {
    headers: { "Content-Type":"application/json",
"Access-Control-Allow-Origin":"*"},
  };
const API_BASE_URL = 'http://localhost:8080'

export function fetchEmployees() {
    return axios.get(`${API_BASE_URL}/api/employees`,config);
}

export function createEmployee(employee) {
    return axios.post(`${API_BASE_URL}/api/employees`, employee,config);
}

export function deleteEmployee(id) {
    return axios.delete(`${API_BASE_URL}/api/employees/${id}`,config);
}

export function fetchEmployeeById(id) {
    return axios.get(`${API_BASE_URL}/api/employees/${id}`,config);
}

export function updateEmployee(id,employee) {
    return axios.delete(`${API_BASE_URL}/api/employees/${id}`,employee,config);
}
export function searchByName(name) {
    return axios.get(`${API_BASE_URL}/api/employees/search?query=${name}`,config);
}
