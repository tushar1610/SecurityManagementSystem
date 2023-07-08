import React, { useEffect, useState } from "react"
import axios from 'axios'
import { useNavigate} from 'react-router-dom'
import {encode as base64_encode} from 'base-64'

export const UserLoginContext = React.createContext()

const loginUserUrl = "http://localhost:8080/user/login"
const userDetailsUrl = "http://localhost:8080/user/get/details/"

export const UserLoginProvider = ({children}) => {
    const [credentials, setCredentials] = useState()
    const [emailAddress, setEmailAddress] = useState()
    const [user, setUser] = useState()


    const navigate = useNavigate()

    const loginUser = async(url, body) => {
        await axios.post(url, body)
           .then((response) => {
            console.log(response)
            if(response){
                console.log(response)
                if(response.data.role !== "ROLE_GUARD_USER"){
                    localStorage.setItem("email", response.data.username)
                    localStorage.setItem("role", response.data.role)
                    localStorage.setItem("jwtToken", response.data.jwtToken)
                    let tempEmail = localStorage.getItem("email")
                    setEmailAddress(tempEmail)
                } else {
                    localStorage.setItem("guardEmail", response.data.username)
                    localStorage.setItem("guardRole", response.data.role)
                    localStorage.setItem("jwtToken", response.data.jwtToken)
                    let tempEmail = localStorage.getItem("guardEmail")
                    setEmailAddress(tempEmail)
                }
            }
        })
           .catch((error) => {console.log(error)})
    }

    useEffect(() => {
        if(credentials) {
            console.log("loginMethod called")
            loginUser(loginUserUrl, credentials)
        }
    }, [credentials])

    const fetchUserDetails = async(url, emailId) => {
        // let stringToEncode = credentials.username + ":" + credentials.password;
        // console.log(stringToEncode)
        // const authHeader = base64_encode(stringToEncode)
        // console.log(authHeader)
        let jwtToken = localStorage.getItem("jwtToken")
        await axios.get(url + `${emailId}`, {
            headers: {
                Authorization : 'Bearer ' + jwtToken
            }
        }).then((response) => {
            console.log(response)
            if(response){
                let tempUser = {
                    userId: response.data.userId,
                    userName: response.data.userName,
                    age: response.data.age,
                    contactNo: response.data.contactNo,
                    email: response.data.email,
                    gender: response.data.gender,
                    role: response.data.role
                }
                setUser(tempUser)
                if(response.data.role !== "ROLE_GUARD_USER"){
                    localStorage.setItem("userId", tempUser.userId)
                    navigate("/userPage")
                } else {
                    localStorage.setItem("guardUserId", tempUser.userId)
                    navigate("/guardPage")
                }
            }
        }).catch((error) => {
            if(error.response){
                navigate("/login")
            } else {
                console.log(error.response)
            }
        })
    }

    useEffect(() => {
        console.log(emailAddress)
        if(emailAddress !== undefined && emailAddress !== ""){
            console.log(emailAddress)
            fetchUserDetails(userDetailsUrl, emailAddress)
        }
    }, [emailAddress])

    return (
        <UserLoginContext.Provider value={{setCredentials, user, setEmailAddress, credentials}}>
            {children}
        </UserLoginContext.Provider>
    )
}