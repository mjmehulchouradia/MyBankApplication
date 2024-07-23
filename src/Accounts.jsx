import React, { useEffect, useState } from "react";
import axios from "axios";
// Import CSS file

const Accounts = (props) => {
  //const account = { type: '', balance: 500 };

  const [message, setMessage] = useState("");
  const [errors, setErrors] = useState([]);

  console.log(props.customerId);

  const token = localStorage.getItem("token");

  const [accounts, setAccounts] = useState([]);

  const [showNewAccountForm, setShowNewAccountForm] = useState(false);
  const [newAccType, setNewAccountType] = useState("");
  const [initialBalance, setInitialBalance] = useState(0);

  const getAllAccounts = async () => {
    axios
      .get(`http://localhost:8080/accounts/${props.customerId}`, {
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
      })
      .then((res) => {
        setAccounts(res.data);
      })
      .catch((error) => {
        console.log(error.response);
        setMessage("No Accounts found,Please request for Account Opening");
      });
  };

  useEffect(() => {
    //Get Account details
    console.log(props.customerId);
    getAllAccounts();
    //set accounts
  }, []);

  //Handler Methods

  const handleNewAccountSubmit = (e) => {
    e.preventDefault();
    // Logic to send new account request
    console.log("New Account Type:", newAccType);
    console.log("Initial Balance:", initialBalance);

    axios
      .post(
        `http://localhost:8080/accounts/create`,
        {
          accountType: newAccType,
          balance: initialBalance,
          custid: props.customerId,
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      )
      .then((res) => {
        console.log(res);
        //setMessage(res.data);
        //setAccounts(res.data.accounts);
        getAllAccounts();
        // Reset form fields
        setNewAccountType("");
        setInitialBalance(0);
        setShowNewAccountForm(false);
      })
      .catch((error) => {
        console.log(error);
        setMessage("Request Rejected");
      });
  };

  const handleViewTransactions = (accountId) => {
    // Logic to view transactions for the selected account
    console.log("View transactions for account:", accountId);
  };

  return (
    <div className="accounts-container">
      <button
        className="new-account-btn"
        onClick={() => setShowNewAccountForm(true)}
      >
        Add Account
      </button>
      <h2>Your Accounts</h2>

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

      <div className="account-list">
        {accounts.map((account) => (
          <div key={account.accountNumber} className="account-card">
            <button className="accounts">
              <h3>{account.accountType} Account</h3>
            </button>
            <p>Balance: ${account.balance.toFixed(2)}</p>
            <button
              className="accBtn"
              onClick={() => handleViewTransactions(account.id)}
            >
              View Transactions
            </button>
          </div>
        ))}
      </div>

      {showNewAccountForm && (
        <form className="new-account-form" onSubmit={handleNewAccountSubmit}>
          <h2>Request for New Account Opening</h2>
          <label htmlFor="account-type">Account Type:</label>
          <select
            id="account-type"
            value={newAccType}
            onChange={(e) => setNewAccountType(e.target.value)}
            required
          >
            <option value="">Select Account Type</option>
            <option value="Savings">SAVINGS</option>
            <option value="Checking">CHECKING</option>
            <option value="Salary">SALARY</option>
          </select>
          <label htmlFor="initial-balance">Initial Balance:</label>
          <input
            type="number"
            id="initial-balance"
            min={500}
            value={initialBalance}
            onChange={(e) => setInitialBalance(parseFloat(e.target.value))}
            required
          />
          <button className="accBtn" type="submit">
            Request
          </button>
          <button
            className="accBtn"
            type="submit"
            style={{ backgroundColor: "red" }}
            onClick={(e) => setShowNewAccountForm(false)}
          >
            Cancel
          </button>
        </form>
      )}
    </div>
  );
};

export default Accounts;
