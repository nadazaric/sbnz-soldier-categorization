import Navbar from "./Navbar";
import style from '../styles/Layout.module.css'

export default function Layout({ children }) {
    return (
        <div>
            <Navbar className={style.navbar} />
            <main className={style.content}>{children}</main>
        </div>
    )
}