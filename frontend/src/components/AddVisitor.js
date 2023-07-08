import React, { useContext } from 'react'
import { VisitorContext } from '../context/VisitorContext';
import { useNavigate } from 'react-router-dom';

export default function AddVisitor() {

  const navigate = useNavigate()

  const { setAddVisitor } = useContext(VisitorContext)
  const handleOnSubmit = (e) => {
    e.preventDefault()
    navigate("/guardPage")
  }
  return (
    <div className="container mt-3 d-flex justify-content-center">
      <div className="card shadow" style={{ backgroundColor: "#0D355D" }}>
        <div className="card-body">
          <h3 className="card-title text-center mb-4 mt-2" style={{ color: "#FFFFFF" }}>Add Visitor</h3>
          <form style={{ width: "400px" }}>
            <div className="form-outline mb-4">
              <input type="text" id="visitorName" className="form-control" placeholder='Visitor Name' />
            </div>

            <div className="row">
              <div className="col-3">
                <div className="mb-3">
                  <input type="number" placeholder="Age" className="form-control" id="age" />
                </div>
              </div>
              <div className="col-6">
                <div className="mb-3">
                  <input type="number" placeholder="Contact Number" className="form-control" id="contactNo" />
                </div>
              </div>
              <div className='col-3'>
                <select className="select form-control" id='selectGender' placeholdor='Gender'>
                  <option value="" disabled selected>Gender</option>
                  <option value="1">Male</option>
                  <option value="2">Female</option>
                </select>
              </div>
            </div>

            <div className="form-outline mb-4">
              <input type="text" id="guardName" className="form-control" placeholder='Guard Name' />
            </div>

            <div className="form-outline mb-4">
              <input type="text" id="flatNo" className="form-control" placeholder='Flat Number' />
            </div>

            <div className="form-outline mb-4">
              <textarea className="form-control" id="purpose" rows="3" placeholder='Purpose' />
            </div>
            <div className="d-grid gap-2">
              <button type="submit" className="btn mb-2" style={{ backgroundColor: "#4FAADC" }} onClick={async (e) => {
                await setAddVisitor({
                  visitorName: document.getElementById("visitorName").value,
                  age: document.getElementById("age").value,
                  contactNo: document.getElementById("contactNo").value,
                  gender: document.getElementById('selectGender').options[document.getElementById('selectGender').selectedIndex].text,
                  purpose: document.getElementById('purpose').value,
                  guardName: document.getElementById('guardName').value,
                  societyUser: {
                    flatNo: document.getElementById('flatNo').value
                  }
                })
                handleOnSubmit(e)
              }}>Send</button>
              <button type="reset" className="btn" style={{ backgroundColor: "#CCCCCC" }}>Cancel</button>
            </div>
          </form>
        </div>
      </div>
    </div>
  )
}
