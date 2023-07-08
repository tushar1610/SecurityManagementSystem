import React, { useContext, useState } from "react";
import { useNavigate } from "react-router-dom";
import { SocietyUserContext } from "../context/SocietyUserContext";
import { Outlet, Link } from "react-router-dom";
import { GuardContext } from "../context/GuardContext";

export default function Registration() {
  const [buttonId, setButtonId] = useState("submit");

  const [userRole, setUserRole] = useState("ROLE_SOCIETY_USER");
  const [userIsAdmin, setUserIsAdmin] = useState(false);

  const { setSocietyUser } = useContext(SocietyUserContext);

  const { setGuardUser } = useContext(GuardContext);

  const handleOnSubmit = (e, id) => {
    e.preventDefault();
    setButtonId(id);
  };

  const handlePageChange = (e) => {
    return (
      <div>
        <Link to="/login" />
        <Outlet />
      </div>
    );
  };

  const handleRoleClick = (e) => {
    console.log(e.target.id);
    if (e.target.id === "adminUser") {
      setUserIsAdmin(true);
      setUserRole("ROLE_ADMIN");
    } else {
      if (e.target.id === "societyUser") {
        setUserIsAdmin(false);
        setUserRole("ROLE_SOCIETY_USER");
      } else {
        setUserIsAdmin(false);
        setUserRole("ROLE_GUARD_USER");
      }
    }
  };

  return (
    <div className="container mt-5 d-flex justify-content-center">
      <div className="card shadow" style={{ backgroundColor: "#0D355D" }}>
        <div className="card-body">
          <h3 className="card-title text-center mb-4 mt-3" style={{ color: "#FFFFFF" }}>Register</h3>
          <form style={{ width: "400px" }}>
            <div>
              <div className="mb-3">
                <input
                  type="text"
                  placeholder="Username"
                  className="form-control"
                  id="inputUserName"
                />
              </div>
              <div className="mb-3">
                <input
                  type="email"
                  placeholder="Email Address"
                  className="form-control"
                  id="inputEmail"
                />
              </div>
              <div className="row">
                <div className="col-6">
                  <div className="mb-3">
                    <input
                      type="number"
                      placeholder="Age"
                      className="form-control"
                      id="inputAge"
                    />
                  </div>
                </div>
                <div className="col-6">
                  <div className="mb-3">
                    <input
                      type="number"
                      placeholder="Contact Number"
                      className="form-control"
                      id="inputContactNo"
                    />
                  </div>
                </div>
              </div>

              <div className="mb-3">
                <input
                  type="text"
                  placeholder="Gender"
                  className="form-control"
                  id="inputGender"
                />
              </div>
              <div className="mb-3">
                <input
                  type="password"
                  placeholder="Password"
                  className="form-control"
                  id="inputPassword"
                />
              </div>
              {userRole !== "ROLE_GUARD_USER" ? (
                <div className="mb-3">
                  <input
                    type="text"
                    placeholder="Flat Number"
                    className="form-control"
                    id="inputFlatNo"
                  />
                </div>
              ) : (
                <div className="mb-3">
                  <input
                    type="text"
                    placeholder="Shift Time"
                    className="form-control"
                    id="inputShiftTime"
                  />
                </div>
              )}
              {userRole !== "ROLE_GUARD_USER" ? (
                <div className="mb-3">
                  <input
                    type="text"
                    placeholder="Owner Name"
                    className="form-control"
                    id="inputOwnerName"
                  />
                </div>
              ) : (
                <div className="mb-3">
                  <input
                    type="text"
                    placeholder="Address"
                    className="form-control"
                    id="inputAddress"
                  />
                </div>
              )}
              <div className="dropdown">
                <button
                  style={{ float: "left", color: "#FFFFFF" }}
                  className="btn dropdown-toggle"
                  type="button"
                  data-bs-toggle="dropdown"
                  aria-expanded="false"
                >
                  {`${userRole}`}
                </button>
                <ul className="dropdown-menu">
                  <li
                    className="dropdown-item"
                    id="adminUser"
                    onClick={(e) => handleRoleClick(e)}
                  >
                    Admin
                  </li>
                  <li
                    className="dropdown-item"
                    id="societyUser"
                    onClick={(e) => handleRoleClick(e)}
                  >
                    Society Member
                  </li>
                  <li
                    className="dropdown-item"
                    id="guardUser"
                    onClick={(e) => handleRoleClick(e)}
                  >
                    Guard
                  </li>
                </ul>
              </div>
              <div className="container">
                <button
                  type="submit"
                  style={{ float: "right", backgroundColor: "#4FAADC" }}
                  className={
                    buttonId === "submit"
                      ? "btn ms-2"
                      : "btn transparent-button gap-2"
                  }
                  onClick={(e) => {
                    handleOnSubmit(e, "submit");
                    if (userRole !== "ROLE_GUARD_USER") {
                      setSocietyUser({
                        flatNo: document.getElementById("inputFlatNo").value,
                        isAdmin: userIsAdmin,
                        ownerName: document.getElementById("inputOwnerName").value,
                        user: {
                          userName: document.getElementById("inputUserName").value,
                          age: document.getElementById("inputAge").value,
                          contactNo: document.getElementById("inputContactNo").value,
                          gender: document.getElementById("inputGender").value,
                          email: document.getElementById("inputEmail").value,
                          password: document.getElementById("inputPassword").value,
                          role: userRole,
                        },
                      });
                    } else {
                      setGuardUser({
                        address: document.getElementById("inputAddress").value,
                        shiftTime: document.getElementById("inputShiftTime").value,
                        user: {
                          userName: document.getElementById("inputUserName").value,
                          age: document.getElementById("inputAge").value,
                          contactNo: document.getElementById("inputContactNo").value,
                          gender: document.getElementById("inputGender").value,
                          email: document.getElementById("inputEmail").value,
                          password: document.getElementById("inputPassword").value,
                          role: userRole,
                        },
                      });
                    }
                    handlePageChange(e);
                  }}
                >
                  Submit
                </button>
                <button
                  type="reset"
                  style={{ float: "right", color: "#FFFFFF" }}
                  className={
                    buttonId === "cancel"
                      ? "btn mr-2 gap-2"
                      : "btn transparent-button gap-2"
                  }
                >
                  Cancel
                </button>
              </div>
            </div>
          </form>
        </div>
      </div>
    </div>
  );
}
