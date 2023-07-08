import React, {useContext, useState } from 'react'
import { UserLoginContext } from '../context/UserLoginContext';
import { VisitorContext } from '../context/VisitorContext';
import Guards from './Guards';
import Visitors from './Visitors';
import {Card} from 'react-bootstrap'

export default function UserPage() {

    
    return (
        <div>
            {/* <Report/> */}
            <Visitors title="societyUser"/>
            {/* <Guards title="societyUser"/> */}
        </div>
    )
}
