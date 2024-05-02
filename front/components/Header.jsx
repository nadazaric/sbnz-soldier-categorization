import style from '../styles/Header.module.css'
import AddIcon from '@mui/icons-material/Add'

export function ButtonHeader({ title }) {

    return(
        <div className={style.wrapper}>
            <div className={style.title}> {title} </div>
            <AddIcon className={`${style.icon} icon`} />
        </div>
    )
}