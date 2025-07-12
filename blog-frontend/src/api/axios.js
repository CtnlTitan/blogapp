import axios from 'axios';

const axiosInstance = axios.create({
  baseURL: 'http://localhost:8080', // Change if your backend path differs
});

export default axiosInstance;
