import { useState } from 'react';
import axios from '../api/axios';
import Styles from'../Styles/Login.module.css'

export default function Login() {
  const [credentials, setCredentials] = useState({
    email: "",
    password: "",
  });

  const handleChange = e => {
    setCredentials({ ...credentials, [e.target.name]: e.target.value });
  };

  const handleSubmit = async e => {
    e.preventDefault();
    try {
      const res = await axios.post('/login', credentials);
      localStorage.setItem('jwt', res.data.token); // Save token
      alert('Login successful!');
      console.log(res.data);
    } catch (err) {
      alert('Login failed');
      console.error(err);
    }
    
      setCredentials({
    email: "",
    password: "",});
    
  };

  return (
    <div className={Styles.loginContainer}>
      <form onSubmit={handleSubmit} className={Styles.loginForm}>
      <h2>Login</h2>
      <input  name="email"     className={Styles.input}
              placeholder="Email"
              value={credentials.email}
              onChange={handleChange} 
      />
      <input  type="password"   className={Styles.input}
              name="password"
              placeholder="Password"
              value={credentials.password}
              onChange={handleChange} 
      />
      <button type="submit" className={Styles.loginButton}>Login</button>
    </form>
    </div>
  );
}
