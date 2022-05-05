import React, { Component, useEffect, useState } from 'react';
import * as api from "./../Api";

import 'bootstrap/dist/css/bootstrap.css'
import { useNavigate } from 'react-router-dom';

const EmployeeList = () => {

    let nav = useNavigate();


    const [employees, setEmployees] = useState([]);
    const [employyeName, setEmployeeName] = useState([]);

    const loadEmployees = () => {
        api.fetchEmployees().then(response => {
            console.log("fetched employees successfully", response)
            setEmployees(response.data.data);
        }).catch(e => console.log("error", e));
    }
    const deleteEmployee = (id) => {
        api.deleteEmployee(id).then(response => {
            console.log("employee deleted successfully", response)
            loadEmployees();
        }).catch(e => console.log("error", e));
    }
    const searchEmployeeByName=(empName)=>{
        api.searchByName(empName).then(response=>{
            console.log("fetched employee successfully", response)
            setEmployees(response.data.data)
        }).catch(e=>console.log("error",e))
    }

    useEffect(()=>{
        loadEmployees();
    },[]);
  
    return ( 
        <div id={"employeeList"}>
        <button onClick={()=>nav('/create')}>create</button>
        <h2>Search employee</h2>
        <div className="form-group col-md-10">
                <label htmlFor="searchByName">Search By Name</label>
                <input
                    id="searchByName"
                    placeholder={"Enter the Name"}
                    className="form-control col-md-12"
                    onChange={e => setEmployeeName(e.target.value)}   
                />
            </div>
            <button className={"btn btn-warning"} onClick={()=>searchEmployeeByName(employyeName)}>Search</button>

        <table className={"table"}>
            <thead>
            <tr>
                <td>Id</td>
                <td>Name</td>
                <td>Email</td>
                <td>Delete</td>
                <td>Update</td>
            </tr>
            </thead>
            <tbody>
            {employees.map(emp => {
                return (
                    <tr key={emp.empId}>
                        <td>{emp.empId}</td>
                        <td>{emp.firstName}</td>
                        <td>{emp.everestEmailId}</td>
                        <td><button className={"btn btn-danger"} onClick={()=> deleteEmployee(emp.empId)}>Delete</button></td>
                        <td><button className={"btn btn-warning"} onClick={()=> nav('/update/'+emp.empId)}>Update</button></td>
                    
                        
                    </tr>
                );
            })}
            </tbody>
        </table>

    </div>
     );
}
 
export default EmployeeList;

 