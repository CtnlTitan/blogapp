import { useState } from 'react';
import axios from '../api/axios';
import '../Styles/Login.css'

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
      const res = await axios.post('/auth/login', credentials);
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
    <form onSubmit={handleSubmit}>
      <h2>Login</h2>
      <input name="email" placeholder="Email" value={credentials.email} onChange={handleChange} /><br/>
      <input type="password" name="password" placeholder="Password" value={credentials.password} onChange={handleChange} /><br/>
      <button type="submit">Login</button>
    </form>
  );
}
