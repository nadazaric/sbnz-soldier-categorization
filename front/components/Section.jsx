import style from '../styles/Section.module.css'
import AddIcon from '@mui/icons-material/Add'

export default function Section({ 
    title,
    action = SECTION_ACTIONS.NONE, 
    onAction,
    children 
}){
    function onActionClick() {
        if (onAction) onAction()
    }

    return(
        <div className={style.wrapper}>
            <div className={style.titleWrapper}>
                <div className={style.title}>{title}</div>
                <div className='icon'>
                    {action == SECTION_ACTIONS.ADD && <AddIcon onClick={onActionClick} />}
                </div>
            </div>
            {children}
        </div>
    )
}

export const SECTION_ACTIONS = {
    NONE: 'None',
    ADD: 'Add'
}