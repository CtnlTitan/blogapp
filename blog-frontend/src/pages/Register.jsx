import { useState } from 'react';
import axios from '../api/axios';

export default function Register() {
  const [user, setUser] = useState({
    userName: '',
    email: '',
    password: '',
  });

  const handleChange = e => {
    setUser({ ...user, [e.target.name]: e.target.value });
  };

  const handleSubmit = async e => {
    e.preventDefault();
    try {
      const res = await axios.post('/register', user);
      alert('Registered successfully!');
      console.log(res.data); // Can store token here if needed
    } catch (err) {
      alert('Registration failed');
      console.error(err);
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Register</h2>
      <input name="name" placeholder="Name" onChange={handleChange} />
      <input name="email" placeholder="Email" onChange={handleChange} />
      <input type="password" name="password" placeholder="Password" onChange={handleChange} />
      <button type="submit">Register</button>
    </form>
  );
}
