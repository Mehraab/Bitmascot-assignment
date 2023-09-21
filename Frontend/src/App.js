import "./App.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import { BrowserRouter as Router, Navigate, Route, Routes } from "react-router-dom";
import Signup from "./Components/Signup";
import Login from "./Components/Login";
import AdminView from "./Components/AdminView";
import Profile from "./Components/Profile";
import CustomNavBar from "./Components/CustomNavBar";
import React from 'react'; // Import the React library
import ReactDOM from 'react-dom';

function App() {
  return (
    <Router>
      <CustomNavBar />
      <Routes>
        <Route path="/signup" element={<Signup />} />
        <Route path="/" element={<Navigate to="/login" replace />} />
        <Route path="/login" element={<Login />} />
        <Route path="/admin" element={<AdminView />} />
        <Route path="/profile" element={<Profile />} />
      </Routes>
    </Router>
  );
}

export default App;
