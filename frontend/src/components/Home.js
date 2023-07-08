import React, { useState } from 'react'
import '../App.css'
import Login from './Login';
import Registration from './Registration';
import { Outlet, Link } from 'react-router-dom';

export default function () {
  const [buttonClicked, setButtonClicked] = useState('');

  return (
    <div>
        <div className='button-group gap-2'>
            <Link to='/login'><button type="button" className="btn btn-primary" id='loginButton' onClick={() => {
              setButtonClicked('loginButton')
              console.log(buttonClicked)
            }}>Login</button></Link>
            <Link to='/registrationPage'><button type="button" className="btn btn-primary" id='registrationButton' onClick={() => {
              setButtonClicked('registrationButton')
              console.log(buttonClicked)
            }}>Register</button></Link>
        </div>
        <Outlet/>
    </div>
  )
}
