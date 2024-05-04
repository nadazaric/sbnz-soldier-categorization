import { useEffect, useRef, useState } from "react"
import style from '../styles/Dialog.module.css'
import CloseIcon from '@mui/icons-material/Close'

export function Dialog({ isOpen=false, onCloseModal, children }) {

    const ref = useRef()
    useEffect(() => {
        if (isOpen) ref.current?.showModal()
        else {
            setTimeout(() => {
                ref.current?.close();
            }, 300);
        }
    }, [isOpen])

    return(
        <dialog
            className={`${style.dialog} ${isOpen ? style.show : style.hide}`}
            ref={ref}
            onCancel={onCloseModal}
        >
            {children}
        </dialog>
    )
}

export function DialogWithHeader({ isOpen=false, onCloseModal, title, children }) {
    return(
        <Dialog
            isOpen={isOpen}
            onCloseModal={onCloseModal}
        >
            <div className={style.header}>
                <div className='title' >{title}</div>
                <CloseIcon 
                    className='icon'
                    onClick={() => {if(onCloseModal) onCloseModal()}}
                />
            </div>
            <div className='spacer_hor_M'></div>
            <div>
                {children}
            </div>
        </Dialog>
    )
}