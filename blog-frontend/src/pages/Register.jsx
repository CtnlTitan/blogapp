import { useState } from 'react';
import axios from '../api/axios';
import Styles from '../Styles/Registration.module.css';

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
    <div className={Styles.registerContainer}>
      <form onSubmit={handleSubmit} className={Styles.registerForm}>
      <h2>Register</h2>
      <input  name="name"
              placeholder="Name"
              onChange={handleChange}
              className={Styles.input}
              />
      <input  name="email"
              placeholder="Email"
              onChange={handleChange}
              className={Styles.input}
              />
      <input  type="password"
              name="password"
              placeholder="Password"
              onChange={handleChange}
              className={Styles.input}
              />
      <button type="submit" className={Styles.registerButton}>Register</button>
    </form>
    </div>
  );
}
