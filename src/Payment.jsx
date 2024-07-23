// BeneficiaryDetailsPage.js
import React, { useEffect, useState } from "react";
import axios from "axios";

// import { setAuthToken } from "./js/setAuthToken";

const Payments = (props) => {
  const [beneficiaries, setBeneficiaries] = useState([]);

  const [amount, setAmount] = useState(0);

  useEffect(() => {
    let token = localStorage.getItem("token");
    console.log(token);
    if (token) {
      //get beneficiary details
      axios
        .get(`http://localhost:8080/payments/${props.customerId}`, {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        })
        .then((res) => {
          console.log(res.data);
          const groupedData = Object.values(groupByCustomerId(res.data));
          setBeneficiaries(res.data);
          console.log(res.data);
          //setToken(true);
        })
        .catch((error) => {
          console.log(error);
          //setToken(false);
        });
    } else {
      console.log("TOKEN :", token);
      //setToken(false);
    }
  }, []);

  // Function to group data by customerId
  const groupByCustomerId = (data) => {
    return data.reduce((acc, item) => {
      const { customerId } = item;
      if (!acc[customerId]) {
        acc[customerId] = { customerId, benificiaries: [] };
      }
      acc[customerId].benificiaries.push(item);
      return acc;
    }, {});
  };

  const [selectedBeneficiary, setSelectedBeneficiary] = useState(null);
  const [paymentType, setPaymentType] = useState("instant");

  const handleBeneficiarySelect = (beneficiary) => {
    setSelectedBeneficiary(beneficiary);
  };

  const handlePaymentTypeChange = (e) => {
    setPaymentType(e.target.value);
  };
  const amountChange = (e) => {
    setAmount(e.target.value);
  };

  const handleCompletePayment = () => {
    if (selectedBeneficiary) {
      alert(
        `Payment completed to ${selectedBeneficiary.benificiaryName} (${selectedBeneficiary.benificiaryAccountNumber}) via ${paymentType}`
      );
      // Actual payment processing logic should go here (API call, etc.)
    } else {
      alert("Please select a beneficiary first.");
    }
  };

  return (
    <div className="beneficiary-details-container">
      <h2>Payments</h2>

      <div className="beneficiary-list">
        <h4>Select Beneficiary:</h4>
        <div className="beneficiary-cards">
          {beneficiaries.map((beneficiary) => (
            <div
              key={beneficiary.customerId}
              className={`beneficiary-card ${
                selectedBeneficiary &&
                selectedBeneficiary.benificiaryAccountNumber ===
                  beneficiary.benificiaryAccountNumber
                  ? "selected"
                  : ""
              }`}
              onClick={() => handleBeneficiarySelect(beneficiary)}
            >
              <div className="beneficiary-info">
                <h4>{beneficiary.benificiaryName}</h4>
                <p>Accounts: {beneficiary.benificiaryAccountNumber}</p>
              </div>
            </div>
          ))}
        </div>
      </div>

      <div className="payment-type">
        <h4>Select Payment Type:</h4>
        <select value={paymentType} onChange={handlePaymentTypeChange}>
          <option value="instant">Instant Transfer</option>
          <option value="scheduled">Scheduled Transfer</option>
        </select>
      </div>

      <div>
        <h4>Enter Amount:</h4>
        <input type="number" onChange={amountChange} />
      </div>

      <button
        className={`complete-payment-btn ${
          selectedBeneficiary ? "" : "disabled"
        }`}
        onClick={handleCompletePayment}
        disabled={!selectedBeneficiary}
      >
        Complete Payment
      </button>
    </div>
  );
};

export default Payments;
