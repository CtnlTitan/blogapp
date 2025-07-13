import { useEffect, useState } from "react";

const getSystemTheme = () =>
  window.matchMedia("(prefers-color-scheme: dark)").matches ? "dark" : "light";

function Themes() {
  const [theme, setTheme] = useState(() => {
     return localStorage.getItem("theme") || getSystemTheme();
  });

  
  useEffect(() => {
    document.documentElement.classList.remove("light", "dark");
    document.documentElement.classList.add(theme);
    localStorage.setItem("theme", theme);
  }, [theme]);

  const toggleTheme = () => setTheme(theme === "light" ? "dark" : "light");

  return (
    <button onClick={toggleTheme} className="theme-toggle-btn">
       {theme === "light" ? "☀️ Light" : "🌙 Dark"} Mode
    </button>
  );
}
export default Themes;
