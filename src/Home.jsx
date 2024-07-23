import React from "react";
import { useNavigate } from "react-router-dom";

const Home = () => {
  const navigate = useNavigate();
  return (
    <>
      <div className="title" style={{ marginTop: "2rem" }}>
        <h2>My Bank</h2>
        <div className="title-underline"></div>
      </div>
      <div>
        <button
          className="btn"
          style={{ marginTop: "5rem" }}
          onClick={() => navigate("/registration")}
        >
          {" "}
          Register
        </button>
      </div>
      <div>
        <button className="btn" onClick={() => navigate("/login")}>
          {" "}
          Login
        </button>
      </div>
    </>
  );
};

export default Home;
