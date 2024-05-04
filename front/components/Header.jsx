import style from '../styles/Header.module.css'
import AddIcon from '@mui/icons-material/Add'

export function ButtonHeader({ title, onAddClick }) {

    return(
        <div className={style.wrapper}>
            <div className='title'> {title} </div>
            <AddIcon 
                className={`${style.icon} icon`}
                onClick={() => { if(onAddClick) onAddClick()}} />
        </div>
    )
}