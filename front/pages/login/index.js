import { useState } from 'react'
import styleForm from '../../styles/Form.module.css'
import { getTranslation } from '@/locales/TranslationHelper'
import { Button } from '@mui/material'

export default function Login() {
    const t = getTranslation()
    const [form, setForm] = useState({
        username: '',
        password: ''
    })

    function onLoginClick() {

    }

    function isButtonDisabled() {
        return form.username == '' || form.password == ''
    }

    return(
        <div className='flex center flexDirectionColumn height_full'>
            <div style={{width: 300}}>
                <div className='loginTitle width_full' style={{textAlign: 'center'}}>{t.logo}</div>
                <div className='spacer_hor_XL'/>
                <div className={`${styleForm.inputWrapper} width_full`}>
                    <input 
                        className={styleForm.input}
                        value={form.username}
                        placeholder={t.login_username}
                        onChange={(e) => {setForm({...form, username: e.target.value})}}  
                    />
                </div>
                <div className='spacer_hor_S'/>
                <div className={`${styleForm.inputWrapper} width_full`}>
                    <input 
                        className={styleForm.input}
                        value={form.password}
                        placeholder={t.login_password}
                        onChange={(e) => {setForm({...form, password: e.target.value})}}  
                    />
                </div>
                <div className='spacer_hor_S'/>
                <Button 
                    disableRipple
                    className='raisedButton width_full'
                    disabled={isButtonDisabled()}
                    onClick={() => onLoginClick()}
                >
                    {t.button_login}
                </Button>

            </div>
        </div>
    )
}