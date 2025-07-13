import Styles from "../Styles/Card.module.css";
import PropTypes from "prop-types";

const BlogCard = ({ blog }) => {
  return (
    <div className={Styles.card}>
      <h2 className={Styles.title}>{blog.blogName}</h2>
      <p className={Styles.category}>Category: {blog.category}</p>
      <p className={Styles.content}>{blog.content}</p>
      <div className={Styles.tags}>
        {blog.tags.map((tag) => (
          <span key={tag} className={Styles.tag}>
            #{tag}
          </span>
        ))}
      </div>
      <p className={Styles.author}>Author: {blog.userName}</p>
    </div>
  );
};

BlogCard.propTypes = {
  blog: PropTypes.shape({
    id: PropTypes.number.isRequired,
    content: PropTypes.string.isRequired,
    blogName: PropTypes.string.isRequired,
    category: PropTypes.string.isRequired,
    tags: PropTypes.arrayOf(PropTypes.string).isRequired,
    userName: PropTypes.string.isRequired,
  }).isRequired,
};

export default BlogCard;
