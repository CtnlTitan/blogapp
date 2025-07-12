import { useEffect, useState } from 'react';
import axios from 'axios';

const About = () => {
  const [info, setInfo] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    axios.get('/about')
      .then(resource => {
        setInfo(resource.data);
        setLoading(false);
      })
      .catch(err => {
        console.error('Failed to load About info:', err);
        setLoading(false);
      });
  }, []);

  if (loading) return <p>Loading...</p>;

  return (
    <div className="p-4 max-w-xl mx-auto">
      <h1 className="text-2xl font-bold mb-4">About This Blog</h1>
      <ul className="space-y-2">
        <li><strong>Project:</strong> {info.project}</li>
        <li><strong>Version:</strong> {info.version}</li>
        <li><strong>Description:</strong> {info.description}</li>
        <li><strong>Author:</strong> {info.author}</li>
      </ul>
    </div>
  );
};

export default About;
