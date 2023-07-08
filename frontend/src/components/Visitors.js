import React, { useContext, useState } from "react";
import { VisitorContext } from "../context/VisitorContext";
import {AiOutlineCheck, AiOutlineExport, AiOutlineImport} from "react-icons/ai"
import {BsDoorOpen, BsPersonCheck} from "react-icons/bs"
import {HiLogout} from "react-icons/hi"

export default function Visitors({ title }) {
  const [buttonId, setButtonId] = useState("Today");

  const {
    setDateToFetch,
    visitorsFetchedByDate,
    allVistorsFetched,
    setOutTimeVisitor,
  } = useContext(VisitorContext);

  const handleOnClick = (id) => {
    setButtonId(id);
    setDateToFetch(id);
  };

  const handleExit = (visitor) => {
    setOutTimeVisitor(visitor);
  };

  return (
    <div className="container mt-3 d-flex justify-content-center">
      <div className="card">
        <div className="card-body" style={{width:"100vh"}}>
          <h3 className="card-title text-center mb-2 mt-2" >Visitors</h3>
        <div className="nav nav-tabs">
          <button
            className={`nav-link ${buttonId === "Today" ? "active" : ""}`}
            onClick={() => handleOnClick("Today")}
          >
            Today
          </button>
          <button
            className={`nav-link ${buttonId === "Yesterday" ? "active" : ""}`}
            onClick={() => handleOnClick("Yesterday")}
          >
            Yesterday
          </button>
          <button
            className={`nav-link ${buttonId === "See All" ? "active" : ""}`}
            onClick={() => handleOnClick("See All")}
          >
            See All
          </button>
        </div>
      </div>

      <div className="container">
        <div className="row">
          <div className="card-columns">
            {buttonId !== "See All"
              ? visitorsFetchedByDate.map((visitor, index) => (
                  <div
                    key={index}
                    className="card shadow rounded mb-2"
                    style={{ cursor: "pointer" }}
                  >
                    <div className="card-body">
                      <h5 className="card-title">
                        Visitor Name: {visitor.visitorName}
                      </h5>
                      <div className="container">
                        <div className="row align-items-center">
                          <div className="col">
                            <p className="card-text">
                            <AiOutlineImport size={20}/> {" "}
                              Intime: {visitor.inTime[0]}:{visitor.inTime[1]}:
                              {visitor.inTime[2]}
                            </p>
                          </div>
                          <div className="col">
                            <p className="card-text">
                              <AiOutlineExport size={20}/> {" "}
                              OutTime:{" "}
                              {visitor.outTime !== null
                                ? `${visitor.outTime[0]}:${visitor.outTime[1]}:${visitor.outTime[2]}`
                                : "--:--"}
                            </p>
                          </div>
                        </div>
                      </div>
                      <div className="container">
                        <div className="row align-items-center">
                          <div className="col">
                            {title === "societyUser" ? (                                                                                                                    
                              <p className="card-text">
                                <BsPersonCheck size={20}/> {" "}
                                Guard: {visitor.guardName}
                              </p>
                            ) : (
                              <p className="card-text">
                                <BsDoorOpen size={20}/> {" "}
                                Flat No: {visitor.societyUser.flatNo}
                              </p>
                            )}
                          </div>
                          <div className="col">
                            {visitor.isApproved !== null ? (
                              <p className="card-text">
                                <AiOutlineCheck size={20}/> {" "}
                                {visitor.isApproved
                                  ? "Approved: Yes"
                                  : "Approved: No"}
                              </p>
                            ) : (
                              <p className="card-text">
                                <AiOutlineCheck size={20}/> {" "}
                                Approval Status: Pending
                              </p>
                            )}
                          </div>
                        </div>
                      </div>
                    </div>
                    {title === "guardUser" && (
                        <button
                          className="btn ms-3 me-3 mb-2"
                          onClick={() => handleExit(visitor)}
                          disabled={visitor.outTime === null ? "" : "disabled"}
                          style={{backgroundColor:"#162135", color:"#FFFFFF", borderRadius:"100px"}}
                        >
                          <HiLogout size={20} color="#FFFFFF"/>
                          Exit
                        </button>
                      )}
                  </div>
                ))
              : allVistorsFetched.map((visitor, index) => (
                  <div
                    key={index}
                    className="card shadow rounded mb-2"
                    style={{ cursor: "pointer" }}
                  >
                    <div className="card-body">
                      <h5 className="card-title">
                        Visitor Name: {visitor.visitorName}
                      </h5>
                      <div className="container">
                        <div className="row align-items-center mb-2">
                          <div className="col">
                            <p className="card-text">
                              <AiOutlineImport size={20}/> {" "}
                              Intime: {visitor.inTime[0]}:{visitor.inTime[1]}:
                              {visitor.inTime[2]}
                            </p>
                          </div>
                          <div className="col">
                            <p className="card-text">
                            <AiOutlineExport size={20}/> {" "}
                              OutTime:{" "}
                              {visitor.outTime !== null
                                ? `${visitor.outTime[0]}:${visitor.outTime[1]}:${visitor.outTime[2]}`
                                : "--:--"}
                            </p>
                          </div>
                        </div>
                      </div>
                      <div className="container">
                        <div className="row align-items-center mb-2">
                          <div className="col">
                            {title === "societyUser" ? (
                              <p className="card-text">
                                <BsPersonCheck size={20}/> {" "}
                                Guard: {visitor.guardName}
                              </p>
                            ) : (
                              <p className="card-text">
                                <BsDoorOpen size={20}/> {" "}
                                Flat No: {visitor.societyUser.flatNo}
                              </p>
                            )}
                          </div>
                          <div className="col">
                            {visitor.isApproved !== null ? (
                              <p className="card-text">
                                <AiOutlineCheck size={20}/> {" "}
                                {visitor.isApproved
                                  ? "Approved: Yes"
                                  : "Approved: No"}
                              </p>
                            ) : (
                              <p className="card-text">
                                <AiOutlineCheck size={20}/> {" "}
                                Approval Status: Pending
                              </p>
                            )}
                          </div>
                        </div>
                      </div>
                    </div>

                    {title === "guardUser" && (
                      <button
                        className="btn ms-3 me-3 mb-2"
                        onClick={() => handleExit(visitor)}
                        disabled={visitor.outTime === null ? "" : "disabled"}
                        style={{backgroundColor:"#162135", color:"#FFFFFF", borderRadius:"100px"}}
                      >
                        <HiLogout size={20} color="#FFFFFF"/>
                        Exit
                      </button>
                    )}
                  </div>
                ))}
          </div>
        </div>
        </div>
      </div>
    </div>
  );

  // const handlePrev = () => {
  //     setCurrentIndex((prevIndex) => prevIndex - 5)
  // }

  // const handleNext = () => {
  //     setCurrentIndex((prevIndex) => prevIndex + 5)
  // }

  // const displayCards = (data) => {
  //     return (
  //         <div className="card-group">
  //             {
  //                 data.slice(currentIndex, currentIndex + 5).map((visitor, index) => (
  //                 <div key={index} className='card m-2 border border-secondary-subtle border-5 rounded' style={{cursor: "pointer"}}>
  //                     <div  className="card-body">
  //                         <h5 className="card-title">Name : {visitor.visitorName}</h5>
  //                         <div className='container'>
  //                             <p className="card-text">Intime : {visitor.inTime[0]}-{visitor.inTime[1]}-{visitor.inTime[2]}</p>
  //                         </div>
  //                         <div className='container'>
  //                             <p className="card-text">OutTime : {visitor.outTime !== null ? <>{visitor.outTime[0]}-{visitor.outTime[1]}-{visitor.outTime[2]}</>: '--:--'}</p>
  //                         </div>
  //                         <div className='container'>
  //                         {title === "societyUser" ? <p className="card-text">Guard : {visitor.guardName}</p> : <p className="card-text">Flat No : {visitor.societyUser.flatNo}</p>}
  //                         </div>
  //                         <div className='container'>
  //                             {visitor.isApproved !== null ? (visitor.isApproved ? <p>Approved : Yes</p> : <p>Approved : No</p>) : <p>Approval Status : Pending</p>}
  //                         </div>
  //                         {title === "guardUser" && <button className='btn btn-primary' onClick={() => handleExit(visitor)} disabled={visitor.outTime === null ? "" : "disabled" }>Exit</button>}
  //                     </div>
  //                 </div>
  //                 ))
  //             }
  //         </div>
  //     )
  // }

  // return (
  //     <div>
  //       <div className="container">
  //         <h3 className="titleVisitor">Visitors</h3>
  //         <div className="nav nav-tabs">
  //           <button
  //             className={`nav-link ${buttonId === "Today" ? "active" : ""}`}
  //             onClick={() => handleOnClick("Today")}
  //           >
  //             Today
  //           </button>
  //           <button
  //             className={`nav-link ${buttonId === "Yesterday" ? "active" : ""}`}
  //             onClick={() => handleOnClick("Yesterday")}
  //           >
  //             Yesterday
  //           </button>
  //           <button
  //             className={`nav-link ${buttonId === "See All" ? "active" : ""}`}
  //             onClick={() => handleOnClick("See All")}
  //           >
  //             See All
  //           </button>
  //         </div>
  //       </div>

  //       <div className="container mt-4">
  //         <div className="row">
  //           <div className="col">
  //             <button
  //               className="btn btn-primary"
  //               onClick={handlePrev}
  //               disabled={currentIndex <= 0}
  //             >
  //               {"<"}
  //             </button>
  //           </div>
  //           <div className="col text-right">
  //             <button
  //               className="btn btn-primary"
  //               onClick={handleNext}
  //               disabled={
  //                 buttonId !== "See All"
  //                   ? currentIndex >= visitorsFetchedByDate.length - 5
  //                   : currentIndex >= visitorsFetchedByDate.length - 5
  //               }
  //             >
  //               Next
  //             </button>
  //           </div>
  //         </div>
  //         <div className="row mt-4">
  //           {buttonId !== "See All"
  //             ? displayCards(visitorsFetchedByDate)
  //             : displayCards(allVistorsFetched)}
  //         </div>
  //       </div>
  //     </div>
  //   );

  //   return (
  //     <div>
  //         <div className='container' style={{display : 'flex', justifyContent : 'space-between', alignItems : 'center'}}>
  //             <h3 className='titleVisitor'>Visitors</h3>
  //             <div className="button-group">
  //                 <button style={{float : 'right'}} className={buttonId === "See All" ? "btn btn-primary gap-2" : "btn transparent-button gap-2"} onClick={() => handleOnClick('See All')}>See All</button>
  //                 <button style={{float : 'right'}} className={buttonId === "Yesterday" ? "btn btn-primary gap-2" : "btn transparent-button gap-2"} onClick={() => handleOnClick('Yesterday')}>Yesterday</button>
  //                 <button style={{float : 'right'}} className={buttonId === "Today" ? "btn btn-primary gap-2" : "btn transparent-button gap-2"} onClick={() => handleOnClick('Today')}>Today</button>
  //             </div>
  //         </div>

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
