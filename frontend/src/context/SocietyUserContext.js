import React, { useEffect, useState } from "react"
import axios from 'axios'
import { useNavigate } from "react-router-dom"
import { useContext } from "react"
import { UserLoginContext } from "./UserLoginContext"
export const SocietyUserContext = React.createContext()

const addSocietyUserUrl = "http://localhost:8080/society/user/add"
const getSocietyUserByIdUrl = "http://localhost:8080/society/user/get/"
const updateSocietyUserUrl = "http://localhost:8080/society/user/update/"

export const SocietyUserProvider = ({children}) => {

    const [societyUser, setSocietyUser] = useState()
    const [userId, setUserId] = useState()
    const [fetchedSocietyUser, setFetchedSocietyUser] = useState()
    const [updatedSocietyUser, setUpdatedSocietyUser] = useState()
    const [isLoading, setIsLoading] = useState(true)
    const [isUpdated, setIsUpdated] = useState(false)

    const navigate = useNavigate();
    const postSocietyUser = async(url, body) => {
        console.log(body)
        await axios.post(url, body).then((response) => {
            console.log(response)
            navigate("/login")
        }).catch((error)  => {
            console.log(error.response)
        })
    }

    useEffect(() => {
        if(societyUser) {
            postSocietyUser(addSocietyUserUrl, societyUser)
        }
    }, [societyUser])

    const getSocietyUserByUserId = async(url, userId) => {
        console.log(userId)
        let jwtToken = localStorage.getItem("jwtToken")
        await axios.get(url + `${userId}`, {
            headers: {
                Authorization : 'Bearer ' + jwtToken
            }
        }).then((response) => {
            localStorage.setItem("societyUser", JSON.stringify(response.data))
            setFetchedSocietyUser(response.data)
            console.log(response.data)
            console.log("societyUser")
        }).catch((error) => {
            console.log(error.response)
        })
        setIsLoading(false)
    }

    useEffect(() => {
        const fetchSocietyUser = async () => {
          if (userId && userId !== 0 && !isUpdated) {
            try {
                setIsLoading(true)
                await getSocietyUserByUserId(getSocietyUserByIdUrl, userId);
                setUserId(0);
            } catch (error) {
                console.log(error.response);
            }
          }
        };
      
        fetchSocietyUser();
      }, [userId]);

    const updateSocietyUser = async(url, userId, body) => {
        console.log(userId)
        let jwtToken = localStorage.getItem("jwtToken")
        await axios.put(url + `${userId}`, body, {
            headers: {
                Authorization : 'Bearer ' + jwtToken
            }
        }).then((response) => {
            localStorage.setItem("societyUser", JSON.stringify(response.data))
            setFetchedSocietyUser(response.data)
            console.log(response.data)
            console.log("societyUser")
        }).catch((error) => {
            console.log(error.response)
            if(error.response.status === 403){
                localStorage.clear()
                navigate("/login", {replace: true})
            }
        })
        setIsUpdated(false)
        setIsLoading(false)
    }

    useEffect(() => {
        const updateData = async () => {
            let tempId = localStorage.getItem("userId")
            setUserId(tempId)
          if (userId && userId !== 0 && isUpdated) {
            try {
                setIsLoading(true)
                await updateSocietyUser(updateSocietyUserUrl, userId, updatedSocietyUser)
                setUserId(0);
            } catch (error) {
                console.log(error.response);
            }
          }
        };
      
        updateData();
      }, [updatedSocietyUser]);

    return (
        <SocietyUserContext.Provider value={{setSocietyUser, setUserId, fetchedSocietyUser, userId, isLoading, setUpdatedSocietyUser, setIsUpdated}}>
            {children}
        </SocietyUserContext.Provider>
    )

}