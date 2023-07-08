import React, { useContext, useState } from 'react'
import { UserLoginContext, UserLoginProvider } from '../context/UserLoginContext';
import { Outlet, Link, useNavigate } from 'react-router-dom';
import "../App.css";

export default function Login() {

    const {setCredentials} = useContext(UserLoginContext)

    const handleOnSubmit = async(e) => {
        e.preventDefault();
        let body = {
        "username" : document.getElementById("username").value,
        "password" : document.getElementById("password").value
        }
        await setCredentials(body)
    }

  return (
    <div className="d-flex justify-content-center align-items-center vh-100">
      <div className="card shadow" style={{ width: "400px", backgroundColor: "#0D355D" }}>
        <div className="card-body">
          <h3 className="card-title text-center mb-4" style={{color: "#FFFFFF"}}>Login</h3>
          <form onSubmit={handleOnSubmit}>
            <div className="mb-3">
              <input type="text" placeholder="Email Address" className="form-control" id="username" name="username" />
            </div>
            <div className="mb-3">
              <input type="password" placeholder="Password" className="form-control" id="password" name="password" />
            </div>
            <div className="d-grid gap-2">
              <button type="submit" className="btn" style={{backgroundColor: "#4FAADC"}}>Submit</button>
              <button type="reset" className="btn" style={{backgroundColor: "#CCCCCC"}}>Cancel</button>
              <hr style={{color:"#FFFFFF"}}/>
              <p className="text-center" style={{color:"#FFFFFF"}}>Don't have an account? <Link to="/registrationPage" className="link-info">Register here</Link></p>
            </div>
          </form>
        </div>
      </div>
    </div>
  )
}
