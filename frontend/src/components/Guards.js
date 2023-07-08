import React, { useContext, useState } from 'react'
import {GuardContext} from "../context/GuardContext"

export default function Guards({title}) {
    
//     const [buttonId, setButtonId] = useState('Today');

//     const {} = useContext(GuardContext)

//     const [currentIndex, setCurrentIndex] = useState(0)

//     const handleOnClick = (id) => {
//         setButtonId(id);
//         setDateToFetch(id)
//     }

//     const handlePrev = () => {
//         setCurrentIndex((prevIndex) => prevIndex - 5)
//     }

//     const handleNext = () => {
//         setCurrentIndex((prevIndex) => prevIndex + 5)
//     }

//     const displayCards = (data) => {
//         return (
//             <div className="card-group">
//                 {
//                     data.slice(currentIndex, currentIndex + 5).map((visitor, index) => (
//                     <div key={index} className='card m-2 border border-secondary-subtle border-5 rounded' style={{cursor: "pointer"}}>
//                         <div  className="card-body">
//                             <h5 className="card-title">Name : {visitor.visitorName}</h5>
//                             <div className='container'>
//                                 <p className="card-text">Intime : {visitor.inTime}</p>
//                             </div>
//                             <div className='container'>
//                                 <p className="card-text">OutTime : {visitor.outTime !== null ? visitor.outTime: '--:--'}</p>
//                             </div>
//                             <div className='container'>
//                             {title === "societyUser" ? <p className="card-text">Guard : {visitor.guardName}</p> : <p className="card-text">Flat No : {visitor.societyUser.flatNo}</p>}
//                             </div>
                    
//                         </div>
//                     </div>
//                     ))
//                 }
//             </div>
//         )
//     }

//   return (
//     <div>
//         <div className='container' style={{display : 'flex', justifyContent : 'space-between', alignItems : 'center'}}>
//                 <h3 className='titleVisitor'>Visitors</h3> 
//                 <div className="button-group">
//                     <button style={{float : 'right'}} className={buttonId === "See All" ? "btn btn-primary gap-2" : "btn transparent-button gap-2"} onClick={() => handleOnClick('See All')}>See All</button>
//                     <button style={{float : 'right'}} className={buttonId === "Yesterday" ? "btn btn-primary gap-2" : "btn transparent-button gap-2"} onClick={() => handleOnClick('Yesterday')}>Yesterday</button>
//                     <button style={{float : 'right'}} className={buttonId === "Today" ? "btn btn-primary gap-2" : "btn transparent-button gap-2"} onClick={() => handleOnClick('Today')}>Today</button>
//                 </div>
//             </div>

//             <div className="container mt-4">
//                 <div className="row">
//                     <div className="col">
//                         <button className="btn btn-primary" onClick={handlePrev} disabled={currentIndex <= 0}>{"<"}</button>
//                     </div>
//                     <div className="col text-right">
//                         <button className="btn btn-primary" onClick={handleNext} disabled={buttonId !== "See All" ? currentIndex >= visitorsFetchedByDate.length - 5 : currentIndex >= allVistorsFetched.length - 5}>Next</button>
//                     </div>
//                 </div>
//                 <div className="row mt-4">
//                         {buttonId !== 'See All' ? displayCards(visitorsFetchedByDate) : displayCards(allVistorsFetched)}
//                 </div>
//             </div>
//     </div>
//   )

}
