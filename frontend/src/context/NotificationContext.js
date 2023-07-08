import axios from "axios";
import React, { useEffect, useState } from "react";

export const NotificationContext = React.createContext()

const getAllNotificationUrl = "http://localhost:8080/notification/get/all"

export const NotificationContextProvider = ({children}) => {
    const [fetchedNotifications, setFetchedNotifications] = useState([])
    const [notificationButtonClicked, setNotificationButtonClicked] = useState(false)

    const getNotifications = async(url, jwtToken, flatNo) => {
        await axios.get(url, {
            headers: {
                Authorization : "Bearer " + jwtToken
            }
        }).then((response) => {
            console.log(response.data)
            let data = response.data
            let tempNotification = []
            data.forEach(notification => {
                tempNotification.push(notification)
            });
            setFetchedNotifications(tempNotification)
            console.log(fetchedNotifications)
            setNotificationButtonClicked(false)
        }).catch((error) => {
            console.log(error)
            setNotificationButtonClicked(false)
        })
    }

    useEffect(() => {
        const societyUser = localStorage.getItem("societyUser")
        if(societyUser){
            let jwtToken = localStorage.getItem("jwtToken")
            if(jwtToken && notificationButtonClicked){
                const flat = societyUser.flatNo
                getNotifications(getAllNotificationUrl, jwtToken, flat)
            }
        }
    }, [notificationButtonClicked])

    return(
        <NotificationContext.Provider value={{fetchedNotifications, setNotificationButtonClicked}}>
            {children}
        </NotificationContext.Provider>
    )
}