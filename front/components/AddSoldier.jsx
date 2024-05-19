import { useState } from 'react'
import styleForm from '../styles/Form.module.css'
import { getTranslation } from '@/locales/TranslationHelper'
import { Button } from '@mui/material'
import Section, { SECTION_ACTIONS } from './Section'
import TableEditableWarObligation from './TableEditableWarObligation'
import TableEditableInjury from './TableEditableInjury'

export function AddSoldier({ formMode=false, onSave }) {
    const t = getTranslation()

    const [form, setForm] = useState({
        fullName: '',
        jmbg: ''
    })

    // war obligations
    const [warObligations, setWarObligations] = useState([])
    const [warObligationsHaveError, setWarObligationsHaveError] = useState(false)
    const addObligationToList = () => {
        setWarObligations([...warObligations, { startDate: new Date(1992, 3, 7), endDate: new Date(1992, 3, 8), type: 'WAR_ZONE' }])
    }
    function onObligationsChange(newObligations, haveError) {
        setWarObligations(newObligations)
        setWarObligationsHaveError(haveError)
    }
    function removeObligationFromList(indexToRemove) {
        setWarObligations(prevObligations => {
            return prevObligations.filter((obligation, index) => index !== indexToRemove)
        })
    }

    // injuries
    const [injuries, setInjuries] = useState([])
    function addInjury() {
        setInjuries([...injuries, { type: 'LOW' }])
    }
    function onInjuriesChange(newInjuries) {
        setInjuries(newInjuries)
    }
    function removeInjuryFromList(indexToRemove) {
        setInjuries(prev => {
            return prev.filter((injury, index) => index !== indexToRemove)
        })
    }
      
    function saveSoldier(e) {
        e.preventDefault()
        if (onSave) onSave(form)
        setForm({
            fullName: '',
            jmbg: ''
        })
    }

    function isButtonDisabled() {
        return form.fullName == '' || form.jmbg == '' || !warObligationsHaveError
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
                    title={t.war_obligation_section}
                    action={SECTION_ACTIONS.ADD}
                    onAction={addObligationToList}
                >
                    <TableEditableWarObligation 
                        obligations={warObligations}
                        onChange={onObligationsChange}
                        onDelete={removeObligationFromList}
                    />
                </Section>
                <div className='spacer_hor_L' />
                <Section 
                    title={t.war_obligation_section}
                    action={SECTION_ACTIONS.ADD}
                    onAction={addInjury}
                >
                    <TableEditableInjury 
                        injuries={injuries}
                        onChange={onInjuriesChange}
                        onDelete={removeInjuryFromList}
                    />
                </Section>
                <div className='spacer_hor_L' />
                <Button 
                    disableRipple
                    className='raisedButton'
                    type='submit'
                    disabled={isButtonDisabled()}
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