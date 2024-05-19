import { useState } from 'react'
import styleForm from '../styles/Form.module.css'
import { getTranslation } from '@/locales/TranslationHelper'
import { Button } from '@mui/material'
import Section, { SECTION_ACTIONS } from './Section'
import TableEditableWarObligation from './TableEditableWarObligation'

export function AddSoldier({ formMode=false, onSave }) {
    const t = getTranslation()

    const [form, setForm] = useState({
        fullName: '',
        jmbg: ''
    })

    const [warObligations, setWarObligations] = useState([])
    const addObligationToList = () => {
        setWarObligations([...warObligations, { startDate: new Date(1992, 3, 7), endDate: new Date(1992, 3, 8), type: 'WAR_ZONE' }]);
    }
    function onObligationsChange(newObligations) {
        setWarObligations(newObligations)
        console.log(newObligations)
    }

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
                <div className={`${styleForm.inputWrapper} width_full`}>
                    <input 
                        className={styleForm.input}
                        value={form.fullName}
                        placeholder={t.soldiers_full_name}
                        onChange={(e) => {setForm({...form, fullName: e.target.value})}}  
                    />
                </div>
                <div className='spacer_hor_S' />
                <div className={`${styleForm.inputWrapper} width_full`}>
                    <input 
                        className={styleForm.input}
                        value={form.jmbg}
                        placeholder={t.soldiers_jmbg}
                        onChange={(e) => {setForm({...form, jmbg: e.target.value})}}  
                    />
                </div>
                <div className='spacer_hor_L' />
                <Section 
                    title={"Ucesce u ratu"}
                    action={SECTION_ACTIONS.ADD}
                    onAction={addObligationToList}
                >
                    <TableEditableWarObligation 
                        obligations={warObligations}
                        onChange={onObligationsChange}
                    />
                </Section>
                <div className='spacer_hor_L' />
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