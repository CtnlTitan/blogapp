import { useEffect, useState } from "react";
import BlogCard from "../component/Card";
import Styles from "../Styles/Home.module.css";

export default function Home() {
  const [blogs, setBlogs] = useState([]);

  useEffect(() => {
    fetch("/blogs")
      .then((res) => res.json())
      .then((data) => setBlogs(data))
      .catch((err) => console.error("Error fetching blogs:", err));
  }, []);

  return (
    <div className={Styles.homeContainer}>
      {/* Fullscreen Hero */}
      <section className={Styles.heroBox}>
        <div className={Styles.heroInner}>
          <h1 className={Styles.title}>Welcome to Your Blog Platform</h1>
          <p className={Styles.subtitle}>
            Share your thoughts. Discover ideas. Connect through words.
          </p>
          <div className={Styles.ctaGroup}>
            <a href="/register" className={Styles.ctaButton}>Get Started</a>
            <a href="/about" className={Styles.ctaLink}>Learn More →</a>
          </div>
        </div>
      </section>

      {/* Blog List */}
      <section className={Styles.blogSection}>
        <div className={Styles.sectionInner}>
          <h2 className={Styles.sectionTitle}>Latest Blogs</h2>
          <div className={Styles.blogGrid}>
            {blogs.map((blog) => (
              <BlogCard key={blog.id} blog={blog} />
            ))}
          </div>
        </div>
      </section>
    </div>
  );
}
