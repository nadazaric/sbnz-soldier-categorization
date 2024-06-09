/* eslint-disable react-hooks/exhaustive-deps */
import { useEffect, useState } from 'react'
import styleForm from '../styles/Form.module.css'
import { getTranslation } from '@/locales/TranslationHelper'
import { Button } from '@mui/material'
import Section, { SECTION_ACTIONS } from './Section'
import TableEditableWarObligation from './TableEditableWarObligation'
import TableEditableInjury from './TableEditableInjury'
import axios from 'axios'
import { BACK_BASE_URL } from '@/helper/environment'
import detailsStyle from '../styles/Details.module.css'
import TableEditableUnit from './TableEditableUnit'

export function AddDetailsSoldier({ 
    selectedId = null,
    onSave, 
    isOpen ,
    onChange
}){
    const t = getTranslation()
    const [form, setForm] = useState({
        fullName: '',
        jmbg: ''
    })
    const [formMode, setFormMode] = useState(true)

    useEffect(() => {
        if (onChange) onChange()
    }, [form])

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

    // units
    const [units, setUnits] = useState([])

    useEffect(() => {
        axios.get(`${BACK_BASE_URL}/unit/simple-units`)
            .then(response => { 
                const options = response.data.map(item => ({ value: item.id, text: item.name, selected: false }))
                setUnits(options)
            })
            .catch(_error => {})
    }, [])

    function isNotAllUnitsFalse () {
        var isAllFalse = true
        for (var unit of units) {
            if (unit.selected) {
                isAllFalse = false
                break
            }
        }
        return !isAllFalse
    }

    // clear form && details view
    useEffect(() => {
        if(!isOpen) {
            setForm({
                fullName: '',
                jmbg: ''
            })
            setWarObligations([])
            setWarObligationsHaveError(false)
            setInjuries([])
            const updatedUnits = units.map(unit => ({ ...unit, selected: false }))
            setUnits(updatedUnits)
        } else if (isOpen && selectedId != null) {
            getSoldierDetails()
            setFormMode(false)
        } else setFormMode(true)
    }, [isOpen])

    function getSoldierDetails() {
        axios.get(`${BACK_BASE_URL}/soldier/${selectedId}`)
        .then(response => { 
            setForm({
                fullName: response.data.fullName,
                jmbg: response.data.jmbg
            })
            setWarObligations(response.data.warDuties)
            setInjuries(response.data.injuries)
            const updatedUnits = units.map(unit => {
                if (response.data.units.includes(unit.value)) return { ...unit, selected: true }
                else return unit
            })
            setUnits(updatedUnits)
         })
        .catch(_error => {})
    }
      
    function saveSoldier(e) {
        e.preventDefault()
        var selectedUnits = []
        for (var unit of units) {
            if (unit.selected) selectedUnits.push(unit.value)
        }
        if (onSave) onSave({
            fullName: form.fullName,
            jmbg: form.jmbg,
            warDuties: warObligations,
            injuries: injuries,
            units: selectedUnits
        })
        const updatedUnits = units.map(unit => ({ ...unit, selected: false }))
        setUnits(updatedUnits)
    }

    function isButtonDisabled() {
        return form.fullName == '' || form.jmbg == '' || !warObligationsHaveError || warObligations.length == 0
    }

    return (
        <form 
            className={styleForm.form}
            onSubmit={(e) => saveSoldier(e)}
        >
            {formMode &&
                <div className={styleForm.form}>
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
                </div>
            }
            {!formMode &&
                <div className={detailsStyle.wrapper}>
                    <div className={detailsStyle.rowWrapper}>
                        <div className={detailsStyle.label}>{t.soldiers_full_name}</div>
                        <div className={detailsStyle.info}>{form.fullName}</div>
                    </div>
                    <div className='spacer_hor_S' />
                    <div className={detailsStyle.rowWrapper}>
                        <div className={detailsStyle.label}>{t.soldiers_jmbg}</div>
                        <div className={detailsStyle.info}>{form.jmbg}</div>
                    </div>
                </div>
            }

            {(formMode || isNotAllUnitsFalse()) &&
                <>
                    <div className='spacer_hor_L' />
                    <Section title={t.unit_section}>
                        <div className='spacer_hor_M' />
                        <TableEditableUnit 
                            formMode={formMode}
                            units={units}
                        />
                    </Section>
                </>
            } 
            
            <div className='spacer_hor_L' />
            <Section 
                title={t.war_obligation_section}
                action={formMode ? SECTION_ACTIONS.ADD : SECTION_ACTIONS.NONE}
                onAction={addObligationToList}
            >
                <TableEditableWarObligation 
                    formMode={formMode}
                    obligations={warObligations}
                    onChange={onObligationsChange}
                    onDelete={removeObligationFromList}
                />
            </Section>

            <div className='spacer_hor_L' />
            {(formMode || injuries.length != 0) &&
                <Section 
                    title={t.injuries_section}
                    action={formMode ? SECTION_ACTIONS.ADD : SECTION_ACTIONS.NONE}
                    onAction={addInjury}
                >
                    <TableEditableInjury 
                        formMode={formMode}
                        injuries={injuries}
                        onChange={onInjuriesChange}
                        onDelete={removeInjuryFromList}
                    />
                </Section>
            }
            
            <div className='spacer_hor_L' />
            {formMode &&
                <Button 
                    className='raisedButton'
                    type='submit'
                    disabled={isButtonDisabled()}
                >
                    {t.button_save}
                </Button>
            }
        </form>
    )
}