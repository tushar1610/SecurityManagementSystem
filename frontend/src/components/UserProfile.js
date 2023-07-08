import React, { useContext, useEffect, useState } from 'react'
import { PencilSquare, PersonBoundingBox } from 'react-bootstrap-icons'
import {SocietyUserContext} from "../context/SocietyUserContext"
import { useNavigate } from 'react-router-dom'

export default function UserProfile(){

    const navigate = useNavigate()
    const {isLoading, fetchedSocietyUser, setUpdatedSocietyUser, setIsUpdated} = useContext(SocietyUserContext)

    const [inputs, setInputs] = useState({
        username: '',
        age: '',
        contactNo: '',
        gender: '',
        owner: '',
        flatNo: ''
    })

    const [edit, setEdit] = useState(false)
    const [sectionId, setSectionId] = useState("userDetails") 

    const handleLoading = () => {
        return (
            <center>
                Loading...
            </center>
        )
    }

    useEffect(() => {
        if(isLoading){
            handleLoading()
        }
    }, [isLoading])

    const handleOnChange = (e, field) => {
        let changedValue = e.target.value
        setInputs({
            ...inputs,
            [field] : changedValue
        })
    }

    const handleOnClick = (id) => {
        setEdit(true)
        setSectionId(id)
    }

    const handleOnSubmit = async() => {
        console.log("entered update")
        setIsUpdated(true)
        let temp = {
            sUserId: fetchedSocietyUser.suserId,
            ownerName: inputs.owner !== '' ? inputs.owner : fetchedSocietyUser.ownerName,
            flatNo: inputs.flatNo !== '' ? inputs.flatNo : fetchedSocietyUser.flatNo,
            isAdmin: fetchedSocietyUser.isAdmin,
            user: {
              userId: fetchedSocietyUser.user.userId,
              userName: inputs.username !== '' ? inputs.username : fetchedSocietyUser.user.userName,
              email: fetchedSocietyUser.user.email,
              age: inputs.age !== '' ? inputs.age : fetchedSocietyUser.user.age,
              contactNo: inputs.contactNo !== '' ? inputs.contactNo : fetchedSocietyUser.user.contactNo,
              gender: inputs.gender !== '' ? inputs.gender : fetchedSocietyUser.user.gender,
              role: fetchedSocietyUser.user.role
            },
        };
        await setUpdatedSocietyUser(temp)
        console.log(temp)
        setEdit(false);
        setInputs({
            username: '',
            age: '',
            contactNo: '',
            gender: '',
            owner: '',
            flatNo: ''
        })
    }

  return (
    <div>
        {fetchedSocietyUser !== undefined && <div>
        <center>
            <PersonBoundingBox className='mt-3' size={150}/>
        </center>
        <div>
            <div style={{display: "flex", flexDirection: "column",  marginLeft:"20px", marginRight:"20px"}}>
                <div style={{ display: 'flex', alignItems: 'center', float:"left" }}>
                    <h3 style={{ flex: 1 , float: "right"}}>User Details</h3>
                    <PencilSquare onClick={() => handleOnClick("userDetails")}/>
                </div>
                <hr style={{ width: '100%' }} />
            </div>
        </div>
        <div style={{ marginLeft:"20px", marginRight:"20px"}}> 
            <div className="row">
                <div className="col-md-12 column">
                    <table className="table">
                        <tbody>        
                            <tr>
                                <td>Name</td>
                                <td>
                                <input type="text" name='username' value={inputs.username !== '' ? inputs.username : fetchedSocietyUser.user.userName} placeholder='Enter Full Name' className="form-control" onChange={(e) => handleOnChange(e, 'username')} disabled={edit && (sectionId === "userDetails") ? "" : "disabled"} required/>
                                </td>
                                <td>Email</td>
                                <td>
                                <input type="email" name='email' value={fetchedSocietyUser.user.email} placeholder='Enter Email Address' className="form-control" disabled required/>
                                </td>
                                <td>Contact Number</td>
                                <td>
                                <input type="number" name='contactNo' value={inputs.contactNo !== '' ? inputs.contactNo : fetchedSocietyUser.user.contactNo} placeholder='Enter Mobile Number' className="form-control" onChange={(e) => handleOnChange(e, 'contactNo')} disabled={edit && (sectionId === "userDetails") ? "" : "disabled"} required/>
                                </td>
                            </tr>
                            <tr>
                                <td>Age</td>
                                <td>
                                <input type="number" name='age' value={inputs.age !== '' ? inputs.age : fetchedSocietyUser.user.age} placeholder='Enter Age' className="form-control" onChange={(e) => handleOnChange(e, 'age')} disabled={edit && (sectionId === "userDetails") ? "" : "disabled"} required/>
                                </td>
                                <td>Gender</td>
                                <td>
                                <input type="text" name='gender' value={inputs.gender !== '' ? inputs.gender : fetchedSocietyUser.user.gender} placeholder='Enter Genderr' className="form-control" onChange={(e) => handleOnChange(e, 'gender')} disabled={edit && (sectionId === "userDetails") ? "" : "disabled"} required/>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <div>
            <div style={{display: "flex", flexDirection: "column",  marginLeft:"20px", marginRight:"20px"}}>
                <div style={{ display: 'flex', alignItems: 'center', float:"left" }}>
                    <h3 style={{ flex: 1 , float: "right"}}>Flat Details</h3>
                    <PencilSquare onClick={() => handleOnClick("flatDetails")}/>
                </div>
                <hr style={{ width: '100%' }} />
            </div>
        </div>
        <div style={{ marginLeft:"20px", marginRight:"20px"}}>
            <div className="row">
                <div className="col-md-12 column">
                    <table className="table">
                        <tbody>
                            <tr>
                                <td>Owner</td>
                                <td>
                                <input type="text" name='owner' value={inputs.owner !== '' ? inputs.owner : fetchedSocietyUser.ownerName} placeholder='Owner Name' className="form-control" onChange={(e) => handleOnChange(e, 'owner')} disabled={edit && (sectionId === "flatDetails") ? "" : "disabled"} required/>
                                </td>
                                <td>Flat Number</td>
                                <td>
                                <input type="text" name='flatNo' value={inputs.flatNo !== '' ? inputs.flatNo : fetchedSocietyUser.flatNo} placeholder='Flat Number' className="form-control" onChange={(e) => handleOnChange(e, 'flatNo')} disabled={edit && (sectionId === "flatDetails") ? "" : "disabled"} required/>
                                </td> 
                                <td>Admin</td>
                                <td>
                                <input type="text" name='role' value={fetchedSocietyUser.isAdmin ? "Yes" : "No"} placeholder='Admin' className="form-control" disabled required/>
                                </td>                                
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            </div>
            { edit && <div style={{ marginTop:"10px",marginRight:"20px"}} >
                <button type="submit" style={{float : 'right'}} className="btn btn-primary gap-2" onClick={() => handleOnSubmit()}>Apply Changes</button>
                <button type="reset" style={{float : 'right'}} className="btn btn-transparent gap-2">Cancel</button>
            </div> }
        </div>}
    </div>
  )
}
