import React, { Component, useEffect, useState } from 'react';
import ReactDOM from 'react-dom';
import { useNavigate, useParams } from 'react-router-dom';
import * as api from "./../Api";

 const UpdateEmployee = () => {

    let {id} = useParams();
    
    let nav = useNavigate();

    const[firstName,setFirstName]= useState("");
    const[lastName,setlastName]= useState("");
    const[personalEmailId,setPersonalEmailId]= useState("");
    const[everestEmailId,setEverestEmailID]= useState("");
    const[dateOfJoin,setDateOfJoin]= useState("");
    const[dateOfBirth,setDateOfBirth]= useState("");
    const[bio,setBio]= useState("");
    const[preExp,setPreExp]= useState("");
    const[password,setPassword]=useState("");


    const getEmployee = () =>{
        api.fetchEmployeeById(id).then(response=>{
            setFirstName(response.data.firstName);
            setEverestEmailID(response.data.everestEmailId);
            setPassword(response.data.password);
            setBio(response.data.bio);
            setDateOfBirth(response.data.dateOfBirth);
            setDateOfJoin(response.data.dateOfJoin);
            setlastName(response.data.lastName);
            setPreExp(response.data.preExp);
            setPersonalEmailId(response.data.personalEmailId);
        })
    }

    const handleSubmit = e => {
        e.preventDefault();
        if(!firstName || !everestEmailId || !password) {
            alert("Enter all fields");
            return;
        }
        const employee = {
            firstName,
            lastName,
            everestEmailId,
            personalEmailId,
            dateOfBirth,
            dateOfJoin,
            bio,
            preExp,
            password
        };
            api.updateEmployee(id,employee).then(response => {
                console.log("employee deleted successfully", response)
                setFirstName("");
                setEverestEmailID("");
                setPassword("");
                setBio("");
                setDateOfBirth("");
                setDateOfJoin("");
                setlastName("");
                setPreExp("");
                setPersonalEmailId("");
                nav('/') ;
            }).catch(e => console.log("error", e));
        
}
useEffect(()=>{
    getEmployee();
},[]);

    return (
        <div id={"addEmployeeForm"}>
        <h2>Add New Employee</h2>
        <form onSubmit={e => handleSubmit(e)} className="row justify-content-center">
            <div className="form-group col-md-10">
                <label htmlFor="firstName">FirstName</label>
                <input
                    id="firstName"
                    placeholder={"Enter the firstName"}
                    className="form-control col-md-12"
                    value={firstName}
                    onChange={e => setFirstName(e.target.value)}
                />
            </div>
            <div className="form-group col-md-10">
                <label htmlFor="lastName">lastName</label>
                <input
                    id="lastName"
                    placeholder={"Enter the firstName"}
                    className="form-control col-md-12"
                    value={lastName}
                    onChange={e => setlastName(e.target.value)}
                />
            </div>
            <div className="form-group col-md-10">
                <label htmlFor="password">Password</label>
                <input
                    id="password"
                    className="form-control col-md-12"
                    placeholder="enter your password"
                    type="password"
                    value={password}
                    onChange={e => setPassword(e.target.value)}
                />
            </div>
            <div className="form-group col-md-10">
                <label htmlFor="everestEmailId">EverestEmailId</label>
                <input
                    id="email"
                    placeholder={"Enter the EverestEmail"}
                    className="form-control col-md-12"
                    type="text"
                    value={everestEmailId}
                    onChange={e => setEverestEmailID(e.target.value)}
                />
            </div>
            <div className="form-group col-md-10">
                <label htmlFor="personalEmailId">PersonalEmailId</label>
                <input
                    id="personalEmailId"
                    placeholder={"Enter the PersonalEmail"}
                    className="form-control col-md-12"
                    type="text"
                    value={personalEmailId}
                    onChange={e => setPersonalEmailId(e.target.value)}
                />
            </div>
            <div className="form-group col-md-10">
                <label htmlFor="dateOfBirth">DateOfBirth</label>
                <input
                    id="dateOfBirth"
                    placeholder={"Enter the DatefBirth"}
                    className="form-control col-md-12"
                    type="date"
                    value={dateOfBirth}
                    onChange={e => setDateOfBirth(e.target.value)}
                />
            </div>
            <div className="form-group col-md-10">
                <label htmlFor="dateOfJoin">DateOfJoin</label>
                <input
                    id="dateOfJoin"
                    placeholder={"Enter the DateOfJoin"}
                    className="form-control col-md-12"
                    type="date"
                    value={dateOfJoin}
                    onChange={e => setDateOfJoin(e.target.value)}
                />
            </div>
            <div className="form-group col-md-10">
                <label htmlFor="bio">Bio</label>
                <input
                    id="bio"
                    placeholder={"Enter the Bio"}
                    className="form-control col-md-12"
                    type="text"
                    value={bio}
                    onChange={e => setBio(e.target.value)}
                />
            </div>
            <div className="form-group col-md-10">
                <label htmlFor="prevExp">Previous Experience</label>
                <input
                    id="preExp"
                    placeholder={"Enter the Preveious Experience"}
                    className="form-control col-md-12"
                    type="number"
                    value={preExp}
                    onChange={e => setPreExp(e.target.value)}
                />
            </div>
         
            <div className="form-group col-md-10">
                <button type="submit" className="btn btn-primary">
                    Save
                </button>
            </div>
        </form>
    </div>
      );
}

export default UpdateEmployee;
 