import { useState } from 'react'
import styleForm from '../styles/Form.module.css'
import { getTranslation } from '@/locales/TranslationHelper'
import { Button } from '@mui/material'
import Section, { SECTION_ACTIONS } from './Section'

export function AddSoldier({ formMode=false, onSave }) {
    const t = getTranslation()

    const [form, setForm] = useState({
        fullName: '',
        jmbg: ''
    })

    function saveSoldier(e) {
        e.preventDefault()
        if (onSave) onSave(form)
        setForm({
            fullName: '',
            jmbg: ''
        })
    }

    if (formMode) {
        return (
            <form 
                className={styleForm.form}
                onSubmit={(e) => saveSoldier(e)}
            >
                <div className={styleForm.inputWrapper}>
                    <input 
                        className={styleForm.input}
                        value={form.fullName}
                        placeholder={t.soldiers_full_name}
                        onChange={(e) => {setForm({...form, fullName: e.target.value})}}  
                    />
                </div>
                <div className='spacer_hor_S' />
                <div className={styleForm.inputWrapper}>
                    <input 
                        className={styleForm.input}
                        value={form.jmbg}
                        placeholder={t.soldiers_jmbg}
                        onChange={(e) => {setForm({...form, jmbg: e.target.value})}}  
                    />
                </div>
                <div className='spacer_hor_S' />
                <Section 
                    title={"Ucesce u ratu"}
                    action={SECTION_ACTIONS.ADD}
                >

                </Section>
                <Button 
                    disableRipple
                    className='raisedButton'
                    type='submit'
                >
                    {t.button_save}
                </Button>
            </form>
        )
    } else {
        return (
            <div>
                Not form
            </div>
        )
    }
}