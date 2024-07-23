import React, { useEffect } from "react";
import { useState } from "react";
import axios from "axios";
import { useLocation } from "react-router-dom";

const Profile = (props) => {
  const data = {
    customerId: props.data.customerId,
    firstname: props.data.firstname,
    lastname: props.data.lastname,
    mobileNum: props.data.mobileNum,
    email: props.data.email,
  };

  const [user, setUser] = useState(data);
  const [message, setMessage] = useState("");
  const [errors, setErrors] = useState([]);

  const token = localStorage.getItem("token");

  // useEffect(() => {
  //   const uname = props.data.firstname;

  //   console.log({ customerId });

  //   axios
  //     .get(`http://localhost:8080/api/${uname}`)
  //     .then((res) => {
  //       setUser(res.data);
  //     })
  //     .catch((error) => {
  //       console.log("ERROR From API : " + error.response.data);
  //       //setErrors(error.response.data);
  //     });
  // });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setUser({
      ...user,
      [name]: value,
    });
  };

  const handleCancel = () => {};

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage("");
    setErrors("");
    console.log(user);
    console.log(token);
    axios
      .put("http://localhost:8080/api/update", user, {
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
      })
      .then((res) => {
        console.log(res.data);
        setMessage("User details Updated successfully");
        user.email = res.data.email;
        user.mobileNum = res.data.mobileNum;
        props.data.email = res.data.email;
        props.data.mobileNum = res.data.mobileNum;
        setUser(user);
        console.log(user);
      })
      .catch((error) => {
        console.log(error);
        setErrors(error);
      });
  };

  return (
    <>
      <div className="title" style={{ marginTop: "2rem" }}>
        <h2>Profile Details</h2>
        <div className="title-underline"></div>
      </div>
      {message && (
        <p style={{ color: "green", textAlign: "center" }}>{message}</p>
      )}
      <ul>
        {errors &&
          errors.map((error) => (
            <li
              style={{
                color: "red",
                textAlign: "center",
              }}
            >
              {" "}
              {error}{" "}
            </li>
          ))}
      </ul>
      <section>
        <form name="form" className="form" onSubmit={handleSubmit}>
          <label className="form-label">
            First name:
            <input
              type="text"
              className="form-input"
              name="firstname"
              value={user.firstname}
              disabled
            />
          </label>
          <label className="form-label">
            Last name:
            <input
              type="text"
              className="form-input"
              name="lastname"
              value={user.lastname}
              disabled
            />
          </label>
          <label className="form-label">
            Customer ID:
            <input
              type="text"
              className="form-input"
              name="customerId"
              value={user.customerId}
              disabled
            />
          </label>
          <label className="form-label">
            Mobile:
            <input
              type="number"
              className="form-input"
              name="mobileNum"
              value={user.mobileNum}
              onChange={handleChange}
            />
          </label>
          <label className="form-label">
            Email:
            <input
              type="email"
              className="form-input"
              name="email"
              value={user.email}
              onChange={handleChange}
            />
          </label>
          <div className="btn-block">
            <button className="btn" type="submit">
              Update
            </button>
            <button className="btn" onClick={handleCancel}>
              Cancel
            </button>
          </div>
        </form>
      </section>
    </>
  );
};

export default Profile;
