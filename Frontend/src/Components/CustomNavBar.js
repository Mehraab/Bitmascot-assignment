import React, { useState } from "react";
import Container from "react-bootstrap/Container";
import Nav from "react-bootstrap/Nav";
import Navbar from "react-bootstrap/Navbar";
import NavDropdown from "react-bootstrap/NavDropdown";
import { Navigate, useNavigate } from "react-router-dom";
import UserService from "../Services/UserService";

function CustomNavBar() {
  const userService = new UserService();
  const navigate = useNavigate();

  const currentUser = userService.getCurrentUser();

  const handleLogout = (e) => {
    userService.logout();
    navigate("/login");
  };

  return (
    <Navbar expand="lg" style={{ 'background-color': "cadetblue" }}>
      <Container >
        <Navbar.Brand aria-disabled>User Registration</Navbar.Brand>
        <Nav className="text-end">
          {userService.is_loggedin() ? (
            <NavDropdown
              title={userService.is_admin() ? "admin" : currentUser.firstName}
              id="basic-nav-dropdown"
            >
              {userService.is_admin() ? (
                <NavDropdown.Item href="/admin">User List</NavDropdown.Item>
              ) : (
                <NavDropdown.Item href="/profile">Profile</NavDropdown.Item>
              )}

              <NavDropdown.Divider />
              <NavDropdown.Item onClick={handleLogout}>Logout</NavDropdown.Item>
            </NavDropdown>
          ) : (
            <NavDropdown.Item href="/login">Login</NavDropdown.Item>
          )}
        </Nav>
      </Container>
    </Navbar>
  );
}

export default CustomNavBar;
