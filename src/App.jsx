import React, { useState } from "react";
import axios from "axios";
import Home from "./Home";
import Registration from "./Registration";
import Login from "./Login";
import { Routes, Route } from "react-router-dom";
import Dashboard from "./Dashboard";
// import { data } from "./user";

const App = () => {
  return (
    <>
      <Routes>
        <Route exact path="/" Component={Home} />
        <Route exact path="/registration" Component={Registration} />
        <Route exact path="/login" Component={Login} />
        <Route exact path="/dashboard" Component={Dashboard} />
      </Routes>
    </>
  );
};
export default App;
