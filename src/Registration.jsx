import React, { useState } from "react";
import axios from "axios";
import Home from "./Home";
// import { data } from "./user";

const Registration = () => {
  const data = {
    firstname: "",
    lastname: "",
    role: "",
    mobileNum: "",
    email: "",
    username: "",
    password: "",
  };

  const [user, setuser] = useState(data);
  const [message, setMessage] = useState("");
  const [errorMessage, setErrorMessage] = useState([]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setuser({
      ...user,
      [name]: value,
    });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setMessage("");
    setErrorMessage("");

    await axios
      .post("http://localhost:8080/api/register", user)
      .then((response) => {
        console.log(response);
        setMessage("User Registered successfully!");
        setuser(data);
      })
      .catch((error) => {
        console.log(error);
        setErrorMessage(error.response.data.errorMessage);
      });
  };
  return (
    <>
      <div style={{ margin: "1" }}>
        {errorMessage && (
          <ul>
            {errorMessage.map((error, id) => (
              <li
                style={{
                  color: "red",
                  textAlign: "center",
                }}
                key={id}
              >
                {error}
              </li>
            ))}
          </ul>
        )}
      </div>
      {message && (
        <p style={{ color: "green", textAlign: "center" }}>{message}</p>
      )}
      <div className="title" style={{ marginTop: "2rem" }}>
        <h2>Register</h2>
        <div className="title-underline"></div>
      </div>
      <section>
        <form className="form" onSubmit={handleSubmit}>
          <label className="form-label">
            First name:
            <input
              type="text"
              className="form-input"
              name="firstname"
              value={user.firstname}
              onChange={handleChange}
            />
          </label>
          <label className="form-label">
            Last name:
            <input
              type="text"
              className="form-input"
              name="lastname"
              value={user.lastname}
              onChange={handleChange}
            />
          </label>
          <label className="form-label">
            Role:
            <input
              type="text"
              className="form-input"
              name="role"
              value={user.role}
              onChange={handleChange}
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
          <label className="form-label">
            Username:
            <input
              type="text"
              className="form-input"
              name="username"
              value={user.username}
              onChange={handleChange}
            />
          </label>
          <label className="form-label">
            Password:
            <input
              type="password"
              className="form-input"
              name="password"
              value={user.password}
              onChange={handleChange}
            />
          </label>
          <div className="btn-block">
            <button className="btn" style={{ marginTop: "2rem" }} type="submit">
              Submit
            </button>
          </div>
          {message && (
            <p style={{ color: "green", textAlign: "center" }}>{message}</p>
          )}
        </form>
      </section>
    </>
  );
};
export default Registration;
