import React from "react";
import { Container, Row, Col, Table } from "react-bootstrap";
import { Navigate } from "react-router-dom";
import UserService from "../Services/UserService";

function Profile() {
  const userService = new UserService();
  const currentUser = userService.getCurrentUser();

  if (!userService.is_loggedin()) {
    return <Navigate replace to="/login" />;
  } else if (userService.is_admin()) {
    return <Navigate replace to="/admin" />;
  }

  return (
    <Container style={{ width: "800px" }} className="mt-5">
      <Row className="text-center border border-dark-subtle">
        <Col>
          <h3>{currentUser.firstName}'s Profile</h3>
        </Col>
      </Row>

      <Row>
        <Col>
          <Table bordered className="text-start border border-dark-subtle">
            <tbody>
              <ProfileRow label="First Name" value={currentUser.firstName} />
              <ProfileRow label="Last Name" value={currentUser.lastName} />
              <ProfileRow label="Address" value={currentUser.address} />
              <ProfileRow label="Phone Number" value={currentUser.phone} />
              <ProfileRow label="Email" value={currentUser.email} />
              <ProfileRow label="Birthdate" value={currentUser.birthDate} />
            </tbody>
          </Table>
        </Col>
      </Row>
    </Container>
  );
}

function ProfileRow({ label, value }) {
  return (
    <tr>
      <td>
        <h4>{label}</h4>
      </td>
      <td>
        <h5>{value}</h5>
      </td>
    </tr>
  );
}

export default Profile;