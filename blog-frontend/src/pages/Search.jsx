import { useState } from "react";
import Card from "../component/Card"; // renamed from BlogCard
import styles from "../Styles/Search.module.css";

const categories = ["Technology", "Lifestyle", "Education", "Health"];
const tags = ["react", "springboot", "java", "ai", "frontend", "backend"];

export default function Search() {
  const [name, setName] = useState("");
  const [selectedCategory, setSelectedCategory] = useState("");
  const [selectedTags, setSelectedTags] = useState([]);
  const [results, setResults] = useState([]);

  const handleTagToggle = (tag) => {
    setSelectedTags((prev) =>
      prev.includes(tag) ? prev.filter((t) => t !== tag) : [...prev, tag]
    );
  };

  const handleSearch = async () => {
    try {
      const response = await fetch("/search", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({
          blogName: name,
          category: selectedCategory,
          tags: selectedTags,
        }),
      });
      const data = await response.json();
      setResults(data);
    } catch (error) {
      console.error("Search failed:", error);
    }
  };

  return (
    <div className={styles.container}>
      <aside className={styles.sidebar}>
        <h2 className={styles.heading}>Search Filters</h2>

        <label className={styles.label}>Blog Name</label>
        <input
          type="text"
          className={styles.input}
          value={name}
          onChange={(e) => setName(e.target.value)}
          placeholder="Enter blog title"
        />

        <div className={styles.section}>
          <label className={styles.label}>Category</label>
          {categories.map((cat) => (
            <label key={cat} className={styles.checkboxLabel}>
              <input
                type="checkbox"
                checked={selectedCategory === cat}
                onChange={() =>
                  setSelectedCategory((prev) => (prev === cat ? "" : cat))
                }
              />
              {cat}
            </label>
          ))}
        </div>

        <div className={styles.section}>
          <label className={styles.label}>Tags</label>
          {tags.map((tag) => (
            <label key={tag} className={styles.checkboxLabel}>
              <input
                type="checkbox"
                checked={selectedTags.includes(tag)}
                onChange={() => handleTagToggle(tag)}
              />
              {tag}
            </label>
          ))}
        </div>

        <button className={styles.searchButton} onClick={handleSearch}>
          Search
        </button>
      </aside>

      <main className={styles.results}>
        <h2 className={styles.sectionTitle}>Results</h2>
        <div className={styles.blogGrid}>
          {results.length === 0 ? (
            <p className={styles.noResults}>No blogs found.</p>
          ) : (
            results.map((blog) => <Card key={blog.id} blog={blog} />)
          )}
        </div>
      </main>
    </div>
  );
}
