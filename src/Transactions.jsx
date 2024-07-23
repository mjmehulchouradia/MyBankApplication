import React from "react";

const Transactions = () => {
  const transactions = [
    {
      id: 1,
      date: "2024-07-19",
      description: "Payment received",
      ben: "sdadsds",
      amount: 500,
      type: "credit",
    },
    {
      id: 2,
      date: "2024-07-18",
      description: "Online purchase",
      ben: "dsdasdsa",
      amount: 150,
      type: "debit",
    },
    // Add more transactions as needed
  ];

  return (
    <div className="transactionList">
      <h1>Transaction History</h1>
      <table className="transactionTable">
        <thead>
          <tr>
            <th>Date</th>
            <th>Mode</th>
            <th>Beneficiary Account</th>
            <th>Amount</th>
            <th>Type</th>
          </tr>
        </thead>
        <tbody>
          {transactions.map((transaction) => (
            <tr key={transaction.id}>
              <td>{transaction.date}</td>
              <td>{transaction.description}</td>
              <td>{transaction.ben}</td>
              <td>${transaction.amount.toFixed(2)}</td>
              <td
                className={transaction.type === "credit" ? "credit" : "debit"}
              >
                {transaction.type === "credit" ? "Credit" : "Debit"}
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};

export default Transactions;
