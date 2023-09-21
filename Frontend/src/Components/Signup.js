import React, { useEffect, useState } from "react";
import { Button, Container, Form, Row, Col } from "react-bootstrap";
import { useNavigate, Navigate } from "react-router-dom";
import UserService from "../Services/UserService";

function Signup() {
  const [formData, setFormData] = useState({
    firstName: "",
    lastName: "",
    address: "",
    phone: "",
    email: "",
    birthDate: "",
    password: "",
  });

  const [allEmails, setAllEmails] = useState([]);
  const [isValidEmail, setIsValidEmail] = useState(true);
  const [emailAlreadyExists, setEmailAlreadyExists] = useState(false);

  const navigate = useNavigate();
  const userService = new UserService();

  useEffect(() => {
    async function fetchAllEmails() {
      try {
        const emails = await userService.getAllEmails();
        setAllEmails(emails);
        console.log(emails);
      } catch (error) {
        console.error("Error fetching emails:", error);
      }
    }
    fetchAllEmails();
  }, []);

  const handleInputChange = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
    
    if (name === "email") {
      setEmailAlreadyExists(allEmails.includes(value));
      const emailRegex =
        /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
      setIsValidEmail(emailRegex.test(value));
    }
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      await userService.registerUser(formData);
      navigate("/login");
    } catch (error) {
      console.error("Error registering user:", error);
    }
  };

  return (
    <Container className="border border-dark-subtle mt-3" style={{ width: "500px" }}>
      <Row className="text-center border-bottom border-dark-subtle">
        <h3>Signup</h3>
      </Row>
      <Row>
        <Form onSubmit={handleSubmit}>
          {/* Form fields... */}
          {/* Same as your original code */}

          <Container>
            <Row className="text-center">
              <Col className="justify-content-end">
                <Button
                  className="m-1"
                  variant="primary"
                  type="submit"
                  disabled={!isValidEmail || emailAlreadyExists}
                >
                  Submit
                </Button>
              </Col>
              <Col className="justify-content-start">
                <Button className="m-1" variant="danger" type="reset">
                  Cancel
                </Button>
              </Col>
            </Row>
          </Container>
        </Form>
      </Row>
    </Container>
  );
}

export default Signup;
