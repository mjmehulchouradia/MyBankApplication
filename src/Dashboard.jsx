import React, { useEffect, useState } from "react";
import { Link, useLocation } from "react-router-dom";
import Profile from "./Profile";
import Accounts from "./Accounts";
import LoanDetails from "./LoanDetails";
import Transactions from "./Transactions";
import Payments from "./Payment";
import Header from "./Header";
import axios from "axios";
import { jwtDecode } from "jwt-decode";

const Dashboard = () => {
  const loc = useLocation();
  var userName = "";
  let token = "";

  const data = {
    customerId: "",
    firstname: "",
    lastname: "",
    mobileNum: "",
    email: "",
    role: "",
    username: "",
  };

  const [user, setUser] = useState(data);

  const [JwtToken, setJwtToken] = useState(false);

  useEffect(() => {
    token = localStorage.getItem("token");
    if (token) {
      const uname = jwtDecode(token).sub;
      axios
        .get(`http://localhost:8080/api/${uname}`, {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          data.customerId = res.data.customerId;
          data.firstname = res.data.firstname;
          data.lastname = res.data.lastname;
          data.mobileNum = res.data.mobileNum;
          data.email = res.data.email;
          data.role = res.data.role;
          data.username = res.data.username;
          setUser(data);
          setJwtToken(true);
        })
        .catch((error) => {
          setJwtToken(false);
          console.log("ERROR From API : " + error.response.data);
        });
    } else {
      setJwtToken(false);
    }
  }, []);

  const [selectedComponent, setSelectedComponent] = useState("Accounts");
  const renderComponent = () => {
    switch (selectedComponent) {
      case "Accounts":
        return <Accounts customerId={user.customerId} />;
      case "Transactions":
        return <Transactions customerId={user.customerId} />;
      case "LoanDetails":
        return <LoanDetails />;
      case "Payments":
        return <Payments customerId={user.customerId} />;
      case "Profile":
        return <Profile data={user} token={token} />;
      default:
        return <Accounts />;
    }
  };

  return (
    <>
      {!JwtToken && (
        <div className="container">
          Unable to access Dashboard, Please <Link to="/login">Login</Link>
        </div>
      )}

      {JwtToken && user.role === "user" && (
        <div className="dashboard">
          <nav className="sidebar">
            <h2>Bank Dashboard</h2>
            <ul>
              <li>
                <button onClick={() => setSelectedComponent("Accounts")}>
                  Accounts
                </button>
              </li>
              <li>
                <button onClick={() => setSelectedComponent("Transactions")}>
                  Transactions
                </button>
              </li>
              <li>
                <button onClick={() => setSelectedComponent("LoanDetails")}>
                  Loan Details
                </button>
              </li>
              <li>
                <button onClick={() => setSelectedComponent("Payments")}>
                  Payments
                </button>
              </li>
            </ul>
          </nav>
          <div className="main-content">
            <Header
              name={user.username}
              setSelectedComponent={setSelectedComponent}
            />
            <div className="content">{renderComponent()}</div>
          </div>
        </div>
      )}

      {JwtToken && user.role === "admin" && (
        <div className="dashboard">
          <nav className="sidebar">
            <h2>Bank Dashboard</h2>
            <ul>
              <li>
                <button onClick={() => setSelectedComponent("LoanApprocals")}>
                  Loan Approvals
                </button>
              </li>
              <li>
                <button onClick={() => setSelectedComponent("UserQueries")}>
                  User Queries
                </button>
              </li>
            </ul>
          </nav>
          <div className="main-content">
            <Header
              name={user.username}
              setSelectedComponent={setSelectedComponent}
            />
            <div className="content">{renderComponent()}</div>
          </div>
        </div>
      )}
    </>
  );
};

export default Dashboard;
