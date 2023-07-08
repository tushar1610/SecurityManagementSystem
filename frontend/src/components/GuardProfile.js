import React, {useContext, useEffect, useState} from 'react'
import { GuardContext } from '../context/GuardContext'
import { PencilSquare, PersonBoundingBox } from 'react-bootstrap-icons'

export default function GuardProfile() {
  const {isLoading, fetchedGuardUser, setUpdatedGuardUser, setIsUpdated} = useContext(GuardContext)

    const [inputs, setInputs] = useState({
        username: '',
        age: '',
        contactNo: '',
        gender: '',
        shiftTime: '',
        address: ''
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
        await setIsUpdated(true)
        let temp = {
            gUserId: fetchedGuardUser.guserId,
            shiftTime: inputs.shiftTime !== '' ? inputs.shiftTime : fetchedGuardUser.shiftTime,
            address: inputs.address !== '' ? inputs.address : fetchedGuardUser.address,
            user: {
              userId: fetchedGuardUser.user.userId,
              userName: inputs.username !== '' ? inputs.username : fetchedGuardUser.user.userName,
              email: fetchedGuardUser.user.email,
              age: inputs.age !== '' ? inputs.age : fetchedGuardUser.user.age,
              contactNo: inputs.contactNo !== '' ? inputs.contactNo : fetchedGuardUser.user.contactNo,
              gender: inputs.gender !== '' ? inputs.gender : fetchedGuardUser.user.gender,
              role: fetchedGuardUser.user.role
            },
        };
        await setUpdatedGuardUser(temp)
        console.log(temp)
        setEdit(false);
        setInputs({
            username: '',
            age: '',
            contactNo: '',
            gender: '',
            shiftTime: '',
            address: ''
        })
    }

  return (
    <div>
        {fetchedGuardUser !== undefined && <div>
        <center>
            <PersonBoundingBox className="mt-3" size={150}/>
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
                                <input type="text" name='username' value={inputs.username !== '' ? inputs.username : fetchedGuardUser.user.userName} placeholder='Enter Full Name' className="form-control" onChange={(e) => handleOnChange(e, 'username')} disabled={edit && (sectionId === "userDetails") ? "" : "disabled"} required/>
                                </td>
                                <td>Email</td>
                                <td>
                                <input type="email" name='email' value={fetchedGuardUser.user.email} placeholder='Enter Email Address' className="form-control" disabled required/>
                                </td>
                                <td>Contact Number</td>
                                <td>
                                <input type="number" name='contactNo' value={inputs.contactNo !== '' ? inputs.contactNo : fetchedGuardUser.user.contactNo} placeholder='Enter Mobile Number' className="form-control" onChange={(e) => handleOnChange(e, 'contactNo')} disabled={edit && (sectionId === "userDetails") ? "" : "disabled"} required/>
                                </td>
                            </tr>
                            <tr>
                                <td>Age</td>
                                <td>
                                <input type="number" name='age' value={inputs.age !== '' ? inputs.age : fetchedGuardUser.user.age} placeholder='Enter Age' className="form-control" onChange={(e) => handleOnChange(e, 'age')} disabled={edit && (sectionId === "userDetails") ? "" : "disabled"} required/>
                                </td>
                                <td>Gender</td>
                                <td>
                                <input type="text" name='gender' value={inputs.gender !== '' ? inputs.gender : fetchedGuardUser.user.gender} placeholder='Enter Genderr' className="form-control" onChange={(e) => handleOnChange(e, 'gender')} disabled={edit && (sectionId === "userDetails") ? "" : "disabled"} required/>
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
                    <h3 style={{ flex: 1 , float: "right"}}>Other Details</h3>
                    <PencilSquare onClick={() => handleOnClick("otherDetails")}/>
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
                                <td>Shift Time</td>
                                <td>
                                <input type="text" name='shiftTime' value={inputs.shiftTime !== '' ? inputs.shiftTime : fetchedGuardUser.shiftTime} placeholder='Shift Time' className="form-control" onChange={(e) => handleOnChange(e, 'shiftTime')} disabled={edit && (sectionId === "otherDetails") ? "" : "disabled"} required/>
                                </td>
                                <td>Address</td>
                                <td>
                                <input type="text" name='address' value={inputs.address !== '' ? inputs.address : fetchedGuardUser.address} placeholder='address' className="form-control" onChange={(e) => handleOnChange(e, 'address')} disabled={edit && (sectionId === "otherDetails") ? "" : "disabled"} required/>
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
