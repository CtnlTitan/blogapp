import { Link } from "react-router-dom";
import Styles from "../Styles/NavBar.module.css"; 
import Themes from "../Themes";

function Navbar() {
  return (
    <nav className={Styles.navbar}>
      <ul className={Styles.ul}>
        <li><Link to="/" className={Styles.link}>Home</Link></li>
        <li><Link to="/login" className={Styles.link}>Login</Link></li>
        <li><Link to="/register" className={Styles.link}>Register</Link></li>
        <li><Link to="/search" className={Styles.link}>Search</Link></li>
        <li><Link to="/about" className={Styles.link}>About</Link></li>
        <li className={Styles.lastItem}><Themes/></li>
      </ul>
    </nav>
  );
}

export default Navbar;
