import React, { useState } from "react";
import { Container, Form, Row, Col, Button, Alert } from "react-bootstrap";
import { useNavigate } from "react-router-dom";
import AuthService from "../Services/AuthService";
import UserService from "../Services/UserService";

function Login() {
  const [formData, setFormData] = useState({
    email: "",
    password: "",
  });
  const [isValidEmail, setIsValidEmail] = useState(true);
  const [showError, setShowError] = useState(false);

  const authService = new AuthService();
  const userService = new UserService();
  const navigate = useNavigate();

  if (userService.is_loggedin()) {
    const redirectPath = userService.is_admin() ? "/admin" : "/profile";
    navigate(redirectPath, { replace: true });
    return null; 
  }

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
    if (name === "email") {
      let re =
        /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      setIsValidEmail(re.test(value));
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setShowError(false);

    let status = await authService.login(formData);
    if (status) {
      navigate("/profile");
    } else {
      setShowError(true);
    }
  };

  return (
    <Container className="border border-dark-subtle mt-5 " style={{ width: "500px",'background-color': "cadetblue" }}>
      <Row className="text-center border-bottom border-dark-subtle">
        <h3>Login</h3>
      </Row>
      <Row>
        <Form onSubmit={handleSubmit}>
          <Form.Group className="m-3" controlId="email">
            <Form.Label>Email Address</Form.Label>
            <Form.Control
              type="email" // Changed to email type
              placeholder="Enter your email address"
              name="email"
              onChange={handleInputChange}
              required
            />
            {!isValidEmail && (
              <Form.Text style={{ color: "red" }} className="ms-3">
                Email is not valid
              </Form.Text>
            )}
          </Form.Group>

          <Form.Group className="m-3" controlId="password">
            <Form.Label>Password</Form.Label>
            <Form.Control
              type="password" // Changed to password type
              placeholder="Enter your password"
              name="password"
              onChange={handleInputChange}
              required
            />
          </Form.Group>

          <Container>
            <Row className="text-center">
              <Col>
                <Button className="m-3" variant="primary" type="submit" disabled={!isValidEmail}>
                  Submit
                </Button>
              </Col>
              <Col>
                <Button className="m-3" variant="danger" type="reset">
                  Cancel
                </Button>
              </Col>
            </Row>
          </Container>
        </Form>
      </Row>

      {showError && (
        <Row>
          <Alert variant="danger" className="m-2">
            Email or password is incorrect.
          </Alert>
        </Row>
      )}

      <Row>
        <p className="m-2">
          Are you new here? <a href="/signup">Register Now!</a>
        </p>
      </Row>
    </Container>
  );
}

export default Login;