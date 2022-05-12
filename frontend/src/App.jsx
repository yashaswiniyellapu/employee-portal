import ReactDOM from 'react-dom';
import React, { Component } from 'react';
import {BrowserRouter as Router, Link, Route, Routes, useNavigate} from 'react-router-dom';
import EmployeeList from './components/employeelist';
import  CreateEmployee  from './components/CreateEmployee';
import UpdateEmployee from './components/UpdateEmployee';

const App = () => {
    let nav = useNavigate();
    return ( 
        <div>
         <Routes>
            <Route path='/' element={<EmployeeList/>} />
            <Route path='/create' element={<CreateEmployee/>} />
            <Route path='/update/:id' element={<UpdateEmployee/>} />  
         </Routes>
         </div>
     );
}
 
export default App;

