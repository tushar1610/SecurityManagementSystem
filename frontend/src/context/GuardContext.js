import axios from "axios";
import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export const GuardContext = React.createContext()

const getAllGuardsUrl = "http://localhost:8080/guard/user/get/all"
const getGuardUserByUserIdUrl = "http://localhost:8080/guard/user/get/"
const addGuardUrl = "http://localhost:8080/guard/user/add"
const updateGuardUrl = "http://localhost:8080/guard/user/update/"

export const GuardContextProvider = ({children}) => {

    const navigate = useNavigate()

    const [allGuardsFetched, setAllGuardsFetched] = useState([])
    const [guardUser, setGuardUser] = useState()
    const [getButton, setGetButton] = useState(false)
    const [fetchedGuardUser, setFetchedGuardUser] = useState()
    const [updatedGuardUser, setUpdatedGuardUser] = useState()
    const [userId, setGuardUserId] = useState()
    const [isLoading, setIsLoading] = useState(true)
    const [isUpdated, setIsUpdated] = useState(false)

    const addGuard = async(url, body) => {
        await axios.post(url, body)
        .then((response) => {
            console.log(response.data)
            navigate("/login")
        })
        .catch((error) => {
            console.log(error.response)
        })
    }

    useEffect(() => {
        if(guardUser){
            console.log(guardUser)
            addGuard(addGuardUrl, guardUser)
        }
    }, [guardUser])

    const getGuardUserByUserId = async(url, userId) => {
        let jwtToken = localStorage.getItem("jwtToken")
        console.log(userId)
        await axios.get(url + `${userId}`, {
            headers: {
                Authorization : 'Bearer ' + jwtToken
            }
        }).then(async (response) => {
            localStorage.setItem("guardUser", JSON.stringify(response.data))
            await setFetchedGuardUser(response.data)
            console.log(response.data)
            console.log("guardUser")
        }).catch((error) => {
            console.log(error.response)
        })
        setIsLoading(false)
    }

    useEffect(() => {
        const fetchGuardUser = async () => {
          if (userId && userId !== 0 && !isUpdated) {
            try {
                setIsLoading(true)
                await getGuardUserByUserId(getGuardUserByUserIdUrl, userId);
                setGuardUserId(0);
            } catch (error) {
                console.log(error.response);
            }
          }
        };
      
        fetchGuardUser();
      }, [userId]);

    const getAllGuards = async(url) => {
        let jwtToken = localStorage.getItem("jwtToken")
        await axios.get(url, {
            headers: {
                Authorization : 'Bearer ' + jwtToken
            }
        })
        .then((response) => {
            console.log(response.data)
            let temp = response.data
            setAllGuardsFetched(temp)
        })
        .catch((error) => {
            console.log(error.response)
            if(error.response.status === 403){
                localStorage.clear()
                navigate("/login", {replace: true})
            }
        })
    }

    useEffect(() => {
        if(getButton){
            getAllGuards(getAllGuardsUrl)
            setGetButton(false)
        }
    }, [getButton])

    const updateGuard = async(url, userId, body) => {
        let jwtToken = localStorage.getItem("jwtToken")
        await axios.put(url + `${userId}`, body, {
            headers: {
                Authorization : 'Bearer ' + jwtToken
            }
        })
        .then(async (response) => {
            console.log(response.data)
            setFetchedGuardUser(response.data)
            console.log(fetchedGuardUser)
        })
        .catch((error) => {
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
            let tempId = localStorage.getItem("guardUserId")
            setGuardUserId(tempId)
          if (userId && userId !== 0 && isUpdated) {
            try {
                setIsLoading(true)
                await updateGuard(updateGuardUrl, userId, updatedGuardUser)
                setGuardUserId(0);
            } catch (error) {
                console.log(error.response);
            }
          }
        };
      
        updateData();
      }, [updatedGuardUser]);

    return(
        <GuardContext.Provider value={{setGuardUser, setGetButton, fetchedGuardUser, setGuardUserId, userId, isLoading, setUpdatedGuardUser, setIsUpdated}}>
            {children}
        </GuardContext.Provider>
    )

}