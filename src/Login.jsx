import React, { useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [message, setMessage] = useState("");
  // const [isLoading, setIsLoading] = useState(true);

  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();
    const payload = { username, password };

    try {
      const response = await axios.post(
        "http://localhost:8080/api/login",
        payload
      );
      console.log(response.data);
      setMessage(response.data);
      // setIsLoading(false);
      localStorage.setItem("token", response.data);
      navigate("/dashboard");
    } catch (error) {
      setMessage(error.response ? error.response.data : "Login failed");
    }
  };

  // if (isLoading) {
  //   return (
  //     <section className="job-centre">
  //       <div className="loading"></div>
  //     </section>
  //   );
  // }

  return (
    <>
      <div className="title" style={{ marginTop: "2rem" }}>
        <h2>Login </h2>
        <div className="title-underline"></div>{" "}
      </div>
      <section>
        <form className="form" onSubmit={handleSubmit}>
          <label className="form-label">
            Username:
            <input
              type="text"
              className="form-input"
              value={username}
              onChange={(e) => setUsername(e.target.value)}
              required
            />
          </label>

          <label className="form-label">
            Password:
            <input
              type="password"
              className="form-input"
              value={password}
              onChange={(e) => setPassword(e.target.value)}
              required
            />
          </label>
          <button className="btn" style={{ marginTop: "2rem" }} type="submit">
            Login
          </button>
        </form>
      </section>
      {message && (
        <p style={{ color: "green", textAlign: "center" }}>{message}</p>
      )}
    </>
  );
};

export default Login;
