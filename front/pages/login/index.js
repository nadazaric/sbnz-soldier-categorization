import { useState } from 'react'
import styleForm from '../../styles/Form.module.css'
import { getTranslation } from '@/locales/TranslationHelper'
import { Button } from '@mui/material'
import axios from 'axios'
import { putUserAccessToken, putUserRefreshToken } from '@/helper/helper'
import { BACK_BASE_URL } from '@/helper/environment'
import { useRouter } from 'next/router'

export default function Login() {
    const t = getTranslation()
    const router = useRouter()
    const [haveError, setHaveError] = useState(false)
    const [form, setForm] = useState({
        username: '',
        password: ''
    })

    const loginUser = async () => {
        try {
            const response = await axios.post(`${BACK_BASE_URL}/user/login`, form, {
                headers: {
                    'Content-Type': 'application/json',
                    'skip': true
                }
            })
            if (response.status === 200) {
                const data = response.data
                putUserAccessToken(data.accessToken)
                putUserRefreshToken(data.refreshToken)
                router.push('/')
            } else { setHaveError(true) }
        } catch (error) { setHaveError(true) }
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
                        onChange={(e) => {
                            setForm({...form, username: e.target.value})
                            setHaveError(false)
                        }}  
                    />
                </div>
                <div className='spacer_hor_S'/>
                <div className={`${styleForm.inputWrapper} width_full`}>
                    <input 
                        className={styleForm.input}
                        value={form.password}
                        placeholder={t.login_password}
                        type='password'
                        onChange={(e) => {
                            setForm({...form, password: e.target.value})
                            setHaveError(false)
                        }}  
                    />
                </div>
                {haveError && 
                    <>
                        <div className='spacer_hor_XS'/>
                        <p className="error" style={{marginLeft: 5}}>{t.login_error}</p>
                    </>
                }
                <div className='spacer_hor_S'/>
                <Button 
                    className='raisedButton width_full'
                    disabled={isButtonDisabled()}
                    onClick={() => loginUser()}
                >
                    {t.button_login}
                </Button>

            </div>
        </div>
    )
}