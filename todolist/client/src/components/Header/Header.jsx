import styles from './Header.module.css'

function Header() {
  return (
    <>
      <div className={styles.content}>
        <div className={styles.left}>
          <h2>some text</h2>
        </div>
        <div className={styles.center}>
          <h1>center text</h1>
        </div>
        <div className={styles.right}>
          <h2 className={styles.dropDownText}>final text</h2>
          {/* dropdown menu here */}
          <div className={styles.dropDownMenu}>
            <button>choice 1</button>
            <button>choice 2</button>
            <button>choice 3</button>
          </div>
        </div>
      </div>
    </>
  );
}

export default Header;