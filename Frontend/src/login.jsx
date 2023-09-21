import React, { useState } from 'react';

function LoginForm() {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');

  const handleEmailChange = (event) => {
    setEmail(event.target.value);
  };

  const handlePasswordChange = (event) => {
    setPassword(event.target.value);
  };

  const handleLogin = () => {
    // Perform login logic here using email and password
    console.log('Logging in with:', email, password);
  };

  const handleClear = () => {
    setEmail('');
    setPassword('');
  };

  return (
    <div>
      <h2>Login Form</h2>
      <label>Email:</label>
      <input type="email" value={email} onChange={handleEmailChange} />
      <br />
      <label>Password:</label>
      <input type="password" value={password} onChange={handlePasswordChange} />
      <br />
      <button onClick={handleLogin}>Login</button>
      <button onClick={handleClear}>Clear</button>
    </div>
  );
}

export default LoginForm;
