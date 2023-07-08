import React, { useContext } from 'react'
import { NotificationContext } from '../context/NotificationContext'
import { VisitorContext } from '../context/VisitorContext'

export default function Notification() {
    const {fetchedNotifications} = useContext(NotificationContext)
    const {setApprovedVisitor} = useContext(VisitorContext)
    const handleOnAccept = (visitor) => {
        const societyUser = localStorage.getItem("societyUser")
        console.log(societyUser)
        if(societyUser){
            setApprovedVisitor({
                ...visitor,
                isApproved: true
            })
        }
    }
    const handleOnDecline = (visitor) => {
        const societyUser = localStorage.getItem("societyUser")
        setApprovedVisitor({
            ...visitor,
            isApproved: false,
            approverName: societyUser.ownerName
        })
    }
    if(fetchedNotifications === undefined || fetchedNotifications === null || fetchedNotifications === []){
        return (
            <center>
                <h4>No notification</h4>
            </center>
        )
    }
  return (
    <div>
        {fetchedNotifications && <div>
            {fetchedNotifications.map((notification) => (
                <div key={notification.notificationId} className="card rounded mt-2 mb-2 ms-2 me-2">
            <div className="card-body shadow ">
                <p className="card-text">{notification.message}</p>
                <p className='card-text'>Date : {notification.timestamp[2]}-{notification.timestamp[1]}-{notification.timestamp[0]}</p>
                {notification.visitor.isApproved === null && <button type="button" className='btn btn-success me-2 btn-sm' onClick={() => handleOnAccept(notification.visitor)}>Accept</button>}
                {notification.visitor.isApproved === null && <button type="button" className="btn btn-danger btn-sm" onClick={() => handleOnDecline(notification.visitor)}>Decline</button>}
                {(notification.visitor.isApproved !== null && (notification.visitor.isApproved ? <button type="button" className='btn btn-success btn-sm' disabled>Accepted</button>
                 : 
                 <button type="button" className="btn btn-danger btn-sm" disabled>Declined</button>))}
            </div>
            </div>))}
        </div>}
    </div>
  )
}
