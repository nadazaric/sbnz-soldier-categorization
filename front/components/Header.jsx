import style from '../styles/Header.module.css'
import AddIcon from '@mui/icons-material/Add'

export function ButtonHeader({ title, onAddClick, children }) {
    return(
        <div className={style.wrapper}>
            <div className='title'> {title} </div>
            <div className={style.rightSide}>
                {children}
                <div className={style.iconWrapper}>
                    <AddIcon 
                        className={`${style.icon}`}
                        onClick={() => { if(onAddClick) onAddClick()}} />
                </div>
            </div>
        </div>
    )
}

export function Header({ title, children }) {
    return(
        <div className={style.wrapper}>
            <div className='title'> {title} </div>
            {children}
        </div>
    )
}