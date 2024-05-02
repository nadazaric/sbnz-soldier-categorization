import Navbar from "./Navbar";
import style from '../styles/Layout.module.css'

export default function Layout({ children }) {
    return (
        <div className={style.test}>
            <div className={style.navbar}> <Navbar /> </div>
            <div className={style.content}> {children} </div>
            {/* <div className={style.content}> <main className={style.content}>{children}</main> </div> */}
            {/* <main className={style.content}>{children}</main> */}
        </div>
    )
}