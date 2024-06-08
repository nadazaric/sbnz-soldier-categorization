import { useEffect, useState } from "react"
import styleForm from '../styles/Form.module.css'
import { getTranslation } from "@/locales/TranslationHelper"
import { Button } from "@mui/material"

export default function AddWorker({ isOpen, onSave }) {
    const t = getTranslation()
    const [form, setForm] = useState({
        name: '',
        username: '',
        password: ''
    })

    function save(e) {
        e.preventDefault()
        if (onSave) onSave(form)
    }

    useEffect(() => {
        if (isOpen) return
        setForm({
            name: '',
            username: '',
            password: ''
        })
    }, [isOpen])

    function isButtonDisabled() {
        return form.name == '' || form.username == '' || form.password == ''
    }

    return(
        <form onSubmit={(e) => save(e)}>
            <div className={`${styleForm.inputWrapper} width_full`}>
                <input 
                    className={styleForm.input}
                    value={form.name}
                    placeholder={t.worker_name}
                    onChange={(e) => {setForm({...form, name: e.target.value})}}  
                />
            </div>
            <div className='spacer_hor_S' />
            <div className={`${styleForm.inputWrapper} width_full`}>
                <input 
                    className={styleForm.input}
                    value={form.username}
                    placeholder={t.worker_username}
                    onChange={(e) => {setForm({...form, username: e.target.value})}}  
                />
            </div>
            <div className='spacer_hor_S' />
            <div className={`${styleForm.inputWrapper} width_full`}>
                <input 
                    className={styleForm.input}
                    value={form.password}
                    placeholder={t.login_password}
                    type="password"
                    onChange={(e) => {setForm({...form, password: e.target.value})}}  
                />
            </div>
            <div className='spacer_hor_S' />
            <Button 
                className='raisedButton width_full'
                type='submit'
                disabled={isButtonDisabled()}
            >
                {t.button_save}
            </Button>
        </form>
    )
}